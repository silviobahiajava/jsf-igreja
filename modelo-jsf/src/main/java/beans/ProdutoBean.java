package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import utils.Utils;
import daos.ClasseDao;
import daos.ProdutoDao;
import entidades.ClasseEscolaDominical;
import entidades.Produto;


@ManagedBean(name="produtoBean")
@ViewScoped
public class ProdutoBean {
	private List<Produto>produtos;
	private Produto produto;
	@PostConstruct
	public void listar() {
		try {
			ProdutoDao pdao=new ProdutoDao();
			produtos=pdao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		produto=new Produto();
		
	}
	public void salvar() {
		try {
			ProdutoDao pdao=new ProdutoDao();
			
			
			pdao.merge(produto);
			produto=new Produto();
			produtos=pdao.listar();
			
			
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
             ProdutoDao pdao=new ProdutoDao();
             pdao.excluir(produto);
			 produtos=pdao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
