package ebi.ensembl.otter.webAPIControllers.model;

public class FeatureAttribute {
	
	private String name;
	
	private String value;

	public FeatureAttribute(Object attribItem, Object attribItem2) {
		super();
		this.name = attribItem.toString();
		this.value = attribItem2.toString();
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
