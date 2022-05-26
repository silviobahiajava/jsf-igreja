package beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraEscolaDominicalDao;
import daos.ComprasDasCriancasDao;
import daos.GrupoDao;
import daos.OfertaDaIgrejaDao;
import entidades.CompraDasCriancas;
import entidades.CompraEscolaDominical;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.OfertaDaIgreja;
import enums.TipoCompra;
import filtros.OfertaFilter;

@ManagedBean(name="compraescoladominicalBean")
@ViewScoped
public class CompraEscolaDominicalBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private CompraEscolaDominical compra = new CompraEscolaDominical();
	CompraEscolaDominicalDao cdao=new CompraEscolaDominicalDao();
	private GrupoDao gdao=new GrupoDao();
	private List<CompraEscolaDominical> compras = new ArrayList<CompraEscolaDominical>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private Date dataInical;
	private Date dataFinal;
	private boolean compraAVista;
	private boolean compraAPrazo;
/*private String nomeGrupo;
private Long codigoGrupo;

	public void pegarGrupoSelecionado(){
	nomeGrupo=compra.getGrupo().getNome();
		codigoGrupo=compra.getGrupo().getCodigo();
		Utils.mostrarMensagemJsfSucesso("compra do grupo "+nomeGrupo);
	}*/

	@PostConstruct
	public void listar() {
		 try{
		
			 
			 compras=cdao.buscarCompraEscolaDominicalPorGrupo(7L);
			 
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}
	
	
	public void listarComprasPorData(){
		try{
		
		compras=cdao.mostrarCompraEscolaDominicalPorData(7L,compra.getDataCompraEBD());
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar");
		}
	}

	
	public void buscarOfertasDoMes() throws ParseException{
		OfertaFilter filter=new OfertaFilter();
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		filter.setDataInical(formato.parse("01/06/2018"));
		filter.setDataFinal(formato.parse("30/06/2018"));
		OfertaDaIgrejaDao dao=new OfertaDaIgrejaDao();
		List<OfertaDaIgreja>ofertas=dao.mostrarTodasOfertasDoMes(filter);
		System.out.println("ofertas do mes");
		for(OfertaDaIgreja oferta:ofertas){
			System.out.println("data "+oferta.getDataOfertaIgreja()+"---valor "+oferta.getValorOfertaIgreja());
		}
	}
	
	public void listarCompraDasCriancasPorPeriodo(){
		try{
		OfertaFilter filter=new OfertaFilter();
		filter.setDataInical(dataInical);
		filter.setDataFinal(dataFinal);
		compras=cdao.mostrarTodasComprasDasCriancasPorPeriodo(filter);
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar ");
		}
	}

	public void novo() {
		compra = new CompraEscolaDominical();
		grupos=gdao.listar();
	}
	

	public void salvar() {
		try {
			Grupo grupo=new Grupo();
			grupo.setCodigo(7L);
			compra.setGrupo(grupo);
			if(compraAVista){
				
				compra.setTipoCompra(TipoCompra.AVISTA);
			}
			if(compraAPrazo){
				
				compra.setTipoCompra(TipoCompra.APRAZO);
			}
			compra.setTipoCompraEBDEnum(compra.getTipoCompraEBDEnum());
			compra.setDescricao(compra.getDescricao());
			
			
			
			compra.setValor(compra.getValor());
			
			compra.setNumeroNota(compra.getNumeroNota());
			compra.setDataCompraEBD(compra.getDataCompraEBD());
			cdao.salvarCompra(compra);
			
			novo();
			
			compras=cdao.buscarCompraEscolaDominicalPorGrupo(7L);
			Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
		}
	}
	
	
	/*public void salvarCompraAVista() {
		contasPagas.setDataPagamento(contasPagas.getDataPagamento());
		contasPagas.setValorPagoParcial(contasPagas.getValorPagoParcial());
		//contasPagasDao.salvar(contasPagas);
		compra.setContasPagas(contasPagas);
		compra.setTipoCompra(TipoCompra.AVISTA);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 compras=compraDao.listarComprasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a vista registrada com sucess");
	}
	
	public void savarCompraAPrazo() {
		contasAPagar.setDataVencmento(contasAPagar.getDataVencimento());
		contasAPagar.setValor(contasAPagar.getValor());
		compra.setContaAPagar(contasAPagar);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 compras=compraDao.listarComprasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a prazo registrada com sucesso");
	}
	
	*/
	
	/*public void excluir(ActionEvent evento) {
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
			compraDao.excluir(compra);
			compras = compraDao.listar();
			Utils.mostrarMensagemJsfSucesso("compra excluida do sistema");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
		grupos = gdao.listar();
	}*/

	

	
	// novo metodo

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void buscar() {
		try {

			
			compras=cdao.buscarCompraPorData(compra.getDataCompraEBD());
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public ContasPagas getContasPagas() {
		return contasPagas;
	}

	public void setContasPagas(ContasPagas contasPagas) {
		this.contasPagas = contasPagas;
	}

	public List<ContasPagas> getListaContasPagas() {
		return listaContasPagas;
	}

	public void setListaContasPagas(List<ContasPagas> listaContasPagas) {
		this.listaContasPagas = listaContasPagas;
	}

	public ContaAPagar getContasAPagar() {
		return contasAPagar;
	}

	public void setContasAPagar(ContaAPagar contasAPagar) {
		this.contasAPagar = contasAPagar;
	}

	public List<ContaAPagar> getListaContasAPagar() {
		return listaContasAPagar;
	}

	public void setListaContasAPagar(List<ContaAPagar> listaContasAPagar) {
		this.listaContasAPagar = listaContasAPagar;
	}

	


	public CompraEscolaDominical getCompra() {
		return compra;
	}


	public void setCompra(CompraEscolaDominical compra) {
		this.compra = compra;
	}


	public List<CompraEscolaDominical> getCompras() {
		return compras;
	}


	public void setCompras(List<CompraEscolaDominical> compras) {
		this.compras = compras;
	}


	public Date getDataInical() {
		return dataInical;
	}


	public void setDataInical(Date dataInical) {
		this.dataInical = dataInical;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	public boolean isCompraAVista() {
		return compraAVista;
	}


	public void setCompraAVista(boolean compraAVista) {
		this.compraAVista = compraAVista;
	}


	public boolean isCompraAPrazo() {
		return compraAPrazo;
	}


	public void setCompraAPrazo(boolean compraAPrazo) {
		this.compraAPrazo = compraAPrazo;
	}

	

	

	
	
//	public String getNomeGrupo() {
//		return nomeGrupo;
//	}
//
//	public void setNomeGrupo(String nomeGrupo) {
//		this.nomeGrupo = nomeGrupo;
//	}
}
