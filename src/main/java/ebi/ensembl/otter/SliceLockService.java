package ebi.ensembl.otter;

import ebi.ensembl.otter.datasources.model.SliceLock;
import ebi.ensembl.otter.datasources.repository.SliceLockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Otter lock system support. Currently otter client works with local database,
 * so when data is loaded and worked on locally - remote copy is locked, this
 * service provides locks endpoints
 * 
 * @author Mira
 *
 */
@Service
public class SliceLockService {

	@Autowired
	private SliceLockRepository sliceLockRepository;

	/**
	 * 
	 * @param csName    - coordinate system name: chromosome, contig, etc. (depends
	 *                  on db)
	 * @param csVersion - coordinate system version: like GRCm39
	 * @param name      - seq region name, usually chromosome name from
	 *                  {@link ebi.ensembl.otter.SeqRegionService#getOtterDataSets
	 *                  top level list}
	 * @return list of slice locks
	 */
	public List<SliceLock> getSliceLocks(String csName, String csVersion, String name) {

		return sliceLockRepository.getLocksByCoordSystemAndName(csName, csVersion, name);
	}
}
