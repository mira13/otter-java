package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.Transcript;
import ebi.ensembl.otter.datasources.model.TranscriptAttribute;
import ebi.ensembl.otter.datasources.repository.EvidenceRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptAttributeRepository;
import ebi.ensembl.otter.webAPIControllers.model.FeatureAttribute;

@Service
public class TranscriptService {

	@Autowired
	private TranscriptAttributeRepository attributeRepository;
	
	@Autowired
	private EvidenceRepository evidenceRepository;

	@Autowired
	private AttributeTypeService attributeTypeService;

	public List<FeatureAttribute> getTranscriptAttribById(Integer transcriptId) {
		List<FeatureAttribute> featureList = new ArrayList<>();
		List<TranscriptAttribute> rawList = attributeRepository.findByTranscriptId(transcriptId);
		for (TranscriptAttribute attribItem : rawList) {
			featureList.add(new FeatureAttribute(
					attributeTypeService.getAttributeNameById(attribItem.getAttributeTypeId()), attribItem.getValue()));
		}
		return featureList;
	}
	
	public List<Evidence> findEvidenceByTranscriptId(Integer transcriptId) {
		return evidenceRepository.findByTranscriptId(transcriptId);
		
	}

}
