package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;



import daos.DespesaCriancaDao;
import daos.LancamentoDao;


import entidades.DespesaCrianca;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentodespesacriancaBean")
@ViewScoped
public class LancamentoDespesaCriancaBean {
	
	private Lancamento lancamento=new Lancamento();
	private DespesaCrianca despesa;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<DespesaCrianca>listaDespesas;
	private List<DespesaCrianca>listaDespesasLancamento;
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;
	private DespesaCriancaDao despesaCriancaDao=new DespesaCriancaDao();
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 listaDespesas=despesaCriancaDao.buscarDespesasDasCriancasPorGrupo(3L);
            listaDespesasLancamento=new ArrayList<DespesaCrianca>();
			
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
		for (int posicao = 0; posicao < listaDespesasLancamento.size(); posicao++) {
			DespesaCrianca despesaCrianca = listaDespesasLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(despesaCrianca.getValorDespescaCrianca() ));
		}
	}
		public void adicionarDespesas(ActionEvent evento){
			despesa=(DespesaCrianca) evento.getComponent().getAttributes().get("despesaSelecionada");
			listaDespesasLancamento.add(despesa);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("despesa"+despesa.getDescricao()+"paga com sucesso");
			
				listaDespesas.remove(despesa);
			}
		
		public void salvarLancamentoDeDespesas(){
			
			try{

				Grupo grupo=new Grupo();
				grupo.setCodigo(3L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(despesa.getDataPagamentoDespesaCrianca());
				lancamento.setReceita(new BigDecimal("0.00"));
				lancamento.setDespesa(despesa.getValorDespescaCrianca());
				lancamento.setHistorico(despesa.getDescricao());
				Long codigo=lancamentoDao.buscaUlitmoCodigo();
				valorSaldo=lancamentoDao.mostrarSaldoPorCodigo(codigo);
				
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
				lancamentoDao.salvarLancamentoDespesaCrianca(lancamento, listaDespesasLancamento);
				listaDespesas=despesaCriancaDao.buscarDespesasDasCriancasPorGrupo(3L);
				
			 
			Utils.mostrarMensagemJsfSucesso("compra registrada  com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de ofertas");
				e.printStackTrace();
			}
}


		public DespesaCrianca getDespesa() {
			return despesa;
		}


		public void setDespesa(DespesaCrianca despesa) {
			this.despesa = despesa;
		}


		public List<DespesaCrianca> getListaDespesas() {
			return listaDespesas;
		}


		public void setListaDespesas(List<DespesaCrianca> listaDespesas) {
			this.listaDespesas = listaDespesas;
		}


		public List<DespesaCrianca> getListaDespesasLancamento() {
			return listaDespesasLancamento;
		}


		public void setListaDespesasLancamento(List<DespesaCrianca> listaDespesasLancamento) {
			this.listaDespesasLancamento = listaDespesasLancamento;
		}

		
		
		
		
		
		
		
		
		
		
		
		
}
