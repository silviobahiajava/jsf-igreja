package beans;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import daos.GrupoDao;
import daos.LancamentoLivroCaixaDao;
import entidades.Grupo;
import entidades.LancamentoLivroCaixa;
import entidades.Oferta;
import utils.Utils;

@ManagedBean
@ViewScoped
public class LancamentoLivroCaixaBean {
	private List<LancamentoLivroCaixa>listalancamentosdoLivroCaixa;
	private LancamentoLivroCaixa lancamentoLivroCaixa;
	private List<Grupo> grupos=new ArrayList<Grupo>();
	private List<Oferta> ofertas=new ArrayList<Oferta>();
	private LancamentoLivroCaixaDao livroCaixaDao=new LancamentoLivroCaixaDao();
	private GrupoDao grupoDao=new GrupoDao();
	private Grupo grupo=new Grupo();
	
	public void novo() {
		lancamentoLivroCaixa=new LancamentoLivroCaixa();
		grupos=grupoDao.listar();
	}
	
	@PostConstruct
	public void listar() {
		try {
			listalancamentosdoLivroCaixa=livroCaixaDao.listar();
			
		}catch(Exception erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
		}
	}
	
	
	public void registrarLancamentoOferta() {
		try {
			grupo.setCodigo(grupo.getCodigo());
			Oferta oferta=new Oferta();
			oferta.setDataOferta(lancamentoLivroCaixa.getDataDoLancamento());
			oferta.setGrupo(lancamentoLivroCaixa.getGrupo());
			oferta.setValorOferta(lancamentoLivroCaixa.getReceita());
			
			GregorianCalendar calendario=new GregorianCalendar();
			calendario.setTime(oferta.getDataOferta());
			if(calendario.get(Calendar.MONTH)==30) {
				Utils.mostrarMensagemJsfSucesso("ultimo dia do mes");
				lancamentoLivroCaixa.setSaldoAnterior(lancamentoLivroCaixa.getSaldo());
			}
			if(calendario.get(Calendar.MONTH)==01) {
				Utils.mostrarMensagemJsfSucesso("primeiro dia do mes");
				lancamentoLivroCaixa.setSaldo(lancamentoLivroCaixa.getSaldoAnterior().add(lancamentoLivroCaixa.getReceita().subtract(lancamentoLivroCaixa.getDespesa())));
			}
			lancamentoLivroCaixa.setHistorico(lancamentoLivroCaixa.getHistorico());
			lancamentoLivroCaixa.setGrupo(grupo);
			lancamentoLivroCaixa.setDespesa(lancamentoLivroCaixa.getDespesa());
			lancamentoLivroCaixa.setReceita(lancamentoLivroCaixa.getReceita());
			ofertas.add(oferta);
			livroCaixaDao.merge(lancamentoLivroCaixa);
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}


	
	public List<LancamentoLivroCaixa> getListalancamentosdoLivroCaixa() {
		return listalancamentosdoLivroCaixa;
	}

	public void setListalancamentosdoLivroCaixa(List<LancamentoLivroCaixa> listalancamentosdoLivroCaixa) {
		this.listalancamentosdoLivroCaixa = listalancamentosdoLivroCaixa;
	}

	public LancamentoLivroCaixa getLancamentoLivroCaixa() {
		return lancamentoLivroCaixa;
	}

	public void setLancamentoLivroCaixa(LancamentoLivroCaixa lancamentoLivroCaixa) {
		this.lancamentoLivroCaixa = lancamentoLivroCaixa;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public void registrarOfertas() {
		try {
			
		}catch(RuntimeException e) {
			
		}
	}
	
	
}
