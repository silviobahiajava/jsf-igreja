package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import daos.CompraDao;
import daos.GrupoDao;
import daos.OfertaDao;
import entidades.Compra;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.Oferta;
import enums.TipoCompra;
import utils.Utils;

@ManagedBean(name="ofertaBean")
@ViewScoped
public class OfertaBean {
	private Oferta oferta = new Oferta();
	private OfertaDao ofertaDao = new OfertaDao();
	private GrupoDao gdao=new GrupoDao();
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private List<Grupo> grupos;
private String nomeGrupo;
private Long codigoGrupo;

	public void pegarGrupoSelecionado(){
	nomeGrupo=oferta.getGrupo().getNome();
		codigoGrupo=oferta.getGrupo().getCodigo();
		Utils.mostrarMensagemJsfSucesso("oferta do grupo "+nomeGrupo);
	}

	@PostConstruct
	public void listar() {
		 try{
		 ofertas=ofertaDao.listarOfertasPorGrupo(8L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		oferta = new Oferta();
		GrupoDao grupoDao = new GrupoDao();
		grupos = grupoDao.listar();
	}

	public void salvar() {
		try {
			
			ofertaDao.merge(oferta);
			novo();
			ofertas=ofertaDao.listarOfertasPorGrupo(8L);
			//compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("oferta registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	public void salvarOferta() {
		//ContasPagasDao contasPagasDao=new ContasPagasDao();
		OfertaDao ofertaDao=new OfertaDao();
		//Compra minhaCompra=compraDao.salvarContasPagas(compra);
		
		
		
		
		
		Grupo grupopesquisado=gdao.buscar(codigoGrupo);
		oferta.setGrupo(grupopesquisado);
		ofertaDao.salvar(oferta);
		 ofertas=ofertaDao.listarOfertasPorGrupo(codigoGrupo);
		Utils.mostrarMensagemJsfSucesso("oferta registrada com sucess");
	}
	
	
	
	
	
	public void excluir(ActionEvent evento) {
		try {
			oferta = (Oferta) evento.getComponent().getAttributes().get("ofertaSelecionada");
			ofertaDao.excluir(oferta);
			ofertas = ofertaDao.listarOfertasPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("oferta cancelada do sistema");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		oferta = (Oferta) evento.getComponent().getAttributes().get("ofertaSelecionada");
		grupos = gdao.listar();
	}

	

	
	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void buscar() {
		try {

			//compras = compraDao.buscarCompraPorData(compra.getDataCompra());
			ofertas=ofertaDao.buscarOfertaPorData(oferta.getDataOferta());
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public ContasPagas getContasPagas() {
		return contasPagas;
	}

	public void setContasPagas(ContasPagas contasPagas) {
		this.contasPagas = contasPagas;
	}

	public List<ContasPagas> getListaContasPagas() {
		return listaContasPagas;
	}

	public void setListaContasPagas(List<ContasPagas> listaContasPagas) {
		this.listaContasPagas = listaContasPagas;
	}

	public Long getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(Long codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

//	public String getNomeGrupo() {
//		return nomeGrupo;
//	}
//
//	public void setNomeGrupo(String nomeGrupo) {
//		this.nomeGrupo = nomeGrupo;
//	}
	
	
}
