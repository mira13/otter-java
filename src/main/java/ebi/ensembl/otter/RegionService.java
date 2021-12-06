package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.SimpleFeature;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.webAPIControllers.model.otter.RegionOtter;

@Service
public class RegionService {

	private HashMap<String, String> biotypesLowerCaseToCorrect = new HashMap<>() {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		{
			put("mirna", "miRNA");
			put("pirna", "piRNA");
			put("rrna", "rRNA");
			put("scrna", "scRNA");
			put("sirna", "siRNA");
			put("snrna", "snRNA");
			put("snorna", "snoRNA");
			put("trna", "tRNA");
			put("vaultrna", "vaultRNA");
			put("rna_pseudogene", "rRNA_pseudogene");
			put("misc_rna", "misc_RNA");
			put("srna", "sRNA");
			put("scarna", "scaRNA");
			put("ambiguous_orf", "Ambiguous_ORF");
			put("ig_gene", "IG_gene");
			put("ig_j_gene", "IG_J_gene");
			put("ig_c_gene", "IG_C_gene");
			put("ig_d_gene", "IG_D_gene");
			put("ig_v_gene", "IG_V_gene");
			put("ig_pseudogene", "IG_pseudogene");
			put("ig_j_pseudogene", "IG_J_pseudogene");
			put("ig_c_pseudogene", "IG_C_pseudogene");
			put("ig_d_pseudogene", "IG_D_pseudogene");
			put("tr_gene", "TR_gene");
			put("tr_pseudogene", "TR_pseudogene");
			put("lincrna", "lincRNA");
			put("macro_lncrna", "macro_lncRNA");
			put("antisense", "Antisense");
			put("3'_overlapping_ncrna", "3'_overlapping_ncRNA");
			put("bidirectional_promoter_lncrna", "Bidirectional_promoter_lncRNA");
			put("lncrna", "lncRNA");
		}
	};

	private HashMap<String, String> biotypesOtter = new HashMap<>() {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		{
			put("KNOWNprotein_coding", "Known_CDS");
			put("NOVELprotein_coding", "Novel_CDS");
			put("PUTATIVEprotein_coding", "Putative_CDS");
			put("KNOWNprocessed_transcript", "Novel_Transcript");
			put("NOVELprocessed_transcript", "Novel_Transcript");
			put("processed_transcript", "transcript");
			put("PUTATIVEprocessed_transcript", "Putative");
			put("PREDICTEDprotein_coding", "Predicted");
		}
	};

	private Integer cacheTranscriptCount = 0;

	private HashMap<Integer, Gene> geneCache = new HashMap<>();

	@Autowired
	GeneService geneService;

	@Autowired
	SeqRegionService seqRegionService;

	@Autowired
	SimpleFeatureService simpleFeatureService;

	@Value("${species}")
	private String species;

	@Autowired
	TranscriptService transcriptService;

	@Value("${cache.transcriptsMaxAmount}")
	private Integer transcriptsMaxAmount;

	private void fillGeneAndTranscriptsWithAttribsAndEvidences(List<Gene> rawList) {
		for (Gene gene : rawList) {
			gene.setAttributes(geneService.getGeneAttribById(gene.getGeneId()));
			List<Transcript> transcripts = gene.getTranscripts();

			for (Transcript transcript : transcripts) {
				Integer transcriptId = transcript.getTranscriptId();

				transcript.setAttributes(transcriptService.getTranscriptAttribById(transcriptId));
				transcript.setEvidence(transcriptService.findEvidenceByTranscriptId(transcriptId));
				cacheTranscriptCount++;
			}
		}
	}

	private String getBiotype(Gene gene) {
		String biotype = "";
		for (String status : gene.getAttributes().get("status")) {
			if (biotypesOtter.containsKey(status + gene.getBiotype())) {
				biotype = biotypesOtter.get(status + gene.getBiotype());
			} else if (biotypesLowerCaseToCorrect.containsKey(gene.getBiotype().toLowerCase())) {
				biotype = gene.getSource() + ":" + biotypesLowerCaseToCorrect.get(gene.getBiotype().toLowerCase());
			} else {
				biotype = gene.getSource() + ":" + gene.getBiotype().toLowerCase();
			}
		}
		return biotype;
	}

