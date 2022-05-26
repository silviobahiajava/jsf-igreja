package br.com.souzawebsistemas.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoGenerica {

	@Bean
	public Connection connection(DataSource ds) throws SQLException {
		
			return ds.getConnection();
		
	}
}
