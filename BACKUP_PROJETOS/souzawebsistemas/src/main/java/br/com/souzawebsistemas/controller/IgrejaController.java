package br.com.souzawebsistemas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/igreja")

public class IgrejaController {

	private String pctLojas="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lojasvirtuais/";
	private String pctLanchonetes="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lanchonete/";
    private String pctPadarias="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-padarias/";
    private String pctIgrejas="souzawebsistemas/souzawebsistemas-cristao/souzawebsistemas-igrejas/";
	
	@GetMapping("/index")//  http://localhost:8080/clientes/index
	public String index() {
		return "souzawebsistemas/index";
	}
	
	@GetMapping("/comercial/lojas")//  http://localhost:8080/clientes/index
	public String lojas() {
		return  pctLojas+"lojavirtual";
	}
	
	
	@GetMapping("/comercial/lanchonetes")//  http://localhost:8080/clientes/index
	public String lanchonetes() {
		return  pctLanchonetes+"lanchonetes";
	}
	
	@GetMapping("/comercial/padarias")//  http://localhost:8080/clientes/index
	public String padarias() {
		return  pctPadarias+"padarias";
	}
	
	@GetMapping("/cristao/igrejas")//  http://localhost:8080/clientes/index
	public String igrejas() {
		return  pctIgrejas+"igrejas";
	}
	
}
