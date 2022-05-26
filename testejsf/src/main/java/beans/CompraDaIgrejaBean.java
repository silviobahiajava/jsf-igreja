package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraIgrejaDao;
import daos.GrupoDao;
import entidades.CompraIgreja;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;

@ManagedBean(name="compraDaIgrejaBean")
@ViewScoped
public class CompraDaIgrejaBean {
	private CompraIgreja compra = new CompraIgreja();
	private CompraIgrejaDao cigdao=new CompraIgrejaDao();
	private GrupoDao gdao=new GrupoDao();
	private List<CompraIgreja> compras = new ArrayList<CompraIgreja>();
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
		
			 //compras=cigdao.listarComprasPorGrupo(8L);
			 compras=cigdao.buscarCompraDaIgrejaPorGrupo(8L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		compra = new CompraIgreja();
		grupos=gdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			compra.setGrupo(grupo);
			compra.setCompraAprazoIgreja(compra.isCompraAprazoIgreja());
			compra.setCompraAvistaIgreja(compra.isCompraAvistaIgreja());
			compra.setDescricao(compra.getDescricao());
			compra.setValorCompraIgreja(compra.getValorCompraIgreja());
			compra.setNumeroNota(compra.getNumeroNota());
			compra.setDataCompraIgreja(compra.getDataCompraIgreja());
			cigdao.salvarCompra(compra);
			novo();
			compras=cigdao.listarComprasPorGrupo(8L);
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

	public CompraIgreja getCompra() {
		return compra;
	}

	public void setCompra(CompraIgreja compra) {
		this.compra = compra;
	}

	public List<CompraIgreja> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraIgreja> compras) {
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
