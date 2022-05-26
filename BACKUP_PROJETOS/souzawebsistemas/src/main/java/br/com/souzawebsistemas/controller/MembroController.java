package br.com.souzawebsistemas.controller;

import java.io.File;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.souzawebsistemas.enums.CargoEnum;
import br.com.souzawebsistemas.enums.Congregacao;
import br.com.souzawebsistemas.enums.EstadoCivil;
import br.com.souzawebsistemas.enums.EstadosBrasileirosEnum;
import br.com.souzawebsistemas.model.Convidado;
import br.com.souzawebsistemas.model.Documentacao;
import br.com.souzawebsistemas.model.Evento;
import br.com.souzawebsistemas.model.Membro;
import br.com.souzawebsistemas.repositories.DocumentacaoRepository;
import br.com.souzawebsistemas.repositories.MembroRepository;
import br.com.souzawebsistemas.service.DocumentacaoService;
import br.com.souzawebsistemas.service.MembroService;
import br.com.souzawebsistemas.service.Relatorios;
@Controller
@RequestMapping("/igrejas")
public class MembroController {
	
	private String pctMembros="souzawebsistemas/souzawebsistemas-cristao/souzawebsistemas-igrejas/membros/";
	private String caminhoImagem="D:\\FOTOMEMBROS\\";
	@Autowired
	private Connection conection;
	
	@Autowired
	private MembroService mService;
	
	@Autowired
	private MembroRepository mRepository;
	
	@Autowired
	private DocumentacaoRepository dRepository;
	
	@Autowired
	private DocumentacaoService docService;
	
	@Autowired
	private Relatorios rService;
	
	public String dataHtml="";

	@GetMapping("/membros")//  http://localhost:8080/clientes/index
	public String index() {
		return "redirect:/igrejas/listarmembros";
	}
	
