package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.model.SliceLock;

public interface SeqRegionRepository extends JpaRepository<SeqRegion, Integer> {

	public List<SeqRegion> findAll();

	public List<SeqRegion> findByNameAndCoordSystemId(String name, int coordSystemId);

	/**
	 * Selects all seq_regions with exact coord_system_id, that has attribute
	 * "hidden" (id 129) set to 0
	 * 
	 * @param cs - coordinate system id
	 * @return native query result
	 */
	@Query(value = """
			select s.*
			from seq_region s
			JOIN seq_region_attrib a
			ON a.seq_region_id = s.seq_region_id
			WHERE a.attrib_type_id = 129
			AND a.value = 0
			AND s.coord_system_id = :cs
			;
			""", nativeQuery = true)
	public List<SeqRegion> findVisibleByCoordSystemId(@Param("cs") Integer cs);

	@Query(value = """
			select * from seq_region
			JOIN coord_system
			ON coord_system.coord_system_id = seq_region.coord_system_id
			WHERE
			coord_system.name = :csname and coord_system.version= :csversion
			AND seq_region.name = :name
			LIMIT 1
			""", nativeQuery = true)
	public SeqRegion getByNameAndCoordSystem(@Param("name") String name, @Param("csname") String csname,
			@Param("csversion") String csversion);

}
