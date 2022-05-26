package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDoPeCriancasDao;
import daos.GrupoDao;
import daos.MembroDao;
import daos.OfertaDasCriancasDao;
import entidades.CampanhaDoPeCriancas;
import entidades.DizimoDaIgreja;
import entidades.Grupo;
import entidades.Membro;
import entidades.OfertaDasCriancas;

@ManagedBean(name="campanhadopecriancasBean")
@ViewScoped
public class CampanhaDoPeCriancasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private CampanhaDoPeCriancas campanha = new CampanhaDoPeCriancas();
	private CampanhaDoPeCriancasDao cdao=new CampanhaDoPeCriancasDao();
	private GrupoDao gdao=new GrupoDao();
	private List<CampanhaDoPeCriancas> campanhas = new ArrayList<CampanhaDoPeCriancas>();
	private List<Membro>contribuintes=new ArrayList<Membro>();
	private MembroDao membroDao=new MembroDao();
	private OfertaDasCriancas oferta=new OfertaDasCriancas();
	private OfertaDasCriancasDao ofertaDao=new OfertaDasCriancasDao();
	private int tamanhodope;
	private BigDecimal taxa;
	private BigDecimal valorRecebido;
	private BigDecimal valorAPagarPorMes;
	private BigDecimal valorPago;
	private BigDecimal troco;
	private BigDecimal totalMesesPagos;
	private int totalMeses;
	private boolean trocoParaOferta;
	private boolean trocoParaDevolver;
	private int totalParcelasQueDesejaPagar;
	//private OfertaDasCriancas ofertaTroco;
	
	private List<Grupo> grupos;
	
	@PostConstruct
	public void listar() {
		 try{
		
			 campanhas=cdao.buscarCampanhaDoPeCriancasPorGrupo(3L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}

	public void novo() {
		campanha = new CampanhaDoPeCriancas();
		grupos=gdao.listar();
		contribuintes=membroDao.listar();
		
	}

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(3L);
			Utils.mostrarMensagemJsfSucesso("valor recebido "+valorRecebido);
			totalMesesPagos=valorRecebido.divide(valorAPagarPorMes,2);
			//Utils.mostrarMensagemJsfSucesso("total de meses que deseja pagar "+totalMesesPagos);
			totalMeses=Utils.converteBigDecimalParaInt(totalMesesPagos);
			Utils.mostrarMensagemJsfSucesso("total de meses que deseja pagar "+totalMeses);
			valorPago=valorAPagarPorMes.multiply(new BigDecimal(totalMeses));
			Utils.mostrarMensagemJsfSucesso("os "+totalMeses+" vale "+valorPago);
			troco=valorRecebido.subtract(valorPago);
			Utils.mostrarMensagemJsfSucesso("seu troco � de "+troco);
			campanha.setContribuinte(campanha.getContribuinte());
			campanha.setDataPagamento(campanha.getDataPagamento());
			campanha.setGrupo(grupo);
			campanha.setObjetivoCampanha("condi��es para realizar a EBF");
			campanha.setValorPraPagar(valorPago);
			campanha.setDescricao("campanha do p�");
			campanha.setNumeroParcelasPagas(totalMeses);
			
			if(trocoParaOferta){
			oferta.setDataOfertaCrianca(campanha.getDataPagamento());
			oferta.setDescricao("TROCO DA CAMPANHA DO P�");
			oferta.setValorOfertaCrianca(troco);
			oferta.setGrupo(grupo);
			ofertaDao.salvarOferta(oferta);
			Utils.mostrarMensagemJsfSucesso("troco lan�ado como oferta" );
			}if(trocoParaDevolver){
				Utils.mostrarMensagemJsfSucesso("seu troco � de "+troco);
			}if(totalParcelasQueDesejaPagar!=totalMeses){
				valorPago=taxa.multiply(new BigDecimal(totalMeses));
			}
			cdao.salvarContribuicaoCampanhaDoPe(campanha);
			campanhas=cdao.buscarCampanhaDoPeCriancasPorGrupo(3L);
			Utils.mostrarMensagemJsfSucesso("campanha registrada com sucesso");
			
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}

	
	
	
	
	public CampanhaDoPeCriancas getCampanha() {
		return campanha;
	}

	public void setCampanha(CampanhaDoPeCriancas campanha) {
		this.campanha = campanha;
	}

	public List<CampanhaDoPeCriancas> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<CampanhaDoPeCriancas> campanhas) {
		this.campanhas = campanhas;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Membro> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Membro> contribuintes) {
		this.contribuintes = contribuintes;
	}

	public int getTamanhodope() {
		return tamanhodope;
	}

	public void setTamanhodope(int tamanhodope) {
		this.tamanhodope = tamanhodope;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	
	
	public BigDecimal getValorAPagarPorMes() {
		return valorAPagarPorMes;
	}

	public void setValorAPagarPorMes(BigDecimal valorAPagarPorMes) {
		this.valorAPagarPorMes = valorAPagarPorMes;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public void calcularValorPraPagar(){
		
	}
	
	public void calcularValorCampanhaDoPe(){
		/*int tamanhoPe=Integer.parseInt(Utils.recebeString("tamanho do pe"));
		BigDecimal taxaCalculo=new BigDecimal("0.10");
		BigDecimal valorAPagarPorMes=taxaCalculo.multiply(new BigDecimal(tamanhoPe));
		Utils.mostrarMensagemSwing("valor a pagar por m�s "+valorAPagarPorMes);
		
		BigDecimal valorRecebido=Utils.converteStringPraBigDecimal("valor recebido");
		Utils.mostrarMensagemSwing("valor recebido "+valorRecebido);
		BigDecimal totalMesesPagos=valorRecebido.divide(valorAPagarPorMes,2);
		int totalMeses=Utils.converteBigDecimalParaInt(totalMesesPagos);
		Utils.mostrarMensagemSwing("total de meses pagos "+totalMeses);
		BigDecimal valorPago=valorAPagarPorMes.multiply(new BigDecimal(totalMeses));
		BigDecimal troco=valorRecebido.subtract(valorPago);
		Utils.mostrarMensagemSwing("troco "+troco);*/
		
		taxa=new BigDecimal("0.10");
		valorAPagarPorMes=taxa.multiply(new BigDecimal(tamanhodope));
		Utils.mostrarMensagemJsfSucesso("valor por m�s "+valorAPagarPorMes);
		
		
		
		
		
	}

	public OfertaDasCriancas getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDasCriancas oferta) {
		this.oferta = oferta;
	}

	public BigDecimal getTotalMesesPagos() {
		return totalMesesPagos;
	}

	public void setTotalMesesPagos(BigDecimal totalMesesPagos) {
		this.totalMesesPagos = totalMesesPagos;
	}

	public int getTotalMeses() {
		return totalMeses;
	}

	public void setTotalMeses(int totalMeses) {
		this.totalMeses = totalMeses;
	}

	public boolean isTrocoParaOferta() {
		return trocoParaOferta;
	}

	public void setTrocoParaOferta(boolean trocoParaOferta) {
		this.trocoParaOferta = trocoParaOferta;
	}

	public boolean isTrocoParaDevolver() {
		return trocoParaDevolver;
	}

	public void setTrocoParaDevolver(boolean trocoParaDevolver) {
		this.trocoParaDevolver = trocoParaDevolver;
	}

	public int getTotalParcelasQueDesejaPagar() {
		return totalParcelasQueDesejaPagar;
	}

	public void setTotalParcelasQueDesejaPagar(int totalParcelasQueDesejaPagar) {
		this.totalParcelasQueDesejaPagar = totalParcelasQueDesejaPagar;
	}
	
}
