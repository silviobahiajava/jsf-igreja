package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraDaMissaoDao;
import daos.GrupoDao;
import entidades.CompraDaMissao;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;

@ManagedBean(name="compradamissaoBean")
@ViewScoped
public class CompraDaMissaoBean {
	private CompraDaMissao compra = new CompraDaMissao();
	private CompraDaMissaoDao cmdao=new CompraDaMissaoDao();
	private GrupoDao gdao=new GrupoDao();
	private List<CompraDaMissao> compras = new ArrayList<CompraDaMissao>();
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
		
			 compras=cmdao.buscarCompraDaMissaoPorGrupo(4L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		compra = new CompraDaMissao();
		grupos=gdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(4L);
			compra.setGrupo(grupo);
			
			compra.setCompraAprazoMissao(compra.isCompraAprazoMissao());
			compra.setCompraAvistaMissao(compra.isCompraAvistaMissao());
			compra.setDescricao(compra.getDescricao());
			compra.setValorCompraMissao(compra.getValorCompraMissao());
			compra.setNumeroNota(compra.getNumeroNota());
			compra.setDataCompraMissao(compra.getDataCompraMissao());
			cmdao.salvarCompra(compra);
			novo();
			compras=cmdao.buscarCompraDaMissaoPorGrupo(4L);
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
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

		//	compras = compraDao.buscarCompraPorData(compra.getDataCompra());
			
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

	public CompraDaMissao getCompra() {
		return compra;
	}

	public void setCompra(CompraDaMissao compra) {
		this.compra = compra;
	}

	public List<CompraDaMissao> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraDaMissao> compras) {
		this.compras = compras;
	}

	

	

	
	
//	public String getNomeGrupo() {
//		return nomeGrupo;
//	}
//
//	public void setNomeGrupo(String nomeGrupo) {
//		this.nomeGrupo = nomeGrupo;
//	}
}
