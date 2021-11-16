package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(schema = "exon_transcript")
@IdClass(ExonTranscript.class)
public class ExonTranscript implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@Column(name = "exon_id")
	private Integer exonId;

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;

	@Id
	private Integer rank;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExonTranscript))
			return false;
		ExonTranscript exonTranscript = (ExonTranscript) o;
		return Objects.equals(getExonId(), exonTranscript.getExonId())
				&& Objects.equals(getTranscriptId(), exonTranscript.getTranscriptId())
				&& Objects.equals(getRank(), exonTranscript.getRank());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getExonId() + getTranscriptId() + getRank());
	}

	public Integer getExonId() {
		return exonId;
	}

	public void setExonId(Integer exonId) {
		this.exonId = exonId;
	}

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
