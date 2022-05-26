package br.com.souzawebsistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.Paginacao;
import br.com.souzawebsistemas.service.PaginacaoService;
import br.com.souzawebsistemas.utils.Util;
@Controller
@RequestMapping("/loja")
public class PaginacaoController {
	 @Autowired
	  private PaginacaoService service;
	 
	 @RequestMapping("/produtos")
	 public String viewHomePage(Model model) {
		
		 return listByPages(model,1);
	 }
	 

	 @GetMapping("/page/{pageNumber}")
	 public String listByPages(Model model,@PathVariable("pageNumber")int currentPage) {
		 Page<Paginacao> page = service.listAll(currentPage);
		 
		 long totalItems = page.getNumberOfElements();
		 int totalPages = page.getTotalPages();
		 List<Paginacao> listaProdutos = page.getContent();
		
		 model.addAttribute("currentPage", currentPage);
		 model.addAttribute("TotalItems", totalItems);
		 model.addAttribute("totalPages", totalPages);
		 model.addAttribute("listaProdutos", listaProdutos);
		 return "paginacao";
	 }

	 @GetMapping("/novoproduto")//FORMULARIO PRA CADASTRAR OS PRODUTOS
		public ModelAndView cadastroProduto() {
			ModelAndView md=new ModelAndView("cadastroproduto");
			md.addObject("paginacao",new Paginacao());
			return md;
		}
		
		
		@PostMapping("/salvarproduto")
		public String  salvarProduto(Paginacao pg)  {
			System.out.println("mostrando datas");
			ModelAndView md=new ModelAndView("paginacao");
			String data=Util.localDateToString(pg.getData());
			java.util.Date dataCadastro = pg.getDataCadastro();
				pg.setNome(pg.getNome());
				pg.setDataStr(data);
				pg.setDataCadastro(dataCadastro);
			System.out.println("data string "+data);
			System.out.println("data string "+dataCadastro);
			service.cadastrar(pg);
			
			 return "redirect:/loja/produtos";
			//return md;
		}
		
	  
}
