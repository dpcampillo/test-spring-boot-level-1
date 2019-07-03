package coop.tecso.examen.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalBeanConfig {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		List<String> mappingFiles = new ArrayList<>();
		mappingFiles.add("dozerJdk8Converters.xml");
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappingFiles);
		return mapper;
	}

}
