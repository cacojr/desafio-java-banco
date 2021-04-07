package com.api.southsystem.sistema.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.api.southsystem.sistema.banco.controller")
@ComponentScan(basePackages = {"com.api.southsystem.sistema.banco.controller","com.api.southsystem.sistema.banco.services","com.api.southsystem.sistema.banco.configs"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
