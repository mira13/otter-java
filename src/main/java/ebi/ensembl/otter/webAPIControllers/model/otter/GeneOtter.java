package ebi.ensembl.otter.webAPIControllers.model.otter;

import java.util.Date;
import java.util.List;

import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public class GeneOtter {

	private Integer analysisId;

	private List<FeatureAttribute> attributes;

	private String biotype;

	private String canonicalTranscriptId;

	private Date createdDate;

	private String description;

	private Integer displayXrefId;

	private Integer geneId;

	private Boolean isCurrent;

	private Date modifiedDate;

	private Integer seqRegionEnd;

	private Integer seqRegionId;

	private Integer seqRegionStart;

	private Integer seqRegionStrand;

	private String source;

	private String stable_id;

	private List<Transcript> transcripts;

	private String version;

	public GeneOtter() {
	}

}
