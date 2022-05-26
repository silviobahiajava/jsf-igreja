package beans;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import daos.DizimoDaIgrejaDao;
import daos.DizimoLancadoDao;
import daos.LancamentoDao;
import daos.OfertaDaIgrejaDao;
import entidades.DizimoDaIgreja;
import entidades.DizimoLancado;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDaIgreja;
import utils.Utils;

@ManagedBean(name = "lancamentodizimodaigrejaBean")
@ViewScoped
public class LancamentoDizimosDaIgrejaBean {
	private Lancamento lancamento = new Lancamento();
	private DizimoDaIgreja dizimo;
	private LancamentoDao lancamentoDao = new LancamentoDao();

	private List<DizimoDaIgreja> listaDeDizimos;
	private List<DizimoDaIgreja> listaDeDizimosPraLancamento;
	// private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private DizimoDaIgrejaDao dzdao = new DizimoDaIgrejaDao();

	// private BigDecimal despesa;
	private BigDecimal saldo;
	private BigDecimal valorReceita;
	private BigDecimal valorDespesa;
	private BigDecimal valorSaldo;
	
	
	
	
	
	
	@PostConstruct
	public void novo() {
		try {
			lancamento = new Lancamento();
			 listaDeDizimos=dzdao.buscarPorTipoDeDizimoECodigo("NAOLANCADO",8L);
			listaDeDizimosPraLancamento = new ArrayList<DizimoDaIgreja>();

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

	// novo metodo

	public void calcular() {
		// venda.setPrecoTotal(new BigDecimal("0.00"));
		lancamento.setDespesa(new BigDecimal("0.00"));
		for (int posicao = 0; posicao < listaDeDizimosPraLancamento.size(); posicao++) {
			DizimoDaIgreja dizimo = listaDeDizimosPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(dizimo.getValorDizimoIgreja()));
		}
	}

	public void adicionarDizimos(ActionEvent evento) {
		
		/*DizimoLancado dizimoLancado=new DizimoLancado();
		DizimoLancadoDao dizimoLancadoDao=new DizimoLancadoDao();
		*/
		
		dizimo = (DizimoDaIgreja) evento.getComponent().getAttributes().get("dizimoSelecionado");
		listaDeDizimosPraLancamento.add(dizimo);
		
		// calcular();
		
		/*dizimoLancado.setDataLancamento(dizimo.getDataDizimoIgreja());
		dizimoLancado.setDizimista(dizimo.getDizimistaIgreja());
		dizimoLancado.setValorDizimo(dizimo.getValorDizimoIgreja());
		dizimoLancadoDao.salvar(dizimoLancado);
		
		dzdao.excluir(dizimo);
		*/
		
		Utils.mostrarMensagemJsfSucesso("dizimo lancado no caixa");
		
		

	}

	public void salvarLancamentoDeDizimos() {
		LancamentoDao ldao = new LancamentoDao();
		try {
			Grupo grupo = new Grupo();
			grupo.setCodigo(8L);
			lancamento.setGrupo(grupo);
			//DizimoLancadoDao dizimoLancadoDao=new DizimoLancadoDao();
			
			dizimo.setTipodizimo("LANCADO");
			lancamento.setData(dizimo.getDataDizimoIgreja());

			lancamento.setHistorico("dizimo");
			lancamento.setDespesa(new BigDecimal("0.00"));
			lancamento.setReceita(dizimo.getValorDizimoIgreja());
			Long codigo = ldao.buscaUlitmoCodigo();
			valorSaldo = ldao.mostrarSaldoPorCodigo(codigo);
			if (valorSaldo == null) {
				valorSaldo = new BigDecimal("0.00");
			}
			
			lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
			ldao.salvarLancamentoDizimoDaIgreja(lancamento, listaDeDizimosPraLancamento);
			
			/*dizimoLancando.setDataLancamento(dizimo.getDataDizimoIgreja());
			dizimoLancando.setDizimista(dizimo.getDizimistaIgreja());
			dizimoLancando.setValorDizimo(dizimo.getValorDizimoIgreja());
			
			listadedizimoslancados.add(dizimoLancando);
			ldao.registrarDizimosLancados(listadedizimoslancados);
			dzdao.excluir(dizimo);*/
			
			//dizimos=dzdao.buscarPorTipoDeDizimoECodigo("NAOLANCADO",8L);
			Utils.mostrarMensagemJsfSucesso("dizimo registrado com sucesso");
			Faces.redirect("./igreja/dizimodaigreja.xhtml");
		} catch (RuntimeException  e) {
			Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de DÍZIMOS");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public DizimoDaIgreja getDizimo() {
		return dizimo;
	}

	public void setDizimo(DizimoDaIgreja dizimo) {
		this.dizimo = dizimo;
	}

	public List<DizimoDaIgreja> getListaDeDizimos() {
		return listaDeDizimos;
	}

	public void setListaDeDizimos(List<DizimoDaIgreja> listaDeDizimos) {
		this.listaDeDizimos = listaDeDizimos;
	}

	public List<DizimoDaIgreja> getListaDeDizimosPraLancamento() {
		return listaDeDizimosPraLancamento;
	}

	public void setListaDeDizimosPraLancamento(List<DizimoDaIgreja> listaDeDizimosPraLancamento) {
		this.listaDeDizimosPraLancamento = listaDeDizimosPraLancamento;
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
