package br.com.souzawebsistemas.controller;
import br.com.souzawebsistemas.model.*;
import br.com.souzawebsistemas.repositories.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vendas")
public class VendasController {
private List<ItensVenda>itensVendas=new ArrayList<>();
private ProddutoRepository pRepository;
private String pacoteLoja="souzawebsistemas-lojasvirtuais";

@GetMapping("/carrinho")
public ModelAndView chamarCarrinho() {
	ItensVenda iv=new ItensVenda();
	iv.setCodigo(1L);
	iv.setQuantidade(10);
	iv.setPrecoParcial(new BigDecimal(15.00));
	itensVendas.add(iv);
	ModelAndView mad=new ModelAndView(pacoteLoja+"/carrinhodecompras");
	mad.addObject("listaProdutos",itensVendas);
	return mad;
}

@GetMapping("adicionarProdutoNoCarrinho/{codigo}")
public ModelAndView adicionarProdutoNoCarrinho(@PathVariable("codigo")Long codigo) {
	ModelAndView mad=new ModelAndView(pacoteLoja+"/carrinhodecompras");
	Optional<Produto> prod = pRepository.findById(codigo);
	Produto produto = prod.get();
	ItensVenda itemdavenda=new ItensVenda();
	itemdavenda.setProduto(produto);
	itemdavenda.setQuantidade(produto.getQuantidade()+1);
	//itensdavenda.setPrecoParcial(new BigDecimal(itensdavenda.getPrecoParcial()));
	itensVendas.add(itemdavenda);
	mad.addObject("listaProdutos",itensVendas);
	return mad;
}


@GetMapping("/loja")
public ModelAndView adicionarProdutoNoCarrinho() {
	ModelAndView mad=new ModelAndView(pacoteLoja+"/lojavirtual");
	
	return mad;
}
}
