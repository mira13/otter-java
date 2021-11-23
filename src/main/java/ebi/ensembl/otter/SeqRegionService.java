package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.CoordSystem;
import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.repository.CoordSystemRepository;
import ebi.ensembl.otter.datasources.repository.SeqRegionRepository;

/**
 * Seq region service provide endpoints to work with seq_region table and
 * related dependent tables
 * 
 * @author Mira
 *
 */
@Service
public class SeqRegionService {

	@Autowired
	private CoordSystemRepository coordSystemRepository;
	@Autowired
	private SeqRegionRepository seqRegionRepository;

	private List<SeqRegion> topDatasetCache = new ArrayList<SeqRegion>();

	/**
	 * The method is used to get top-level list of chromosomes, it uses cached value
	 * as soon as top level list is changed relatively rare - ones in half a year or
	 * less, so we don't need to really generate it every time. For now to empty the
	 * cache there is no endpoint, and application should be restarted to free cache
	 * 
	 * @return list of top level regions, for in otter-client initial db list.
	 */
	public List<SeqRegion> getOtterDataSets() {
		if (topDatasetCache.isEmpty()) {
			/*
			 * we use cached value here. Because fetching attributes like description and
			 * write_access takes significant time. But changed almost never - not more
			 * often than release. Current cache is dropped by app restart
			 */
			int topLevelCoorSystemId = coordSystemRepository.findByRank(1).get(0).getCoordSystemId();
			topDatasetCache = seqRegionRepository.findVisibleByCoordSystemId(topLevelCoorSystemId);
		}
		return topDatasetCache;
	}

	/**
	 * Internal service method that retrieves coordinate system with rank 1
	 *
	 * @return CoordSystem entry with rank 1
	 */
	public CoordSystem getTopCoordSystem() {
		return coordSystemRepository.findByRank(1).get(0);
	}
	
	public SeqRegion getNameAndCoordSystem(String name, String csName, String csVersion) {
		return seqRegionRepository.getByNameAndCoordSystem(name, csName, csVersion);
	}
	
}
