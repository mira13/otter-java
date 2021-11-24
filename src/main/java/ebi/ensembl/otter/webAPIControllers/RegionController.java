package ebi.ensembl.otter.webAPIControllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebi.ensembl.otter.RegionService;
import ebi.ensembl.otter.datasources.model.Gene;

@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionController {

	@Autowired
	RegionService service;

	@PostMapping("/getBySeqRegionNameAndCoordSystem")
	public List<Gene> findAllBySeqRegionId(@RequestBody String body) throws JSONException {

		JSONObject jObject = new JSONObject(body);
		return service.getByCoordSysAndRegionNameAndStartAndEnd(
				jObject.getString("csName"),
				jObject.getString("csVersion"),
				jObject.getString("seqRegionName"),
				Integer.valueOf(jObject.getString("seqRegionStart")),
				Integer.valueOf(jObject.getString("seqRegionEnd"))
				);

	}

}
