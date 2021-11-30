package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ebi.ensembl.otter.datasources.model.SimpleFeature;

public interface SimpleFeatureRepository extends JpaRepository<SimpleFeature, Integer> {

	@Query(value = """
			SELECT * FROM simple_feature 
			WHERE seq_region_id = :seqRegionId
			AND (
			seq_region_start > :seqRegionStart
			AND seq_region_start < :seqRegionEnd
			OR
			seq_region_end < :seqRegionEnd
			AND seq_region_end > :seqRegionStart
			)"""
			,nativeQuery = true
			)
	public List<SimpleFeature> findBySeqRegionIdStartAndEnd (
			@Param("seqRegionId") Integer seqRegionId,
			@Param("seqRegionStart") Integer seqRegionStart,
			@Param("seqRegionEnd") Integer seqRegionEnd);

}
