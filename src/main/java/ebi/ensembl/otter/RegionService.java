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
import ebi.ensembl.otter.datasources.model.SimpleFeature;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;
import ebi.ensembl.otter.webAPIControllers.model.otter.RegionOtter;

@Service
public class RegionService {

	private Integer cacheTranscriptCount = 0;

	private HashMap<Integer, Gene> geneCache = new HashMap<>();

	@Autowired
	GeneService geneService;

	@Autowired
	SeqRegionService seqRegionService;

	@Autowired
	TranscriptService transcriptService;
	
	@Autowired
	SimpleFeatureService simpleFeatureService;

	@Value("${cache.transcriptsMaxAmount}")
	private Integer transcriptsMaxAmount;

	private void fillGeneAndTranscriptsWithAttribsAndEvidences(List<Gene> rawList) {
		for (Gene gene : rawList) {
			gene.setAttributes(geneService.getGeneAttribById(gene.getGeneId()));
			List<Transcript> transcripts = gene.getTranscripts();

			for (Transcript transcript : transcripts) {
				Integer transcriptId = transcript.getTranscriptId();

				transcript.setAttributes( transcriptService
						.getTranscriptAttribById(transcriptId));
				transcript.setEvidence(transcriptService.findEvidenceByTranscriptId(transcriptId));
				cacheTranscriptCount++;
			}
		}
	}

	public List<Gene> getByRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart, Integer seqRegionEnd) {

		/*
		 * Logic level. 1. Fill transcripts with attribs and evidences 2. Remove exons
		 * that are out of range 3. For each gene add remark about truncation and set
		 * truncated flag
		 */

		// This request we execute always, even it fetches already cached
		// genes-transcripts-exons it always finished in 0.2 sec approximately
		List<Gene> rawList = geneService.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
		List<Gene> returnedGeneList = new ArrayList<>();

		// Flush cache if it exceed allowed size
		// TODO: remove genes form cache, after genes are changed saved to db.
		if (cacheTranscriptCount > transcriptsMaxAmount) { // reset cache if it is too big
			geneCache = new HashMap<>();
		}
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

		fillGeneAndTranscriptsWithAttribsAndEvidences(rawList);

		// Add genes with attributes, transcripts with attrib and evidences
		// and exons to result list and to cache
		for (Gene gene : rawList) {
			returnedGeneList.add(gene);
			geneCache.put(gene.getGeneId(), gene);
		}

		trimExons(returnedGeneList, seqRegionStart, seqRegionEnd);
		return returnedGeneList;
	}

	public List<Gene> getOtterRegion(String csName, String csVerison, String regionName, Integer seqRegionStart,
			Integer seqRegionEnd) {
		List<RegionOtter> result = new ArrayList<>();

		Integer seqRegionId = seqRegionService.getNameAndCoordSystem(regionName, csName, csVerison).getSeqRegionId();
		List<Gene> genes = getByRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);

		List<SimpleFeature> simpleFeatures = simpleFeatureService.findBySeqRegionIdStartAndEnd(seqRegionId,
				seqRegionStart, seqRegionEnd);

		return genes;
	}

	private void trimExons(List<Gene> geneList, int seqRegionStart, int seqRegionEnd) {
		for (Gene gene : geneList) {
			for (Transcript transcript : gene.getTranscripts()) {
				String transcriptName = "";
				int removedCount = 0;

				Iterator<Exon> iter = transcript.getExons().iterator();
				while (iter.hasNext()) {
					Exon exon = iter.next();
					if (exon.getSeqRegionStart() < seqRegionStart || exon.getSeqRegionEnd() > seqRegionEnd) {
						iter.remove();
						removedCount++;
					}
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
