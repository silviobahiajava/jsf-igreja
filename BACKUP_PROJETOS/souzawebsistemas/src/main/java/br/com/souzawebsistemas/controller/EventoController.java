package br.com.souzawebsistemas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.Convidado;
import br.com.souzawebsistemas.model.Evento;
import br.com.souzawebsistemas.service.ConvidadoService;
import br.com.souzawebsistemas.service.EventoService;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Autowired
	private EventoService evService;
	
	@Autowired
	private ConvidadoService conService;

	private List<Convidado> convidados=new ArrayList<Convidado>();
	
	
	
	
	//=====================================
	@GetMapping("/index")//  http://localhost:8080/clientes/index
	public String index() {
		return "redirect:/eventos/listareventos";
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroEvento() {
		ModelAndView md=new ModelAndView("cadastroevento");
		md.addObject("evento",new Evento());
		return md;
	}
	
	@PostMapping("/cadastrarcliente")
	public String salvarEvento(Evento evento) {
		evService.cadastrar(evento);
		return "redirect:/eventos/listareventos";
	}
	
	@GetMapping("/listareventos")
	public ModelAndView listarEventos() {
		List<Evento>eventos=evService.buscarTodos();
		ModelAndView md=new ModelAndView("listaeventos");
		md.addObject("eventos",eventos);
		md.addObject("evento",new Evento());
		return md;
	}
	
	@GetMapping("/{codigo}/selecionareventos")
	public ModelAndView selecionarCliente(@PathVariable Long codigo) {
		//Optional<Cliente> cliente = service.buscarPorCodigo(codigo);
		Evento evento=evService.buscar(codigo);
		ModelAndView md=new ModelAndView("cadastroevento");
		md.addObject("evento", evento);
		return md;
}
	
	
	//telefones do cliente
	@GetMapping("/{codigo}/selecionarconvidados")
	public ModelAndView selecionarTelefones(@PathVariable Long codigo) {
		Evento evento=evService.buscar(codigo);
		ModelAndView md=new ModelAndView("cadastroeventos");
		md.addObject("evento", evento);
		return md;
}
	
	
	
	
	@GetMapping("/testar")
	public ModelAndView testar() {
		ModelAndView md=new ModelAndView("listaeventos");
		return md;
	}

	
	
	
	
		@RequestMapping(value="/{codigo}",method=RequestMethod.GET)
		public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo){
		Evento evento=evService.buscar(codigo);
		ModelAndView md=new ModelAndView("evento/detalhesEvento");
		md.addObject("evento",evento);
		Iterable<Convidado>convidados=conService.buscarPorEvento(evento);
		md.addObject("convidados",convidados);
		return md;
		}

		@RequestMapping(value="/{codigo}/selecionarconvidados",method=RequestMethod.POST)
		public String detalhesEventoPost(@PathVariable("codigo") Long codigo,Convidado convidado){
		Evento evento=evService.buscar(codigo);
		convidado.setEvento(evento);
		conService.cadastrar(convidado);
		return "redirect:/{codigo}/selecionarconvidados}";
		}

		public List<Convidado> getConvidados() {
			return convidados;
		}

		public void setConvidados(List<Convidado> convidados) {
			this.convidados = convidados;
		}	
		
		
	}
	

