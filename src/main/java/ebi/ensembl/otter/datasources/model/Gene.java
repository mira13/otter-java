package ebi.ensembl.otter.datasources.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "gene")
public class Gene {
	
	@Id
	@Column(name = "gene_id")
	private Integer geneId;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	private String biotype;

	@Column(name = "analysis_id")
	private Integer analysisId;
	
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
	
	@Column (name = "is_current")
	private Boolean isCurrent;
	
	@Column (name = "canonical_transcript_id")
	private Integer canonicalTranscriptId;
	
	@Column (name = "stable_id")
	private Integer stable_id;
	
	@Column (name = "created_date", columnDefinition="DATETIME")
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

	public Integer getCanonicalTranscriptId() {
		return canonicalTranscriptId;
	}

	public void setCanonicalTranscriptId(Integer canonicalTranscriptId) {
		this.canonicalTranscriptId = canonicalTranscriptId;
	}

	public Integer getStable_id() {
		return stable_id;
	}

	public void setStable_id(Integer stable_id) {
		this.stable_id = stable_id;
	}

	public Date getCreatedDate() {
		return createdDate;
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

	@Column (name = "modified_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

}
