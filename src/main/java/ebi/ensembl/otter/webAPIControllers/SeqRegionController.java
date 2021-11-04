package ebi.ensembl.otter.webAPIControllers;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebi.ensembl.otter.SeqRegionService;
import ebi.ensembl.otter.datasources.model.SeqRegion;

@RestController
@CrossOrigin
@RequestMapping("/seqRegion")
public class SeqRegionController {
	@Autowired
	private SeqRegionService service;

	@GetMapping("/topVisible")
	public List<SeqRegion> findAllTopRankSeqRegionsWithVisibleAttrib() throws JSONException {

		return service.getOtterDataSets() ;
	}

}
