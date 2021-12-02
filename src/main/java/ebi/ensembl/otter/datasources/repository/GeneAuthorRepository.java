package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.GeneAuthor;

public interface GeneAuthorRepository extends JpaRepository<GeneAuthor, Integer> {

	public List<GeneAuthor> findByGeneId(Integer geneId);

}
