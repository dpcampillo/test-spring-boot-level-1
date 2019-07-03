package coop.tecso.examen.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.service.AccountHolderService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountHolderController.class)
@AutoConfigureMockMvc(secure = false)
public class AccountHolderControllerTest {

	private static String URL_GET = "/accountholders";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountHolderService accountHolderService;

	@Test
	public void getAccountHolderTest() throws Exception {
		mockMvc.perform(get(URL_GET).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteAccountHolderTest() throws Exception {
		when(accountHolderService.deleteById(1L)).thenThrow(new RecordNotFoundException("Not Found Exception"));
		mockMvc.perform(
				delete(URL_GET + "/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
