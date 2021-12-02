package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.TranscriptAuthor;

public interface TranscriptAuthorRepository extends JpaRepository<TranscriptAuthor, Integer> {

	public List<TranscriptAuthor> findByTranscriptId(Integer transcriptId);

}
