package ebi.ensembl.otter.datasources.model;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;

import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Entity
@Table(schema = "gene")
public class Gene {

	/*
	 * Performance optimization for region fetch. Region fetch includes fetch of all
	 * genes with attribs (including names from attrib type) transcripts with
	 * attribs and evidences and exons. Fetching genes and then for each gene
	 * transcripts and for each transcript - exons - is rather slow (commit
	 * "tem commit"), comparing to join fetch of gens transcripts and exons. The
	 * region that takes 3 sec for separate fetch, join fetch takes 0.2 sec but then
	 * we can't fetch transcript attr in the same join fetch, as it involves two
	 * tables transcripts_attrib and attrib_type. With attrib fetch 0.2 sec
	 * increases to 1.5 sec, it is still better than no-attrib separate fetch. Fetch
	 * of attibs in automatic JPQL way (if we fetch from transcript_attrib having
	 * attrib_type field in it) works much slower than current native query in
	 * transcript repo (1 sec vs 6 sec)
	 */

	@Column(name = "analysis_id")
	private Integer analysisId;

	@Transient
	private List<FeatureAttribute> attributes;

	private String biotype;

	@Column(name = "canonical_transcript_id")
	private String canonicalTranscriptId;

	@Column(name = "created_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String description;

	@Column(name = "display_xref_id")
	private Integer displayXrefId;

	@Id
	@Column(name = "gene_id")
	private Integer geneId;

	@Column(name = "is_current")
	private Boolean isCurrent;

	@Column(name = "modified_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Transient
	@Autowired
	GeneRepository repository;

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
	private String stable_id;

	@OneToMany(mappedBy = "geneId", fetch = FetchType.EAGER)
	private List<Transcript> transcripts;

	private String version;

	public Gene() {
	}

	public Gene(Object geneId, List<Transcript> transcripts, Object biotype, Object analysisId, Object seqRegionId,
			Object seqRegionStart, Object seqRegionEnd, Object seqRegionStrand, Object displayXrefId, Object source,
			Object description, Object version, Object isCurrent, Object canonicalTranscriptId, Object stable_id,
			Object createdDate, Object modifiedDate) {
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
		if (isCurrent.toString().equals("true")) {
			this.isCurrent = true;
		} else {
			this.isCurrent = false;
		}

		this.canonicalTranscriptId = canonicalTranscriptId.toString();
		this.stable_id = stable_id.toString();
		this.attributes = new ArrayList<>();
		this.createdDate = (Date) createdDate;
		this.modifiedDate = (Date) modifiedDate;
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

	public String getCanonicalTranscriptId() {
		return canonicalTranscriptId;
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

	public String getSource() {
		return source;
	}

	public String getStable_id() {
		return stable_id;
	}

	public List<Transcript> getTranscripts() {
		return transcripts;
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

	public void setCanonicalTranscriptId(String canonicalTranscriptId) {
		this.canonicalTranscriptId = canonicalTranscriptId;
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

	public void setStable_id(String stable_id) {
		this.stable_id = stable_id;
	}

	public void setTranscripts(List<Transcript> transcripts) {
		this.transcripts = transcripts;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
