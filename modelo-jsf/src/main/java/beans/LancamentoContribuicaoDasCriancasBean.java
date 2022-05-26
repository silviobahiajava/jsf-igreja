package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ContribuicaoDasCriancasDao;
import daos.LancamentoDao;
import entidades.ContribuicaoDasCriancas;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentocontribuicaodascriancasBean")
@ViewScoped
public class LancamentoContribuicaoDasCriancasBean {
	private Lancamento lancamento=new Lancamento();
	private ContribuicaoDasCriancas  contribuicao;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private BigDecimal valorSaldo;
	 private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		

	private List<ContribuicaoDasCriancas>listaDeContribuicoes;
	private List<ContribuicaoDasCriancas>listaDeContribuicoesPraLancamento;
	private ContribuicaoDasCriancasDao cdao=new ContribuicaoDasCriancasDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeContribuicoes=cdao.buscarContribuicaoDasCriancasPorGrupo(3L);
			listaDeContribuicoesPraLancamento=new ArrayList<ContribuicaoDasCriancas>();
			
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
			ContribuicaoDasCriancas contribuicao = listaDeContribuicoesPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(contribuicao.getValorQuePodeContribuir()));
		}
	}
		public void adicionarContribuicoes(ActionEvent evento){
			contribuicao=(ContribuicaoDasCriancas)evento.getComponent().getAttributes().get("contribuicaoSelecionada");
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
				grupo.setCodigo(3L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(contribuicao.getDataPrevistaPraContribuir());
				
				lancamento.setHistorico("contribuição para o Ministério Infantil");
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(contribuicao.getValorQuePodeContribuir());
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   
			   ldao.salvarLancamentoContribuicaoDasCriancas(lancamento, listaDeContribuicoesPraLancamento);
			   
			   
			   listaDeContribuicoes=cdao.buscarContribuicaoDasCriancasPorGrupo(3L);
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

		public ContribuicaoDasCriancas getContribuicao() {
			return contribuicao;
		}

		public void setContribuicao(ContribuicaoDasCriancas contribuicao) {
			this.contribuicao = contribuicao;
		}

		public List<ContribuicaoDasCriancas> getListaDeContribuicoes() {
			return listaDeContribuicoes;
		}

		public void setListaDeContribuicoes(List<ContribuicaoDasCriancas> listaDeContribuicoes) {
			this.listaDeContribuicoes = listaDeContribuicoes;
		}

		public List<ContribuicaoDasCriancas> getListaDeContribuicoesPraLancamento() {
			return listaDeContribuicoesPraLancamento;
		}

		public void setListaDeContribuicoesPraLancamento(List<ContribuicaoDasCriancas> listaDeContribuicoesPraLancamento) {
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
