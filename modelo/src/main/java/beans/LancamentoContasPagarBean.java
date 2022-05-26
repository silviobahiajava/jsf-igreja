package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.ContasParaPagarDao;
import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.ContasParaPagar;
import entidades.Grupo;
import entidades.Lancamento;
import utils.Utils;

@ManagedBean(name="lancamentoContasPagarBean")
@ViewScoped
public class LancamentoContasPagarBean {
	private Lancamento lancamento=new Lancamento();
	private ContasParaPagar contasPagar;
	private LancamentoDao lancamentoDao=new LancamentoDao();
	private List<ContasParaPagar>listaContasPagar;
	private List<ContasParaPagar>listaContasPagarLancamento;
	private String nomeGrupo;
	private Long codigoGrupo;
	
	public void pegarGrupoSelecionado(){
		nomeGrupo=contasPagar.getGrupo().getNome();
		codigoGrupo=contasPagar.getGrupo().getCodigo();
		Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}

	
	@PostConstruct
	public void novo() {
		try {
			lancamento=new Lancamento();
			lancamento.setData(new Date());
			lancamento.setDespesa(new BigDecimal("0.00"));
			 ContasParaPagarDao contasDao=new ContasParaPagarDao();
			// listaCompras=compraDao.listar();
			 listaContasPagar=contasDao.buscarContasPorGrupo(codigoGrupo);
            listaContasPagarLancamento=new ArrayList<ContasParaPagar>();
			
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
		for (int posicao = 0; posicao < listaContasPagarLancamento.size(); posicao++) {
			ContasParaPagar contasParaPagar = listaContasPagarLancamento.get(posicao);
			//venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
			lancamento.setDespesa(lancamento.getDespesa().add(contasParaPagar.getValorContaParaPagar()));
		}
	}
		public void adicionarContasParaPagar(ActionEvent evento){
			contasPagar=(ContasParaPagar) evento.getComponent().getAttributes().get("contaSelecionada");
			listaContasPagarLancamento.add(contasPagar);
			calcular();
			Utils.mostrarMensagemJsfSucesso("adicionada na lista de compras do lançamento");
				listaContasPagar.remove(contasPagar);
			}
		
		public void salvarLancamentoDeCompras(){
			LancamentoDao ldao=new LancamentoDao();
			try{
				GrupoDao gdao=new GrupoDao();
				Grupo grupopesquisado=gdao.buscar(codigoGrupo);
				lancamento.setGrupo(grupopesquisado);
			
			ldao.registrarContasParaPagar(lancamento, listaContasPagarLancamento);
			Utils.mostrarMensagemJsfSucesso("conta registrada realizado com sucesso");
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao fazer lançamento de compras");
				e.printStackTrace();
			}
			
		}
		
}
