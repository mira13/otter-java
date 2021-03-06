package ebi.ensembl.otter.webAPIControllers.model.otter;

public class RegionOtter {

	private String coord_system_name;

	private String coord_system_version;

	private String description;

	private String is_hidden;

	private String name;

	private String write_access;

	public RegionOtter(String name, String description, String coord_system_version, String is_hidden,
			String write_access, String coord_system_name) {
		super();
		this.name = name;
		this.description = description;
		this.coord_system_version = coord_system_version;
		this.is_hidden = is_hidden;
		this.write_access = write_access;
		this.coord_system_name = coord_system_name;
	}

	public String getCoord_system_name() {
		return coord_system_name;
	}

	public String getCoord_system_version() {
		return coord_system_version;
	}

	public String getDescription() {
		return description;
	}

	public String getIs_hidden() {
		return is_hidden;
	}

	public String getName() {
		return name;
	}

	public String getWrite_access() {
		return write_access;
	}

	public void setCoord_system_name(String coord_system_name) {
		this.coord_system_name = coord_system_name;
	}

	public void setCoord_system_version(String coord_system_version) {
		this.coord_system_version = coord_system_version;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWrite_access(String write_access) {
		this.write_access = write_access;
	}

}
