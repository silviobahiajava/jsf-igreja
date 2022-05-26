package br.com.souzawebsistemas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.TesteData;
import br.com.souzawebsistemas.repositories.TesteDataRepository;
import br.com.souzawebsistemas.utils.Util;


@Controller
@RequestMapping("/testes")
public class TesteDataController {
  
	@Autowired
	private TesteDataRepository tr;
	
	private static String caminhoImagem="D:\\fotos_clientes\\";
	private String prefixoImagem="";
	@GetMapping("/listartestes")
	public ModelAndView listarClientes() {
		@SuppressWarnings("unchecked")
		//List<TesteData>testes=(List<TesteData>) ;
		ModelAndView md=new ModelAndView("listatestes");
		md.addObject("testes",tr.findAll(PageRequest.of(0, 5,Sort.by("nome"))));
		//md.addObject("teste",new TesteData());
		return md;
	}
	
	
	@GetMapping("/testespag")
	public ModelAndView carregaTestePorPaginacao(@PageableDefault(size = 5) Pageable pageable
			) {
		ModelAndView model=new ModelAndView("listatestes");
		Page<TesteData> pageTeste = tr.findAll(pageable);
		model.addObject("testes", pageTeste);
		model.addObject("teste", new TesteData());
		
		
		return model;
		
		
	}
	
	@GetMapping("/datas")
	public ModelAndView  mostrarData() {
		System.out.println("mostrando datas");
		ModelAndView md=new ModelAndView("datas");
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
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroTeste() {
		ModelAndView md=new ModelAndView("cadastroteste");
		md.addObject("teste",new TesteData());
		return md;
	}
	
	
	@PostMapping("/salvar")
	public ModelAndView  salvarTeste2(TesteData td,@RequestParam("file")MultipartFile arquivo)  {
		System.out.println("mostrando datas");
		ModelAndView md=new ModelAndView("datas");
		String data=Util.localDateToString(td.getData());
		java.util.Date dataCadastro = td.getDataCadastro();
			td.setDataStr(data);
			td.setDataCadastro(dataCadastro);
		System.out.println("data string "+data);
		System.out.println("data string "+dataCadastro);
		tr.save(td);
		if(! arquivo.isEmpty()) {
			try {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagem+String.valueOf(td.getCodigo())+arquivo.getOriginalFilename());
				// prefixoImagem=String.valueOf(td.getCodigo());
				Files.write(caminho, bytes);
				td.setNomeImagem(String.valueOf(td.getCodigo())+arquivo.getOriginalFilename());
				//td.setNomeImagem(arquivo.getOriginalFilename());
				System.out.println(arquivo);
				System.out.println(td.getNomeImagem());
				tr.save(td);
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
		Page<TesteData> page = tr.findAll(PageRequest.of(0,5));
		ModelAndView md=new ModelAndView("listatestes");
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
		
		Page<TesteData> page = tr.findAll(pageable);
		ModelAndView md=new ModelAndView();
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();
		md.addObject("totalPages", totalPages);
		md.addObject("totalElements",totalElements);
		return page.getContent().toString();
	}
	
	}

