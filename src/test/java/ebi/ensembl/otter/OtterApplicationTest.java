package ebi.ensembl.otter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ebi.ensembl.otter.webAPIControllers.SeqRegionController;
import ebi.ensembl.otter.webAPIControllers.SliceLockController;

@SpringBootTest
public class OtterApplicationTest {
	
	@Autowired
	SliceLockController sliceLockController;
	
	@Autowired
	SeqRegionController seqRegionController;
	
	@Autowired
	SeqRegionService seqRegionService;
	
	@Autowired
	SliceLockService sliceLockService;
	
	@Test
	public void contextLoads() {
		assertThat(sliceLockController).isNotNull();
		assertThat(seqRegionController).isNotNull();
		assertThat(sliceLockService).isNotNull();
		assertThat(seqRegionService).isNotNull();
	}
	
	 @Test
	 public void main() {
	      OtterApplication.main(new String[] {});
	 }
}
