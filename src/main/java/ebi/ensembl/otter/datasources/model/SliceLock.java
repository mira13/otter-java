package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "slice_lock")
public class SliceLock {

	private String active;

	@ManyToOne()
	@JoinColumn(name = "author_id", nullable = true)
	private Author author;

	private String freed;

	@ManyToOne()
	@JoinColumn(name = "freed_author_id", nullable = true)
	private Author freedAuthor;

	private String hostname;

	private String intent;

	@Column(name = "otter_version")
	private int otterVersion;

	@Column(name = "seq_region_end")
	private int seqRegionEnd;

	@Column(name = "seq_region_id")
	private int seqRegionId;

	@Column(name = "seq_region_start")
	private int seqRegionStart;

	@Id
	@Column(name = "slice_lock_id")
	private int sliceLockId;

	@Column(name = "ts_activity")
	private String tsActivity;

	@Column(name = "ts_begin")
	private String tsBegin;

	@Column(name = "ts_free")
	private String tsFree;

	public SliceLock() {
	}

	public SliceLock(int sliceLockId, int seqRegionId, int seqRegionStart, int seqRegionEnd, Author author,
			String tsBegin, String tsActivity, String active, String freed, Author freedAuthor, String intent,
			String hostname, int otterVersion, String tsFree) {
		super();
		this.sliceLockId = sliceLockId;
		this.seqRegionId = seqRegionId;
		this.seqRegionStart = seqRegionStart;
		this.seqRegionEnd = seqRegionEnd;
		this.author = author;
		this.tsBegin = tsBegin;
		this.tsActivity = tsActivity;
		this.active = active;
		this.freed = freed;
		this.freedAuthor = freedAuthor;
		this.intent = intent;
		this.hostname = hostname;
		this.otterVersion = otterVersion;
		this.tsFree = tsFree;
	}

	public String getActive() {
		return active;
	}

	public Author getAuthor() {
		return author;
	}

	public String getFreed() {
		return freed;
	}

	public Author getFreedAuthor() {
		return freedAuthor;
	}

	public String getHostname() {
		return hostname;
	}

	public String getIntent() {
		return intent;
	}

	public int getOtterVersion() {
		return otterVersion;
	}

	public int getSeqRegionEnd() {
		return seqRegionEnd;
	}

	public int getSeqRegionId() {
		return seqRegionId;
	}

	public int getSeqRegionStart() {
		return seqRegionStart;
	}

	public int getSliceLockId() {
		return sliceLockId;
	}

	public String getTsActivity() {
		return tsActivity;
	}

	public String getTsBegin() {
		return tsBegin;
	}

	public String getTsFree() {
		return tsFree;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setFreed(String freed) {
		this.freed = freed;
	}

	public void setFreedAuthor(Author freedAuthor) {
		this.freedAuthor = freedAuthor;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public void setOtterVersion(int otterVersion) {
		this.otterVersion = otterVersion;
	}

	public void setSeqRegionEnd(int seqRegionEnd) {
		this.seqRegionEnd = seqRegionEnd;
	}

	public void setSeqRegionId(int seqRegionId) {
		this.seqRegionId = seqRegionId;
	}

	public void setSeqRegionStart(int seqRegionStart) {
		this.seqRegionStart = seqRegionStart;
	}

	public void setSliceLockId(int sliceLockId) {
		this.sliceLockId = sliceLockId;
	}

	public void setTsActivity(String tsActivity) {
		this.tsActivity = tsActivity;
	}

	public void setTsBegin(String tsBegin) {
		this.tsBegin = tsBegin;
	}

	public void setTsFree(String tsFree) {
		this.tsFree = tsFree;
	}

}
