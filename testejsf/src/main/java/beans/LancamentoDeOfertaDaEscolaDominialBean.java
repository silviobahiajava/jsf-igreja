package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.AulaEscolaDominicalDao;
import daos.DizimoDaIgrejaDao;
import daos.LancamentoDao;
import daos.OfertaDaIgrejaDao;
import daos.OfertaEscolaDominicalDao;
import entidades.AulaEscolaDominical;
import entidades.DizimoDaIgreja;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDaIgreja;
import entidades.OfertaEscolaDominical;

@ManagedBean(name="lancamentodeofertadaescoladominicalBean")
@ViewScoped
public class LancamentoDeOfertaDaEscolaDominialBean {
	private Lancamento lancamento = new Lancamento();
	//private AulaEscolaDominical aula;
	private OfertaEscolaDominical oferta;
	private LancamentoDao lancamentoDao = new LancamentoDao();

	private List<OfertaEscolaDominical> listaDeOfertasEscolaDominical;
	private List<OfertaEscolaDominical> listaDeOfertasEscolaDomincialLancamento;
	
	private OfertaEscolaDominicalDao ofertaDao=new OfertaEscolaDominicalDao();
	

	// private BigDecimal despesa;
	private BigDecimal saldo;
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;

	@PostConstruct
	public void novo() {
		try {
			lancamento = new Lancamento();
			listaDeOfertasEscolaDominical=ofertaDao.buscarOfertaEscolaDominicalPorGrupo(7L);
			listaDeOfertasEscolaDomincialLancamento=new ArrayList<OfertaEscolaDominical>();

		} catch (RuntimeException erro) {

			erro.printStackTrace();
		}
	}

	

	// novo metodo

	public void calcular() {
		// venda.setPrecoTotal(new BigDecimal("0.00"));
		lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeOfertasEscolaDomincialLancamento.size(); posicao++) {
			OfertaEscolaDominical oferta = listaDeOfertasEscolaDomincialLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(oferta.getValorOfertaEscolaDominical() ));
		}
	}

	public void adicionarOferta(ActionEvent evento) {
		oferta = (OfertaEscolaDominical) evento.getComponent().getAttributes().get("ofertaSelecionada");
		listaDeOfertasEscolaDomincialLancamento.add(oferta);
		Utils.mostrarMensagemJsfSucesso("oferta  adicionada com sucesso");

	}

	public void salvarLancamentoDeOfertaDaEscolaDominical() {
		LancamentoDao ldao = new LancamentoDao();
		try {
			Grupo grupo = new Grupo();
			grupo.setCodigo(7L);
			lancamento.setGrupo(grupo);

			lancamento.setData(oferta.getDataOfertaEescolaDominical());

			lancamento.setHistorico("oferta da Escola Dominical");
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setReceita(oferta.getValorOfertaEscolaDominical());
			Long codigo = ldao.buscaUlitmoCodigo();
			valorSaldo = ldao.mostrarSaldoPorCodigo(codigo);
			if (valorSaldo == null) {
				valorSaldo = new BigDecimal("0.00");
			}
			lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			
			ldao.salvarLancamentoDeOfertaDaEscolaDominical(lancamento, listaDeOfertasEscolaDomincialLancamento);
			listaDeOfertasEscolaDominical=ofertaDao.buscarOfertaDaEscolaDominicalPorGrupo(7L);

			Utils.mostrarMensagemJsfSucesso("lan�amento de oferta realizado com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao fazer lan�amento de compras");
			e.printStackTrace();
		}
	}



	public Lancamento getLancamento() {
		return lancamento;
	}



	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
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



	public OfertaEscolaDominical getOferta() {
		return oferta;
	}



	public void setOferta(OfertaEscolaDominical oferta) {
		this.oferta = oferta;
	}



	public List<OfertaEscolaDominical> getListaDeOfertasEscolaDominical() {
		return listaDeOfertasEscolaDominical;
	}



	public void setListaDeOfertasEscolaDominical(List<OfertaEscolaDominical> listaDeOfertasEscolaDominical) {
		this.listaDeOfertasEscolaDominical = listaDeOfertasEscolaDominical;
	}



	public List<OfertaEscolaDominical> getListaDeOfertasEscolaDomincialLancamento() {
		return listaDeOfertasEscolaDomincialLancamento;
	}



	public void setListaDeOfertasEscolaDomincialLancamento(
			List<OfertaEscolaDominical> listaDeOfertasEscolaDomincialLancamento) {
		this.listaDeOfertasEscolaDomincialLancamento = listaDeOfertasEscolaDomincialLancamento;
	}

	
	
	
	

		
}
