package br.com.souzawebsistemas.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.service.PaginaService;

@Controller
@RequestMapping("/igrejas")
public class HomeController {
	@Autowired
	PaginaService pg;
	
	@Autowired
	private Connection con;
	
	@RequestMapping("/index")
 public String inicio() {
	 String pagina=pg.paginaIndex();
	 return pagina;
 }
	@RequestMapping("/teste")
	public String fim() {
		String pagina=pg.paginaTeste();
		return pagina;
	}
	
	@RequestMapping("/conexao")
	public  String conexao() {
		String resultado=con != null? "conectado com sucesso":"erro ao  conectar";
		System.out.println(resultado);
		return "conexao";
	}
	
}
