package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class AssemblyId implements Serializable {

	private static final long serialVersionUID = 0;

	private Integer assemblySeqRegionId;

	private Integer cmpSeqRegionId;

	public Integer getAssemblySeqRegionId() {
		return assemblySeqRegionId;
	}

	public void setAssemblySeqRegionId(Integer assemblySeqRegionId) {
		this.assemblySeqRegionId = assemblySeqRegionId;
	}

	public Integer getCmpSeqRegionId() {
		return cmpSeqRegionId;
	}

	public void setCmpSeqRegionId(Integer cmpSeqRegionId) {
		this.cmpSeqRegionId = cmpSeqRegionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assemblySeqRegionId, cmpSeqRegionId);
	}
	
	

	public AssemblyId(Integer assemblySeqRegionId, Integer cmpSeqRegionId) {
		super();
		this.assemblySeqRegionId = assemblySeqRegionId;
		this.cmpSeqRegionId = cmpSeqRegionId;
	}

	public AssemblyId() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		AssemblyId other = (AssemblyId) obj;
		return Objects.equals(assemblySeqRegionId, other.assemblySeqRegionId)
				&& Objects.equals(cmpSeqRegionId, other.cmpSeqRegionId);
	}
}