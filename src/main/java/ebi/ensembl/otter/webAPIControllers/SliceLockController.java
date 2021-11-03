package ebi.ensembl.otter.webAPIControllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebi.ensembl.otter.SliceLockService;
import ebi.ensembl.otter.datasources.model.SliceLock;

@RestController
@CrossOrigin
@RequestMapping("/sliceLock")
public class SliceLockController {

	@Autowired
	private SliceLockService service;

	@PostMapping("/")
	public List<SliceLock> findGeneByIdInDb(@RequestBody String body) throws JSONException {
		JSONObject jObject = new JSONObject(body);
		System.out.println(jObject.getString("csName") + jObject.getString("csVersion") + jObject.getString("name"));
		return service.getSliceLocks(jObject.getString("csName"), jObject.getString("csVersion"),
				jObject.getString("name"));
	}

	@PostMapping("/2")
	public List<SliceLock> findGeneByIdInDbF(@RequestBody String body) throws JSONException {
		JSONObject jObject = new JSONObject(body);

		return service.getSliceLocksHibernates(jObject.getString("csName"), jObject.getString("csVersion"),
				jObject.getString("name"));
	}

}
