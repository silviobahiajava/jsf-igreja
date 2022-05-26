package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CategoriaDeMovimentacaoDao;
import entidades.CategoriaDeMovimentacao;

@ManagedBean(name="categoriaBean")
@ViewScoped
public class CategoriaMovimentacaoFinanceiraBean {
	private List<CategoriaDeMovimentacao>categoriasDeMovimentacoFinanceira;
	private CategoriaDeMovimentacao categoriaMovimentacoFinanceira;
	@PostConstruct
	public void listar() {
		try {
			CategoriaDeMovimentacaoDao cmdao=new CategoriaDeMovimentacaoDao();
			categoriasDeMovimentacoFinanceira=cmdao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		categoriaMovimentacoFinanceira=new CategoriaDeMovimentacao();
	}
	
	
	
	public void salvar() {
		try {
			CategoriaDeMovimentacaoDao cmdao=new CategoriaDeMovimentacaoDao();
			
			cmdao.merge(categoriaMovimentacoFinanceira);
			categoriasDeMovimentacoFinanceira=cmdao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}
  
	
	public void excluir(ActionEvent evento) {
		try {
			categoriaMovimentacoFinanceira = (CategoriaDeMovimentacao) evento.getComponent().getAttributes().get("categoriaSelecionado");
			CategoriaDeMovimentacaoDao cmdao=new CategoriaDeMovimentacaoDao();
			cmdao.excluir(categoriaMovimentacoFinanceira);
			categoriasDeMovimentacoFinanceira=cmdao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		categoriaMovimentacoFinanceira = (CategoriaDeMovimentacao) evento.getComponent().getAttributes().get("categoriaSelecionado");
	}
	public List<CategoriaDeMovimentacao> getCategoriasDeMovimentacoFinanceira() {
		return categoriasDeMovimentacoFinanceira;
	}
	public void setCategoriasDeMovimentacoFinanceira(
			List<CategoriaDeMovimentacao> categoriasDeMovimentacoFinanceira) {
		this.categoriasDeMovimentacoFinanceira = categoriasDeMovimentacoFinanceira;
	}
	public CategoriaDeMovimentacao getCategoriaMovimentacoFinanceira() {
		return categoriaMovimentacoFinanceira;
	}
	public void setCategoriaMovimentacoFinanceira(
			CategoriaDeMovimentacao categoriaMovimentacoFinanceira) {
		this.categoriaMovimentacoFinanceira = categoriaMovimentacoFinanceira;
	}
	
	
}
