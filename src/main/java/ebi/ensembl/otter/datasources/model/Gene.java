package ebi.ensembl.otter.datasources.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(schema = "gene")
public class Gene {
 
	
	public Gene() {
	}

	public Gene(Object geneId, List<Transcript> transcripts, Object biotype, Object analysisId, Object seqRegionId,
			Object seqRegionStart, Object seqRegionEnd, Object seqRegionStrand, Object displayXrefId,
			Object source, Object description, Object version, Object isCurrent, Object canonicalTranscriptId,
			Object stable_id, Object createdDate, Object modifiedDate) {
		super();
		this.geneId = Integer.valueOf((geneId.toString()));
		this.transcripts = transcripts;
		this.biotype = biotype.toString();
		this.analysisId = Integer.valueOf(analysisId.toString());
		this.seqRegionId = Integer.valueOf(seqRegionId.toString());
		this.seqRegionStart = Integer.valueOf(seqRegionStart.toString());
		this.seqRegionEnd = Integer.valueOf(seqRegionEnd.toString());
		this.seqRegionStrand = Integer.valueOf(seqRegionStrand.toString());
		if (displayXrefId != null) {
			this.displayXrefId = Integer.valueOf(displayXrefId.toString());
		}
		this.source = source.toString();
		this.description = description.toString();
		this.version = version.toString();
		if (isCurrent.toString().equals("1")) {
			this.isCurrent = true;
		} else {
			this.isCurrent = false;
		}
	    
		this.canonicalTranscriptId = canonicalTranscriptId.toString();
		this.stable_id = stable_id.toString();
		this.createdDate = (Date) createdDate; // probably we just need that field as string
		this.modifiedDate = (Date) modifiedDate; // not sure, as no usage yet
	}

	@Id
	@Column(name = "gene_id")
	private Integer geneId;
	
	@Transient
	private List<FeatureAttribute> attributes;

	public List<FeatureAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<FeatureAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getVersion() {
		return version;
	}

	@OneToMany(mappedBy = "geneId", fetch = FetchType.EAGER)
	private List<Transcript> transcripts;

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Transcript> getTranscripts() {
		return transcripts;
	}

	public void setTranscripts(List<Transcript> transcripts) {
		this.transcripts = transcripts;
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
	private Integer seqRegionStrand;

	@Column(name = "display_xref_id")
	private Integer displayXrefId;

	private String source;

	private String description;

	private String version;

	@Column(name = "is_current")
	private Boolean isCurrent;

	@Column(name = "canonical_transcript_id")
	private String canonicalTranscriptId;

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

	public Integer getSeqRegionStrand() {
		return seqRegionStrand;
	}

	public void setSeqRegionStrand(Integer seqRegionStrand) {
		this.seqRegionStrand = seqRegionStrand;
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

	public String getCanonicalTranscriptId() {
		return canonicalTranscriptId;
	}

	public void setCanonicalTranscriptId(String canonicalTranscriptId) {
		this.canonicalTranscriptId = canonicalTranscriptId;
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
