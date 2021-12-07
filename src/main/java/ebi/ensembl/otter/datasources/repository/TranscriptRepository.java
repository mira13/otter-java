package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.Transcript;

public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

	@Query(value = """
			SELECT DISTINCT t FROM Transcript t
			JOIN FETCH t.exons
			WHERE t.geneId = :geneId
			""")
	public List<Transcript> fetchWithExonsByGeneId(@Param("geneId") Integer geneId);

}
