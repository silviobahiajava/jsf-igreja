package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ContribuicaoDaIgrejaDao;
import daos.LancamentoDao;
import entidades.ContribuicaoDaIgreja;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentocontribuicaodaigrejaBean")
@ViewScoped
public class LancamentoContribuicaoDaIgrejaBean {
	private Lancamento lancamento=new Lancamento();
	private ContribuicaoDaIgreja  contribuicao;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private BigDecimal valorSaldo;
	 private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		

	private List<ContribuicaoDaIgreja>listaDeContribuicoes;
	private List<ContribuicaoDaIgreja>listaDeContribuicoesPraLancamento;
	private ContribuicaoDaIgrejaDao cdao=new ContribuicaoDaIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeContribuicoes=cdao.buscarContribuicaoDaIgrejaPorGrupo(8L);
			listaDeContribuicoesPraLancamento=new ArrayList<ContribuicaoDaIgreja>();
			
		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}
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

	

	
	
	
	//novo metodo
	
	

	

	

	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeContribuicoesPraLancamento.size(); posicao++) {
			ContribuicaoDaIgreja contribuicao = listaDeContribuicoesPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(contribuicao.getValorQuePodeContribuir()));
		}
	}
		public void adicionarContribuicoes(ActionEvent evento){
			contribuicao=(ContribuicaoDaIgreja)evento.getComponent().getAttributes().get("contribuicaoSelecionada");
			listaDeContribuicoesPraLancamento.add(contribuicao);
			//implementar e exclusão desta contribuição da lista de contribuições apos esta contribuicao ser adicionada na lista de 
			//contribuicções pra lançamento
			
			//calcular();
			Utils.mostrarMensagemJsfSucesso("contribuicao registrada no livro caixa da igreja");
				
			}
		
		public void salvarLancamentoDeContribuicoes(){
			LancamentoDao ldao=new LancamentoDao();
			try{
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(contribuicao.getDataPrevistaPraContribuir());
				
				lancamento.setHistorico("contribuição para igreja");
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(contribuicao.getValorQuePodeContribuir());
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   
			   ldao.salvarLancamentoContribuicaoDaIgreja(lancamento, listaDeContribuicoesPraLancamento);
			   
			   
			   listaDeContribuicoes=cdao.buscarContribuicaoDaIgrejaPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("contribuição registrada   com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer esta operação");
				e.printStackTrace();
			}
		}
		
		

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public ContribuicaoDaIgreja getContribuicao() {
			return contribuicao;
		}

		public void setContribuicao(ContribuicaoDaIgreja contribuicao) {
			this.contribuicao = contribuicao;
		}

		public List<ContribuicaoDaIgreja> getListaDeContribuicoes() {
			return listaDeContribuicoes;
		}

		public void setListaDeContribuicoes(List<ContribuicaoDaIgreja> listaDeContribuicoes) {
			this.listaDeContribuicoes = listaDeContribuicoes;
		}

		public List<ContribuicaoDaIgreja> getListaDeContribuicoesPraLancamento() {
			return listaDeContribuicoesPraLancamento;
		}

		public void setListaDeContribuicoesPraLancamento(List<ContribuicaoDaIgreja> listaDeContribuicoesPraLancamento) {
			this.listaDeContribuicoesPraLancamento = listaDeContribuicoesPraLancamento;
		}

		public BigDecimal getValorSaldo() {
			return valorSaldo;
		}

		public void setValorSaldo(BigDecimal valorSaldo) {
			this.valorSaldo = valorSaldo;
		}

		public BigDecimal getValorReceita() {
			return valorReceita;
		}

		public void setValorReceita(BigDecimal valorReceita) {
			this.valorReceita = valorReceita;
		}

		public BigDecimal getValorDespesa() {
			return valorDespesa;
		}

		public void setValorDespesa(BigDecimal valorDespesa) {
			this.valorDespesa = valorDespesa;
		}

		

}