	public List<Gene> getByRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart, Integer seqRegionEnd) {
		/*
		 * Logic level. 1. Fill transcripts with attribs and evidences 2. Remove exons
		 * that are out of range 3. For each gene add remark about truncation and set
		 * truncated flag
		 */

		// This request we execute always, even it fetches already cached
		// genes-transcripts-exons it always finished in 0.2 sec approximately
		List<Gene> rawList = geneService.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
		List<Gene> returnedGeneList = new ArrayList<>();

		// Flush cache if it exceed allowed size
		// TODO: remove genes form cache, after genes are changed saved to db.
		if (cacheTranscriptCount > transcriptsMaxAmount) { // reset cache if it is too big
			geneCache = new HashMap<>();
		}
		/*
		 * cached genes we add to returned result directly, not cached we leave in raw
		 * list for pull
		 */
		Iterator<Gene> iterGene = rawList.iterator();
		while (iterGene.hasNext()) {
			Gene gene = iterGene.next();
			if (geneCache.containsKey(gene.getGeneId())) {
				returnedGeneList.add(geneCache.get(gene.getGeneId()));
				iterGene.remove();
			}
		}

		fillGeneAndTranscriptsWithAttribsAndEvidences(rawList);

		// Add genes with attributes, transcripts with attrib and evidences
		// and exons to result list and to cache
		for (Gene gene : rawList) {
			returnedGeneList.add(gene);
			geneCache.put(gene.getGeneId(), gene);
		}

		trimExons(returnedGeneList, seqRegionStart, seqRegionEnd);
		return returnedGeneList;
	}

	public String getOtterRegion(String csName, String csVerison, String regionName, Integer seqRegionStart,
			Integer seqRegionEnd) throws JsonProcessingException {
		List<RegionOtter> result = new ArrayList<>();

		Integer seqRegionId = seqRegionService.getNameAndCoordSystem(regionName, csName, csVerison).getSeqRegionId();
		List<Gene> genes = getByRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);

		List<SimpleFeature> simpleFeatures = simpleFeatureService.findBySeqRegionIdStartAndEnd(seqRegionId,
				seqRegionStart, seqRegionEnd);

		/*
		 * Otter client has very complicated and distributed code to parse exact xml,
		 * it's easier just to give it expected xml for now, that fixing client
		 */
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		ObjectNode otter = rootNode.putObject("otter");

		otter.put("species", species);

		ObjectNode sequenceNode = otter.putObject("sequence_set");
		sequenceNode.put("assembly_type", regionName);

		if (!simpleFeatures.isEmpty()) {
			ObjectNode featureSetNode = sequenceNode.putObject("feature_set");
			ArrayNode featureNode = featureSetNode.putArray("feature");

			for (SimpleFeature feature : simpleFeatures) {
				ObjectNode featureNodeImpl = featureNode.addObject();
				featureNodeImpl.put("type", feature.getDisplayLabel().toLowerCase());
				featureNodeImpl.put("start", feature.getSeqRegionStart());
				featureNodeImpl.put("end", feature.getSeqRegionEnd());
				featureNodeImpl.put("strand", feature.getSeqRegionStrand());
				featureNodeImpl.put("score", feature.getScore());
				featureNodeImpl.put("label", feature.getDisplayLabel());

			}

		}

		ArrayNode locuSetNode = sequenceNode.putArray("locus");
		// Here we use otter biotype translation, see GeneTranscriptBiotypeStatus.pm

		for (Gene gene : genes) {
			ObjectNode featureNodeImpl = locuSetNode.addObject();
			featureNodeImpl.put("stable_id", gene.getStable_id());
			featureNodeImpl.put("description", gene.getDescription());
			featureNodeImpl.put("name", String.join(", ", gene.getAttributes().get("name")));
			String biotype = getBiotype(gene);

			featureNodeImpl.put("type", biotype);
			featureNodeImpl.put("known", gene.getAttributes().get("status").contains("KNOWN") ? 1 : 0);
			featureNodeImpl.put("truncated", gene.getTruncated());

			ArrayNode remarkSetNode = featureNodeImpl.putArray("remark");

			for (String remark : gene.getAttributes().get("remark")) {
				ArrayNode remarkNodeImpl = remarkSetNode.add(remark);
			}
			featureNodeImpl.put("author", geneService.getAuthorByGeneId(gene.getGeneId()));
			featureNodeImpl.put("author_email", geneService.getAuthorByGeneId(gene.getGeneId()));

			ArrayNode transcriptSetNode = featureNodeImpl.putArray("transcript");

			for (Transcript transcript : gene.getTranscripts()) {
				if (!transcript.getExons().isEmpty()) {
					ObjectNode transcriptNodeImpl = transcriptSetNode.addObject();
					transcriptNodeImpl.put("stable_id", transcript.getStableId());
					featureNodeImpl.put("author",
							transcriptService.getAuthorByTranscriptId(transcript.getTranscriptId()));
					featureNodeImpl.put("author_email",
							transcriptService.getAuthorByTranscriptId(transcript.getTranscriptId()));

					ArrayNode remarkTranscriptNode = transcriptNodeImpl.putArray("remark");

					for (String remark : transcript.getAttributes().get("remark")) {
						ArrayNode remarkTranscriptNodeImpl = remarkTranscriptNode.add(remark);
					}

					transcriptNodeImpl.put("source", transcript.getSource());
					transcriptNodeImpl.put("name", transcript.getAttributes().getFirst("name"));

					ObjectNode evidenceSetNode = transcriptNodeImpl.putObject("evidence_set");
					ArrayNode evidenceNode = evidenceSetNode.putArray("evidence");
					for (Evidence evidence : transcript.getEvidence()) {
						ObjectNode evidenceNodeImpl = evidenceNode.addObject();
						evidenceNodeImpl.put("name", evidence.getName());
						evidenceNodeImpl.put("type", evidence.getType());
					}

					ObjectNode exonSetNode = transcriptNodeImpl.putObject("exon_set");
					ArrayNode exonNode = exonSetNode.putArray("exon");
					for (Exon exon : transcript.getExons()) {
						ObjectNode exonNodeImpl = exonNode.addObject();
						exonNodeImpl.put("stable_id", exon.getStableId());
						exonNodeImpl.put("start", exon.getSeqRegionStart());
						exonNodeImpl.put("end", exon.getSeqRegionEnd());
						exonNodeImpl.put("strand", exon.getSeqRegionStrand());
						exonNodeImpl.put("phase", exon.getPhase());
						exonNodeImpl.put("end_phase", exon.getEndPhase());

					}
				}
			}
		}

		String unwrappedXml = "";
		XmlMapper xmlMapper = new XmlMapper();
		Map.Entry<String, JsonNode> entry = rootNode.fields().next();
		return xmlMapper.writerWithDefaultPrettyPrinter().withRootName(entry.getKey())
				.writeValueAsString(entry.getValue());
	}

	private void trimExons(List<Gene> geneList, int seqRegionStart, int seqRegionEnd) {
		for (Gene gene : geneList) {
			for (Transcript transcript : gene.getTranscripts()) {
				String transcriptName;
				int removedCount = 0;
				transcriptName = transcript.getAttributes().getFirst("name");
				Iterator<Exon> iter = transcript.getExons().iterator();

				while (iter.hasNext()) {
					Exon exon = iter.next();
					if (exon.getSeqRegionStart() < seqRegionStart || exon.getSeqRegionEnd() > seqRegionEnd) {
						iter.remove();
						gene.setTruncated(1);
						removedCount++;
					}
				}

				if (removedCount == 1) {
					gene.getAttributes().add("remark",
							"Transcript " + transcriptName + " has 1 exon that is not in this slice");
				} else if (removedCount > 1) {
					gene.getAttributes().add("remark", "Transcript " + transcriptName + " has " + removedCount
							+ " exons that are not in this slice");
				}
			}
		}
	}
}
