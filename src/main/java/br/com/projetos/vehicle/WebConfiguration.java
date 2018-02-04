package br.com.projetos.vehicle;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
	
	@Bean
	ServletRegistrationBean<WebServlet> h2servletRegistration() {
		return new ServletRegistrationBean<WebServlet>(new WebServlet(), "/console/*");
	}
	
}
