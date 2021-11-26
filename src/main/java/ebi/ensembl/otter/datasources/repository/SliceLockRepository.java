package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.SliceLock;

public interface SliceLockRepository extends JpaRepository<SliceLock, Integer> {

	@Override
	public List<SliceLock> findAll();

	public List<SliceLock> findBySeqRegionIdAndActiveNot(int seqRegionId, String active);

	@Query(value = """
			select slice_lock.*
			from slice_lock
			JOIN seq_region
			ON seq_region.seq_region_id = slice_lock.seq_region_id
			JOIN coord_system
			ON coord_system.coord_system_id = seq_region.coord_system_id
			WHERE
			coord_system.name= :csname and coord_system.version= :csversion
			AND seq_region.name= :name
			AND slice_lock.active != \"free\" ;
			""", nativeQuery = true)
	public List<SliceLock> getLocksByCoordSystemAndName(@Param("csname") String csname,
			@Param("csversion") String csversion, @Param("name") String name);

}
