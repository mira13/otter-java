package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.AttributeType;

public interface AttributeTypeRepository extends JpaRepository<AttributeType, Integer> {
	
	public List<AttributeType> findAll();

}
