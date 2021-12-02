package ebi.ensembl.otter.datasources.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "gene_author")
@IdClass(GeneAuthor.class)
public class GeneAuthor  implements Serializable{
	
	private static final long serialVersionUID = 0;

	public Integer getGeneId() {
		return geneId;
	}

	public void setGeneId(Integer geneId) {
		this.geneId = geneId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, geneId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneAuthor other = (GeneAuthor) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(geneId, other.geneId);
	}

	@Id
	@Column(name = "gene_id")
	private Integer geneId;
	
	@Id
	@Column(name = "author_id")
	private Integer authorId;

}
