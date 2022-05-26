package beans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import utils.Utils;
import daos.EstabelecimentoDeComprasDao;
import entidades.EstabelecimentoDeCompra;
@ManagedBean(name="estabelecimentoBean")
@ViewScoped
public class EstabelecimentoDeComprasBean {
	private List<EstabelecimentoDeCompra>estabelcimentosDeCompras;
	private EstabelecimentoDeCompra estabelecimento=new EstabelecimentoDeCompra();
	@PostConstruct
	public void listar() {
		try {
			EstabelecimentoDeComprasDao edao=new EstabelecimentoDeComprasDao();
			estabelcimentosDeCompras= edao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
	
	
	public void salvar() {
		try {
			EstabelecimentoDeComprasDao edao=new EstabelecimentoDeComprasDao();
			edao.merge(estabelecimento);
			estabelecimento=new EstabelecimentoDeCompra();
			estabelcimentosDeCompras= edao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}
	

	
	public void excluir(ActionEvent evento) {
		try {
			estabelecimento = (EstabelecimentoDeCompra) evento.getComponent().getAttributes().get("estabelecimentoSelecionado");
			EstabelecimentoDeComprasDao edao=new EstabelecimentoDeComprasDao();
			edao.excluir(estabelecimento);
			estabelcimentosDeCompras=edao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		estabelecimento=new EstabelecimentoDeCompra();
	}
	
	
	public void editar(ActionEvent evento){
		estabelecimento = (EstabelecimentoDeCompra) evento.getComponent().getAttributes().get("estabelecimentoSelecionado");
	}
	public List<EstabelecimentoDeCompra> getEstabelcimentosDeCompras() {
		return estabelcimentosDeCompras;
	}
	public void setEstabelcimentosDeCompras(
			List<EstabelecimentoDeCompra> estabelcimentosDeCompras) {
		this.estabelcimentosDeCompras = estabelcimentosDeCompras;
	}
	public EstabelecimentoDeCompra getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(EstabelecimentoDeCompra estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
}
