package ebi.ensembl.otter.datasources.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Gene;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	public List<Gene> findAll();

	@Query(value = """
		SELECT DISTINCT g FROM Gene g 
        LEFT JOIN FETCH g.transcripts t
        JOIN t.exons
        WHERE g.seqRegionId = :seqRegionId
        AND g.seqRegionEnd < :seqRegionEnd
        AND g.seqRegionStart > :seqRegionStart
        AND g.isCurrent = 1
         """)
	public List<Gene> findBySeqRegionIdAndStartAndEnd(@Param("seqRegionId") Integer seqRegionId,
			@Param("seqRegionStart") Integer seqRegionStart,
			@Param("seqRegionEnd") Integer seqRegionEnd);

}
