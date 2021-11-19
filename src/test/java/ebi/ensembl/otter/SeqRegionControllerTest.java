package ebi.ensembl.otter;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ebi.ensembl.otter.datasources.model.Author;
import ebi.ensembl.otter.datasources.model.CoordSystem;
import ebi.ensembl.otter.datasources.model.SeqRegion;
import ebi.ensembl.otter.datasources.model.SeqRegionAttribute;
import ebi.ensembl.otter.datasources.model.SliceLock;
import ebi.ensembl.otter.webAPIControllers.SeqRegionController;

@WebMvcTest(SeqRegionController.class)
public class SeqRegionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SeqRegionService seqRegionService;

	@Test
	public void shouldReturnCorrectClientFormat() throws Exception {

		List<SeqRegion> seqRegionList = new ArrayList<SeqRegion>();

		SeqRegion seqRegion = new SeqRegion();
		SeqRegionAttribute seqRegionAttribDescription = new SeqRegionAttribute();
		seqRegionAttribDescription.setAttribTypeId(49);
		seqRegionAttribDescription.setSeqRegionId(20);
		seqRegionAttribDescription.setValue("test description");

		SeqRegionAttribute seqRegionAttribWriteAccess = new SeqRegionAttribute();
		seqRegionAttribWriteAccess.setAttribTypeId(128);
		seqRegionAttribWriteAccess.setSeqRegionId(20);
		seqRegionAttribWriteAccess.setValue("1");

		SeqRegionAttribute seqRegionAttribHidden = new SeqRegionAttribute();
		seqRegionAttribHidden.setAttribTypeId(48);
		seqRegionAttribHidden.setSeqRegionId(20);
		seqRegionAttribHidden.setValue("0");

		seqRegion.setAttributes(List.of(seqRegionAttribDescription, seqRegionAttribHidden, seqRegionAttribWriteAccess));
		seqRegion.setCoordSystemId(10);
		seqRegion.setLength(2345);
		seqRegion.setName("17");
		seqRegion.setSeq_region_id(20);
		seqRegionList.add(seqRegion);

		CoordSystem coordSystem = new CoordSystem();
		coordSystem.setName("chromosome");
		coordSystem.setRank(1);
		coordSystem.setVersion("38");
		coordSystem.setSpeciesId(123);

		Mockito.when(seqRegionService.getOtterDataSets()).thenReturn(seqRegionList);

		Mockito.when(seqRegionService.getTopCoordSystem()).thenReturn(coordSystem);

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/seqRegion/topVisible").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("17"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("test description"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].write_access").value("1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].is_hidden").value("0"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].coord_system_name").value("chromosome"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].coord_system_version").value("38"));

	}

}
