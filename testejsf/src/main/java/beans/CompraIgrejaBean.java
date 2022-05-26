package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraIgrejaDao;
import daos.GrupoDao;
import entidades.Compra;
import entidades.CompraIgreja;
import entidades.Grupo;

@ManagedBean(name="compraIgrejaBean")
@ViewScoped
public class CompraIgrejaBean {
	private CompraIgreja compraIgreja;
	private CompraIgrejaDao cigDao=new CompraIgrejaDao();
	private List<CompraIgreja>comprasDaIgreja;
	private List<Grupo>grupos=new ArrayList<Grupo>();	
	public CompraIgreja getCompraIgreja() {
		return compraIgreja;
	}
	public void setCompraIgreja(CompraIgreja compraIgreja) {
		this.compraIgreja = compraIgreja;
	}
	public List<CompraIgreja> getComprasDaIgreja() {
		return comprasDaIgreja;
	}
	public void setComprasDaIgreja(List<CompraIgreja> comprasDaIgreja) {
		this.comprasDaIgreja = comprasDaIgreja;
	}
	
	@PostConstruct
	public void listar() {
		 try{
		 comprasDaIgreja=cigDao.listar();
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}
	public void novo() {
		compraIgreja = new CompraIgreja();
		GrupoDao grupoDao = new GrupoDao();
		grupos = grupoDao.listar();
	}
	
	public void salvar() {
		try {
			
			if(compraIgreja.isCompraAprazoIgreja()) {
				compraIgreja.setTipoCompra(" compra a prazo");
			}
			if(compraIgreja.isCompraAvistaIgreja()) {
				compraIgreja.setTipoCompra("compra a vista");
			}
			cigDao.salvar(compraIgreja);
			novo();
			comprasDaIgreja=cigDao.listar();
			//compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
}
