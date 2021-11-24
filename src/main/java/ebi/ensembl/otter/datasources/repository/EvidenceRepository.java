package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer>  {
	
	
	public List<Evidence> findByTranscriptId(Integer transcriptId);

}
