package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "evidence")
public class Evidence {
	
	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;
	
	public Integer getTranscriptId() {
		return transcriptId;
	}
	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String name;
	private String type;

}
