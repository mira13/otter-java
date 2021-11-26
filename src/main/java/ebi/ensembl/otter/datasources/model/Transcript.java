package ebi.ensembl.otter.datasources.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import ebi.ensembl.otter.datasources.repository.TranscriptRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Entity
@Table(schema = "transcript")
public class Transcript {

	@Column(name = "analysis_id")
	private Integer analysisId;

	@Transient
	private List<FeatureAttribute> attributes;

	private String biotype;

	/*
	 * Connected table attributes is not added here as a class filed, as
	 * trqanscript_attrib makes no sense without attrib_type, and this cascade fetch
	 * is problematic for auto-fetch and lose performance
	 */

	@Column(name = "canonical_translation_id")
	private String canonicalTranslationId;

	@Column(name = "created_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String description;

	@Column(name = "display_xref_id")
	private Integer displayXrefId;

	@OneToMany(mappedBy = "transcriptId")
	private List<Evidence> evidence;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "exon_transcript", joinColumns = @JoinColumn(name = "transcript_id"), inverseJoinColumns = @JoinColumn(name = "exon_id"))
	private List<Exon> exons;

	@Column(name = "gene_id")
	private Integer geneId;

	@Column(name = "is_current")
	private Boolean isCurrent;

	@Column(name = "modified_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "seq_region_end")
	private Integer seqRegionEnd;

	@Column(name = "seq_region_id")
	private Integer seqRegionId;

	@Column(name = "seq_region_start")
	private Integer seqRegionStart;

	@Column(name = "seq_region_strand")
	private Integer seqRegionStrand;

	private String source;

	@Column(name = "stable_id")
	private String stableId;

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;

	@Transient
	@Autowired
	TranscriptRepository transcriptRepository;

	private String version;

	public Transcript() {
	}

	/*
	 * This constructor is used in region fetch, when all transcripts are fetched
	 * and filled in gene
	 */
	public Transcript(Object transcriptId, Object biotype, Object analysisId, Object geneId, Object seqRegionId,
			Object seqRegionStart, Object seqRegionEnd, Object seqRegionStrand, Object displayXrefId, Object source,
			Object description, Object version, Object isCurrent, Object canonicalTranslationId, Object stableId) {
		super();
		this.transcriptId = Integer.valueOf(transcriptId.toString());
		this.biotype = biotype.toString();
		this.analysisId = Integer.valueOf(analysisId.toString());
		this.geneId = Integer.valueOf(geneId.toString());
		this.seqRegionId = Integer.valueOf(seqRegionId.toString());
		this.seqRegionStart = Integer.valueOf(seqRegionStart.toString());
		this.seqRegionEnd = Integer.valueOf(seqRegionEnd.toString());
		this.seqRegionStrand = Integer.valueOf(seqRegionStrand.toString());
		if (displayXrefId != null) {
			this.displayXrefId = Integer.valueOf(displayXrefId.toString());
		}
		this.source = source.toString();
		if (description != null) {
			this.description = description.toString();
		}
		this.version = version.toString();
		if (isCurrent.toString().equals("true")) {
			this.isCurrent = true;
		} else {
			this.isCurrent = false;
		}		if (canonicalTranslationId != null) {
			this.canonicalTranslationId = canonicalTranslationId.toString();
		}
		this.stableId = stableId.toString();
		this.createdDate = (Date) createdDate;
		this.modifiedDate = (Date) modifiedDate;

		this.exons = new ArrayList<>();
		this.attributes = new ArrayList<>();
		this.evidence = new ArrayList<>();

	}

	public Integer getAnalysisId() {
		return analysisId;
	}

	public List<FeatureAttribute> getAttributes() {
		return attributes;
	}

	public String getBiotype() {
		return biotype;
	}

	public String getCanonicalTranslationId() {
		return canonicalTranslationId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getDescription() {
		return description;
	}

	public Integer getDisplayXrefId() {
		return displayXrefId;
	}

	public List<Evidence> getEvidence() {
		return evidence;
	}

	public List<Exon> getExons() {
		return exons;
	}

	public Integer getGeneId() {
		return geneId;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public Date getModifiedDate() {
		return modifiedDate;
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

	public Integer getSeqRegionStrandt() {
		return seqRegionStrand;
	}

	public String getSource() {
		return source;
	}

	public String getStableId() {
		return stableId;
	}

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public String getVersion() {
		return version;
	}

	public void setAnalysisId(Integer analysisId) {
		this.analysisId = analysisId;
	}

	public void setAttributes(List<FeatureAttribute> attributes) {
		this.attributes = attributes;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public void setCanonicalTranslationId(String canonicalTranslationId) {
		this.canonicalTranslationId = canonicalTranslationId;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisplayXrefId(Integer displayXrefId) {
		this.displayXrefId = displayXrefId;
	}

	public void setEvidence(List<Evidence> evidence) {
		this.evidence = evidence;
	}

	public void setExons(List<Exon> exons) {
		this.exons = exons;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public void setSource(String source) {
		this.source = source;
	}

	public void setStableId(String stable_id) {
		this.stableId = stable_id;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
