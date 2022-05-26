package beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import daos.GrupoDao;
import daos.MembroDao;
import daos.ReceitaDao;
import entidades.Dizimo;
import entidades.Grupo;
import entidades.Membro;
import entidades.Oferta;
import entidades.Receita;
import utils.Utils;


@ManagedBean(name="receitaBean")
@ViewScoped
public class ReceitaBean {
	private List<Receita>receitas;
	private Receita receita;
	private Dizimo dizimo;
	private Oferta oferta;
	private String nomecliente;
	private String livrocaixa;
	private List<Membro>dizimistas=new ArrayList<Membro>();
	private MembroDao membroDao=new MembroDao();
	private ReceitaDao rdao=new ReceitaDao();
	
;	@PostConstruct
public void init(){
	/*Map<String, String> meumapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	nomecliente=meumapa.get("groupkey");
	switch(nomecliente){
	case "1":
		livrocaixa="Livro Caixa do Grupo de Varões";
		break;
	case "2":
		livrocaixa="Livro Caixa do Círculo de Oração";
		break;
	case "3":
		livrocaixa="Livro Caixa do Grupo de Crianças";
		break;
	case "4":
		livrocaixa="Livro Caixa do Grupo de Adolescentes";
		break;
	case "5":
		livrocaixa="Livro Caixa da Mocidade";
		break;
	case "6":
		livrocaixa="Livro Caixa do Grupo de Coreografia Adulto";
		break;
	case "7":
		livrocaixa="Livro Caixa da igreja";
		break;
		default:
			livrocaixa="indefinido";
	}
	listarReceita();
}
public void listarReceita(){
	;
	
receitas=rdao.buscarReceitaPorCodigo(Long.parseLong(nomecliente));
	
	
	for(Receita r:receitas){
		System.out.println("descricao "+r.getDescricaoDaReceita()+"\ntipo de receita "+r.getTipoDeReceita().getDescricao()+"\nvalor"+r.getValorDaReceita());
	}
	*/
}
	public void novo(){
		receita=new Receita();
		dizimistas=membroDao.listar();
	}
	
	
	
	public void salvar() {
		try {
			GrupoDao grupoDao=new GrupoDao();
			Grupo grupo=grupoDao.buscar(Long.parseLong(nomecliente));
			receita.setGrupo(grupo);
			
			rdao.merge(receita);
			
			receita=new Receita();
			
			receitas=rdao.listar();
			
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		}
	}

	
	public void excluir(ActionEvent evento) {
		try {
			receita = (Receita) evento.getComponent().getAttributes().get("receitaSelecionada");
             rdao=new ReceitaDao();
			 rdao.excluir(receita);
			receitas=rdao.listar();
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("não foi possível excluir");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		receita =(Receita) evento.getComponent().getAttributes().get("receitaSelecionada");
	}
	public List<Receita> getReceitas() {
		return receitas;
	}
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}
	public Receita getReceita() {
		return receita;
	}
	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	public Dizimo getDizimo() {
		return dizimo;
	}
	public void setDizimo(Dizimo dizimo) {
		this.dizimo = dizimo;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public List<Membro> getDizimistas() {
		return dizimistas;
	}
	public void setDizimistas(List<Membro> dizimistas) {
		this.dizimistas = dizimistas;
	}
	
	public void buscar() {
		try {

			receitas = rdao.buscarReceitaPorData(receita.getDataLancamento());
			for (Receita receita : receitas) {
				System.out.println("descricao "+receita.getDescricaoDaReceita()+"\ntipo "+receita.getTipoDeReceita().getDescricao()+"\nvalor "+receita.getValorDaReceita());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	public String getNomecliente() {
		return nomecliente;
	}
	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}
	public String getLivrocaixa() {
		return livrocaixa;
	}
	public void setLivrocaixa(String livrocaixa) {
		this.livrocaixa = livrocaixa;
	}
	
	
}
