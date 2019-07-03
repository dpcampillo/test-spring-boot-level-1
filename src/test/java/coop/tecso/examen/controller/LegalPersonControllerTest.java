package coop.tecso.examen.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import coop.tecso.examen.dto.LegalPersonDto;
import coop.tecso.examen.service.LegalPersonService;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(LegalPersonController.class)
@AutoConfigureMockMvc(secure = false)
public class LegalPersonControllerTest {

	public static final String RUT_TEST = "8888888";

	private static String URL_LEGAL_PERSON = "/legalpersons";

	private LegalPersonDto legalPersonDto;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LegalPersonService legalPersonService;

	@Before
	public void setUp() {
		legalPersonDto = new AccountHolderTestDataBuilder().withRut(RUT_TEST).buildLegalPersonDto();
		when(legalPersonService.findById(5L)).thenReturn(legalPersonDto);
	}

	@Test
	public void saveLegalPersonTest() throws Exception {
		LegalPersonDto legalPersonDto = new AccountHolderTestDataBuilder().buildLegalPersonDto();
		mockMvc.perform(post(URL_LEGAL_PERSON + "").content(asJsonString(legalPersonDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getFindById() throws Exception {
		mockMvc.perform(get(URL_LEGAL_PERSON + "/5").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateLegalPersonTest() throws Exception {
		LegalPersonDto legalPersonDto = new AccountHolderTestDataBuilder().withRut(RUT_TEST).buildLegalPersonDto();
		legalPersonDto.setBusinessname("AAAAAAAAAAAAAAAA");
		when(legalPersonService.update(5L, legalPersonDto)).thenReturn(legalPersonDto);
		mockMvc.perform(put(URL_LEGAL_PERSON + "/5").content(asJsonString(legalPersonDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
