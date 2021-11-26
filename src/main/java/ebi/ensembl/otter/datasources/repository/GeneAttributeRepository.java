package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.GeneAttribute;

public interface GeneAttributeRepository extends JpaRepository<GeneAttribute, Integer> {

	public List<GeneAttribute> findByGeneId(Integer geneId);

}
