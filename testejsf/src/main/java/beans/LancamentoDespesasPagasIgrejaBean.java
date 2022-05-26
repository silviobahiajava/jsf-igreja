package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.DespesaDaIgrejaDao;
import daos.LancamentoDao;

import entidades.DespesaDaIgreja;
import entidades.Grupo;
import entidades.Lancamento;
import enums.SitucaoDaConta;


@ManagedBean(name="lancamentodespesaspagasdaigrejaBean")
@ViewScoped
public class LancamentoDespesasPagasIgrejaBean {
	
	private Lancamento lancamento=new Lancamento();
	private DespesaDaIgreja despesa;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	
	
   	 private BigDecimal valorReceita;
  		private BigDecimal valorDespesa;
  		private BigDecimal valorSaldo;
    

	private List<DespesaDaIgreja>listaDeDespesaDaIgrejas=new ArrayList<DespesaDaIgreja>();
	private List<DespesaDaIgreja>listaDeDespesaDaIgrejasPraLancamento=new ArrayList<DespesaDaIgreja>();
	private List<DespesaDaIgreja>listaDeDespesaDaIgrejasPraLancamentoNoCaixa=new ArrayList<DespesaDaIgreja>();
	private DespesaDaIgrejaDao despesadao=new DespesaDaIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			listaDeDespesaDaIgrejas=new ArrayList<DespesaDaIgreja>();
			listaDeDespesaDaIgrejasPraLancamento=new ArrayList<DespesaDaIgreja>();
			listaDeDespesaDaIgrejasPraLancamentoNoCaixa=new ArrayList<DespesaDaIgreja>();
			lancamento=new Lancamento();
			mostrarDespesasPagas();
			
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
		for (int posicao = 0; posicao < listaDeDespesaDaIgrejasPraLancamentoNoCaixa.size(); posicao++) {
			DespesaDaIgreja despesa = listaDeDespesaDaIgrejasPraLancamentoNoCaixa.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(despesa.getValorDespescaDaIgreja() ));
		}
	}
		public void adicionarDespesaDaIgrejas(ActionEvent evento){
			despesa=(DespesaDaIgreja) evento.getComponent().getAttributes().get("despesaDaIgrejaSelecionada");
			listaDeDespesaDaIgrejasPraLancamentoNoCaixa.add(despesa);
			
			//calcular();
			Utils.mostrarMensagemJsfSucesso("despesa paga registrada no caixa");
				
			}
		
		public void salvarLancamentoDeDespesaDaIgrejas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				//valorReceita=ldao.mostrarReceitaPorGrupo(8L);
				//valorDespesa=ldao.mostrarDespesaPorGrupo(8L);
				
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(despesa.getDataPagamentoDespesaDaIgreja());
				
				lancamento.setHistorico(despesa.getDescricaoDespesaDaIgreja());
				lancamento.setDespesa(despesa.getValorDespescaDaIgreja() );
				lancamento.setReceita(new BigDecimal("0.00"));
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				ldao.salvarLancamentoDespesaPagaDaIgreja(lancamento, listaDeDespesaDaIgrejasPraLancamentoNoCaixa);
			   
			   mostrarDespesasPagas();
			Utils.mostrarMensagemJsfSucesso("lan�amento de DespesaDaIgrejas realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de DespesaDaIgrejas");
				e.printStackTrace();
			}
		}
		
		

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
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
		

		
		public DespesaDaIgreja getDespesa() {
			return despesa;
		}

		public void setDespesa(DespesaDaIgreja despesa) {
			this.despesa = despesa;
		}

		public List<DespesaDaIgreja> getListaDeDespesaDaIgrejas() {
			return listaDeDespesaDaIgrejas;
		}

		public void setListaDeDespesaDaIgrejas(List<DespesaDaIgreja> listaDeDespesaDaIgrejas) {
			this.listaDeDespesaDaIgrejas = listaDeDespesaDaIgrejas;
		}

		public List<DespesaDaIgreja> getListaDeDespesaDaIgrejasPraLancamento() {
			return listaDeDespesaDaIgrejasPraLancamento;
		}

		public void setListaDeDespesaDaIgrejasPraLancamento(List<DespesaDaIgreja> listaDeDespesaDaIgrejasPraLancamento) {
			this.listaDeDespesaDaIgrejasPraLancamento = listaDeDespesaDaIgrejasPraLancamento;
		}

		public List<DespesaDaIgreja> getListaDeDespesaDaIgrejasPraLancamentoNoCaixa() {
			return listaDeDespesaDaIgrejasPraLancamentoNoCaixa;
		}

		public void setListaDeDespesaDaIgrejasPraLancamentoNoCaixa(
				List<DespesaDaIgreja> listaDeDespesaDaIgrejasPraLancamentoNoCaixa) {
			this.listaDeDespesaDaIgrejasPraLancamentoNoCaixa = listaDeDespesaDaIgrejasPraLancamentoNoCaixa;
		}

		public void mostrarDespesasPagas(){
			
			try{
			listaDeDespesaDaIgrejas=despesadao.buscarDespesaDaIgrejaPorGrupo(8L);
			for(DespesaDaIgreja despesa:listaDeDespesaDaIgrejas){
				if(despesa.getSituacaoDaDespesaEnum().getDescricao().equals( SitucaoDaConta.PAGO.getDescricao())){
					listaDeDespesaDaIgrejasPraLancamento.add(despesa);
					
				
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				Utils.mostrarMensagemSwing("erro ao carregar as despesas "+e.getMessage());
			}
		}
		
		
}
