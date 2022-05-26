package br.com.souzawebsistemas.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.Cliente;
import br.com.souzawebsistemas.model.Documentacao;
import br.com.souzawebsistemas.model.Telefone;
import br.com.souzawebsistemas.repositories.ClienteRepository;
import br.com.souzawebsistemas.service.ClienteService;
import br.com.souzawebsistemas.service.DocumentacaoService;
import br.com.souzawebsistemas.service.Relatorios;
import br.com.souzawebsistemas.service.TelefoneService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	private String pctClientes="souzawebsistemas/souzawebsistemas-comercial/souzawebsistemas-lojasvirtuais/clientes/";
	
	@Autowired
	private Connection conection;
	
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private Relatorios rService;
	
	@Autowired
	private TelefoneService tService;

	@Autowired
	 private DocumentacaoService dService;
	
	@Autowired
	private ClienteRepository cRepository;
	
	private List<Telefone> telefonescliente=new ArrayList<Telefone>();
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
	
	@GetMapping("/index")//  http://localhost:8080/clientes/index
	public String index() {
		return "redirect:/clientes/listarclientes";
	}
	
	
	
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroCliente() {
		ModelAndView md=new ModelAndView(pctClientes+"cadastrocliente");
		md.addObject("cliente",new Cliente());
		return md;
	}
	//=========================================
	
	/*@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView md=new ModelAndView("cadastromembro");
		return md;
	}*/
	
	
	//=======================================
	
	@PostMapping("/cadastrarcliente")
	public String salvarCliente(Cliente cliente) {
		service.cadastrar(cliente);
		return "redirect:/clientes/listarclientes";
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
	
	
	
	
	
	
	
	@GetMapping("/listarclientes")
	public ModelAndView listarClientes() {
		ModelAndView md=new ModelAndView(pctClientes+"listaclientes");
		///List<Cliente>fregueses=service.buscarTodos();
		//md.addObject("clientes",fregueses);
		md.addObject("clientes",cRepository.findAll(PageRequest.of(0, 3,Sort.by("nomeCliente"))));
		md.addObject("cliente",new Cliente());
		return md;
	}
	
	@GetMapping("/clientespag")
	public ModelAndView carregarClientesPorPaginacao(@PageableDefault(size=5)Pageable pageable,
			ModelAndView model,@RequestParam("nomeCliente") String nomeCliente
			,@RequestParam("emailCliente") String emailCliente) {
		//Page<Cliente> pageCliente = cRepository.findAll(pageable);
		Page<Cliente> pageCliente = cRepository.findClienteByNamePage(nomeCliente,pageable);
		model.addObject("clientes", pageCliente);
		model.addObject("cliente", new Cliente());
		model.addObject("nomeCliente", nomeCliente);
		model.addObject("emailCliente", emailCliente);
		///model.addObject(null, pageCliente)
		model.setViewName(pctClientes+"listaclientes");
		return model;
	}
	
	

	//faz a busca por nome e/ou email e retorna o resultado numa lista paginada
	@PostMapping("/buscarporparametros")
   public ModelAndView testarParametros(
		   @RequestParam("nomeCliente")String nomeCliente,
		   @RequestParam("emailCliente")String emailCliente,
		   @PageableDefault(size = 3, sort = {"nomeCliente"}) Pageable pageable) {
	   String email=emailCliente;
	   String nome=nomeCliente;
	   int pageNumber = pageable.getPageNumber();
	   Page<Cliente> clientes = cRepository.findClienteByNameEmailPage(nomeCliente, emailCliente, pageable);
	   System.out.println("parametro recebido "+email);
	   System.out.println("parametro recebido "+nome);
	   System.out.println("numero de paginas "+pageNumber);
	   System.out.println(clientes.getNumberOfElements());
	   ModelAndView modelAndView = new ModelAndView(pctClientes+"listaclientes");
		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("cliente", new Cliente());
		modelAndView.addObject("nomeCliente", nomeCliente);
		modelAndView.addObject("emailCliente", emailCliente);
	   return modelAndView;
   }
	
	
	//==================================================
	@GetMapping("/{codigo}/selecionarclientes")
	public ModelAndView selecionarCliente(@PathVariable Long codigo) {
		//Optional<Cliente> cliente = service.buscarPorCodigo(codigo);
		Cliente cliente=service.buscar(codigo);
		ModelAndView md=new ModelAndView(pctClientes+"cadastrocliente");
		md.addObject("cliente", cliente);
		return md;
}
	
	
	//telefones do cliente
	@GetMapping("/{codigo}/selecionartelefones")
	public ModelAndView selecionarTelefones(@PathVariable Long codigo) {
		Cliente cliente=service.buscar(codigo);
		ModelAndView md=new ModelAndView(pctClientes+"cadastrotelefones");
		md.addObject("cliente", cliente);
		return md;
}
	
	// carregar pagina com os documentos do cliente
		@GetMapping("/{codigo}/selecionardocumentos")
		public ModelAndView selecionarDocumentos(@PathVariable Long codigo) {
			Cliente cliente=service.buscar(codigo);
			ModelAndView md=new ModelAndView(pctClientes+"cadastrodocumentos");
			md.addObject("cliente", cliente);
			md.addObject("documentacao", new Documentacao());
			return md;
	}
		
	
		//telefones do cliente
				@PostMapping("/{codigo}/addDocumento")
				public String addDocumentacao( Documentacao doc, Long codigo) {
				  documentoscliente.add(doc);
				  System.out.println("adicionando documentos..");
				  ModelAndView md=new ModelAndView(pctClientes+"cadastrodocumentos");
				  md.addObject("documentoscliente",documentoscliente);
				  
				  return "";
				 
			}
			
	
	
	
	@GetMapping("/testar")
	public ModelAndView testar() {
		ModelAndView md=new ModelAndView(pctClientes+"listaclientes");
		return md;
	}

	
	
	
	//telefones do cliente
	@PostMapping("/{codigo}/adicionartelefonedocliente")
	public ModelAndView adicionarTeledoneCliente(@ModelAttribute Telefone telefone,@PathVariable Long codigo) {
	  Cliente cliente=service.buscar(codigo);
	  List<Telefone>telefonescliente=new ArrayList<>();
	  telefone.setCliente(cliente);
	  telefonescliente.add(telefone);
	 
		ModelAndView md=new ModelAndView(pctClientes+"cadastrotelefones");
		//Iterable<Telefone>telefones=tservice.buscarTelefonesPorCliente(cliente);
		md.addObject("cliente",cliente);
		md.addObject("telefones",telefonescliente);
		System.out.println("adicionando telefones...");
		
		return md;
}
	
	//telefones do cliente
		@PostMapping("/{codigo}/addTelefone")
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
	

