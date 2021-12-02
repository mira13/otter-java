package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "transcript_author")
@IdClass(TranscriptAuthor.class)
public class TranscriptAuthor  implements Serializable{
	
	private static final long serialVersionUID = 0;

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, transcriptId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranscriptAuthor other = (TranscriptAuthor) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(transcriptId, other.transcriptId);
	}

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;
	
	@Id
	@Column(name = "author_id")
	private Integer authorId;

}
