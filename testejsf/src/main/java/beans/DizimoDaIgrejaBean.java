package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.DizimoDaIgrejaDao;
import daos.GrupoDao;
import daos.MembroDao;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.DizimoDaIgreja;
import entidades.Grupo;
import entidades.Membro;

@ManagedBean(name="dizimodaigrejaBean")
@ViewScoped
public class DizimoDaIgrejaBean {
	private DizimoDaIgreja dizimo = new DizimoDaIgreja();
	private DizimoDaIgrejaDao dzdao=new DizimoDaIgrejaDao();
	private GrupoDao gdao=new GrupoDao();
	private MembroDao membroDao=new MembroDao();
	private List<DizimoDaIgreja> dizimos = new ArrayList<DizimoDaIgreja>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private List<Membro>dizimistas=new ArrayList<Membro>();
	
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
			 dizimos=dzdao.buscarPorTipoDeDizimoECodigo("LANCADO",8L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		
		dizimo=new DizimoDaIgreja();
		grupos=gdao.listar();
		dizimistas=membroDao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			
			dizimo.setGrupo(grupo);
			
			dizimo.setTipodizimo("NAOLANCADO");
			dizimo.setDataDizimoIgreja(dizimo.getDataDizimoIgreja());
			dizimo.setValorDizimoIgreja(dizimo.getValorDizimoIgreja());
			dizimo.setDizimistaIgreja(dizimo.getDizimistaIgreja());
			dzdao.salvarDizimoDaIgreja(dizimo);
			novo();
			 dizimos=dzdao.buscarPorTipoDeDizimoECodigo("NAOLANCADO",8L);
			
			Utils.mostrarMensagemJsfSucesso("dizimo registrado com sucesso");
			Faces.redirect("./igreja/lancamentodizimosdaigreja.xhtml");
		} catch (RuntimeException  e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
*/
	public void editar(ActionEvent evento) {
		dizimo = (DizimoDaIgreja) evento.getComponent().getAttributes().get("dizimoSelecionado");
		grupos = gdao.listar();
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
			
			//dizimos=dzdao.buscarValorDoDizimoPorData(dizimo.getDataDizimoIgreja());
			
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

	public DizimoDaIgreja getDizimo() {
		return dizimo;
	}

	public void setDizimo(DizimoDaIgreja dizimo) {
		this.dizimo = dizimo;
	}

	public MembroDao getMembroDao() {
		return membroDao;
	}

	public void setMembroDao(MembroDao membroDao) {
		this.membroDao = membroDao;
	}

	public List<DizimoDaIgreja> getDizimos() {
		return dizimos;
	}

	public void setDizimos(List<DizimoDaIgreja> dizimos) {
		this.dizimos = dizimos;
	}

	public List<Membro> getDizimistas() {
		return dizimistas;
	}

	public void setDizimistas(List<Membro> dizimistas) {
		this.dizimistas = dizimistas;
	}

	

}
