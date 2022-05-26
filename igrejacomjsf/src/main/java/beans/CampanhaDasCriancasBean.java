package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import daos.CampanhaDasCriancasDao;
import daos.GrupoDao;
import daos.MembroDao;
import entidades.CampanhaDasCriancas;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.Membro;
import utils.Utils;

@ManagedBean(name="campanhadascriancasBean")
public class CampanhaDasCriancasBean {
	private CampanhaDasCriancas campanha = new CampanhaDasCriancas();
	private List<CampanhaDasCriancas>lista = new ArrayList<CampanhaDasCriancas>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private List<Membro>contribuintes=new ArrayList<Membro>();
	
	private CampanhaDasCriancasDao cpdao=new CampanhaDasCriancasDao();
	private GrupoDao gdao=new GrupoDao();
	private MembroDao mbdao=new MembroDao();
	
	
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
			 
			 lista=cpdao.buscarCampanhaDasCriancasPorGrupo(3L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		campanha=new CampanhaDasCriancas();
		grupos=gdao.listar();
		contribuintes=mbdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			campanha.setGrupo(grupo);
			campanha.setDataCampanha(campanha.getDataCampanha());
			campanha.setNomeCampanhaDasCriancas(campanha.getNomeCampanhaDasCriancas());
			campanha.setObjetivoCampanhaDasCriancas(campanha.getObjetivoCampanhaDasCriancas());
			campanha.setValorCampanhaDasCriancas(campanha.getValorCampanhaDasCriancas());
			
			
			
			cpdao.salvarCampanhaDasCriancas(campanha);
			novo();
			lista=cpdao.buscarCampanhaPorGrupo(3L);
			Utils.mostrarMensagemJsfSucesso("operação realizada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao realizar esta operação");
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
		 compras=compraDao.listarComprasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a vista registrada com sucess");
	}
	
	public void savarCompraAPrazo() {
		contasAPagar.setDataVencmento(contasAPagar.getDataVencimento());
		contasAPagar.setValor(contasAPagar.getValor());
		compra.setContaAPagar(contasAPagar);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 compras=compraDao.listarComprasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a prazo registrada com sucesso");
	}
	
	*/
	
	/*public void excluir(ActionEvent evento) {
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
			compraDao.excluir(compra);
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra excluida do sistema");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
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

		//	compras = compraDao.buscarCompraPorData(compra.getDataCompra());
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public CampanhaDasCriancas getCampanha() {
		return campanha;
	}

	public void setCampanha(CampanhaDasCriancas campanha) {
		this.campanha = campanha;
	}

	public List<CampanhaDasCriancas> getLista() {
		return lista;
	}

	public void setLista(List<CampanhaDasCriancas> lista) {
		this.lista = lista;
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

	public List<Membro> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Membro> contribuintes) {
		this.contribuintes = contribuintes;
	}
	
	
}
