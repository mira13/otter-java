package ebi.ensembl.otter;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import ebi.ensembl.otter.datasources.model.SliceLock;
import ebi.ensembl.otter.webAPIControllers.SliceLockController;

@WebMvcTest(SliceLockController.class)
class SliceLockControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SliceLockService sliceLockService;

	@Test
	public void shouldReturnCorrectClientFormat() throws Exception {

		List<SliceLock> lockList = new ArrayList<SliceLock>();

		lockList.add(new SliceLock(39369, 131554, 590738, 722422, new Author(3, "Ben", "Ben", 2), "2021-09-08 14:46:36",
				"2021-09-08 14:46:36", "held", null, null, "lock_region for otter", "otter-virtual-machine", 110,
				null));

		Mockito.when(sliceLockService.getSliceLocks(any(String.class), any(String.class), any(String.class)))
		.thenReturn(lockList);

		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/sliceLock/")
				.content("{\"csName\": \"chromosome\", \"csVersion\": \"GRCh38\", \"name\": \"1\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].sliceLockId").value("39369"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].seqRegionId").value("131554"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].seqRegionStart").value("590738"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].seqRegionEnd").value("722422"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].author.authorEmail").value("Ben"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].tsBegin").value("2021-09-08 14:46:36"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].tsActivity").value("2021-09-08 14:46:36"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value("held"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].freed").value(nullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].freedAuthor").value(nullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].intent").value("lock_region for otter"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].hostname").value("otter-virtual-machine"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].tsFree").value(nullValue()));

	}

}