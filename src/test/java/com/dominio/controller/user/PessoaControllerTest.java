package com.dominio.controller.user;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.dominio.api.controller.users.PessoaController;
import com.dominio.api.model.user.Pessoa;
import com.dominio.api.service.user.PessoaService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class PessoaControllerTest {

	@Autowired
	private PessoaController pessoaController;

	@MockBean
	private PessoaService pessoaService;

	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.pessoaController);
	}

	@Test
	public void deveRetornarSucesso_QuandoBuscarPessoa() {

		when(this.pessoaService.buscarPorIdMock(1L)).thenReturn(new Pessoa("1", "Leonardo", LocalDate.now()));

		// @formatter:off
		RestAssuredMockMvc
		.given()
			.accept(ContentType.JSON)
		.when()
			.get("/users/mock/{id}", 1L)
		.then().statusCode(HttpStatus.OK.value());
		// @formatter:on

	}
}
