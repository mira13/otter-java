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

	/**
	 * /sliceLock POST endpoint to get locks list for particular region
	 *
	 * @param body must contain csname, csversion, and name (of the seqRegion),
	 *             usually chromosome
	 * @return list of locks for particular region
	 * @throws JSONException
	 */
	@PostMapping("/")
	public List<SliceLock> findGeneByIdInDb(@RequestBody String body) throws JSONException {
		JSONObject jObject = new JSONObject(body);
		return service.getSliceLocks(jObject.getString("csName"), jObject.getString("csVersion"),
				jObject.getString("name"));
	}

}
