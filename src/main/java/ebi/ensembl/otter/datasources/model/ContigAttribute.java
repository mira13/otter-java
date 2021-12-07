package ebi.ensembl.otter.datasources.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "cintig_attrib")
@IdClass(ContigAttribute.class)
public class ContigAttribute {

	private static final long serialVersionUID = 0;

	public Integer getAttributeTypeId() {
		return attributeTypeId;
	}

	public void setAttributeTypeId(Integer aattributeTypeId) {
		this.attributeTypeId = attributeTypeId;
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

	@Override
	public int hashCode() {
		return Objects.hash(attributeTypeId, seqRegionId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		ContigAttribute other = (ContigAttribute) obj;
		return Objects.equals(attributeTypeId, other.attributeTypeId) && Objects.equals(seqRegionId, other.seqRegionId)
				&& Objects.equals(value, other.value);
	}

	@Id
	@Column(name = "attrib_type_id")
	private Integer attributeTypeId;

	@Id
	@Column(name = "contig_rinfo_id")
	private Integer seqRegionId;

	@Id
	private String value;


}
