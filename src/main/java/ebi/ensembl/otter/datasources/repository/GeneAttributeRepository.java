package ebi.ensembl.otter.datasources.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.GeneAttribute;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public interface GeneAttributeRepository extends JpaRepository<GeneAttribute, Integer>  {

	public List<GeneAttribute> findByGeneId(Integer geneId);
	
}
