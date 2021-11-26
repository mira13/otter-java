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

	@Column(name = "end_phase")
	private Integer end_phase;

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
	private String stable_id;

	private String version;

	public Exon() {
	}

	/*
	 * This constructor is used in region fetch, when all exons are fetched and
	 * filled in gene
	 */
	public Exon(Object exontId, Object seqRegionId, Object seqRegionStart, Object seqRegionEnd, Object seqRegionStrand,
			Object phase, Object end_phase, Object version, Boolean isCurrent, Object isConstitutive,
			Object stable_id) {
		super();
		this.exontId = Integer.valueOf(exontId.toString());
		this.seqRegionId = Integer.valueOf(seqRegionId.toString());
		this.seqRegionStart = Integer.valueOf(seqRegionStart.toString());
		this.seqRegionEnd = Integer.valueOf(seqRegionEnd.toString());
		this.seqRegionStrand = Integer.valueOf(seqRegionStrand.toString());
		this.phase = Integer.valueOf(phase.toString());
		this.end_phase = Integer.valueOf(end_phase.toString());
		this.version = version.toString();
		this.isCurrent = isCurrent;
		if (isConstitutive.toString().equals("1")) {
			this.isConstitutive = true;
		} else {
			this.isConstitutive = false;
		}

		this.stable_id = stable_id.toString();
		// this.createdDate = createdDate.toString();
		// this.modifiedDate = modifiedDate.toString();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Integer getEnd_phase() {
		return end_phase;
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

	public String getStable_id() {
		return stable_id;
	}

	public String getVersion() {
		return version;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setEnd_phase(Integer end_phase) {
		this.end_phase = end_phase;
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

	public void setStable_id(String stable_id) {
		this.stable_id = stable_id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
