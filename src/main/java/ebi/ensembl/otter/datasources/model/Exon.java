package ebi.ensembl.otter.datasources.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "exon")
public class Exon {
	
	@Id
	@Column(name = "exon_id")
	private Integer exontId;
	
	@Column(name = "seq_region_id")
	private Integer seqRegionId;
	
	@Column(name = "seq_region_start")
	private Integer seqRegionStart;
	
	@Column(name = "seq_region_end")
	private Integer seqRegionEnd;
	
	@Column(name = "seq_region_strand")
	private Integer seqRegionStrandt;
	
	@Column(name = "phase")
	private Integer phase;
	
	@Column(name = "end_phase")
	private Integer end_phase;
	
	private String version;
	
	@Column (name = "is_current")
	private Boolean isCurrent;
	
	@Column (name = "is_constitutive")
	private Boolean isConstitutive;
	
	@Column (name = "stable_id")
	private Integer stable_id;
	
	@Column (name = "created_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column (name = "modified_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

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

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
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

	public Integer getExontId() {
		return exontId;
	}

	public void setExontId(Integer exontId) {
		this.exontId = exontId;
	}

	public Integer getPhase() {
		return phase;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	public Integer getEnd_phase() {
		return end_phase;
	}

	public void setEnd_phase(Integer end_phase) {
		this.end_phase = end_phase;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Boolean getIsConstitutive() {
		return isConstitutive;
	}

	public void setIsConstitutive(Boolean isConstitutive) {
		this.isConstitutive = isConstitutive;
	}

}
