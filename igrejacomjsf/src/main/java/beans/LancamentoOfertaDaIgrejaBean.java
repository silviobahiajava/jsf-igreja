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

import daos.LancamentoDao;
import daos.OfertaDaIgrejaDao;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.OfertaDaIgreja;
import utils.Utils;

@ManagedBean(name="lancamentoofertadaigrejaBean")
@ViewScoped
public class LancamentoOfertaDaIgrejaBean {
	private Lancamento lancamento=new Lancamento();
	private OfertaDaIgreja oferta;
	private LancamentoDao lancamentoDao=new LancamentoDao();

	private List<OfertaDaIgreja>listaDeOfertas;
	private List<OfertaDaIgreja>listaDeOfertasPraLancamento;
	//private CompraIgrejaDaocigdao=new CompraIgrejaDao();
	private OfertaDaIgrejaDao oidao=new OfertaDaIgrejaDao();
	
	//private BigDecimal despesa;
	private BigDecimal saldo;
	
	 private BigDecimal valorReceita;
		private BigDecimal valorDespesa;
		private BigDecimal valorSaldo;
	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			listaDeOfertas=oidao.buscarPorTipoDeOfertaECodigo("NAOLANCADO",8L);
			 
			listaDeOfertasPraLancamento=new ArrayList<OfertaDaIgreja>();
			
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
		for (int posicao = 0; posicao < listaDeOfertasPraLancamento.size(); posicao++) {
			OfertaDaIgreja oferta = listaDeOfertasPraLancamento.get(posicao);
			lancamento.setReceita(lancamento.getReceita().add(oferta.getValorOfertaIgreja()  ));
		}
	}
		public void adicionarOfertas(ActionEvent evento){
			oferta=(OfertaDaIgreja) evento.getComponent().getAttributes().get("ofertaSelecionada");
			listaDeOfertasPraLancamento.add(oferta);
		//calcular();
			Utils.mostrarMensagemJsfSucesso("oferta da igreja adicionada com sucesso");
				
			}
		
		public void salvarLancamentoDeOfertas(){
			LancamentoDao ldao=new LancamentoDao();
			try{
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				lancamento.setGrupo(grupo);
				oferta.setTipodeoferta("LANCADO");
				
				lancamento.setData(oferta.getDataOfertaIgreja());
				
				lancamento.setHistorico(oferta.getDescricao());
				lancamento.setDespesa(new BigDecimal("0.00"));
				lancamento.setReceita(oferta.getValorOfertaIgreja());
				Long codigo = ldao.buscaUlitmoCodigo();
				valorSaldo = ldao.mostrarSaldoPorCodigo(codigo);
				if (valorSaldo == null) {
					valorSaldo = new BigDecimal("0.00");
				}
				lancamento.setSaldo(valorSaldo.add(lancamento.getReceita().subtract(lancamento.getDespesa())));
		
			   ldao.salvarLancamentoOfertaDaIgreja(lancamento, listaDeOfertasPraLancamento);
			   
			   Utils.mostrarMensagemJsfSucesso("ofertas registrado com sucesso");
				Faces.redirect("./igreja/ofertasdaigreja.xhtml");
			}catch(RuntimeException  e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de compras");
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

		public OfertaDaIgreja getOferta() {
			return oferta;
		}

		public void setOferta(OfertaDaIgreja oferta) {
			this.oferta = oferta;
		}

		public List<OfertaDaIgreja> getListaDeOfertas() {
			return listaDeOfertas;
		}

		public void setListaDeOfertas(List<OfertaDaIgreja> listaDeOfertas) {
			this.listaDeOfertas = listaDeOfertas;
		}

		public List<OfertaDaIgreja> getListaDeOfertasPraLancamento() {
			return listaDeOfertasPraLancamento;
		}

		public void setListaDeOfertasPraLancamento(List<OfertaDaIgreja> listaDeOfertasPraLancamento) {
			this.listaDeOfertasPraLancamento = listaDeOfertasPraLancamento;
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
