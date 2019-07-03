package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.dto.LegalPersonDto;
import coop.tecso.examen.model.LegalPerson;
import coop.tecso.examen.repository.LegalPersonRepository;
import coop.tecso.examen.service.impl.LegalPersonServiceImpl;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;
import coop.tecso.examen.testdata.DozerBeanMapperTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LegalPersonServiceTest {

	public static final LegalPersonDto legalPersonDto = new AccountHolderTestDataBuilder().buildLegalPersonDto();

	private LegalPerson legalPerson;

	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private LegalPersonRepository legalPersonRepository;

	@Before
	public void setUp() {
		dozerBeanMapper = DozerBeanMapperTest.dozerBeanMapper();
		legalPerson = legalPersonRepository.save(dozerBeanMapper.map(legalPersonDto, LegalPerson.class));
	}

	@Test(expected = DuplicateRecordException.class)
	public void saveDuplicateRecord() {
		LegalPersonService legalPersonService = new LegalPersonServiceImpl(legalPersonRepository, dozerBeanMapper);
		legalPersonService.save(legalPersonDto);
	}

	@Test
	public void findByIdTest() {
		LegalPersonService legalPersonService = new LegalPersonServiceImpl(legalPersonRepository, dozerBeanMapper);
		assertEquals(legalPerson.getId().intValue(), legalPersonService.findById(legalPerson.getId()).getId().intValue());
	}
}
