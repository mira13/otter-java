package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.repository.AuthorRepository;
import ebi.ensembl.otter.datasources.repository.EvidenceRepository;
import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Service
public class RegionService {

	@Autowired
	AuthorRepository authorRepository;

	private Integer cacheTranscriptCount = 0;

	@Autowired
	EvidenceRepository evidenceRepository;
	
	@Value("${cache.transcriptsMaxAmount}")
	private Integer transcriptsMaxAmount;

	@Autowired
	GeneAttributeService geneAttributeService;

	private HashMap<Integer, Gene> geneCache = new HashMap<>();

	@Autowired
	GeneRepository geneRepository;

	@Autowired
	SeqRegionService seqRegionService;

	@Autowired
	TranscriptAttributeService transcriptAttributeService;

	@Autowired
	TranscriptRepository transcriptRepository;

	private void fillGeneAndTranscriptsWithAttribsAndEvidences(List<Gene> rawList) {
		for (Gene gene : rawList) {
			gene.setAttributes(geneAttributeService.getGeneAttribById(gene.getGeneId()));
			List<Transcript> transcripts = gene.getTranscripts();

			for (Transcript transcript : transcripts) {
				Integer transcriptId = transcript.getTranscriptId();

				List<FeatureAttribute> transcriptAttributesList = transcriptAttributeService
						.getTranscriptAttribById(transcriptId);

				transcript.setAttributes(transcriptAttributesList);
				transcript.setEvidence(evidenceRepository.findByTranscriptId(transcriptId));
				this.cacheTranscriptCount++;
			}
		}
	}

	public List<Gene> getByCoordSysAndRegionNameAndStartAndEnd(String csName, String csVerison, String regionName,
			Integer seqRegionStart, Integer seqRegionEnd) {

		/*
		 * Logic level. 
 		 * 1. Fill transcripts with attribs and evidences 
 		 * 2. Remove exons that are out of range
		 * 3. For each gene add remark about truncation and set truncated flag 
		 */
		
		Integer seqRegionId = seqRegionService.getNameAndCoordSystem(regionName, csName, csVerison).getSeqRegionId();

		// This request we execute always, even it fetches already cached
		// genes-transcripts-exons it always finished in 0.2 sec approximately
		List<Gene> rawList = geneRepository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
		List<Gene> returnedGeneList = new ArrayList<>();

		// Flush cache if it exceed allowed size
		// TODO: remove genes form cache, after genes are changed saved to db.
		if (cacheTranscriptCount > transcriptsMaxAmount) { // reset cache if it is too big
			geneCache = new HashMap<>();
		}
        System.out.println(cacheTranscriptCount);
		/*
		 * cached genes we add to returned result directly, not cached we leave in raw
		 * list for pull
		 */
		Iterator<Gene> iterGene = rawList.iterator();
		while (iterGene.hasNext()) {
			Gene gene = iterGene.next();
			if (geneCache.containsKey(gene.getGeneId())) {
				returnedGeneList.add(geneCache.get(gene.getGeneId()));
				iterGene.remove();
			}
		}

		this.fillGeneAndTranscriptsWithAttribsAndEvidences(rawList);

		// Add genes with attributes, transcripts with attrib and evidences
		// and exons to result list and to cache
		for (Gene gene : rawList) {
			returnedGeneList.add(gene);
			geneCache.put(gene.getGeneId(), gene);
		}

		this.trimExons(returnedGeneList, seqRegionStart, seqRegionEnd);
		return returnedGeneList;
	}

	private void trimExons(List<Gene> geneList, int seqRegionStart, int seqRegionEnd) {
		for (Gene gene : geneList) {
			for (Transcript transcript : gene.getTranscripts()) {
				
				Integer transcriptId = transcript.getTranscriptId();
				String transcriptName = "";

				int i = 0;
				int removedCount = 0;

				Iterator<Exon> iter = transcript.getExons().iterator();

				while (iter.hasNext()) {
					Exon exon = iter.next();
					if (exon.getSeqRegionStart() < seqRegionStart || exon.getSeqRegionEnd() > seqRegionEnd) {
						iter.remove();
						removedCount++;
					}
					i++;
				}
				if (removedCount == 1) {
					gene.getAttributes().add(new FeatureAttribute("remark",
							"Transcript " + transcriptName + " has 1 exon that is not in this slice"));
				} else if (removedCount > 1) {
					gene.getAttributes().add(new FeatureAttribute("remark", "Transcript " + transcriptName + " has "
							+ removedCount + " exons that are not in this slice"));

				}
			}
		}
	}
}
