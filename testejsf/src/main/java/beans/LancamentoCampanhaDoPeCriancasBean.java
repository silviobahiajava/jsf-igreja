package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDoPeCriancasDao;
import daos.LancamentoDao;
import entidades.CampanhaDoPeCriancas;
import entidades.Grupo;
import entidades.Lancamento;

@ManagedBean(name="lancamentocampanhadopeCriancasBean")
@ViewScoped
public class LancamentoCampanhaDoPeCriancasBean {
	private Lancamento lancamento=new Lancamento();
	private CampanhaDoPeCriancas campanha;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	
   	 private BigDecimal valorReceita;
  		private BigDecimal valorDespesa;
  		private BigDecimal valorSaldo;
    

	private List<CampanhaDoPeCriancas>listaContribuicoesCampanhaDoPeCriancas;
	private List<CampanhaDoPeCriancas>listaContribuicoesCampanhaDoPeCriancasLancamento;
	private CampanhaDoPeCriancasDao campanhaDao=new CampanhaDoPeCriancasDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaContribuicoesCampanhaDoPeCriancas=campanhaDao.buscarCampanhaDoPeCriancasPorGrupo(2L);
			listaContribuicoesCampanhaDoPeCriancasLancamento=new ArrayList<CampanhaDoPeCriancas>();
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
		for (int posicao = 0; posicao < listaContribuicoesCampanhaDoPeCriancasLancamento.size(); posicao++) {
			CampanhaDoPeCriancas compra = listaContribuicoesCampanhaDoPeCriancasLancamento.get(posicao);
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setReceita(lancamento.getReceita().add(campanha.getValorPraPagar()));
		}
	}
		public void adicionarContribuicao(ActionEvent evento){
			campanha=(CampanhaDoPeCriancas) evento.getComponent().getAttributes().get("contribuinteSelecionado");
			
			listaContribuicoesCampanhaDoPeCriancasLancamento.add(campanha);
			//calcular();
			Utils.mostrarMensagemJsfSucesso("contribui��o registrada com sucesso");
				
			}
		
		public void salvarLancamentoDeCampanhaDoPeCriancas(){
			LancamentoDao ldao=new LancamentoDao();
			try{

				//valorReceita=ldao.mostrarReceitaPorGrupo(8L);
				//valorDespesa=ldao.mostrarDespesaPorGrupo(8L);
				
				Grupo grupo=new Grupo();
				grupo.setCodigo(2L);
				lancamento.setGrupo(grupo);
				
				lancamento.setData(campanha.getDataPagamento());
				
				lancamento.setHistorico(campanha.getDescricao());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(campanha.getValorPraPagar());
				Long codigo=ldao.buscaUlitmoCodigo();
				valorSaldo=ldao.mostrarSaldoPorCodigo(codigo);
				if(valorSaldo==null) {
					valorSaldo=new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			   
			   ldao.salvarLancamentoCampanhaDoPeCriancas(lancamento, listaContribuicoesCampanhaDoPeCriancasLancamento);
			   listaContribuicoesCampanhaDoPeCriancas=campanhaDao.buscarCampanhaDoPeCriancasPorGrupo(2L);
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

		public CampanhaDoPeCriancas getCampanha() {
			return campanha;
		}

		public void setCampanha(CampanhaDoPeCriancas campanha) {
			this.campanha = campanha;
		}

		public List<CampanhaDoPeCriancas> getListaContribuicoesCampanhaDoPeCriancas() {
			return listaContribuicoesCampanhaDoPeCriancas;
		}

		public void setListaContribuicoesCampanhaDoPeCriancas(
				List<CampanhaDoPeCriancas> listaContribuicoesCampanhaDoPeCriancas) {
			this.listaContribuicoesCampanhaDoPeCriancas = listaContribuicoesCampanhaDoPeCriancas;
		}

		public List<CampanhaDoPeCriancas> getListaContribuicoesCampanhaDoPeCriancasLancamento() {
			return listaContribuicoesCampanhaDoPeCriancasLancamento;
		}

		public void setListaContribuicoesCampanhaDoPeCriancasLancamento(
				List<CampanhaDoPeCriancas> listaContribuicoesCampanhaDoPeCriancasLancamento) {
			this.listaContribuicoesCampanhaDoPeCriancasLancamento = listaContribuicoesCampanhaDoPeCriancasLancamento;
		}

		

	
}
