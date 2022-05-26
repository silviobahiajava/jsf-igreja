package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ContaAPagarDao;
import entidades.ContaAPagar;
import utils.Utils;

@ManagedBean(name="contaAPagarBean")
@ViewScoped
public class ContaAPagarBean {
	private ContaAPagar contaAPagar=new ContaAPagar();;
	private List<ContaAPagar>listaContasAPagar;
	private ContaAPagarDao contaAPagarDao=new ContaAPagarDao();
	
	@PostConstruct
	public void listar() {
		try {
			
			listaContasAPagar=contaAPagarDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		contaAPagar=new ContaAPagar();
	}

	public ContaAPagar getContaAPagar() {
		return contaAPagar;
	}

	public void setContaAPagar(ContaAPagar contaAPagar) {
		this.contaAPagar = contaAPagar;
	}

	public List<ContaAPagar> getListaContasAPagar() {
		return listaContasAPagar;
	}

	public void setListaContasAPagar(List<ContaAPagar> listaContasAPagar) {
		this.listaContasAPagar = listaContasAPagar;
	}
	

	public void salvar() {
		try {
			contaAPagarDao.merge(contaAPagar);
			contaAPagar=new ContaAPagar();
			listaContasAPagar=contaAPagarDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			contaAPagar = (ContaAPagar) evento.getComponent().getAttributes().get("contaAPagarSelecionada");
			contaAPagarDao.excluir(contaAPagar);
			listaContasAPagar=contaAPagarDao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		contaAPagar = (ContaAPagar) evento.getComponent().getAttributes().get("contaAPagarSelecionada");
	}
	
	
	
	
}
