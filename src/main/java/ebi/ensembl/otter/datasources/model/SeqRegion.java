package ebi.ensembl.otter.datasources.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "seq_region")
public class SeqRegion {

	@OneToMany(mappedBy = "seqRegionId", fetch = FetchType.EAGER)
	private List<SeqRegionAttribute> attributes;

	@Column(name = "coord_system_id")
	private int coordSystemId;

	private int length;

	private String name;

	@Id
	@Column(name = "seq_region_id")
	private int seqRegionId;

	public List<SeqRegionAttribute> getAttributes() {
		return attributes;
	}

	public int getCoordSystemId() {
		return coordSystemId;
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public int getSeqRegionId() {
		return seqRegionId;
	}

	public void setAttributes(List<SeqRegionAttribute> attributes) {
		this.attributes = attributes;
	}

	public void setCoordSystemId(int coordSystemId) {
		this.coordSystemId = coordSystemId;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeq_region_id(int seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

}
