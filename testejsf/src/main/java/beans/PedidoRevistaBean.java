package beans;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.PedidoRevistaDao;
import daos.ClasseDao;
import daos.GrupoDao;
import daos.MembroDao;
import entidades.ClasseEscolaDominical;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.PedidoRevista;
import entidades.Grupo;
import entidades.Membro;

@ManagedBean(name="PedidoRevistaBean")
@ViewScoped
public class PedidoRevistaBean {
	private PedidoRevista pedidoRevista = new PedidoRevista();
	private PedidoRevistaDao prdao=new PedidoRevistaDao();
	private GrupoDao gdao=new GrupoDao();
	private MembroDao membroDao=new MembroDao();
	private List<PedidoRevista> pedidoRevistas = new ArrayList<PedidoRevista>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private List<Membro>alunos=new ArrayList<Membro>();
	private List<ClasseEscolaDominical>classes=new ArrayList<ClasseEscolaDominical>();
	private ClasseDao cdao=new ClasseDao();
	
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
			 pedidoRevistas=prdao.listar();
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		
		pedidoRevista=new PedidoRevista();
		classes=cdao.listar();
		alunos=membroDao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(7L);
			pedidoRevista.setGrupo(grupo);
			pedidoRevista.setDataPedido(pedidoRevista.getDataPedido());
			pedidoRevista.setDataRecebimento(pedidoRevista.getDataRecebimento());
			pedidoRevista.setMembroRevista(pedidoRevista.getMembroRevista());
			pedidoRevista.setQuantidade(pedidoRevista.getQuantidade());
			pedidoRevista.setValor(pedidoRevista.getValor());
			pedidoRevista.setValorTotal(pedidoRevista.getValorTotal());
		    prdao.salvar(pedidoRevista);
		    novo();
		    pedidoRevistas=prdao.listar();
			
			
			
			
			
			Utils.mostrarMensagemJsfSucesso("pedidoRevista registrado com sucesso");
			Faces.redirect("./escoladominical/revistaescoladominical.xhtml");
		} catch (RuntimeException e) {
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
		pedidoRevista = (PedidoRevista) evento.getComponent().getAttributes().get("pedidoRevistaSelecionado");
		prdao.editar(pedidoRevista);
		pedidoRevistas=prdao.listar();
	}

	
	public void excluir(ActionEvent evento) {
		pedidoRevista = (PedidoRevista) evento.getComponent().getAttributes().get("pedidoRevistaSelecionado");
		prdao.excluir(pedidoRevista);
		pedidoRevistas=prdao.listar();
	}

	public void calcularValorTotal(){
		pedidoRevista.setValorTotal(
			pedidoRevista.getValor().multiply(
											new BigDecimal(
															pedidoRevista.getQuantidade()
														  )
											)
									);
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
			
			//pedidoRevistas=dzdao.buscarValorDopedidoRevistaPorData(pedidoRevista.getDatapedidoRevistaIgreja());
			
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

	public PedidoRevista getpedidoRevista() {
		return pedidoRevista;
	}

	public void setpedidoRevista(PedidoRevista pedidoRevista) {
		this.pedidoRevista = pedidoRevista;
	}

	public MembroDao getMembroDao() {
		return membroDao;
	}

	public void setMembroDao(MembroDao membroDao) {
		this.membroDao = membroDao;
	}

	public List<PedidoRevista> getpedidoRevistas() {
		return pedidoRevistas;
	}

	public void setpedidoRevistas(List<PedidoRevista> pedidoRevistas) {
		this.pedidoRevistas = pedidoRevistas;
	}

	public List<Membro> getalunos() {
		return alunos;
	}

	public void setalunos(List<Membro> alunos) {
		this.alunos = alunos;
	}

	public List<Membro> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Membro> alunos) {
		this.alunos = alunos;
	}

	public List<ClasseEscolaDominical> getClasses() {
		return classes;
	}

	public void setClasses(List<ClasseEscolaDominical> classes) {
		this.classes = classes;
	}

	

}