	@GetMapping("/cadastro-membro")
	public ModelAndView cadastroCliente() {
		ModelAndView md=new ModelAndView(pctMembros+"cadastromembro");
		md.addObject("membro",new Membro());
		md.addObject("listaEstadoCivil",EstadoCivil.values());
		md.addObject("listaCongregacao",Congregacao.values());
		md.addObject("listaEstadoBrasileiro",EstadosBrasileirosEnum.values());
		md.addObject("listaCargo",CargoEnum.values());
		return md;
	}
	@PostMapping("/cadastrarmembro")
	public String salvarMembro(Membro membro,@RequestParam("file")MultipartFile arquivo) {
		
		
		//Date dataBanco=Util.converterStringParaDate(dataHtml);
		mRepository.save(membro);
		if(! arquivo.isEmpty()) {
			try {
				byte[] bytes = arquivo.getBytes();
				String codigoStr=String.valueOf(membro.getCodigo());
				String nomeImagem="MEMBRO_"+codigoStr+"_"+arquivo.getOriginalFilename();
				String nomecompletoimagem=caminhoImagem+nomeImagem;
				Path caminho = Paths.get(nomecompletoimagem);
				
				Files.write(caminho, bytes);
				
				
				
				System.out.println(nomecompletoimagem);
				System.out.println(nomeImagem);
				
				membro.setNomedaimagem(nomeImagem);
				mRepository.save(membro);
				System.out.println(nomecompletoimagem);
				System.out.println(nomeImagem);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	
		return "redirect:/igrejas/listarmembros";
	}
	
	/*
	 * 	@PostMapping("/salvar")
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
	*/
	
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
	
	/*  @PostMapping("/salvar")
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
	  */
	 
	 /* <div class="container">
		<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-12">
					<h2>CADASTRO DE TESTES PARA MOTORISTAS DA ESCOLA SOUZA CURSOS</h2>
				</div>
			</div>
		<form action="/testes/salvar" 
			th:object="${teste}" 
			enctype="multipart/form-data"
			method="POST">
		<div class="row">
				<div class="col-xs-12 col-md-3 col-lg-3"  >
					<label for="codigo">codigo</label>
				</div>
				<div class="col-xs-12 col-md-9 col-lg-9" >
					 <input type="text" name="codigo"
						th:field="*{codigo}" readonly="readonly" class="form-control"
						id="codigo" aria-describedby="codigo" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12 col-md-4 col-lg-4" >
					<label for="nome">Nome do candidato</label> 
					<input type="text" name="nome"
						th:field="*{nome}" class="form-control" id="nome"
						aria-describedby="nome" />
				</div>
				<div class="col-xs-12 col-md-4 col-lg-4" >
					<label for="data">Data do teste</label> 
					<input type="date" name="data"
						th:field="*{data}" class="form-control" id="data"
						aria-describedby="data" />
				</div>
				<div class="col-xs-12 col-md-4 col-lg-4" >
					<label for="data">Data do cadastro</label> 
					<input type="date"
						name="dataCadastro" th:field="*{dataCadastro}"
						class="form-control" id="dataCadastro"
						aria-describedby="dataCadastro" />
				</div>
				</div>
				<div class="row">
				<div class="col-xs-12 col-md-4 col-lg-4" >
					<label for="file">Foto do Candidato</label> 
					<input type="file"
						name="file" 
						class="form-control" id="file"
						aria-describedby="file" />
				</div>
				</div>
				<div class="row">
				<div class="col-xs-12 col-md-3 col-lg-3" >
					<button type="submit" class="btn btn-primary">cadastrar</button>
				</div>
				<div class="col-xs-12 col-md-3 col-lg-3" >
					<a href="http://localhost:8080/testes/listartestes">testes</a>
				</div>
				</div>
				
			</form>
		
	</div>
	
	
	
	    <table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">codigo</th>
     <th scope="col">nome</th>
      <th scope="col">data</th>
      <th scope="col">data cadastro</th>
      <th scope="col">foto</th>
      <th scope="col">ações</th>
    </tr>
  </thead>
  <tbody>
    <tr th:each="t:${testes.content}">
       <td th:text="${t.codigo}"/>
        <td th:text="${t.nome}"/>
       <td th:text="${t.dataStr}"/>
       <td th:text="${#dates.format(t.dataCadastro, 'dd/MM/yyyy')}"></td>
       <td><img th:src="@{/testes/{imagem}/mostrarImagem(imagem=${t.nomeImagem})}" width="50" height="50"/></td>
       <td> 
 	     <a href="#"> 
 	        editar 
 	     </a>
 	      <a href="#">
 	        excluir 
 	     </a>
	  </td> 
    </tr>
   
  </tbody>
</table>
	 * 
	 * */
	 
	@GetMapping("/listarmembros")
	public ModelAndView listarMembros() {
		//List<Membro>membros=mRepository.findAll(PageRequest.of(0, 3,Sort.by("nome")));
		ModelAndView md=new ModelAndView(pctMembros+"listamembros");
		md.addObject("dataMembro",dataHtml);
		md.addObject("membros",mRepository.findAll(PageRequest.of(0, 3,Sort.by("nome"))));
		md.addObject("membro",new Membro());
		return md;
	}
	
	
	@GetMapping("/membrospag")
	public ModelAndView carregarMembrosPorPaginacao(@PageableDefault(size=5)Pageable pageable,
			ModelAndView model,@RequestParam("nome") String nome
			,@RequestParam("naturalidade") String naturalidade) {
		//Page<Cliente> pageCliente = cRepository.findAll(pageable);
		Page<Membro> pageMembro = mRepository.findMembroByNamePage(nome,pageable);
		model.addObject("membros", pageMembro);
		model.addObject("membro", new Membro());
		model.addObject("nome", nome);
		model.addObject("naturalidade", naturalidade);
		///model.addObject(null, pageCliente)
		model.setViewName(pctMembros+"listamembros");
		return model;
	}
	
	//faz a busca por nome e/ou email e retorna o resultado numa lista paginada
		@PostMapping("/buscarporparametros")
	   public ModelAndView testarParametros(@RequestParam("nome")String nome,
			   @RequestParam("naturalidade")String naturalidade,
			   @PageableDefault(size = 3, sort = {"nome"}) Pageable pageable) {
		 //  String email=emailCliente;
		 //  String nome=nomeCliente;
		   int pageNumber = pageable.getPageNumber();
		   Page<Membro> membros = mRepository.findMembroByNameCodigoPage(nome,naturalidade, pageable);
		   System.out.println("parametro recebido "+nome);
		   System.out.println("parametro recebido "+naturalidade);
		   System.out.println("numero de paginas "+pageNumber);
		   System.out.println(membros.getNumberOfElements());
		   ModelAndView modelAndView = new ModelAndView(pctMembros+"listaclientes");
			modelAndView.addObject("membros",membros);
			modelAndView.addObject("membro", new Membro());
			modelAndView.addObject("nome", nome);
			modelAndView.addObject("naturalidade", naturalidade);
		   return modelAndView;
	   }
	//=================================================================
		//FUNCIONA
		
		/*@GetMapping("/clientespag")
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
			model.setViewName("listaclientes");
			return model;
		}*/
		
		
//FUNCIONA
		//faz a busca por nome e/ou email e retorna o resultado numa lista paginada
		/*@PostMapping("/buscarporparametros")
	   public ModelAndView testarParametros(@RequestParam("emailCliente")String emailCliente,
			   @RequestParam("nomeCliente")String nomeCliente,
			   @PageableDefault(size = 3, sort = {"nomeCliente"}) Pageable pageable) {
		   String email=emailCliente;
		   String nome=nomeCliente;
		   int pageNumber = pageable.getPageNumber();
		   Page<Cliente> clientes = cRepository.findClienteByNameEmailPage(nomeCliente, emailCliente, pageable);
		   System.out.println("parametro recebido "+email);
		   System.out.println("parametro recebido "+nome);
		   System.out.println("numero de paginas "+pageNumber);
		   System.out.println(clientes.getNumberOfElements());
		   ModelAndView modelAndView = new ModelAndView("listaclientes");
			modelAndView.addObject("clientes", clientes);
			modelAndView.addObject("cliente", new Cliente());
			modelAndView.addObject("nomeCliente", nomeCliente);
			modelAndView.addObject("emailCliente", emailCliente);
		   return modelAndView;
	   }
      */
		
///============================================================================
		@GetMapping("/relatorios")
		public void geraRelatorios(HttpServletRequest req,HttpServletResponse resp) {
			//String nomeRelatorio="MISSAOEFE_CLIENTE";
			try {
				String nomeRelatorio="IGREJA";
				byte[]bytes=rService.geraRelatorioGenerico(nomeRelatorio);
				resp.setContentType(MediaType.APPLICATION_PDF_VALUE);
				resp.setHeader("content-disposition","inline;filename="+nomeRelatorio+".pdf");
				resp.getOutputStream().write(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
	
	
///================================================================	
	@GetMapping("/{codigo}/selecionarmembro")
	public ModelAndView selecionarMembro(@PathVariable Long codigo) {
		//Optional<Cliente> cliente = service.buscarPorCodigo(codigo);
		Membro membro=mService.buscar(codigo);
		ModelAndView md=new ModelAndView("cadastromembro");
		md.addObject("listaEstadoCivil",EstadoCivil.values());
		md.addObject("listaCongregacao",Congregacao.values());
		md.addObject("listaEstadoBrasileiro",EstadosBrasileirosEnum.values());
		md.addObject("listaCargo",CargoEnum.values());
		md.addObject("membro", membro);
		return md;
}

	//=====================================================
	
	
	private List<Documentacao>listadocumentos=new ArrayList<>();
	//mostrardetalhesdomembro
	@GetMapping("/{codigo}/addDocumentos")
	public ModelAndView mostrarDetalhesMembro(@PathVariable Long codigo) {
		ModelAndView md=new ModelAndView("documentos");
		Membro membro=mService.buscar(codigo);
		md.addObject("membro", membro);
		md.addObject("documentacao",new Documentacao());
		md.addObject("cod",membro.getCodigo());
		
		Iterable<Documentacao> listadocumentos = dRepository.findByMembro(membro);
		md.addObject("listadocumentos", listadocumentos);
	
		
		
		return md;
}
	
	@PostMapping("/{codigo}/cadastrardocumento")
	public String cadastrarDocumento(Documentacao obj,@PathVariable("codigo") long codigo) {
		/*ModelAndView md=new ModelAndView("listamembros");
		Long cod=codigo;
		Membro membro=mService.buscar(cod);
		obj.setRg(obj.getRg());
		obj.setCpf(obj.getCpf());
		obj.setMembro(membro);
		
		docService.cadastrar(obj);
		*/
		
		Membro membro = mRepository.findByCodigo(codigo);
		obj.setMembro(membro);
		dRepository.save(obj);
		//md.addObject("membros",mRepository.findAll(PageRequest.of(0, 3,Sort.by("nome"))));
		//return md;
		
		return "redirect:/igrejas/{codigo}/addDocumentos";
	}
	
	
	///==================================================
	//===================================================
	//======================================================
	
	@RequestMapping(value="/{codigo}/detalhesMembro", method=RequestMethod.GET)
	public ModelAndView detalhesMembro(@PathVariable("codigo") long codigo){
		Membro membro = mRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("documentos");
		mv.addObject("membro", membro);
		
		//Iterable<Documentacao> listadocumentos = dRepository.findByMembro(membro);
		//mv.addObject("listadocumentos", listadocumentos);
		
		return mv;
	}
	
	@RequestMapping(value="/{codigo}/detalhesMembro", method=RequestMethod.POST)
	public String detalhesMembroPost(@PathVariable("codigo") long codigo,  Documentacao doc,  BindingResult result, RedirectAttributes attributes){
		
		Membro membro = mRepository.findByCodigo(codigo);
		doc.setMembro(membro);
		dRepository.save(doc);
		
		//attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	//================================================
	///============================================
}
