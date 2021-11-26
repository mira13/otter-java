package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "gene_attrib")
@IdClass(GeneAttribute.class)
public class GeneAttribute implements Serializable {
	
	private static final long serialVersionUID = 0;

	
	@Id
	@Column(name = "gene_id")
	private Integer geneId;
	
	@Id
	@Column(name = "attrib_type_id")
	private Integer attributeTypeId;
	
	@Id
	@Column(name = "value")
	private String value;

	public Integer getGeneId() {
		return geneId;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
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
		return Objects.hash(attributeTypeId, geneId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneAttribute other = (GeneAttribute) obj;
		return Objects.equals(attributeTypeId, other.attributeTypeId) && Objects.equals(geneId, other.geneId)
				&& Objects.equals(value, other.value);
	}

}
