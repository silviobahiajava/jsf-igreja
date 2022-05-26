package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDaIgrejaDao;
import daos.LancamentoDao;
import entidades.CampanhaDaIgreja;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="lancamentocampanhadaigrejaBean")
public class LancamentoCampanhaDaIgrejaBean {
	private Lancamento lancamento=new Lancamento();
	private CampanhaDaIgreja campanha;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<CampanhaDaIgreja>listaCampanha;
	private List<CampanhaDaIgreja>listaCampanhaLancamento;
	private CampanhaDaIgrejaDao cpdao=new CampanhaDaIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	 private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaCampanha=cpdao.buscarCampanhaDaIgrejaPorGrupo(8L);
			listaCampanhaLancamento=new ArrayList<CampanhaDaIgreja>();
			
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
			CampanhaDaIgreja campanhadaigreja = listaCampanhaLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(campanha.getValorCampanhaIgreja()));
		}
	}
		public void adicionarCompras(ActionEvent evento){
			
			campanha=(CampanhaDaIgreja) evento.getComponent().getAttributes().get("campanhaSelecionada");
			listaCampanhaLancamento.add(campanha);
			
			
			//calcular();
			Utils.mostrarMensagemJsfSucesso("opera��o realizada com sucesso");
				
			}
		
		public void salvarLancamentoDeCampanha(){
			LancamentoDao ldao=new LancamentoDao();
			try{
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(campanha.getDataCampanha());
				
				lancamento.setHistorico("campanha da igreja");
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(campanha.getValorCampanhaIgreja());
				Long codigo = ldao.buscaUlitmoCodigo();
				valorSaldo = ldao.mostrarSaldoPorCodigo(codigo);
				if (valorSaldo == null) {
					valorSaldo = new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   ldao.salvarLancamentoCampanhaDaIgreja(lancamento, listaCampanhaLancamento);
			   listaCampanha=cpdao.buscarCampanhaDaIgrejaPorGrupo(8L);
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

		public CampanhaDaIgreja getCampanha() {
			return campanha;
		}

		public void setCampanha(CampanhaDaIgreja campanha) {
			this.campanha = campanha;
		}

		public List<CampanhaDaIgreja> getListaCampanha() {
			return listaCampanha;
		}

		public void setListaCampanha(List<CampanhaDaIgreja> listaCampanha) {
			this.listaCampanha = listaCampanha;
		}

		public List<CampanhaDaIgreja> getListaCampanhaLancamento() {
			return listaCampanhaLancamento;
		}

		public void setListaCampanhaLancamento(List<CampanhaDaIgreja> listaCampanhaLancamento) {
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
