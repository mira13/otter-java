package ebi.ensembl.otter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ebi.ensembl.otter.datasources.model.Gene;
import ebi.ensembl.otter.datasources.model.GeneAttribute;
import ebi.ensembl.otter.datasources.model.GeneAuthor;
import ebi.ensembl.otter.datasources.repository.GeneAttributeRepository;
import ebi.ensembl.otter.datasources.repository.GeneAuthorRepository;
import ebi.ensembl.otter.datasources.repository.GeneRepository;

@Service
public class GeneService {

	@Autowired
	private GeneAttributeRepository attributeRepository;

	@Autowired
	private GeneAuthorRepository authorRepository;

	@Autowired
	GeneRepository repository;

	@Autowired
	private AttributeTypeService attributeTypeService;

	@Autowired
	private AuthorService authorService;

	private HashMap<Integer, String> geneAuthor = new HashMap<>();

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

	public String getAuthorByGeneId(Integer geneId) {
		if (geneAuthor.containsKey(geneId)) {
			return geneAuthor.get(geneId);
		}
		List<GeneAuthor> author = authorRepository.findByGeneId(geneId);
		if (!author.isEmpty()) {
			geneAuthor.put(geneId,
					authorService.getAuthorNameById(authorRepository.findByGeneId(geneId).get(0).getAuthorId()));
		}
		return geneAuthor.get(geneId);
	}

}
