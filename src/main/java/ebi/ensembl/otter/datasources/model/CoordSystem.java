package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "coord_system")
public class CoordSystem {

	private String attrib;

	@Id
	@Column(name = "coord_system_id")
	private Integer coordSystemId;

	private String name;

	private int rank;

	@Column(name = "species_id")
	private int speciesId;

	private String version;

	public String getAttrib() {
		return attrib;
	}

	public Integer getCoordSystemId() {
		return coordSystemId;
	}

	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public String getVersion() {
		return version;
	}

	public void setAttrib(String attrib) {
		this.attrib = attrib;
	}

	public void setCoordSystemId(Integer coordSystemId) {
		this.coordSystemId = coordSystemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
