package ebi.ensembl.otter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.repository.GeneRepository;

@Service
public class GeneService {
	
	@Autowired
	GeneRepository repository;

	public List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, 
			Integer seqRegionStart,
			Integer seqRegionEnd) {
		return repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, 
				seqRegionStart, 
				seqRegionEnd);
	}

}
