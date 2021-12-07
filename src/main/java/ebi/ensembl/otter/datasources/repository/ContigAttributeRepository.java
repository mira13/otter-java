package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.ContigAttribute;

public interface ContigAttributeRepository extends JpaRepository<ContigAttribute, Integer> {

	public List<ContigAttribute> findByContigInfoId(Integer contigInfoId);

}