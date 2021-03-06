package ebi.ensembl.otter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.AttributeType;
import ebi.ensembl.otter.datasources.repository.AttributeTypeRepository;

/*
 * Cache attribute types list from db
 */
@Service
public class AttributeTypeService {

	@Autowired
	private AttributeTypeRepository repository;
	private HashMap<Integer, String> attribList = new HashMap<>();

	private void updateAttributeTypeList() {
		List<AttributeType> attributeTypeRawList = repository.findAll();
		for (AttributeType attribType : attributeTypeRawList) {
			attribList.put(attribType.getAttribTypeId(), attribType.getName());
		}
	}

	public String getAttributeNameById(Integer id) {
		if (!attribList.containsKey(id)) {
			updateAttributeTypeList();
		}

		return attribList.containsKey(id) ? attribList.get(id) : "Warning: no attribute name found";
	}

}
