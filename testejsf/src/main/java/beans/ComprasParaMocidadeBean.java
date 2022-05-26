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
import entidades.Grupo;

@ManagedBean(name="comprasparaMocidadeBean")
@ViewScoped
public class ComprasParaMocidadeBean {
	private Compra compra = new Compra();
	CompraDao compraDao = new CompraDao();
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Grupo> grupos;
	private String nomeGrupo;
	public void pegarGrupoSelecionado(){
		nomeGrupo=compra.getGrupo().getNome();
		Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}

	@PostConstruct
	public void listar() {
		// try{
		// compras=compraDao.listar();
		// }catch(RuntimeException e){
		// Utils.mostrarMensagemJsfErro("erro ao listar");
		// }
		buscar();
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
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
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
			compras=compraDao.buscarCompraPorGrupo(5L);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
}
