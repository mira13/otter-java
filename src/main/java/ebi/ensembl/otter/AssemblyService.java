package ebi.ensembl.otter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebi.ensembl.otter.datasources.model.Assembly;
import ebi.ensembl.otter.datasources.repository.AssemblyRepository;

@Service
public class AssemblyService {

	@Autowired
	AssemblyRepository repository;

	public List<Integer> getSequenceLevelAssociatedRegionIdsByRegionId(Integer regionId, Integer regionStart, Integer regionEnd) {

		List<Integer> result = new ArrayList<>();

		List<Assembly> assemblyList = repository.getSequenceLevelByAssemblyRegionIdAndStartAndEnd(regionId, regionStart, regionEnd);

		for (Assembly assembly : assemblyList) {
			result.add(assembly.getAssemblyId().getCmpSeqRegionId());
			System.out.println(assembly.getAssemblyId().toString());
		}
		
		System.out.println(assemblyList.size());
		return result;

	}

}
