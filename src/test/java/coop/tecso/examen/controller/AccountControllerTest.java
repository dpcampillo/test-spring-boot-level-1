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
import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.service.AccountService;
import coop.tecso.examen.testdata.AccountTestDataBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(secure = false)
public class AccountControllerTest {

	private static String URL_ACCOUNTS = "/accounts";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@Test
	public void getAccountTest() throws Exception {
		mockMvc.perform(get(URL_ACCOUNTS).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteAccountTest() throws Exception {
		when(accountService.deleteById(1L)).thenThrow(new RecordNotFoundException("Not Found Exception"));
		mockMvc.perform(
				delete(URL_ACCOUNTS + "/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void getFindById() throws Exception {
		when(accountService.deleteById(5L)).thenReturn(new AccountTestDataBuilder().buildDto());
		mockMvc.perform(get(URL_ACCOUNTS + "/5").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteAccountWithoutProblemsTest() throws Exception {
		AccountDto accountDto = new AccountTestDataBuilder().buildDto();
		when(accountService.deleteById(5L)).thenReturn(accountDto);
		mockMvc.perform(delete(URL_ACCOUNTS + "/5").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
