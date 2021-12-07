package ebi.ensembl.otter.datasources.model;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class ContigInfo {

	@Column(name = "contig_info_id")
	private Integer contigInfoId;

	@Column(name = "seq_region_id")
	private Integer seqRegionId;

	@ManyToOne
    @JoinColumn(name="author_id", nullable=false)
	private Author author;

	@Column(name = "create_date",  columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "is_current")
	private Integer isCurrent;
	
	@Transient
	private HashMap<String, String> attributes;

	public Integer getContigInfoId() {
		return contigInfoId;
	}

	public void setContigInfoId(Integer contigInfoId) {
		this.contigInfoId = contigInfoId;
	}

	public Integer getSeqRegionId() {
		return seqRegionId;
	}

	public void setSeqRegionId(Integer seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}

}
