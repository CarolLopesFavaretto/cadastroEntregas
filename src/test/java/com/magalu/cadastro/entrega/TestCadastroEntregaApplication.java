package com.magalu.cadastro.entrega;

import org.springframework.boot.SpringApplication;

public class TestCadastroEntregaApplication {

	public static void main(String[] args) {
		SpringApplication.from(CadastroEntregaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
