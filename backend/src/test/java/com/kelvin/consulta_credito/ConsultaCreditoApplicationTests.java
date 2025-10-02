package com.kelvin.consulta_credito;

import com.kelvin.consulta_credito.controller.CreditoController;
import com.kelvin.consulta_credito.kafka.CreditoProducer;
import com.kelvin.consulta_credito.model.Credito;
import com.kelvin.consulta_credito.repository.CreditoRepository;
import com.kelvin.consulta_credito.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ConsultaCreditoApplicationTests {

	@Mock
	private CreditoRepository creditoRepository;
	@Mock
	private CreditoProducer creditoProducer;

	@InjectMocks
	private CreditoService creditoService;

	private Credito credito;
	private CreditoController creditoController;
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		creditoController = new CreditoController(creditoService);
		mockMvc = MockMvcBuilders.standaloneSetup(creditoController).build();

		credito = new Credito();
		credito.setId(1L);
		credito.setNumeroCredito("123456");
		credito.setNumeroNfse("7891011");
		credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
		credito.setValorIssqn(new BigDecimal(	"1500.75"));
		credito.setTipoCredito("ISSQN");
		credito.setSimplesNacional(true);
		credito.setAliquota(new BigDecimal("5.00"));
		credito.setValorFaturado(new BigDecimal("30000.00"));
		credito.setValorDeducao(new BigDecimal("5000.00"));
		credito.setBaseCalculo(new BigDecimal("25000.00"));
	}

	@Test
	void deveBuscarPorNumeroNfse() {
		when(creditoRepository.findByNumeroNfse("7891011")).thenReturn(List.of(credito));

		List<Credito> result = creditoService.findByNumeroNfse("7891011");

		assertFalse(result.isEmpty());
		assertEquals("7891011", result.get(0).getNumeroNfse());
	}

	@Test
	void deveBuscarPorNumeroCredito() {
		when(creditoRepository.findByNumeroCredito("123456")).thenReturn(credito);

		Credito result = creditoService.findByNumeroCredito("123456");

		assertNotNull(result);
		assertEquals("123456", result.getNumeroCredito());
	}

	@Test
	void deveRetornarCreditoPorNumeroNfseController() throws Exception {
		when(creditoRepository.findByNumeroNfse("7891011")).thenReturn(List.of(credito));

		mockMvc.perform(get("/api/creditos/7891011"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].numeroNfse").value("7891011"));
	}

	@Test
	void deveRetornarCreditoPorNumeroCreditoController() throws Exception {
		when(creditoRepository.findByNumeroCredito("123456")).thenReturn(credito);

		mockMvc.perform(get("/api/creditos/credito/123456"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numeroCredito").value("123456"));
	}
}
