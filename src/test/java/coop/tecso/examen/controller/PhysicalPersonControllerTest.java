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

import coop.tecso.examen.dto.PhysicalPersonDto;
import coop.tecso.examen.service.PhysicalPersonService;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(PhysicalPersonController.class)
@AutoConfigureMockMvc(secure = false)
public class PhysicalPersonControllerTest {

	private static String URL_PHYSICAL_PERSON = "/physicalpersons";

	public static final String RUT_TEST = "88886666";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhysicalPersonService physicalPersonService;

	private PhysicalPersonDto physicalPersonDto;

	@Before
	public void setUp() {
		physicalPersonDto = new AccountHolderTestDataBuilder().withRut(RUT_TEST).buildPhysicalPersonDto();
		when(physicalPersonService.findById(5L)).thenReturn(physicalPersonDto);
	}

	@Test
	public void savePhysicalPersonTest() throws Exception {
		PhysicalPersonDto physicalPersonDto = new AccountHolderTestDataBuilder().buildPhysicalPersonDto();
		mockMvc.perform(post(URL_PHYSICAL_PERSON).content(asJsonString(physicalPersonDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getFindById() throws Exception {
		mockMvc.perform(get(URL_PHYSICAL_PERSON + "/5").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateLegalPersonTest() throws Exception {
		PhysicalPersonDto physicalPersonDto = new AccountHolderTestDataBuilder().withRut(RUT_TEST)
				.buildPhysicalPersonDto();
		physicalPersonDto.setLastname("LLLLLLLLLLLLLLLLLLLLLL");
		when(physicalPersonService.update(5L, physicalPersonDto)).thenReturn(physicalPersonDto);
		mockMvc.perform(put(URL_PHYSICAL_PERSON + "/5").content(asJsonString(physicalPersonDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
