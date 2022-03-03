package com.ibm.academia.movies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwagger
{
    @Bean
    public Docket getDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ibm.academia.movies.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Servicio para peliculas online",
                "Servicio para lemanejo de los datos de una aplicacion para ver peliculas",
                "V1",
                "Terminos del servicio",
                new Contact("José Miguel Toledo Reyes", "www.github.com/josemigueltr", "josemigueltr@ibm.com"),
                "Licencia de API", "API licencia url", Collections.emptyList()
        );
    }
}