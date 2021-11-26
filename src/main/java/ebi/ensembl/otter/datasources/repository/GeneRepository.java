package ebi.ensembl.otter.datasources.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.GeneAttribute;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.model.TranscriptAttribute;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public interface GeneRepository extends JpaRepository<Gene, Integer> {

	public List<Gene> findAll();

	@Query(value = """
			select  g.gene_id, g.biotype,
			g.analysis_id, g.seq_region_id,
			g.seq_region_start, g.seq_region_end,
			g.seq_region_strand, g.display_xref_id,
			g.source, g.description,
			g.is_current, g.canonical_transcript_id,
			g.stable_id, g.version,
			g.created_date, g.modified_date,
			t.transcript_id, t.analysis_id as transcriptAnalysis_id,
			t.seq_region_start as transcriptSeqRegionStart, t.seq_region_end as transcriptSeqRegionEnd,
			t.display_xref_id as transcriptXref, t.source as transcriptSource,
			t.biotype as transcriptBiotype, t.description as transcriptDescription,
			t.canonical_translation_id,
			t.stable_id as transcriptStableId, t.version as transcriptVersion,
			t.created_date as transcriptCreatedDate, t.modified_date as transcriptModifiedDate,
			e.exon_id, e.version as exonVersion,
			e.phase, e.end_phase, 
			e.is_constitutive, e.stable_id as exonStableId,
			e.seq_region_start as ExonSeqRegionStart, e.seq_region_end as exonSeqRegionEnd
			from gene g
			JOIN transcript t
			ON t.gene_id = g.gene_id
			JOIN exon_transcript et
			ON t.transcript_id = et.transcript_id
			JOIN exon e
			ON et.exon_id = e.exon_id
			WHERE g.seq_region_id = :seqRegionId
			AND (
			g.seq_region_start > :seqRegionStart 
			AND g.seq_region_start < :seqRegionEnd
			OR 
			g.seq_region_end < :seqRegionEnd
			AND g.seq_region_end > :seqRegionStart
			)
			AND g.is_current = 1
			AND t.is_current=1
			AND e.is_current=1
			ORDER BY g.gene_id, t.transcript_id
			""", nativeQuery = true)
	public List<Object[]> findBySeqRegionIdAndStartAndEndUnparsed(@Param("seqRegionId") Integer seqRegionId,
			@Param("seqRegionStart") Integer seqRegionStart, @Param("seqRegionEnd") Integer seqRegionEnd);
	
	public default List<Gene> findBySeqRegionIdAndStartAndEnd (Integer seqRegionId,
			Integer seqRegionStart, Integer seqRegionEnd) {
		
		List<Object[]> rawList = this.findBySeqRegionIdAndStartAndEndUnparsed(seqRegionId, seqRegionStart, seqRegionEnd);

		List<Gene> genes = new ArrayList<>();
		Gene gene = new Gene();
		Transcript transcript = new Transcript();
		Exon exon = new Exon();

		int geneIndex = 0;
		int transcriptIndex = 0;
		
		for (Object[] item : rawList) {
System.out.println(item[0]);
			if (genes.isEmpty() || !item[0].toString().equals(gene.getGeneId().toString())) {
				gene = new Gene(item[0], new ArrayList<Transcript>(), item[1], item[2], item[3], item[4], item[5],
						item[6], item[7], item[8], item[9], item[10], true, item[11], item[12], item[14], item[15]);
				genes.add(gene);

			}
			geneIndex = genes.size() - 1;
			transcriptIndex = genes.get(geneIndex).getTranscripts().size() - 1;

			if (genes.get(geneIndex).getTranscripts().isEmpty() || !item[16].toString()
					.equals(genes.get(geneIndex).getTranscripts().get(transcriptIndex).getTranscriptId().toString())) {
				transcript = new Transcript(item[16], item[22], item[17], item[0], item[3], item[18], item[19], item[6],
						item[20], item[21], item[23], item[26], true, item[24], item[25]);
				genes.get(geneIndex).getTranscripts().add(transcript);
			}

			transcriptIndex = genes.get(geneIndex).getTranscripts().size() - 1;
			exon = new Exon(item[29], item[3], item[35], item[36], item[6], item[31], item[32],
					item[30], true, item[33], item[34]);
			genes.get(geneIndex).getTranscripts().get(transcriptIndex).getExons().add(exon);
		}
		return genes;
	}

}
