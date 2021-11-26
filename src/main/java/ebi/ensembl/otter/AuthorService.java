package ebi.ensembl.otter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Author;
import ebi.ensembl.otter.datasources.repository.AuthorRepository;

/*
 * Cache author list from db
 */
@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository repository;
	private HashMap<Integer, String> authorList = new HashMap<Integer, String>();

	
	private void updateAuthorList() {
		List<Author> authorRawList= repository.findAll();
		for (Author author : authorRawList) {
			authorList.put(author.getAuthorId(), author.getAuthorName());
		}
	}
	
	public String getAuthorNameById(Integer id) {
		if (!authorList.containsKey(id)) {
			this.updateAuthorList();		
		}
		
		return authorList.containsKey(id) ? authorList.get(id) 
				: "Warning: no author found";
	}

}
