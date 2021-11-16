package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(schema = "author")
public class Author {

	public Author(int authorId, String authorEmail, String authorName, int groupId) {
		super();
		this.authorId = authorId;
		this.authorEmail = authorEmail;
		this.authorName = authorName;
		this.groupId = groupId;
	}

	public Author() {
	}

	@Id
	@Column(name = "author_id")
	private int authorId;

	@Column(name = "author_email")
	private String authorEmail;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "group_id")
	private int groupId;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
