package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.GeneAttribute;
import ebi.ensembl.otter.datasources.repository.GeneAttributeRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Service
public class GeneAttributeService {
	
	@Autowired
	private GeneAttributeRepository repository;
	
	@Autowired
	private AttributeTypeService attributeTypeService;
	
	public List<FeatureAttribute> getGeneAttribById( Integer geneId) {
		List <FeatureAttribute> featureList = new ArrayList<FeatureAttribute>();
		List<GeneAttribute> rawList = repository.findByGeneId(geneId);
		for (GeneAttribute attribItem : rawList) {
			featureList.add(new FeatureAttribute(attributeTypeService.getAttributeNameById(attribItem.getAttributeTypeId())
					, attribItem.getValue()));
		}	
		return featureList;		
	}

}
