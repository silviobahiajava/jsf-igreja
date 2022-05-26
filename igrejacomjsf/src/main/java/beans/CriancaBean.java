package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.CriancaDao;
import entidades.Crianca;
import entidades.Filiacao;
import utils.Utils;

@ManagedBean(name="criancaBean")
@ViewScoped
public class CriancaBean {
	private Long codigoCrianca;
	private List<Crianca>criancas;
	private Crianca crianca=new Crianca();
	private Filiacao filiacao=new Filiacao();
	CriancaDao criancaDao;
	
	@PostConstruct
	public void iniciar() {
		try {
			 criancaDao=new CriancaDao();
			filiacao=new Filiacao();
			criancas= criancaDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	
	public void listar() {
		
		criancas=criancaDao.listar();
	}

	
	public void novo(){
		crianca=new Crianca();
		filiacao=new Filiacao();
	}
	
	public void carregarEdicao(){
		
		
		crianca=criancaDao.buscar(codigoCrianca);
		
	}
	
	
	public void salvar() {
		try {

			
			crianca.setNome(crianca.getNome());
			filiacao.setNomeDaMae(filiacao.getNomeDaMae());
			filiacao.setNomeDoPai(filiacao.getNomeDoPai());
			crianca.setFiliacao(filiacao);
			crianca.setEndereco(crianca.getEndereco());
			crianca.setNumeroEndereco(crianca.getNumeroEndereco());
			crianca.setBairro(crianca.getBairro());
			criancaDao.salvar(crianca);
		      
			
			criancas=criancaDao.listar();
			Utils.mostrarMensagemJsfSucesso("criança matriculada  com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar "+erro.getMessage());
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			crianca = (Crianca) evento.getComponent().getAttributes().get("criancaSelecionada");
			CriancaDao criancaDao=new CriancaDao();
			criancaDao.excluir(crianca);
			criancas=criancaDao.listar();
			Utils.mostrarMensagemJsfSucesso("excluído com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		CriancaDao criancaDao=new CriancaDao();
		crianca = (Crianca) evento.getComponent().getAttributes().get("criancaSelecionada");
		crianca.setNome(crianca.getNome());
		filiacao.setNomeDaMae(filiacao.getNomeDaMae());
		filiacao.setNomeDoPai(filiacao.getNomeDoPai());
		crianca.setFiliacao(filiacao);
		crianca.setEndereco(crianca.getEndereco());
		crianca.setNumeroEndereco(crianca.getNumeroEndereco());
		crianca.setBairro(crianca.getBairro());
		criancaDao.editar(crianca);
		
		novo();
		criancas=criancaDao.listar();
		Utils.mostrarMensagemJsfSucesso("registro alterado com sucesso");
	}
	public List<Crianca> getCriancas() {
		return criancas;
	}
	public void setCriancas(List<Crianca> criancas) {
		this.criancas = criancas;
	}
	public Crianca getCrianca() {
		return crianca;
	}
	public void setCrianca(Crianca crianca) {
		this.crianca = crianca;
	}
	public Filiacao getFiliacao() {
		return filiacao;
	}
	public void setFiliacao(Filiacao filiacao) {
		this.filiacao = filiacao;
	}

	public Long getCodigoCrianca() {
		return codigoCrianca;
	}

	public void setCodigoCrianca(Long codigoCrianca) {
		this.codigoCrianca = codigoCrianca;
	}
	
	
}
