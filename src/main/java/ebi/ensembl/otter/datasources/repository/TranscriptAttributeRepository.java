package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.TranscriptAttribute;

public interface TranscriptAttributeRepository extends JpaRepository<TranscriptAttribute, Integer> {

	public List<TranscriptAttribute> findByTranscriptId(Integer transcriptId);

}
