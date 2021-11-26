package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(schema = "seq_region_attrib")
@IdClass(SeqRegionAttribute.class)
public class SeqRegionAttribute implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	@Column(name = "seq_region_id")
	private Integer seqRegionId;

	@Id
	private String value;

	@Id
	@Column(name = "attrib_type_id")
	private Integer attribTypeId;


	@Override
	public int hashCode() {
		return Objects.hash(attribTypeId, seqRegionId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeqRegionAttribute other = (SeqRegionAttribute) obj;
		return Objects.equals(attribTypeId, other.attribTypeId) && Objects.equals(seqRegionId, other.seqRegionId)
				&& Objects.equals(value, other.value);
	}

	public Integer getSeqRegionId() {
		return seqRegionId;
	}

	public void setSeqRegionId(Integer seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAttribTypeId() {
		return attribTypeId;
	}

	public void setAttribTypeId(Integer attribTypeId) {
		this.attribTypeId = attribTypeId;
	}

}
