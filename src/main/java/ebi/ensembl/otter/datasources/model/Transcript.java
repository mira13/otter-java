package ebi.ensembl.otter.datasources.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "transcript")
public class Transcript {

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;

	private String biotype;

	@Column(name = "analysis_id")
	private Integer analysisId;

	@Column(name = "gene_id")
	private Integer geneId;

	@Column(name = "seq_region_id")
	private Integer seqRegionId;

	@Column(name = "seq_region_start")
	private Integer seqRegionStart;

	@Column(name = "seq_region_end")
	private Integer seqRegionEnd;

	@Column(name = "seq_region_strand")
	private Integer seqRegionStrandt;

	@Column(name = "display_xref_id")
	private Integer displayXrefId;

	private String source;

	private String description;

	private String version;

	@Column(name = "is_current")
	private Boolean isCurrent;

	@Column(name = "canonical_translation_id")
	private Integer canonicalTranslationId;

	@Column(name = "stable_id")
	private String stable_id;

	@Column(name = "created_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Integer getGeneId() {
		return geneId;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	public String getBiotype() {
		return biotype;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public Integer getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(Integer analysisId) {
		this.analysisId = analysisId;
	}

	public Integer getSeqRegionId() {
		return seqRegionId;
	}

	public void setSeqRegionId(Integer seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public Integer getSeqRegionStart() {
		return seqRegionStart;
	}

	public void setSeqRegionStart(Integer seqRegionStart) {
		this.seqRegionStart = seqRegionStart;
	}

	public Integer getSeqRegionEnd() {
		return seqRegionEnd;
	}

	public void setSeqRegionEnd(Integer seqRegionEnd) {
		this.seqRegionEnd = seqRegionEnd;
	}

	public Integer getSeqRegionStrandt() {
		return seqRegionStrandt;
	}

	public void setSeqRegionStrandt(Integer seqRegionStrandt) {
		this.seqRegionStrandt = seqRegionStrandt;
	}

	public Integer getDisplayXrefId() {
		return displayXrefId;
	}

	public void setDisplayXrefId(Integer displayXrefId) {
		this.displayXrefId = displayXrefId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Integer getCanonicalTranslationId() {
		return canonicalTranslationId;
	}

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCanonicalTranslationId(Integer canonicalTranslationId) {
		this.canonicalTranslationId = canonicalTranslationId;
	}

	public String getStable_id() {
		return stable_id;
	}

	public void setStable_id(String stable_id) {
		this.stable_id = stable_id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(
	  name = "exon_transcript", 
	  joinColumns = @JoinColumn(name = "transcript_id"), 
	  inverseJoinColumns = @JoinColumn(name = "exon_id"))
	private List<Exon> exons;

	public List<Exon> getExons() {
		return exons;
	}

	public void setExons(List<Exon> exons) {
		this.exons = exons;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "modified_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

}
