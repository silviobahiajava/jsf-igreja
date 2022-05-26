package br.com.souzawebsistemas.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.EntradaItem;
import br.com.souzawebsistemas.model.EntradaProduto;
import br.com.souzawebsistemas.repositories.*;
@Controller
@RequestMapping("/souzawebsistemas/comercial")
public class EntradaProdutoController {
private EntradaProdutoRepository epRepository;
private EntradaItemRepository eiRepository;
private ProddutoRepository pRepository;
private FuncionarioRepository fRepository;

//metodo pra construir o formulário para entrada de produtos
@GetMapping("/cadastro_entradaproduto")
public ModelAndView cadastroEntradaProduto(EntradaProduto entradaProduto,
		List<EntradaItem>listaEntradaItens,EntradaItem entradaItens) {
	ModelAndView md=new ModelAndView("lojavirtual/cadastro_entradaproduto");
	md.addObject("entradaProduto",entradaProduto);
	md.addObject("listaEntradaItens",listaEntradaItens);
	md.addObject("entradaItens",entradaItens);
	md.addObject("listaFuncionarios",fRepository.findAll());
	md.addObject("listaProdutos",pRepository.findAll());
	return md;
}

//método pra salvar os produtos que foram informados no formulário
public ModelAndView salvar(String acao,EntradaProduto entradaProduto,
		List<EntradaItem>listaEntradaItens,EntradaItem entradaItens) {
	ModelAndView md=new ModelAndView("lojavirtual/cadastro_entradaproduto");
	if(acao.equals("itens")) {
		listaEntradaItens.add(entradaItens);
	}
	return cadastroEntradaProduto(entradaProduto,
			listaEntradaItens,new EntradaItem());
}		

@GetMapping("/loja")
public ModelAndView mostrarpagina()
		 {
	ModelAndView md=new ModelAndView("/lojavirtual/lojavirtual");
	
	return md;
			
			
}	


}
