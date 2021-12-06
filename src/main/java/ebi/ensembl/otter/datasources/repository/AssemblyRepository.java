package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.Assembly;

public interface AssemblyRepository extends JpaRepository<Assembly, Integer> {

	@Query(value = """
			SELECT * from assembly a, seq_region sr, seq_region sr2
			WHERE a.asm_seq_region_id=sr.seq_region_id AND a.cmp_seq_region_id=sr2.seq_region_id
			AND  sr.seq_region_id = :seqRegionId
			AND (
			a.asm_start <= :asmEnd AND a.asm_end >= :asmStart
			OR a.asm_start <= :asmEnd AND a.asm_start >= :asmStart
			OR a.asm_end <= :asmEnd AND a.asm_end >= :asmStart)
			AND sr2.coord_system_id = 1
			""", nativeQuery = true)
	List<Assembly> getSequenceLevelByAssemblyRegionIdAndStartAndEnd(@Param("seqRegionId") Integer seqRegionId,
			@Param("asmStart") Integer asmStart, @Param("asmEnd") Integer asmEnd);
}
