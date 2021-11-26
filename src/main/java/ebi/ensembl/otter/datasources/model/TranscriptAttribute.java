package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "transcript_attrib")
@IdClass(TranscriptAttribute.class)
public class TranscriptAttribute implements Serializable {
	
	private static final long serialVersionUID = 0;

	@Id
	@Column(name = "transcript_id")
	private Integer transcriptId;
	
	@Id
	@Column(name = "attrib_type_id")
	private Integer attributeTypeId;
	
	@Id
	@Column(name = "value")
	private String value;

	public Integer getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(Integer transcriptId) {
		this.transcriptId = transcriptId;
	}

	public Integer getAttributeTypeId() {
		return attributeTypeId;
	}

	public void setAttributeTypeId(Integer attributeTypeId) {
		this.attributeTypeId = attributeTypeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attributeTypeId, transcriptId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranscriptAttribute other = (TranscriptAttribute) obj;
		return Objects.equals(attributeTypeId, other.attributeTypeId) && Objects.equals(transcriptId, other.transcriptId)
				&& Objects.equals(value, other.value);
	}

}
