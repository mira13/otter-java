package ebi.ensembl.otter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ebi.ensembl.otter.datasources.model.Author;
import ebi.ensembl.otter.datasources.model.SliceLock;

public class SliceLockTest {

	@Test
	public void gettersSettersConstructorCorrect() throws Exception {

		SliceLock sliceLock = new SliceLock(1234, 2345, 1, 3, new Author(), "12:00", "13:00", "freed", "finished",
				new Author(), "used for otter", "virt machine", 0, "14:00");

		assertThat(sliceLock.getSliceLockId()).isEqualTo(1234);

	}

}
