package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import daos.GrupoDao;
import daos.OfertaDasCriancasDao;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.OfertaDasCriancas;
import utils.Utils;

@ManagedBean(name="ofertadascriancasBean")
@ViewScoped
public class OfertaDasCriancasBean implements Serializable{

	  
	 
	private static final long serialVersionUID = 1L;
	private OfertaDasCriancas oferta = new OfertaDasCriancas();
	private OfertaDasCriancasDao ocdao=new OfertaDasCriancasDao();
	private GrupoDao gdao=new GrupoDao();
	private List<OfertaDasCriancas> ofertas = new ArrayList<OfertaDasCriancas>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	
/*private String nomeGrupo;
private Long codigoGrupo;

	public void pegarGrupoSelecionado(){
	nomeGrupo=compra.getGrupo().getNome();
		codigoGrupo=compra.getGrupo().getCodigo();
		Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}*/

	@PostConstruct
	public void listar() {
		 try{
		
			 ofertas=ocdao.buscarOfertaDasCriancasPorGrupo(2L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		oferta = new OfertaDasCriancas();
		grupos=gdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(2L);
			oferta.setGrupo(grupo);
			oferta.setValorOfertaCrianca(oferta.getValorOfertaCrianca());
			oferta.setDataOfertaCrianca(oferta.getDataOfertaCrianca());
			ocdao.salvar(oferta);
			ofertas=ocdao.buscarOfertaDasCriancasPorGrupo(2L);
			Utils.mostrarMensagemJsfSucesso("oferta  registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	
	/*public void salvarCompraAVista() {
		contasPagas.setDataPagamento(contasPagas.getDataPagamento());
		contasPagas.setValorPagoParcial(contasPagas.getValorPagoParcial());
		//contasPagasDao.salvar(contasPagas);
		compra.setContasPagas(contasPagas);
		compra.setTipoCompra(TipoCompra.AVISTA);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 ofertas=compraDao.listarofertasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a vista registrada com sucess");
	}
	
	public void savarCompraAPrazo() {
		contasAPagar.setDataVencmento(contasAPagar.getDataVencimento());
		contasAPagar.setValor(contasAPagar.getValor());
		compra.setContaAPagar(contasAPagar);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 ofertas=compraDao.listarofertasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a prazo registrada com sucesso");
	}
	
	*/
	
	/*public void excluir(ActionEvent evento) {
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
			compraDao.excluir(compra);
			ofertas = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra excluida do sistema");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
		grupos = gdao.listar();
	}*/

	

	
	// novo metodo

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void buscar() {
		try {

			ofertas=ocdao.buscarOfertaPorData(oferta.getDataOfertaCrianca());
			
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

	public ContaAPagar getContasAPagar() {
		return contasAPagar;
	}

	public void setContasAPagar(ContaAPagar contasAPagar) {
		this.contasAPagar = contasAPagar;
	}

	public List<ContaAPagar> getListaContasAPagar() {
		return listaContasAPagar;
	}

	public void setListaContasAPagar(List<ContaAPagar> listaContasAPagar) {
		this.listaContasAPagar = listaContasAPagar;
	}

	

	public OfertaDasCriancas getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDasCriancas oferta) {
		this.oferta = oferta;
	}

	public List<OfertaDasCriancas> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaDasCriancas> ofertas) {
		this.ofertas = ofertas;
	}

	public List<OfertaDasCriancas> getofertas() {
		return ofertas;
	}

	public void setofertas(List<OfertaDasCriancas> ofertas) {
		this.ofertas = ofertas;
	}

	

	

	
	
//	public String getNomeGrupo() {
//		return nomeGrupo;
//	}
//
//	public void setNomeGrupo(String nomeGrupo) {
//		this.nomeGrupo = nomeGrupo;
//	}
}
