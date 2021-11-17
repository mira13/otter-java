package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Exon;
import ebi.ensembl.otter.datasources.model.FeatureAttrib;
import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptRepository;

@Service
public class GeneService {
	
	@Autowired
	GeneRepository repository;
	
	@Autowired
	TranscriptRepository transcriptRepository;

	public List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, 
			Integer seqRegionStart,
			Integer seqRegionEnd) {
		List<Object[]> rawList = repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart,
				seqRegionEnd);

		List<Gene> genes = new ArrayList<>();
		List<Transcript> transcripts = new ArrayList<>();
		List<Exon> exons = new ArrayList<>();

		Gene gene = new Gene();
		Transcript transcript = new Transcript();
		Exon exon = new Exon();

		for (Object[] item : rawList) {

			if (genes.isEmpty() || !item[0].toString().equals(gene.getGeneId().toString())) {
				List<Object[]> attribs = 
						repository.getGeneAttribById(Integer.valueOf(item[0].toString()));
				List <FeatureAttrib> featureAttribs = new ArrayList<>();
				for(Object[] attrib : attribs) {
					featureAttribs.add(new FeatureAttrib(attrib[0], attrib[1]));
				}

				gene = new Gene(item[0], new ArrayList<Transcript>(), item[1], item[2], item[3], item[4], item[5], item[6], item[7],
						item[8], item[9], item[10], true, item[12], item[13], item[14], item[15]);
				gene.setAttributes(featureAttribs);
				genes.add(gene);

			}
            int geneIndex = genes.size() - 1;
            int transcriptIndex = genes.get(geneIndex).getTranscripts().size() - 1;
			if (genes.get(geneIndex).getTranscripts().isEmpty() 
					|| !String.valueOf(item[16])
					.equals(genes.get(geneIndex).getTranscripts()
							.get(transcriptIndex).getTranscriptId())) {
                List<Object[]> transcriptattrib = transcriptRepository.getTranscriptAttribById(Integer.valueOf(item[16].toString()));
				transcript = new Transcript(item[16]);
				genes.get(genes.size() - 1).getTranscripts().add(transcript);
			}

			// construct new exon here and add it to list of exons


		}

		return genes;

	}
	

}
