package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.GeneAttribute;
import ebi.ensembl.otter.datasources.repository.GeneAttributeRepository;
import ebi.ensembl.otter.datasources.repository.GeneRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Service
public class GeneService {

	@Autowired
	private GeneAttributeRepository attributeRepository;
	

	@Autowired
	GeneRepository repository;

	@Autowired
	private AttributeTypeService attributeTypeService;

	public List<FeatureAttribute> getGeneAttribById(Integer geneId) {
		List<FeatureAttribute> featureList = new ArrayList<>();
		List<GeneAttribute> rawList = attributeRepository.findByGeneId(geneId);
		for (GeneAttribute attribItem : rawList) {
			featureList.add(new FeatureAttribute(
					attributeTypeService.getAttributeNameById(attribItem.getAttributeTypeId()), attribItem.getValue()));
		}
		return featureList;
	}

	public List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart,
			Integer seqRegionEnd) {	
		return repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
	}

	public void fetchGeneAttributes(Gene gene) {
		gene.setAttributes(this.getGeneAttribById(gene.getGeneId()));
	}

}
