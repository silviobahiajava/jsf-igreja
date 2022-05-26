package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CompraIgrejaDao;
import daos.DespesasDaIgrejaPagasDao;
import daos.LancamentoDao;
import entidades.CompraIgreja;
import entidades.DespesasPagasDaIgreja;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="lancamentodespesasBean")
@ViewScoped
public class LancamentoDespesadaIgrejaBean {
	private Lancamento lancamento=new Lancamento();
	private DespesasPagasDaIgreja despesa;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	
   	 private BigDecimal valorReceita;
  		private BigDecimal valorDespesa;
  		private BigDecimal valorSaldo;
    

	private List<DespesasPagasDaIgreja>listaDeDespesaPagas;
	private List<DespesasPagasDaIgreja>listaDeDespesaPagasPraLancamento;
	private DespesasDaIgrejaPagasDao despesaDao=new DespesasDaIgrejaPagasDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeDespesaPagas=despesaDao.buscarPorGrupo(8L);
			listaDeDespesaPagasPraLancamento=new ArrayList<DespesasPagasDaIgreja>();
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


	public void calcular() {
		//venda.setPrecoTotal(new BigDecimal("0.00"));
        lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeDespesaPagasPraLancamento.size(); posicao++) {
			DespesasPagasDaIgreja despesadaigreja = listaDeDespesaPagasPraLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(despesadaigreja.getValorDespescaDaIgreja() ));
		}
	}
		public void adicionarDespesaPaga(ActionEvent evento){
			despesa=(DespesasPagasDaIgreja) evento.getComponent().getAttributes().get("despesaSelecionada");
			
			listaDeDespesaPagasPraLancamento.add(despesa);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("despesa paga registrada no caixa");
				
			}
		
		public void salvarLancamentoDeDespesas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				//valorReceita=ldao.mostrarReceitaPorGrupo(8L);
				//valorDespesa=ldao.mostrarDespesaPorGrupo(8L);
				
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(despesa.getDataPagamentoDespesaDaIgreja() );
				
				lancamento.setHistorico( despesa.getDescricaoDespesaDaIgreja() );
				lancamento.setDespesa(despesa.getValorDespescaDaIgreja() );
				lancamento.setReceita(new BigDecimal("0.00"));
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   ldao.salvarLancamentoDespesasPagasDaIgreja(lancamento,listaDeDespesaPagasPraLancamento );
			   listaDeDespesaPagas=despesaDao.buscarPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("lan�amento de compras realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
				e.printStackTrace();
			}
		}
		
		

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		

		public DespesasPagasDaIgreja getDespesa() {
			return despesa;
		}

		public void setDespesa(DespesasPagasDaIgreja despesa) {
			this.despesa = despesa;
		}

		public List<DespesasPagasDaIgreja> getListaDeDespesaPagas() {
			return listaDeDespesaPagas;
		}

		public void setListaDeDespesaPagas(List<DespesasPagasDaIgreja> listaDeDespesaPagas) {
			this.listaDeDespesaPagas = listaDeDespesaPagas;
		}

		public List<DespesasPagasDaIgreja> getListaDeDespesaPagasPraLancamento() {
			return listaDeDespesaPagasPraLancamento;
		}

		public void setListaDeDespesaPagasPraLancamento(List<DespesasPagasDaIgreja> listaDeDespesaPagasPraLancamento) {
			this.listaDeDespesaPagasPraLancamento = listaDeDespesaPagasPraLancamento;
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

		public BigDecimal getValorSaldo() {
			return valorSaldo;
		}

		public void setValorSaldo(BigDecimal valorSaldo) {
			this.valorSaldo = valorSaldo;
		}

		
		
}
