package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.ContasParaPagarDao;
import daos.GrupoDao;
import entidades.ContasPagas;
import entidades.ContasParaPagar;
import entidades.Grupo;

@ManagedBean(name="contasPagarBean")
@ViewScoped
public class ContasParaPagarBean {
	private ContasParaPagar contasPagar = new ContasParaPagar();
	private ContasParaPagarDao contasDao = new ContasParaPagarDao();
	private List<ContasParaPagar> listaContasPagar = new ArrayList<ContasParaPagar>();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private List<Grupo> grupos;
	private String nomeGrupo;
	private Long codigoGrupo;
	
	private ContasPagas contaPaga;
	
	public void pegarGrupoSelecionado(){
		nomeGrupo=contasPagar.getGrupo().getNome();
		codigoGrupo=contasPagar.getGrupo().getCodigo();
		Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}

	@PostConstruct
	public void listar() {
		
		buscar();
	}

	public void novo() {
		contasPagar = new ContasParaPagar();
		GrupoDao grupoDao = new GrupoDao();
		grupos = grupoDao.listar();
	}

	public void salvar() {
		try {
			GrupoDao gDao=new GrupoDao();
			Grupo grupo=gDao.buscar(codigoGrupo);
			contasPagar.setGrupo(grupo);
			contasDao.merge(contasPagar);
			novo();
			listaContasPagar = contasDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	
	
	/*public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setNome(nomeGrupo);
			compra.setGrupo(grupo);
			compraDao.merge(compra);
			novo();
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso para o grupo "+compra.getGrupo().getNome());
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}*/
	
	
	public void excluir(ActionEvent evento) {
		try {
			contasPagar = (ContasParaPagar) evento.getComponent().getAttributes().get("contaSelecionada");
			contasDao.excluir(contasPagar);
			listaContasPagar=contasDao.listar();
			Utils.mostrarMensagemJsfSucesso("opera��o realizada com sucesso");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}

	public void pagarConta(ActionEvent evento) {
		
		/*
		 * Contas Pagas
		 * private Date dataPagamento;
	private BigDecimal valorPagoParcial;
	private boolean temDesconto;
	private BigDecimal valorPagoComDesconto;
	private boolean temJuros;
	private BigDecimal valorPagoComJuro;
	@ManyToOne
	private Pagamento pagamento;
	
	@ManyToOne
	private ContasParaPagar contasParaPagar;
	
	@ManyToOne
	private Grupo grupo;
	
	@ManyToOne
	private Lancamento lancamento;
		 * 
		 * 
		 * 
		 */
		
		contasPagar = (ContasParaPagar) evento.getComponent().getAttributes().get("contaSelecionada");
		for(int posicao=0;posicao<listaContasPagas.size();posicao++) {
			ContasPagas contasPagas=new ContasPagas();
			//contasPagas.setContasParaPagar(contasPagar);
			contasPagas.setValorPagoParcial(contasPagar.getValorContaParaPagar());
			contasPagas.setDataPagamento(contasPagar.getDataVencimento());
			listaContasPagas.add(contasPagas);
		}
	}

	

	public ContasParaPagar getContasPagar() {
		return contasPagar;
	}

	public void setContasPagar(ContasParaPagar contasPagar) {
		this.contasPagar = contasPagar;
	}

	public List<ContasParaPagar> getListaContasPagar() {
		return listaContasPagar;
	}

	public void setListaContasPagar(List<ContasParaPagar> listaContasPagar) {
		this.listaContasPagar = listaContasPagar;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void buscar() {
		try {

			//compras = compraDao.buscarCompraPorData(compra.getDataCompra());
			//listaContasPagar=contasDao.buscarContasPorGrupo(codigoGrupo);
			listaContasPagar=contasDao.listar();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public ContasPagas getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(ContasPagas contaPaga) {
		this.contaPaga = contaPaga;
	}

	public List<ContasPagas> getListaContasPagas() {
		return listaContasPagas;
	}

	public void setListaContasPagas(List<ContasPagas> listaContasPagas) {
		this.listaContasPagas = listaContasPagas;
	}

	public Long getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(Long codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	
}
