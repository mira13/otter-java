package ebi.ensembl.otter.datasources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "coord_system")
public class CoordSystem {

	@Id
	@Column(name = "coord_system_id")
	private int coordSystemId;

	@Column(name = "species_id")
	private int speciesId;

	private String name;

	private String version;

	private int rank;

	private String attrib;

	public int getCoordSystemId() {
		return coordSystemId;
	}

	public void setCoordSystemId(int coordSystemId) {
		this.coordSystemId = coordSystemId;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getAttrib() {
		return attrib;
	}

	public void setAttrib(String attrib) {
		this.attrib = attrib;
	}

}
