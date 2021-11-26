package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attrib_type")
public class AttributeType {

	@Id
	@Column(name = "attrib_type_id")
	private Integer attribTypeId;

	private String code;

	private String description;

	private String name;

	public Integer getAttribTypeId() {
		return attribTypeId;
	}

	public String getCode() {
		return code;
	}

	public String getDesciption() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setAttribTypeId(Integer attribTypeId) {
		this.attribTypeId = attribTypeId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesciption(String desciption) {
		this.description = desciption;
	}

	public void setName(String name) {
		this.name = name;
	}

}
