package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.GrupoDao;
import daos.OfertaDosVaroesDao;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.OfertaDosVaroes;

@ManagedBean(name="ofertadosvaroesBean")
@ViewScoped
public class OfertaDosVaroesBean {
	private OfertaDosVaroes oferta = new OfertaDosVaroes();
	private OfertaDosVaroesDao ocdao=new OfertaDosVaroesDao();
	private GrupoDao gdao=new GrupoDao();
	private List<OfertaDosVaroes> ofertas = new ArrayList<OfertaDosVaroes>();
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
		
			 ofertas=ocdao.buscarOfertaDosVaroesPorGrupo(1L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		oferta = new OfertaDosVaroes();
		grupos=gdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(1L);
			oferta.setGrupo(grupo);
			
			
			oferta.setValorOfertaVarao(oferta.getValorOfertaVarao());
			oferta.setDataOfertaVarao(oferta.getDataOfertaVarao());
			ocdao.salvarOferta(oferta);
			novo();
			ofertas=ocdao.buscarOfertaDosVaroesPorGrupo(1L);
			
			
			
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

			ofertas=ocdao.buscarOfertaPorData(oferta.getDataOfertaVarao());
			
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

	

	public OfertaDosVaroes getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDosVaroes oferta) {
		this.oferta = oferta;
	}

	public List<OfertaDosVaroes> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaDosVaroes> ofertas) {
		this.ofertas = ofertas;
	}

	public List<OfertaDosVaroes> getofertas() {
		return ofertas;
	}

	public void setofertas(List<OfertaDosVaroes> ofertas) {
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
