package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraDao;
import daos.GrupoDao;
import daos.LancamentoDao;
import daos.OfertaDao;
import entidades.Compra;
import entidades.Grupo;
import entidades.Lancamento;
import entidades.Oferta;

@ManagedBean(name="lancamentoTesteBean")
@ViewScoped
public class LancamentoTesteBean {
	private Lancamento lancamento=new Lancamento();
	private Oferta oferta;
	private Compra compra;
	private Long codigodomeugrupo;
	
	//listas
	private List<Lancamento> lancamentos=new ArrayList<Lancamento>();
	private List<Grupo>grupos=new ArrayList<Grupo>();
	private List<Oferta>ofertas;
	private List<Compra>compras;
	
	//daos
	
	private LancamentoDao lcdao;
	private GrupoDao gdao=new GrupoDao();
	private OfertaDao ofdao;
	private CompraDao cpdao;
	
	
	
	@PostConstruct
	public void listar() {
		try {
			lancamento=new Lancamento();
			LancamentoDao ldao=new LancamentoDao();
			lancamentos=ldao.buscarLancamentoPorCodigo(codigodomeugrupo);
			
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public GrupoDao getGdao() {
		return gdao;
	}

	public void setGdao(GrupoDao gdao) {
		this.gdao = gdao;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Long getCodigodomeugrupo() {
		return codigodomeugrupo;
	}

	public void setCodigodomeugrupo(Long codigodomeugrupo) {
		this.codigodomeugrupo = codigodomeugrupo;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	public void pegarCodigo(){
		Utils.mostrarMensagemJsfSucesso("codigo do produto "+codigodomeugrupo);
	}
	
}
