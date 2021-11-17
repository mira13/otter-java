package ebi.ensembl.otter.datasources.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.FeatureAttrib;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	public List<Gene> findAll();

	@Query(value = """
			select  g.gene_id, g.biotype, g.analysis_id, g.seq_region_id, g.seq_region_start, g.seq_region_end,
			g.seq_region_strand, g.display_xref_id, g.source, g.description,g.is_current, g.canonical_transcript_id,
			g.stable_id, g.version, g.created_date, g.modified_date,
			t.transcript_id, t.analysis_id as transcriptAnalysis_id,
			t.seq_region_start as transcriptSeqRegionStart, 
			t.seq_region_end as transcriptSeqRegionEnd,
			t.display_xref_id as transcriptXref, t.biotype as transcriptBiotype, t.description as transcriptDescription,
			t.canonical_translation_id, 
			t.stable_id as transcriptStableId, t.version as transcriptVersion,
			t.created_date as transcriptCreatedDate, t.modified_date as transcriptModifiedDate
			from gene g
			JOIN transcript t
			ON t.gene_id = g.gene_id
			JOIN exon_transcript et
			ON t.transcript_id = et.transcript_id
			JOIN exon e
			ON et.exon_id = e.exon_id
			WHERE g.seq_region_id = :seqRegionId
			AND g.seq_region_start > :seqRegionStart
			AND g.seq_region_end < :seqRegionEnd
			AND g.is_current = 1
			AND t.is_current=1
			AND e.is_current=1
			ORDER BY g.gene_id, t.transcript_id
			""", nativeQuery = true)
	public List<Object[]> findBySeqRegionIdAndStartAndEnd(@Param("seqRegionId") Integer seqRegionId,
			@Param("seqRegionStart") Integer seqRegionStart, @Param("seqRegionEnd") Integer seqRegionEnd);

	@Query(value = """
			SELECT attrib_type.name as name, gene_attrib.value as value from gene_attrib
			JOIN attrib_type
			ON attrib_type.attrib_type_id = gene_attrib.attrib_type_id
			where gene_id = :geneId
			""", nativeQuery = true)
	public List<Object[]> getGeneAttribById(@Param("geneId") Integer geneId);

		

}
