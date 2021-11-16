package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Gene;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	public List<Gene> findAll();

	public List<Gene> findBySeqRegionId(Integer seqRegionId);

}
