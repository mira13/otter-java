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

	@Column(name = "created_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "endPhase")
	private Integer endPhase;

	@Id
	@Column(name = "exon_id")
	private Integer exontId;

	@Column(name = "is_constitutive")
	private Boolean isConstitutive;

	@Column(name = "is_current")
	private Boolean isCurrent;

	@Column(name = "modified_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "phase")
	private Integer phase;

	@Column(name = "seq_region_end")
	private Integer seqRegionEnd;

	@Column(name = "seq_region_id")
	private Integer seqRegionId;

	@Column(name = "seq_region_start")
	private Integer seqRegionStart;

	@Column(name = "seq_region_strand")
	private Integer seqRegionStrand;

	@Column(name = "stable_id")
	private String stableId;

	private String version;

	public Exon() {
	}

	/*
	 * This constructor is used in region fetch, when all exons are fetched and
	 * filled in gene
	 */
	public Exon(Object exontId, Object seqRegionId, Object seqRegionStart, Object seqRegionEnd, Object seqRegionStrand,
			Object phase, Object endPhase, Object version, Boolean isCurrent, Object isConstitutive, Object stable_id) {
		super();
		this.exontId = Integer.valueOf(exontId.toString());
		this.seqRegionId = Integer.valueOf(seqRegionId.toString());
		this.seqRegionStart = Integer.valueOf(seqRegionStart.toString());
		this.seqRegionEnd = Integer.valueOf(seqRegionEnd.toString());
		this.seqRegionStrand = Integer.valueOf(seqRegionStrand.toString());
		this.phase = Integer.valueOf(phase.toString());
		this.endPhase = Integer.valueOf(endPhase.toString());
		this.version = version.toString();
		this.isCurrent = isCurrent;
		if (isConstitutive.toString().equals("true")) {
			this.isConstitutive = true;
		} else {
			this.isConstitutive = false;
		}

		this.stableId = stable_id.toString();
		this.createdDate = (Date) createdDate;
		this.modifiedDate = (Date) modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Integer getEndPhase() {
		return endPhase;
	}

	public Integer getExontId() {
		return exontId;
	}

	public Boolean getIsConstitutive() {
		return isConstitutive;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Integer getPhase() {
		return phase;
	}

	public Integer getSeqRegionEnd() {
		return seqRegionEnd;
	}

	public Integer getSeqRegionId() {
		return seqRegionId;
	}

	public Integer getSeqRegionStart() {
		return seqRegionStart;
	}

	public Integer getSeqRegionStrand() {
		return seqRegionStrand;
	}

	public String getStableId() {
		return stableId;
	}

	public String getVersion() {
		return version;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setEndPhase(Integer endPhase) {
		this.endPhase = endPhase;
	}

	public void setExontId(Integer exontId) {
		this.exontId = exontId;
	}

	public void setIsConstitutive(Boolean isConstitutive) {
		this.isConstitutive = isConstitutive;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setPhase(Integer phase) {
		this.phase = phase;
	}

	public void setSeqRegionEnd(Integer seqRegionEnd) {
		this.seqRegionEnd = seqRegionEnd;
	}

	public void setSeqRegionId(Integer seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public void setSeqRegionStart(Integer seqRegionStart) {
		this.seqRegionStart = seqRegionStart;
	}

	public void setSeqRegionStrand(Integer seqRegionStrand) {
		this.seqRegionStrand = seqRegionStrand;
	}

	public void setStableId(String stableId) {
		this.stableId = stableId;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
