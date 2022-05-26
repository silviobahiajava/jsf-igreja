package br.com.souzawebsistemas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/souzawebsistemas")

public class SouzaWebSistemasController {

	private String pctLojas="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lojasvirtuais/usuarios/";
	private String pctLanchonetes="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lanchonete/usuarios/";
    private String pctPadarias="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-padarias/usuarios/";
    private String pctIgrejas="souzawebsistemas/souzawebsistemas-cristao/souzawebsistemas-igrejas/usuarios/";
    private String pctEscolas="souzawebsistemas/souzawebsistemas-educacao/souzawebsistemas-escolas/usuarios/";
	
	@GetMapping("/index")//  http://localhost:8080/souzawebsistemas/index
	public String index() {
		return "souzawebsistemas/index";
	}
	
	@GetMapping("/slidefoto3")//   http://localhost:8080/souzawebsistemas/slidefoto3
	public String slidefoto() {
		return "souzawebsistemas/slidefoto3";
	}
	
	
	@GetMapping("/comercial/lojas")//  http://localhost:8080/souzawebsistemas/comercial/lojas
	public String lojas() {
		return  pctLojas+"cadastroloja";
	}
	
	
	@GetMapping("/comercial/lanchonetes")//  http://localhost:8080/souzawebsistemas/comercial/lanchonetes
	public String lanchonetes() {
		return  pctLanchonetes+"cadastrolanchonete";
	}
	
	@GetMapping("/comercial/padarias")//  http://localhost:8080/souzawebsistemas/comercial/padarias
	public String padarias() {
		return  pctPadarias+"cadastropadaria";
	}
	
	@GetMapping("/cristao/igrejas")// http://localhost:8080/souzawebsistemas/cristao/igrejas
	public String igrejas() {
		return  pctIgrejas+"cadastroigreja";
	}
	@GetMapping("/educacao/escolas")// http://localhost:8080/souzawebsistemas/educacao/escolas
	public String escolas() {
		return  pctEscolas+"cadastroescola";
	}
	
}
