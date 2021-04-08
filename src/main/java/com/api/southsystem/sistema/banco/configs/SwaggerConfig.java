package com.api.southsystem.sistema.banco.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDetalhe() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.southsystem.sistema.banco"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build());

        return docket;
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api-Cadastro-Pessoas");
        apiInfoBuilder.description("Api para Cadastrar uma Pessoa e automaticamente criar sua conta e liberar os respectivos produtos financeiros.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: fiquem a vontade para baixar, melhorar e dar feedbacks");
        apiInfoBuilder.license("Licença - Open Source");
        apiInfoBuilder.licenseUrl("https://github.com/cacojr/desafio-java-banco");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }

    private springfox.documentation.service.Contact contato() {

        return new Contact(
                "Calos Alberto Costa Junior",
                "https://www.linkedin.com/in/carlos-costa-523886144/",
                "carlos.analista.si@gmail.com");
    }
}
