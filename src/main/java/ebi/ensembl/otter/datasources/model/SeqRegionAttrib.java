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
@IdClass(SeqRegionAttrib.class)
public class SeqRegionAttrib implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@Id
	@Column(name = "seq_region_id")
	private Integer seqRegionId;
	
	@Id
	private String value;
    
	@Id
	@Column(name="attrib_type_id")
	private Integer attribTypeId;

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof SeqRegionAttrib)) return false;
	        SeqRegionAttrib attr = (SeqRegionAttrib) o;
	        return Objects.equals(getSeqRegionId(), attr.getSeqRegionId())
	        		&& Objects.equals(getAttribTypeId(), attr.getAttribTypeId())
	        		&& Objects.equals(getValue(), attr.getValue());
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(getSeqRegionId() + getAttribTypeId() + getValue());
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
