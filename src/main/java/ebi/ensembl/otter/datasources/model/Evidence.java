package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "evidence")
public class Evidence {

	private String name;

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;

	private String type;

	public String getName() {
		return name;
	}

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public void setType(String type) {
		this.type = type;
	}

}
