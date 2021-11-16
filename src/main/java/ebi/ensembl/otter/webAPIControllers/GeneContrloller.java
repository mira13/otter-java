package ebi.ensembl.otter.webAPIControllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebi.ensembl.otter.GeneService;
import ebi.ensembl.otter.datasources.model.Gene;

@RestController
@CrossOrigin
@RequestMapping("/gene")
public class GeneContrloller {

	@Autowired
	GeneService service;

	@GetMapping("/getbySeqRegionId")
	public List<Gene> findAllBySeqRegionId(@RequestBody String body) throws JSONException {

		JSONObject jObject = new JSONObject(body);
		return service.findBySeqRegionId(Integer.valueOf(jObject.getString("seqRegionId")));

	}

}
