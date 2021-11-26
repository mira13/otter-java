package ebi.ensembl.otter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.AttributeType;
import ebi.ensembl.otter.datasources.model.Author;
import ebi.ensembl.otter.datasources.repository.AttributeTypeRepository;
import ebi.ensembl.otter.datasources.repository.AuthorRepository;

/*
 * Cache author list from db
 */
@Service
public class AttributeTypeService {
	
	@Autowired
	private AttributeTypeRepository repository;
	private HashMap<Integer, String> attribList = new HashMap<Integer, String>();
	
	private void updateAttributeTypeList() {
		List<AttributeType> attributeTypeRawList = repository.findAll();
		for (AttributeType attribType : attributeTypeRawList) {
			attribList.put(attribType.getAttribTypeId() , attribType.getName());
		}
	}
	
	public String getAttributeNameById(Integer id) {
		if (!attribList.containsKey(id)) {
			this.updateAttributeTypeList();		
		}
		
		return attribList.containsKey(id) ? attribList.get(id) 
				: "Warning: no attribute name found";
	}

}
