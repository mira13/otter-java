package ebi.ensembl.otter.datasources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.ContigInfo;

public interface ContigInfoRepository extends JpaRepository<ContigInfo, Integer> {

	public List<ContigInfo> findByContigInfoId(Integer contigInfoId);
	
	public List<ContigInfo> findBySeqRegionId(Integer cseqRegionId);

}