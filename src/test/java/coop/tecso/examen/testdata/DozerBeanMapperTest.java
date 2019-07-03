package coop.tecso.examen.testdata;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class DozerBeanMapperTest {

	public static DozerBeanMapper dozerBeanMapper() {
		List<String> mappingFiles = new ArrayList<>();
		mappingFiles.add("dozerJdk8Converters.xml");
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappingFiles);
		return mapper;
	}

}
