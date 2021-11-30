package ebi.ensembl.otter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.SimpleFeature;
import ebi.ensembl.otter.datasources.repository.SimpleFeatureRepository;

@Service
public class SimpleFeatureService {

	@Autowired
	SimpleFeatureRepository repository;

	public List<SimpleFeature> findBySeqRegionIdStartAndEnd(Integer seqRegionId, Integer seqRegionStart,
			Integer seqRegionEnd) {
		return repository.findBySeqRegionIdStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
	}
}
