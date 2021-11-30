package ebi.ensembl.otter.webAPIControllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebi.ensembl.otter.SeqRegionService;
import ebi.ensembl.otter.datasources.model.CoordSystem;
import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.model.SeqRegionAttribute;
import ebi.ensembl.otter.webAPIControllers.model.otter.RegionOtter;

@RestController
@CrossOrigin
@RequestMapping("/seqRegion")
public class SeqRegionController {

	@Autowired
	private SeqRegionService service;

	/**
	 * /seqRegion/topVisible GET endpoint to get top datasets list Converts list of
	 * SeqRegion retrieved with seqRegionService to otter compatible entity -
	 * DataSet and returns it to user
	 *
	 * @return list of otter compatible datasets
	 * @throws JSONException
	 */
	@GetMapping("/topVisible")
	public List<RegionOtter> findAllTopRankSeqRegionsWithVisibleAttrib() throws JSONException {
		List<SeqRegion> seqRegionList = service.getOtterDataSets();
		List<RegionOtter> dataSetList = new ArrayList<>();
		CoordSystem cs = service.getTopCoordSystem();
		String csName = cs.getName();
		String csVersion = cs.getVersion();
		String description;
		String write_access;

		for (SeqRegion region : seqRegionList) {

			description = "";
			write_access = "";
			for (SeqRegionAttribute attr : region.getAttributes()) {
				if (attr.getAttribTypeId().equals(128)) {
					write_access = attr.getValue();
				}
				if (attr.getAttribTypeId().equals(49)) {
					description = attr.getValue();
				}
			}
			dataSetList.add(new RegionOtter(region.getName(), description, csVersion, "0", write_access, csName));

		}

		return dataSetList;
	}

}
