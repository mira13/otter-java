package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.SeqRegion;

public interface SeqRegionRepository extends JpaRepository<SeqRegion, Integer> {

	public List<SeqRegion> findAll();

	public List<SeqRegion> findByNameAndCoordSystemId(String name, int coordSystemId);

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

}

