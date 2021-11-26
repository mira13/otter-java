package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Override
	public List<Author> findAll();

}
