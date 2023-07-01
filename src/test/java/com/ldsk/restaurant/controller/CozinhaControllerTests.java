package com.ldsk.restaurant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldsk.restaurant.dto.CozinhaUpdateRequestDto;
import com.ldsk.restaurant.mapper.CozinhaMapper;
import com.ldsk.restaurant.service.CozinhaService;

@WebMvcTest(controllers = CozinhaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("testing")
class CozinhaControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CozinhaService cozinhaService;
	
	@MockBean
	private CozinhaMapper cozinhaMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void itShouldAddCozinhaAndReturnCreated() {
		
		CozinhaUpdateRequestDto cozinhaDto = CozinhaUpdateRequestDto.builder().nome("Americana").build();
		
		BDDMockito.given(cozinhaService.addCozinha(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArguments()));
		
		try {
			ResultActions response = mockMvc.perform(post("/cozinha")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(cozinhaDto)));
			
			response.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
