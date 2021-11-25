package ebi.ensembl.otter;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.FeatureAttribute;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.repository.EvidenceRepository;
import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptRepository;

@Service
public class RegionService {

	@Autowired
	GeneRepository geneRepository;

	@Autowired
	TranscriptRepository transcriptRepository;

	@Autowired
	EvidenceRepository evidenceRepository;

	@Autowired
	SeqRegionService seqRegionService;

	public List<Gene> getByCoordSysAndRegionNameAndStartAndEnd(String csName, String csVerison, String regionName,
			Integer seqRegionStart, Integer seqRegionEnd) {
		/*
		 * Performance optimization for region fetch. Region fetch includes fetch of all
		 * genes with attribs (including names from attrib type) transcripts with
		 * attribs and evidences and exons. Fetching genes and then for each gene
		 * transcripts and for each transcript - exons - is rather slow (commit
		 * "tem commit"), comparing to join fetch of gens transcripts and exons. The
		 * region that takes 3 sec for separate fetch, join fetch takes 0.2 sec but then
		 * we can't fetch transcript attr in the same join fetch, as it involves two
		 * tables transcripts_attrib and attrib_type. With attrib fetch 0.2 sec
		 * increases to 1.5 sec, it is still better than no-attrib separate fetch. Fetch
		 * of attibs in automatic JPQL way (if we fetch from transcript_attrib having
		 * attrib_type field in it) works much slower than current native query in
		 * transcript repo (1 sec vs 6 sec)
		 */

		/*
		 * Logic level. Filling transcripts with attribs could be done in "region"
		 * high-level entity that is given to users. but then it follows out that any
		 * logic should be done in regio constructor, as logic includes all
		 * transformation needed to create user region entity, and also it will violate
		 * dependency inversion principle that high level components should not depend
		 * on low level, but vice versa So all logic of creating high level entity from
		 * lower level is here in service for now. Steps are following: 1. Fill
		 * transcripts with attribs and evidences 2. Remove exons that are out of range
		 * 3. For each gene add remark about truncation and set truncated flag 4.
		 * Convert all remark and attrib objects to high-level entity format All this
		 * steps includes knowledges about how genes-transcripts-exons-evidences and
		 * attribs are connected logically to one structure,so it is higher-level than
		 * simple data-models so should be implemented here in logic level.
		 *
		 */
		Integer seqRegionId = seqRegionService.getNameAndCoordSystem(regionName, csName, csVerison).getSeqRegionId();
		List<Gene> rawList = geneRepository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);

		for (Gene gene : rawList) {
			List<FeatureAttribute> geneAttributesList = geneRepository
					.getGeneAttribById(gene.getGeneId());
			gene.setAttributes(geneAttributesList);
			List<Transcript> transcripts = gene.getTranscripts();
			for (Transcript transcript : transcripts) {
				Integer transcriptId = transcript.getTranscriptId();

				List<FeatureAttribute> transcriptAttributesList = transcriptRepository
						.getTranscriptAttribById(transcriptId);
				transcript.setAttributes(transcriptAttributesList);
				List<Evidence> evidenceList = evidenceRepository.findByTranscriptId(transcriptId);
				transcript.setEvidence(evidenceList);
				
				int i = 0;
				int removedCount = 0;
				
				Iterator<Exon> iter = transcript.getExons().iterator();
				
				while (iter.hasNext()) {
					Exon exon = iter.next();
					if (exon.getSeqRegionStart() < seqRegionStart 
							|| exon.getSeqRegionEnd() > seqRegionEnd) {
						iter.remove();
						removedCount++;
					}
					i++;
				}
				
				if (removedCount == 1) {
					gene.getAttributes().add(new FeatureAttribute("remark",
							"Transcript " + transcript.getStableId() + " has 1 exon that is not in this slice"));
				} else if (removedCount > 1 ) {
					gene.getAttributes().add(new FeatureAttribute("remark",
							"Transcript " + transcript.getStableId() + " has "
							+ removedCount +
							" exons that are not in this slice"));

				}
			}
		}
		return rawList;

	}

}
