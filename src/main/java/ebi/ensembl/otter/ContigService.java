package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ebi.ensembl.otter.datasources.model.ContigAttribute;
import ebi.ensembl.otter.datasources.model.ContigInfo;
import ebi.ensembl.otter.datasources.repository.ContigAttributeRepository;
import ebi.ensembl.otter.datasources.repository.ContigInfoRepository;

@Service
public class ContigService {

	@Autowired
	private ContigInfoRepository repository;

	@Autowired
	private ContigAttributeRepository attributeRepository;

	@Autowired
	private AttributeTypeService attributeTypeService;

	private MultiValueMap<Integer, ContigInfo> contigCache;

	@Autowired
	AssemblyService assemblyService;

	@Value("${cache.transcriptsMaxAmount}")
	private Integer contigMaxAmount;

	public List<ContigInfo> getAllByTopLevelRegion(Integer regionId, Integer regionStart, Integer regionEnd) {
		List<ContigInfo> returnedContigList = new ArrayList<>();
		// Flush cache if it exceed allowed size
		// TODO: remove genes form cache, after genes are changed saved to db.
		if (contigCache.size() > contigMaxAmount) { // reset cache if it is too big
			contigCache = new LinkedMultiValueMap<>();
		}

		List<ContigInfo> rawList = new ArrayList<>();

		for (Integer regionIdCurrent : assemblyService.getSequenceLevelAssociatedRegionIdsByRegion(regionId,
				regionStart, regionEnd)) {
			for (ContigInfo contig : repository.findBySeqRegionId(regionIdCurrent)) {
				if (!contigCache.containsKey(regionIdCurrent)) {
					fillWithAttributes(contig);
					contigCache.add(contig.getContigInfoId(), contig);
				}
				returnedContigList.add(contig);
			}
		}

		return returnedContigList;
	}

	private MultiValueMap<String, String> fillWithAttributes(ContigInfo contig) {
		MultiValueMap<String, String> attribList = new LinkedMultiValueMap<>();
		Integer contigId = contig.getContigInfoId();
		List<ContigAttribute> rawList = attributeRepository.findByContigInfoId(contigId);
		for (ContigAttribute attribItem : rawList) {
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

}
