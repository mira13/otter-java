package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.SeqRegion;

public interface SeqRegionRepository
	extends JpaRepository<SeqRegion, Integer> {
		
		public List<SeqRegion> findAll();
		public List<SeqRegion> findByNameAndCoordSystemId(String name, int coordSystemId);

}
