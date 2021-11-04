package ebi.ensembl.otter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.repository.CoordSystemRepository;
import ebi.ensembl.otter.datasources.repository.SeqRegionRepository;
import ebi.ensembl.otter.datasources.repository.SliceLockRepository;
import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.model.SliceLock;
import ebi.ensembl.otter.datasources.model.CoordSystem;

@Service
public class SliceLockService {

	@Autowired
	private SliceLockRepository sliceLockRepository;

	/*
	 * This method is questionable for now. It runs 90ms vs 150 in JPA query for
	 * ch17, but here if we need to add repo, we should manually implement query
	 * that also not a big deal as we add repos not so often, and implement query -
	 * is 3 min of dev
	 */
	public List<SliceLock> getSliceLocks(String csName, String csVersion, String name) {

		return sliceLockRepository.getLocksByCoordSystemAndName(csName, csVersion, name);

	}

}
