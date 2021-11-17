package ebi.ensembl.otter.datasources.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	public List<Gene> findAll();

	@Query(value = """
			select  g.gene_id, g.biotype, g.analysis_id, g.seq_region_id, g.seq_region_start, g.seq_region_end,
			g.seq_region_strand, g.display_xref_id, g.source, g.description,g.is_current, g.canonical_transcript_id,
			g.stable_id, g.version, g.created_date, g.modified_date
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
	public List<Object[]> findBySeqRegionIdAndStartAndEndUnparsed(@Param("seqRegionId") Integer seqRegionId,
			@Param("seqRegionStart") Integer seqRegionStart, @Param("seqRegionEnd") Integer seqRegionEnd);

	@Query(value = """
            SELECT gene_attrib.value, attrib_type.name  from gene_attrib
			JOIN attrib_type
			ON attrib_type.attrib_type_id = gene_attrib.attrib_type_id
			where gene_id = :geneId
			""", nativeQuery = true)
	public List<Object[]> getGeneAttribById(@Param("geneId") Integer geneId);

	
	public default List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart,
			Integer seqRegionEnd) {
		List<Object[]> rawList = this.findBySeqRegionIdAndStartAndEndUnparsed(seqRegionId, seqRegionStart,
				seqRegionEnd);

		List<Gene> genes = new ArrayList<>();
		List<Transcript> transcripts = new ArrayList<>();
		List<Exon> exons = new ArrayList<>();

		Gene gene = new Gene();
		Transcript transcript = new Transcript();
		Exon exon = new Exon();
		int geneI = -1;
		int transcriptI = 0;

		for (Object[] item : rawList) {

			if (genes.isEmpty() || !item[0].toString().equals(gene.getGeneId().toString())) {
				List<Object[]> attrib = 
						this.getGeneAttribById(Integer.valueOf(item[0].toString()));
				gene = new Gene(item[0], transcripts, item[1], item[2], item[3], item[4], item[5], item[6], item[7],
						item[8], item[9], item[10], true, item[12], item[13], item[14], item[15]);
				genes.add(gene);
				geneI++;
			}

			if (transcripts.isEmpty() || !String.valueOf(item[16]).equals(transcript.getTranscriptId())) {
				// construct new transcript here, a
			}

			// construct new exon here

		}

		return genes;

	}

}
