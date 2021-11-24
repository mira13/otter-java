package ebi.ensembl.otter;

import java.util.ArrayList;
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
	GeneRepository repository;

	@Autowired
	TranscriptRepository transcriptRepository;
	
	@Autowired
	EvidenceRepository evidenceRepository;
	
	@Autowired
	SeqRegionService seqRegionService;

	public List<Gene> getByCoordSysAndRegionNameAndStartAndEnd(String csName, String csVerison, 
			String regionName,
			Integer seqRegionStart,
			Integer seqRegionEnd) {
	/*
	 * Performance optimization for region fetch.
	 * Region fetch includes fetch of all genes with attribs (including names from attrib type)
	 * transcripts with attribs and evidences and exons.	
	 * Fetching genes and then for each gene transcripts and for each transcript - exons -
	 * is rather slow (commit "tem commit"), comparing to join fetch of gens transcripts and exons.
	 * The region that takes 3 sec for separate fetch, join fetch takes 0.2 sec
	 * but then we can't fetch transcript attr in the same join fetch, as it involves two tables
	 * transcripts_attrib and attrib_type. With attrib fetch 0.2 sec increases to 1.5 sec, it is still better 
	 * than no-attrib separate fetch.
	 */
		
	/*
	 * Logic level.
	 * Filling transcripts with attribs could be done in "region" high-level entity that is given to users.
	 * but then it follows out that any logic should be done in regio constructor, as logic includes all
	 * transformation needed to create user region entity, and also it will violate dependency
	 * inversion principle that high level components should not depend on low level, but vice versa
	 * So all logic of creating high level entity from lower level is here in service for now.
	 *  Steps are following: 
	 *  1. Fill transcripts with attribs and evidences
	 *  2. Remove exons that are out of range
	 *  3. For each gene add remark about truncation and set truncated flag
	 *  4. Convert all remark and attrib objects to high-level entity format
	 *  All this steps includes knowledges about how genes-transcripts-exons-evidences and attribs 
	 *  are connected logically to one structure,so it is higher-level than simple data-models
	 * so should be implemented here in logic level.
	 *  
	 */
		Integer seqRegionId = seqRegionService.getNameAndCoordSystem(regionName, csName, csVerison)
				.getSeqRegionId();
		List<Gene> rawList = repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);

		for (int i = 0; i < rawList.size(); i++ ) {
			List <Transcript> transcripts = rawList.get(i).getTranscripts();
			for (int j = 0; j < transcripts.size(); j++) {
				Integer transcriptId = transcripts.get(j).getTranscriptId();

				List<FeatureAttribute> transcriptAttributesList = transcriptRepository
						.getTranscriptAttribById(transcriptId);						
				transcripts.get(j).setAttributes(transcriptAttributesList);
				List <Evidence> evidenceList = evidenceRepository
						.findByTranscriptId(transcriptId);
				transcripts.get(j).setEvidence(evidenceList);
			}			
		}
		return rawList;

	}

}
