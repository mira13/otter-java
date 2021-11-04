package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(schema = "seq_region_attrib")
public class SeqRegionAttrib {
	
	@Id
	@Column(name = "seq_region_id")
	private Integer seqRegionId;
	
	private String value;

	@Column(name="attrib_type_id", nullable=false)
	private Integer attribTypeId;

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
