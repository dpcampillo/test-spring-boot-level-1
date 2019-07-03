package coop.tecso.examen.controller;

import static org.mockito.Mockito.when;
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
import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.service.MovementService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovementController.class)
@AutoConfigureMockMvc(secure = false)
public class MovementControllerTest {

	private static String URL_MOVEMENTS = "/movements";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovementService movementService;

	@Test
	public void getAccountTest() throws Exception {
		mockMvc.perform(get(URL_MOVEMENTS + "?idAccount=1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getMovementByIdWithErrorTest() throws Exception {
		when(movementService.findById(1L)).thenThrow(new RecordNotFoundException("Not Found Exception"));
		mockMvc.perform(
				get(URL_MOVEMENTS + "/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void getMovementByIdTest() throws Exception {
		MovementDto movementDto = new MovementDto();
		movementDto.setId(6L);
		when(movementService.findById(6L)).thenReturn(movementDto);
		mockMvc.perform(
				get(URL_MOVEMENTS + "/6").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
