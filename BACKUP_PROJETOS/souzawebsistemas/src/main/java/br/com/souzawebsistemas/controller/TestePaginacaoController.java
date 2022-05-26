package br.com.souzawebsistemas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.TesteData;
import br.com.souzawebsistemas.model.TestePaginacao;
import br.com.souzawebsistemas.repositories.TesteDataRepository;
import br.com.souzawebsistemas.repositories.TestePaginacaoRepository;
import br.com.souzawebsistemas.service.TestePaginacaoService;
import br.com.souzawebsistemas.utils.Util;


@Controller
@RequestMapping("/paginacao")
public class TestePaginacaoController {
  
	@Autowired
	private TestePaginacaoRepository tr;
	
	@Autowired
	private TestePaginacaoService ts;
	
	private static String caminhoImagem="D:\\fotos_clientes\\";
	private String prefixoImagem="";
	@GetMapping("/listarpaginacao")
	public ModelAndView listarPaginacao() {
		@SuppressWarnings("unchecked")
		//List<TesteData>testes=(List<TesteData>) ;
		ModelAndView md=new ModelAndView("listapaginacao");
		md.addObject("testespaginacao",tr.findAll(PageRequest.of(0, 5,Sort.by("nome"))));
		//md.addObject("teste",new TesteData());
		return md;
	}
	
	
	@GetMapping("/testarpaginacao")
	public ModelAndView carregaTestePorPaginacao(@PageableDefault(size = 5) Pageable pageable
			) {
		ModelAndView model=new ModelAndView("listapaginacao");
		Page<TestePaginacao> pageTeste = tr.findAll(pageable);
		model.addObject("testespaginacao", pageTeste);
		model.addObject("testepaginacao", new TestePaginacao());
		
		
		return model;
		
		
	}
	
	@GetMapping("/dataspaginacao")
	public ModelAndView  mostrarData() {
		System.out.println("mostrando datas");
		ModelAndView md=new ModelAndView("dataspaginacao");
		int ano=1979;
		int mes=06;
		int dia=05;
		LocalDate dataLocalDate;
		try {
			dataLocalDate = Util.converterDataPraLocalDate(ano,mes,dia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md;
	}
	
	@GetMapping("/cadastropaginacao")
	public ModelAndView cadastroTeste() {
		ModelAndView md=new ModelAndView("cadastropaginacao");
		md.addObject("testepaginacao",new TestePaginacao());
		return md;
	}
	
	
	@PostMapping("/salvar")
	public ModelAndView  salvarTeste2(TestePaginacao tp,@RequestParam("file")MultipartFile arquivo)  {
		System.out.println("mostrando datas");
		ModelAndView md=new ModelAndView("dataspaginacao");
		String data=Util.localDateToString(tp.getData());
		java.util.Date dataCadastro = tp.getDataCadastro();
			tp.setDataStr(data);
			tp.setDataCadastro(dataCadastro);
		System.out.println("data string "+data);
		System.out.println("data string "+dataCadastro);
		tr.save(tp);
		if(! arquivo.isEmpty()) {
			try {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagem+String.valueOf(tp.getId())+arquivo.getOriginalFilename());
				// prefixoImagem=String.valueOf(td.getCodigo());
				Files.write(caminho, bytes);
				tp.setNomeImagem(String.valueOf(tp.getId())+arquivo.getOriginalFilename());
				//td.setNomeImagem(arquivo.getOriginalFilename());
				System.out.println(arquivo);
				System.out.println(tp.getNomeImagem());
				tr.save(tp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return md;
	}
	
	@GetMapping("/{imagem}/mostrarImagem")
	@ResponseBody
	public byte[] mostrarImagem(@PathVariable String imagem) {
		 File imagemArquivo = new File(caminhoImagem+imagem);
		 if(imagem!=null ||imagem.trim().length()>0) {
			 try {
					return Files.readAllBytes(imagemArquivo.toPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 return null;
		 
}
	
	//metodos de paginacao
	
	@GetMapping("/pagacao-smples")
	public ModelAndView paginacaoSimples() {
		Page<TestePaginacao> page = tr.findAll(PageRequest.of(0,5));
		ModelAndView md=new ModelAndView("listapaginacao");
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();
		md.addObject("totalPages", totalPages);
		md.addObject("totalElements",totalElements);
		md.addObject("page",page);
		return md;
		//return page.getContent().toString();
	}
	
	@GetMapping("/pagacao-com-parametros")
	public String paginacaoComParametros(Pageable pageable) {
		
		Page<TestePaginacao> page = tr.findAll(pageable);
		ModelAndView md=new ModelAndView();
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();
		md.addObject("totalPages", totalPages);
		md.addObject("totalElements",totalElements);
		return page.getContent().toString();
	}
	//=================================================================
	//==================================================================
	
	// display list of employees
		@GetMapping("/listagem")
		public String viewHomePage(Model model) {
			return findPaginated(1, "nome", "asc", model);		
		}
	
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<TestePaginacao> page = ts.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<TestePaginacao> listaPaginacaoTeste = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaPaginacaoTeste", listaPaginacaoTeste);
		return "listapaginacaoteste";
	}

	}

