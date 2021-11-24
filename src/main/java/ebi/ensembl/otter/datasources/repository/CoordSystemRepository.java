package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.CoordSystem;

public interface CoordSystemRepository extends JpaRepository<CoordSystem, Integer> {

	public List<CoordSystem> findAll();

	public List<CoordSystem> findByNameAndVersion(String name, String version);

	public List<CoordSystem> findByRank(int rank);
}
