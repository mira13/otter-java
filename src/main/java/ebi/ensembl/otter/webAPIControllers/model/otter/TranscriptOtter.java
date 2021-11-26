package ebi.ensembl.otter.webAPIControllers.model.otter;

import java.util.Set;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public class TranscriptOtter {

	private Integer transcriptId;

	private String biotype;

	private Integer analysisId;

	private Integer geneId;

	private Integer seqRegionId;

	private Integer seqRegionStart;

	private Integer seqRegionEnd;

	private Integer seqRegionStrand;

	private Integer displayXrefId;

	private String source;

	private String description;

	private String version;

	private Boolean isCurrent;

	private Set<Evidence> evidence;
    
	private Set<Exon> exons;
	
	private Set<FeatureAttribute> attributes;

}
