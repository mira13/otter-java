package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.SliceLock;
import ebi.ensembl.otter.datasources.model.Transcript;


public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

	@Query(value = """
			SELECT DISTINCT t FROM Transcript t 
			JOIN FETCH t.exons
	        WHERE t.geneId = :geneId
	         """)
	public List<Transcript> fetchByGeneId(@Param("geneId") Integer geneId);
	
	@Query(value = """
			SELECT attrib_type.name as name, transcript_attrib.value as value from transcript_attrib
			JOIN attrib_type
			ON attrib_type.attrib_type_id = transcript_attrib.attrib_type_id
			WHERE transcript_id = :transcriptId
			AND (attrib_type.name = "name"
			OR attrib_type.name = "type")
			""", nativeQuery = true)
	public List<Object[]> getTranscriptAttribById(@Param("transcriptId") Integer geneId);


}
