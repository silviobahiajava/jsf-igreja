package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CompraDao;
import daos.GrupoDao;
import entidades.Compra;
import entidades.ContasPagas;
import entidades.Grupo;
import enums.TipoCompra;

@ManagedBean(name="comprasParaOGrupoDeCriancasBean")
@ViewScoped
public class ComprasParaOGrupoDeCriancasBean {
	private Compra compra = new Compra();
	CompraDao compraDao = new CompraDao();
	private GrupoDao gdao=new GrupoDao();
	private List<Compra> compras = new ArrayList<Compra>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private List<Grupo> grupos;
	private String nomeGrupo;
	private Long codigoGrupo;
	
	public void pegarGrupoSelecionado(){
		nomeGrupo=compra.getGrupo().getNome();
		codigoGrupo=compra.getGrupo().getCodigo();
	Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}

	@PostConstruct
	public void listar() {
		 try{
			 compras=compraDao.listarComprasPorGrupo(codigoGrupo);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		compra = new Compra();
		GrupoDao grupoDao = new GrupoDao();
		grupos = grupoDao.listar();
	}

	public void salvar() {
		try {
			
			compraDao.merge(compra);
			novo();
			 compras=compraDao.listarComprasPorGrupo(codigoGrupo);
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	
	public void salvarCompraAVista() {
		//ContasPagasDao contasPagasDao=new ContasPagasDao();
		CompraDao compraDao=new CompraDao();
		//Compra minhaCompra=compraDao.salvarContasPagas(compra);
		
		contasPagas.setDataPagamento(contasPagas.getDataPagamento());
		contasPagas.setValorPagoParcial(contasPagas.getValorPagoParcial());
		//contasPagasDao.salvar(contasPagas);
		compra.setContasPagas(contasPagas);
		compra.setTipoCompra(TipoCompra.AVISTA);
		Grupo grupopesquisado=gdao.buscar(codigoGrupo);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 compras=compraDao.listarComprasPorGrupo(codigoGrupo);
		Utils.mostrarMensagemJsfSucesso("compra a vista registrada com sucess");
	}
	
	/*public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setNome(nomeGrupo);
			compra.setGrupo(grupo);
			compraDao.merge(compra);
			novo();
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso para o grupo "+compra.getGrupo().getNome());
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}*/
	
	
	public void excluir(ActionEvent evento) {
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("compraSelecionada");
			compraDao.excluir(compra);
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra excluida do sistema");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		compra = (Compra) evento.getComponent().getAttributes().get("compraSelecionada");
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	// novo metodo

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void buscar() {
		try {

			//compras = compraDao.buscarCompraPorData(compra.getDataCompra());
			compras=compraDao.buscarCompraPorGrupo(3L);
			
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

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public Long getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(Long codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	
	
}
