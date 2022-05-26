package br.com.souzawebsistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

///import br.com.projeto01_souzawebsistemas.ComponentScan;
//import br.com.projeto01_souzawebsistemas.EnableJpaRepositories;
//import br.com.projeto01_souzawebsistemas.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.souzawebsistemas.model")
@ComponentScan(basePackages = { "br.com.souzawebsistemas.*" })
@EnableJpaRepositories(basePackages = { "br.com.souzawebsistemas.repositories" })
public class SouzawebsistemasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SouzawebsistemasApplication.class, args);
	}

}
