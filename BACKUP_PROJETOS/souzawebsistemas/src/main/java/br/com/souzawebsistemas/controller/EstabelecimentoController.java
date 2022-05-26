package br.com.souzawebsistemas.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.Documentacao;
import br.com.souzawebsistemas.model.Estabelecimento;
import br.com.souzawebsistemas.repositories.EstabelecimentoRepository;
import br.com.souzawebsistemas.service.EstabelecimentoService;
import br.com.souzawebsistemas.service.Relatorios;

@Controller
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
	private String pctEstabelecimentos="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lojasvirtuais/usuarios/";
	
	@Autowired
	private Connection conection;
	
	private String caminhoImagem="D:\\ESTABELECIMENTOS\\";
	@Autowired
	private EstabelecimentoService service;
	
	@Autowired
	private Relatorios rService;
	
	/*@Autowired
	private TelefoneService tService;

	@Autowired
	 private DocumentacaoService dService;
	*/
	@Autowired
	private EstabelecimentoRepository eRepository;
	
	//private List<Telefone> telefonescliente=new ArrayList<Telefone>();
	private List<Documentacao> documentoscliente=new ArrayList<Documentacao>();
	
	
	
	//=====================================
	
	@GetMapping("/conexao")
	 public ModelAndView testarConexao() {
		 String resposta=conection !=null? "conectado":"erro ao conectar";
		 System.out.println(resposta);
		 ModelAndView md=new ModelAndView("conexao");
		 md.addObject("resposta",resposta);
		 
		 return md;
	 }
	
	@GetMapping("/index")//    http://localhost:8080/estabelecimentos/index
	public String index() {
		return "redirect:/estabelecimentos/listarestabelecimentos";  //http://localhost:8080/estabelecimentos/listarestabelecimentos
	}
	
	
	
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroEstabelecimento() {
		ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastroloja");
		md.addObject("estabelecimento",new Estabelecimento());
		return md;
	}
	//=========================================
	
	/*@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView md=new ModelAndView("cadastromembro");
		return md;
	}*/
	
	
	//=======================================
	
	@PostMapping("/cadastrar")
	public String salvarEstabelecimento(Estabelecimento estabelecimento,@RequestParam("file")MultipartFile arquivo) {
		service.cadastrar(estabelecimento);
		if(! arquivo.isEmpty()) {
			try {
				byte[] bytes = arquivo.getBytes();
				String codigoStr=String.valueOf(estabelecimento.getCodigo());
				String nomeImagem="ESTABELECIMENTO_"+codigoStr+"_"+arquivo.getOriginalFilename();
				String nomecompletoimagem=caminhoImagem+nomeImagem;
				Path caminho = Paths.get(nomecompletoimagem);
				
				Files.write(caminho, bytes);
				
				
				
				System.out.println(nomecompletoimagem);
				System.out.println(nomeImagem);
				
				estabelecimento.setNomedaimagem(nomeImagem);
				eRepository.save(estabelecimento);
				System.out.println(nomecompletoimagem);
				System.out.println(nomeImagem);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}
		return "redirect:/estabelecimentos/listarestabelecimentos";
	}
	//======================================================
	
	@GetMapping("/relatorios")
	public void geraRelatorios(HttpServletRequest req,HttpServletResponse resp) {
		//String nomeRelatorio="MISSAOEFE_CLIENTE";
		try {
			String nomeRelatorio="MISSAOEFE_CLIENTE01";
			byte[]bytes=rService.geraRelatorioGenerico(nomeRelatorio);
			resp.setContentType(MediaType.APPLICATION_PDF_VALUE);
			resp.setHeader("content-disposition","inline;filename="+nomeRelatorio+".pdf");
			resp.getOutputStream().write(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{codigo}/detalhesEstabelecimento", method=RequestMethod.GET)
	public ModelAndView detalhesEstabelecimento(@PathVariable("codigo") long codigo){
		Estabelecimento estabelecimento = eRepository.getById(codigo);
		ModelAndView mv = new ModelAndView("detalhesloja");
		mv.addObject("estabelecimento",estabelecimento);
		
		return mv;
	}
	
	
	@GetMapping("/{codigo}/selecionarEstabelecimento")
	public ModelAndView selecionarMembro(@PathVariable Long codigo) {
		//Optional<Cliente> cliente = service.buscarPorCodigo(codigo);
		Estabelecimento estabelecimento=service.buscar(codigo);
		ModelAndView md=new ModelAndView("cadastroloja");
		/*md.addObject("listaEstadoCivil",EstadoCivil.values());
		md.addObject("listaCongregacao",Congregacao.values());
		md.addObject("listaEstadoBrasileiro",EstadosBrasileirosEnum.values());
		md.addObject("listaCargo",CargoEnum.values());
		*/
		md.addObject("estabelecimento", estabelecimento);
		return md;
}
	
	
	@GetMapping("/listarestabelecimentos")
	public ModelAndView listarEstabelecimentos() {
		ModelAndView md=new ModelAndView(pctEstabelecimentos+"listaloja");
		///List<Cliente>fregueses=service.buscarTodos();
		//md.addObject("clientes",fregueses);
		md.addObject("estabelecimentos",eRepository.findAll(PageRequest.of(0, 3,Sort.by("nomeFantasia"))));
		md.addObject("estabelecimento",new Estabelecimento());
		return md;
	}
	
	@GetMapping("/estabelecimentospag")
	public ModelAndView carregarestabelecimentosPorPaginacao(@PageableDefault(size=5)Pageable pageable,
			ModelAndView model,@RequestParam("nomeFantasia") String nomeFantasia
			,@RequestParam("razaoSocial") String razaoSocial) {
		//Page<Cliente> pageCliente = cRepository.findAll(pageable);
		Page<Estabelecimento> pageEstabelecimentos = eRepository.findClienteByNamePage(nomeFantasia,pageable);
		model.addObject("estabelecimentos", pageEstabelecimentos);
		model.addObject("estabelecimento", new Estabelecimento());
		model.addObject("nomeFantasia", nomeFantasia);
		model.addObject("razaoSocial", razaoSocial);
		///model.addObject(null, pageCliente)
		model.setViewName(pctEstabelecimentos+"listaestabelecimentos");
		return model;
	}
	
	

	//faz a busca por nome e/ou email e retorna o resultado numa lista paginada
	@PostMapping("/buscarporparametros")
   public ModelAndView testarParametros(
		   @RequestParam("nomeFantasia")String nomeFantasia,
		   @RequestParam("razaoSocial")String razaoSocial,
		   @PageableDefault(size = 3, sort = {"nomeFantasia"}) Pageable pageable) {
		
	   String nomeF=nomeFantasia;
	   String razaoS=razaoSocial;
	   int pageNumber = pageable.getPageNumber();
	   Page<Estabelecimento> estabelecimentos = eRepository.findClienteByNameEmailPage(nomeF, razaoS, pageable);
	   System.out.println("parametro recebido "+nomeF);
	   System.out.println("parametro recebido "+razaoSocial);
	   System.out.println("numero de paginas "+pageNumber);
	   System.out.println(estabelecimentos.getNumberOfElements());
	   ModelAndView modelAndView = new ModelAndView(pctEstabelecimentos+"listaestabelecimentos");
		modelAndView.addObject("estabelecimentos",estabelecimentos);
		modelAndView.addObject("estabelecimento", new Estabelecimento());
		modelAndView.addObject("nomeFantasia", nomeFantasia);
		modelAndView.addObject("razaoSocial", razaoSocial);
	   return modelAndView;
   }
	
	
	//==================================================
	@GetMapping("/{codigo}/selecionarestabelecimentos")
	public ModelAndView selecionarEstabelecimento(@PathVariable Long codigo) {
		//Optional<Cliente> cliente = service.buscarPorCodigo(codigo);
		Estabelecimento estabelecimento=service.buscar(codigo);
		ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastroloja");
		md.addObject("estabelecimento",estabelecimento);
		return md;
}
	
	
	//telefones do cliente
	@GetMapping("/{codigo}/selecionartelefones")
	public ModelAndView selecionarTelefones(@PathVariable Long codigo) {
		Estabelecimento cliente=service.buscar(codigo);
		ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastrotelefones");
		md.addObject("cliente", cliente);
		return md;
}
	
	// carregar pagina com os documentos do cliente
		@GetMapping("/{codigo}/selecionardocumentos")
		public ModelAndView selecionarDocumentos(@PathVariable Long codigo) {
			Estabelecimento cliente=service.buscar(codigo);
			ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastrodocumentos");
			md.addObject("cliente", cliente);
			md.addObject("documentacao", new Documentacao());
			return md;
	}
		
	
		//telefones do cliente
				@PostMapping("/{codigo}/addDocumento")
				public String addDocumentacao( Documentacao doc, Long codigo) {
				  documentoscliente.add(doc);
				  System.out.println("adicionando documentos..");
				  ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastrodocumentos");
				  md.addObject("documentoscliente",documentoscliente);
				  
				  return "";
				 
			}
			
	
	
	
	@GetMapping("/testar")
	public ModelAndView testar() {
		ModelAndView md=new ModelAndView(pctEstabelecimentos+"listaloja");
		return md;
	}

	
	
	
	//telefones do cliente
/*	@PostMapping("/{codigo}/adicionartelefonedocliente")
	public ModelAndView adicionarTeledoneCliente(@ModelAttribute Telefone telefone,@PathVariable Long codigo) {
		Estabelecimento cliente=service.buscar(codigo);
	  List<Telefone>telefonescliente=new ArrayList<>();
	  telefone.setEstabelecimentos(cliente);
	  telefonescliente.add(telefone);
	 */
	/*	ModelAndView md=new ModelAndView(pctEstabelecimentos+"cadastrotelefones");
		//Iterable<Telefone>telefones=tservice.buscarTelefonesPorCliente(cliente);
		md.addObject("cliente",cliente);
		md.addObject("telefones",telefonescliente);
		System.out.println("adicionando telefones...");
		
		return md;
}
*/
	
	//telefones do cliente
	/*	@PostMapping("/{codigo}/addTelefone")
		public String addTelefone( Telefone telefone, Long codigo) {
		  telefonescliente.add(telefone);
		  ModelAndView md=new ModelAndView(pctClientes+"cadastrotelefones");
		  System.out.println("adicionando telefones...");
			for (Telefone tel : telefonescliente) {
				  tService.cadastrar(tel);
				md.addObject("telefones",telefonescliente);
				 md=new ModelAndView(pctClientes+"cadastrotelefones");
				System.out.println(tel.getNumero()+" "+tel.getTipo()+"\n");
			}
			
			Cliente cliente=service.buscar(codigo);
			telefone.setCliente(cliente);
			tService.cadastrar(telefone);
			return "redirect:/{codigo}/addTelefone";
	}
	*/
		/*@RequestMapping(value="/{codigo}",method=RequestMethod.GET)
		public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo){
		Evento evento=evService.buscar(codigo);
		ModelAndView md=new ModelAndView("evento/detalhesEvento");
		md.addObject("evento",evento);
		Iterable<Convidado>convidados=.buscarPorEvento(evento);
		md.addObject("convidados",convidados);
		return md;
		}
		*/

		/*@RequestMapping(value="/{codigo}/selecionarconvidados",method=RequestMethod.POST)
		public String detalhesEventoPost(@PathVariable("codigo") Long codigo,Convidado convidado){
		Evento evento=evService.buscar(codigo);
		convidado.setEvento(evento);
		conService.cadastrar(convidado);
		return "redirect:/{codigo}/selecionarconvidados}";
		}*/

		
		
	}
	

