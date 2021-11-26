package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "author")
public class Author {

	@Column(name = "author_email")
	private String authorEmail;

	@Id
	@Column(name = "author_id")
	private int authorId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "group_id")
	private int groupId;

	public Author() {
	}

	public Author(int authorId, String authorEmail, String authorName, int groupId) {
		super();
		this.authorId = authorId;
		this.authorEmail = authorEmail;
		this.authorName = authorName;
		this.groupId = groupId;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public int getAuthorId() {
		return authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
