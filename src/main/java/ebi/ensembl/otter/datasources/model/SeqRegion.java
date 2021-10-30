package ebi.ensembl.otter.datasources.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "seq_region")
public class SeqRegion {
	
	@Id
	@Column(name = "seq_region_id")
	private int seqRegionId;
	
	private String name;
	
	@Column(name = "coord_system_id")
	private int coordSystemId;
	
	private int length;

    @OneToMany(mappedBy="seqRegionId")
	private Set<SliceLock> sliceLocks;
	
	public int getSeqRegionId() {
		return seqRegionId;
	}

	public void setSeq_region_id(int seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoordSystemId() {
		return coordSystemId;
	}

	public void setCoordSystemId(int coordSystemId) {
		this.coordSystemId = coordSystemId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Set<SliceLock> getSliceLocks() {
		return sliceLocks;
	}

	public void setSliceLocks(Set<SliceLock> sliceLocks) {
		this.sliceLocks = sliceLocks;
	}

}