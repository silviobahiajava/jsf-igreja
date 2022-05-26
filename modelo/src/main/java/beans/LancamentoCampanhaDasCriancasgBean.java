package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import daos.CampanhaDasCriancasDao;
import daos.LancamentoDao;
import entidades.CampanhaDasCriancas;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentocampanhadascriancasBean")
public class LancamentoCampanhaDasCriancasgBean {
	private Lancamento lancamento=new Lancamento();
	private CampanhaDasCriancas campanha;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<CampanhaDasCriancas>listaCampanha;
	private List<CampanhaDasCriancas>listaCampanhaLancamento;
	private CampanhaDasCriancasDao cpdao=new CampanhaDasCriancasDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	 private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaCampanha=cpdao.buscarCampanhaDasCriancasPorGrupo(2L);
			listaCampanhaLancamento=new ArrayList<CampanhaDasCriancas>();
			
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
		for (int posicao = 0; posicao < listaCampanhaLancamento.size(); posicao++) {
			CampanhaDasCriancas CampanhaDasCriancas = listaCampanhaLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(campanha.getValorCampanhaDasCriancas() ));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			
			campanha=(CampanhaDasCriancas) evento.getComponent().getAttributes().get("campanhaSelecionada");
			listaCampanhaLancamento.add(campanha);
			
			
			//calcular();
			Utils.mostrarMensagemJsfSucesso("operação realizada com sucesso");
				
			}
		
		public void salvarLancamentoDeCampanha(){
			LancamentoDao ldao=new LancamentoDao();
			try{
				Grupo grupo=new Grupo();
				grupo.setCodigo(2L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(campanha.getDataCampanha());
				
				lancamento.setHistorico("campanha para o Ministério Infantil");
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(campanha.getValorCampanhaDasCriancas());
				Long codigo = ldao.buscaUlitmoCodigo();
				valorSaldo = ldao.mostrarSaldoPorCodigo(codigo);
				if (valorSaldo == null) {
					valorSaldo = new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
		
			   
			   
			   ldao.salvarLancamentoCampanhaDasCriancas(lancamento, listaCampanhaLancamento);
			   listaCampanha=cpdao.buscarCampanhaDasCriancasPorGrupo(2L);
			   
			   
			Utils.mostrarMensagemJsfSucesso("lançamento  realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de compras");
				e.printStackTrace();
			}
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public CampanhaDasCriancas getCampanha() {
			return campanha;
		}

		public void setCampanha(CampanhaDasCriancas campanha) {
			this.campanha = campanha;
		}

		public List<CampanhaDasCriancas> getListaCampanha() {
			return listaCampanha;
		}

		public void setListaCampanha(List<CampanhaDasCriancas> listaCampanha) {
			this.listaCampanha = listaCampanha;
		}

		public List<CampanhaDasCriancas> getListaCampanhaLancamento() {
			return listaCampanhaLancamento;
		}

		public void setListaCampanhaLancamento(List<CampanhaDasCriancas> listaCampanhaLancamento) {
			this.listaCampanhaLancamento = listaCampanhaLancamento;
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
