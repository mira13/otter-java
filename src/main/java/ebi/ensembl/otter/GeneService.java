package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

	public MultiValueMap<String, String> getGeneAttribById(Integer geneId) {
		MultiValueMap<String, String> attribList = new LinkedMultiValueMap<>();
		List<GeneAttribute> rawList = attributeRepository.findByGeneId(geneId);
		for (GeneAttribute attribItem : rawList) {
			attribList.add(attributeTypeService.getAttributeNameById(attribItem.getAttributeTypeId()).toLowerCase(),
					attribItem.getValue());
		}
		// Name and status key is expected to be always there
		if (!attribList.containsKey("name")) {
			attribList.add("name", "");
		}
		
		if (!attribList.containsKey("status")) {
			attribList.add("status", "");
		}
		return attribList;
	}

	public List<Gene> findBySeqRegionIdAndStartAndEnd(Integer seqRegionId, Integer seqRegionStart,
			Integer seqRegionEnd) {
		return repository.findBySeqRegionIdAndStartAndEnd(seqRegionId, seqRegionStart, seqRegionEnd);
	}

}
