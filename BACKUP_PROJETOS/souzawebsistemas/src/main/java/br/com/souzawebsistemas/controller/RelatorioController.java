package br.com.souzawebsistemas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.souzawebsistemas.service.RelatorioService;

@Controller
@RequestMapping("/igreja")
public class RelatorioController {
	
	@GetMapping("/pdf")//  http://localhost:8080/clientes/index
	public String relatorios() {
		return "relatorio";
	}
   
	@Autowired
	private RelatorioService service;
	 @GetMapping("/relatorio/pdf/jr1")
	    public void exibirRelatorio01(@RequestParam("code") String code,
	                                  @RequestParam("acao") String acao,
	                                  HttpServletResponse response) throws IOException {
	        byte[] bytes = service.exportarPDF(code);
	        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
	        if (acao.equals("v")) {
	            response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
	        } else {
	            response.setHeader("Content-disposition", "attachment; filename=relatorio-"+ code + ".pdf" );
	        }
	        response.getOutputStream().write(bytes);
	    }
	 
	 //====================================================
	 
	 
	 @GetMapping("/relatorio/pdf")
	    public void exibirPdf(
	                                  @RequestParam("acao") String acao,
	                                  HttpServletResponse response) throws IOException {
		 	String nomeRelatorio="MISSAOEFE_CLIENTE01";
	        byte[] bytes = service.gerarPDF(nomeRelatorio);
	        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
	        if (acao.equals("v")) {
	            response.setHeader("Content-disposition", "inline; filename="+nomeRelatorio+"-"+".pdf");
	        } else {
	            response.setHeader("Content-disposition", "attachment; filename=relatorio-"+".pdf");
	        }
	        response.getOutputStream().write(bytes);
	    }
			 
	 
}
