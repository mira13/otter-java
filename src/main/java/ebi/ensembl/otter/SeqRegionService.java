package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.CoordSystem;
import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.repository.CoordSystemRepository;
import ebi.ensembl.otter.datasources.repository.SeqRegionRepository;

@Service
public class SeqRegionService {
	
	@Autowired
	private CoordSystemRepository coordSystemRepository;
	@Autowired
	private SeqRegionRepository seqRegionRepository;
	
	private List<SeqRegion> topDatasetCache = new ArrayList<SeqRegion>(); 
	
	public List<SeqRegion> getOtterDataSets() {     	
		if (topDatasetCache.isEmpty()) {
			int topLevelCoorSystemId = coordSystemRepository.findByRank(1).get(0).getCoordSystemId();
			topDatasetCache = seqRegionRepository.findVisibleByCoordSystemId(topLevelCoorSystemId);
		}
    	return topDatasetCache;
	}
	
	public CoordSystem getTopCoordSystem() {     		
    	return coordSystemRepository.findByRank(1).get(0);
	}
}
