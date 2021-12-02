package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

	public MultiValueMap<String, String> getTranscriptAttribById(Integer transcriptId) {
		MultiValueMap<String, String> attribList = new LinkedMultiValueMap<>();
		List<TranscriptAttribute> rawList = attributeRepository.findByTranscriptId(transcriptId);
		for (TranscriptAttribute attribItem : rawList) {
			attribList.add(attributeTypeService.getAttributeNameById(attribItem.getAttributeTypeId()).toLowerCase(),
					attribItem.getValue());
		}
		// Name key is expected to be always there
		if (!attribList.containsKey("name")) {
			attribList.add("name", "");
		}
		return attribList;
	}

	public List<Evidence> findEvidenceByTranscriptId(Integer transcriptId) {
		return evidenceRepository.findByTranscriptId(transcriptId);

	}

}
