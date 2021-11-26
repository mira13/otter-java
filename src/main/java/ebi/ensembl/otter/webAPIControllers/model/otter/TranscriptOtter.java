package ebi.ensembl.otter.webAPIControllers.model.otter;

import java.util.Set;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

public class TranscriptOtter {

	private Integer analysisId;

	private Set<FeatureAttribute> attributes;

	private String biotype;

	private String description;

	private Integer displayXrefId;

	private Set<Evidence> evidence;

	private Set<Exon> exons;

	private Integer geneId;

	private Boolean isCurrent;

	private Integer seqRegionEnd;

	private Integer seqRegionId;

	private Integer seqRegionStart;

	private Integer seqRegionStrand;

	private String source;

	private Integer transcriptId;

	private String version;

}
