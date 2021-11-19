package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.FeatureAttribute;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptRepository;

@Service
public class GeneService {

	@Autowired
	GeneRepository repository;

	@Autowired
	TranscriptRepository transcriptRepository;

	public List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart,
			Integer seqRegionEnd) {
		List<Gene> rawList = repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);

		for (int i = 0; i < rawList.size(); i++ ) {
			List <Transcript> transcripts = rawList.get(i).getTranscripts();
			for (int j = 0; j < transcripts.size(); j++) {
				List<FeatureAttribute> transcriptAttributesList = transcriptRepository
						.getTranscriptAttribById(transcripts.get(j).getTranscriptId());						
				transcripts.get(j).setAttributes(transcriptAttributesList);
			}			
		}

		return rawList;

	}

}
