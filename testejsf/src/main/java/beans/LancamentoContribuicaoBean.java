package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.ContribuicaoDao;
import daos.LancamentoDao;
import entidades.Contribuicao;
import entidades.Lancamento;

@ManagedBean(name="lancamentoContribuicaoBean")
@ViewScoped
public class LancamentoContribuicaoBean {
	private Lancamento lancamento=new Lancamento();
	private Contribuicao contribuicao=new Contribuicao();
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private ContribuicaoDao contribuicaoDao=new ContribuicaoDao();
	private List<Contribuicao>listaContribuicoes=new ArrayList<Contribuicao>();
	private List<Contribuicao>listaContribuicoesLancamento=new ArrayList<Contribuicao>();
	private String nomecliente;
	private String livrocaixa;
	
	/*@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 OfertaDao ofertaDao=new OfertaDao();
			// listaOfertas=ofertaDao.listar();
			// listaOfertas=ofertaDao.buscarOfertaPorGrupo("var�es");
			 listaOfertas=ofertaDao.buscarOfertaPorCodigo(1L);
            listaOfertasLancamento=new ArrayList<>();
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
	}*/
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			listaContribuicoes=contribuicaoDao.buscarContribuicaoPorCodigo(8L);
			listaContribuicoesLancamento=new ArrayList<Contribuicao>();
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
	}
	
	
	
	/*@PostConstruct
	public void init(){
		Map<String, String> meumapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		nomecliente=meumapa.get("namekey");
		switch(nomecliente){
		case "1":
			livrocaixa="Livro Caixa do Grupo de Var�es";
			break;
		case "2":
			livrocaixa="Livro Caixa do C�rculo de Ora��o";
			break;
		case "3":
			livrocaixa="Livro Caixa do Grupo de Crian�as";
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
		
		listarContribuicao();
	}
	public void listarContribuicao(){
		
		ContribuicaoDao contribuicaoDao=new ContribuicaoDao();
		listaContribuicoes=contribuicaoDao.buscarContribuicaoPorCodigo(Long.parseLong(nomecliente));
		
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	

	public LancamentoDao getLancamentoDao() {
		return lancamentoDao;
	}

	public void setLancamentoDao(LancamentoDao lancamentoDao) {
		this.lancamentoDao = lancamentoDao;
	}

*/	

	
	
	//novo metodo
	
	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        //lancamento.setDespesa(new BigDecimal("0.00"));
		lancamento.setReceita(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaContribuicoesLancamento.size(); posicao++) {
			Contribuicao contribuicao = listaContribuicoesLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			//lancamento.setDespesa(lancamento.getDespesa().add(meuOferta.getValorOferta()));
			lancamento.setReceita(lancamento.getReceita().add(contribuicao.getValorContribuicao()));
			
		}
	}
		public void adicionarContribuicao(ActionEvent evento){
			contribuicao=(Contribuicao) evento.getComponent().getAttributes().get("contribuicaoSelecionada");
			listaContribuicoesLancamento.add(contribuicao);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("A contribui��o para o grupo "+contribuicao.getGrupo().getNome()+" foi registrado ");
				//listaContribuicoes.remove(contribuicao);
			}
		
		public void salvarLancamentoDeOfertas(){
			LancamentoDao ldao=new LancamentoDao();
			try{
			lancamento.setDescricao("contribuicao");
			lancamento.setData(contribuicao.getDataContribuicao());
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setReceita(contribuicao.getValorContribuicao());
			ldao.salvarLancamentoContribuicao(lancamento, listaContribuicoes);
			
			Utils.mostrarMensagemJsfSucesso("lan�amento de d�zimo realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
				e.printStackTrace();
			}
		}
		public Contribuicao getContribuicao() {
			return contribuicao;
		}
		public void setContribuicao(Contribuicao contribuicao) {
			this.contribuicao = contribuicao;
		}
		public List<Contribuicao> getListaContribuicoes() {
			return listaContribuicoes;
		}
		public void setListaContribuicoes(List<Contribuicao> listaContribuicoes) {
			this.listaContribuicoes = listaContribuicoes;
		}
		public List<Contribuicao> getListaContribuicoesLancamento() {
			return listaContribuicoesLancamento;
		}
		public void setListaContribuicoesLancamento(List<Contribuicao> listaContribuicoesLancamento) {
			this.listaContribuicoesLancamento = listaContribuicoesLancamento;
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



		public Lancamento getLancamento() {
			return lancamento;
		}



		public void setLancamento(Lancamento lancamento) {
			this.lancamento = lancamento;
		}

		
}
