package ebi.ensembl.otter.datasources.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Assembly {

	@AttributeOverrides({
        @AttributeOverride(name="assemblySeqRegionId",
                           column=@Column(name="asm_seq_region_id")),
        @AttributeOverride(name="cmpSeqRegionId",
                           column=@Column(name="cmp_seq_region_id"))
    })
    @EmbeddedId
    private AssemblyId assemblyId;

	@Column(name = "asm_start")
	private Integer assemblyRegionStart;

	@Column(name = "asm_end")
	private Integer assemblyRegionEnd;

	@Column(name = "cmp_start")
	private Integer cpmRegionStart;

	@Column(name = "cmp_end")
	private Integer cmpRegionEnd;

	private Integer ori;

	public AssemblyId getAssemblyId() {
		return assemblyId;
	}

	public void setAssemblyId(AssemblyId assemblyId) {
		this.assemblyId = assemblyId;
	}

	public Integer getAssemblyRegionStart() {
		return assemblyRegionStart;
	}

	public void setAssemblyRegionStart(Integer assemblyRegionStart) {
		this.assemblyRegionStart = assemblyRegionStart;
	}

	public Integer getAssemblyRegionEnd() {
		return assemblyRegionEnd;
	}

	public void setAssemblyRegionEnd(Integer assemblyRegionEnd) {
		this.assemblyRegionEnd = assemblyRegionEnd;
	}

	public Integer getCpmRegionStart() {
		return cpmRegionStart;
	}

	public void setCpmRegionStart(Integer cpmRegionStart) {
		this.cpmRegionStart = cpmRegionStart;
	}

	public Integer getCmpRegionEnd() {
		return cmpRegionEnd;
	}

	public void setCmpRegionEnd(Integer cmpRegionEnd) {
		this.cmpRegionEnd = cmpRegionEnd;
	}

	public Integer getOri() {
		return ori;
	}

	public void setOri(Integer ori) {
		this.ori = ori;
	}

}

