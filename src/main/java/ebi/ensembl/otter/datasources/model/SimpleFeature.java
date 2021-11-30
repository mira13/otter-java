package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "simple_feature")
public class SimpleFeature {
	
	@Id
	@Column(name = "simple_feature_id")
	public Integer simpleFeatureId;
	
	@Column(name = "seq_region_id")
	public Integer seqRegionId;
	
	@Column(name = "seq_region_start")
	public Integer seqRegionStart;
	
	@Column(name = "seq_region_end")
	public Integer seqRegionEnd;
	
	@Column(name = "seq_region_strand")
	public Integer seqRegionStrand;
	
	@Column(name = "display_label")
	public String displayLabel;
	
	@Column(name = "analysis_id")
	public Integer analysisId;
	
	public Float score;

	public Integer getSimpleFeatureId() {
		return simpleFeatureId;
	}

	public void setSimpleFeatureId(Integer simpleFeatureId) {
		this.simpleFeatureId = simpleFeatureId;
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

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public Integer getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(Integer analysisId) {
		this.analysisId = analysisId;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
}
