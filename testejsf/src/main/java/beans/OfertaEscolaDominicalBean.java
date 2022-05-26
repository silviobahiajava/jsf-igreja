package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.OfertaEscolaDominicalDao;
import daos.ClasseDao;
import daos.GrupoDao;
import daos.MembroDao;
import entidades.ClasseEscolaDominical;
import entidades.ContaAPagar;
import entidades.ContasPagas;

import entidades.Grupo;
import entidades.Membro;
import entidades.OfertaEscolaDominical;

@ManagedBean(name="ofertaescoladominicalBean")
@ViewScoped
public class OfertaEscolaDominicalBean {
	private OfertaEscolaDominical oferta = new OfertaEscolaDominical();
	private OfertaEscolaDominicalDao odao=new OfertaEscolaDominicalDao();
	private GrupoDao gdao=new GrupoDao();
	private ClasseDao cdao=new ClasseDao();
	private List<OfertaEscolaDominical> ofertas = new ArrayList<OfertaEscolaDominical>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private List<ClasseEscolaDominical>classesDaEscolaDominical=new ArrayList<ClasseEscolaDominical>();
	
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
			 
			 ofertas=odao.buscarOfertaEscolaDominicalPorGrupo(7L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		
		oferta=new OfertaEscolaDominical();
		grupos=gdao.listar();
		classesDaEscolaDominical=cdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(7L);
			oferta.setGrupo(grupo);
			oferta.setDataOfertaEescolaDominical(oferta.getDataOfertaEescolaDominical());
			oferta.setValorOfertaEscolaDominical(oferta.getValorOfertaEscolaDominical());
			oferta.setClasse(oferta.getClasse());
			odao.salvar(oferta);
			novo();
			ofertas=odao.buscarOfertaEscolaDominicalPorGrupo(7L);
			Utils.mostrarMensagemJsfSucesso("oferta registrado com sucesso");
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
*/
	public void editar(ActionEvent evento) {
		oferta = (OfertaEscolaDominical) evento.getComponent().getAttributes().get("ofertaSelecionada");
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
			
			//ofertas=dzdao.buscarValorDoofertaPorData(oferta.getDataofertaIgreja());
			
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

	public OfertaEscolaDominical getoferta() {
		return oferta;
	}

	public void setoferta(OfertaEscolaDominical oferta) {
		this.oferta = oferta;
	}

	

	public List<OfertaEscolaDominical> getofertas() {
		return ofertas;
	}

	public void setofertas(List<OfertaEscolaDominical> ofertas) {
		this.ofertas = ofertas;
	}

	public OfertaEscolaDominical getOferta() {
		return oferta;
	}

	public void setOferta(OfertaEscolaDominical oferta) {
		this.oferta = oferta;
	}

	public List<OfertaEscolaDominical> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaEscolaDominical> ofertas) {
		this.ofertas = ofertas;
	}

	public List<ClasseEscolaDominical> getClassesDaEscolaDominical() {
		return classesDaEscolaDominical;
	}

	public void setClassesDaEscolaDominical(List<ClasseEscolaDominical> classesDaEscolaDominical) {
		this.classesDaEscolaDominical = classesDaEscolaDominical;
	}

	

	
}
