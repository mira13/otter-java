package ebi.ensembl.otter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ebi.ensembl.otter.datasources.model.Evidence;
import ebi.ensembl.otter.datasources.model.TranscriptAttribute;
import ebi.ensembl.otter.datasources.model.TranscriptAuthor;
import ebi.ensembl.otter.datasources.repository.EvidenceRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptAttributeRepository;
import ebi.ensembl.otter.datasources.repository.TranscriptAuthorRepository;

@Service
public class TranscriptService {

	@Autowired
	private TranscriptAttributeRepository attributeRepository;

	@Autowired
	private EvidenceRepository evidenceRepository;

	@Autowired
	private AttributeTypeService attributeTypeService;
	
	@Autowired
	private TranscriptAuthorRepository authorRepository;
	
	@Autowired
	private AuthorService authorService;
	
	private HashMap<Integer, String> transcriptAuthor = new HashMap<>();

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
		
		if (!attribList.containsKey("remark")) {
			attribList.add("remark", "");
		}
		return attribList;
	}

	public List<Evidence> findEvidenceByTranscriptId(Integer transcriptId) {
		return evidenceRepository.findByTranscriptId(transcriptId);

	}
	
	public String getAuthorByTranscriptId(Integer transcriptId) {
		if (transcriptAuthor.containsKey(transcriptId)) {
			return transcriptAuthor.get(transcriptId);
		}
		List<TranscriptAuthor> author = authorRepository.findByTranscriptId(transcriptId);
		if (!author.isEmpty()) {
			transcriptAuthor.put(transcriptId,
					authorService.getAuthorNameById(authorRepository.findByTranscriptId(transcriptId).get(0).getAuthorId()));
		}
		return transcriptAuthor.get(transcriptId);
	}

}
