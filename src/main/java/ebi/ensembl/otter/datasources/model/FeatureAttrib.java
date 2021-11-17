package ebi.ensembl.otter.datasources.model;

public class FeatureAttrib {
	
	private String name;
	
	private String value;

	public FeatureAttrib(Object name, Object value) {
		this.name = name.toString();
		this.value = value.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
