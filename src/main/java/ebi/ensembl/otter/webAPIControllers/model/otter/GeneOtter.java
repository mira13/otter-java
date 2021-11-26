package ebi.ensembl.otter.webAPIControllers.model.otter;

import java.util.Date;
import java.util.List;

import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public class GeneOtter {
	
	public GeneOtter() {
	}

	private Integer geneId;

	private List<FeatureAttribute> attributes;

	private List<Transcript> transcripts;

	private String biotype;

	private Integer analysisId;

	private Integer seqRegionId;

	private Integer seqRegionStart;

	private Integer seqRegionEnd;

	private Integer seqRegionStrand;

	private Integer displayXrefId;

	private String source;

	private String description;

	private String version;

	private Boolean isCurrent;

	private String canonicalTranscriptId;

	private String stable_id;

	private Date createdDate;

	private Date modifiedDate;

}

