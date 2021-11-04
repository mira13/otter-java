package ebi.ensembl.otter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.repository.CoordSystemRepository;
import ebi.ensembl.otter.datasources.repository.SeqRegionRepository;

@Service
public class SeqRegionService {
	
	@Autowired
	private CoordSystemRepository coordSystemRepository;
	@Autowired
	private SeqRegionRepository seqRegionRepository;
	
	public List<SeqRegion> getOtterDataSets() {
        
		int topLevelCoorSystemId = coordSystemRepository.findByRank(1).get(0).getCoordSystemId();
        System.out.println(topLevelCoorSystemId);
		return seqRegionRepository.findVisibleByCoordSystemId(topLevelCoorSystemId);


	}

}
