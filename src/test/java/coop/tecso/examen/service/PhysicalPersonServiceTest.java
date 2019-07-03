package coop.tecso.examen.service;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.dto.PhysicalPersonDto;
import coop.tecso.examen.model.PhysicalPerson;
import coop.tecso.examen.repository.PhysicalPersonRepository;
import coop.tecso.examen.service.impl.PhysicalPersonServiceImpl;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;
import coop.tecso.examen.testdata.DozerBeanMapperTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhysicalPersonServiceTest {

	public static final PhysicalPersonDto PHYSICAL_PERSON_DTO = new AccountHolderTestDataBuilder().buildPhysicalPersonDto();

	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private PhysicalPersonRepository physicalPersonRepository;

	@Before
	public void setUp() {
		dozerBeanMapper = DozerBeanMapperTest.dozerBeanMapper();
		physicalPersonRepository.save(dozerBeanMapper.map(PHYSICAL_PERSON_DTO, PhysicalPerson.class));
	}

	@Test(expected = DuplicateRecordException.class)
	public void saveDuplicateRecord() {
		PhysicalPersonService physicalPersonService = new PhysicalPersonServiceImpl(physicalPersonRepository, dozerBeanMapper);
		physicalPersonService.save(PHYSICAL_PERSON_DTO);
	}
}
