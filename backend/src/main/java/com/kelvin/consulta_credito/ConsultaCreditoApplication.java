package com.kelvin.consulta_credito;

import com.kelvin.consulta_credito.repository.CreditoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsultaCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCreditoApplication.class, args);
	}

	@Bean
	public CommandLineRunner testDatabase(CreditoRepository repo) {
		return args -> {
			System.out.println("Total de créditos no banco: " + repo.count());
		};
	}

}
