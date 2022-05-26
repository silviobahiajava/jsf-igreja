package beans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.GrupoDao;
import daos.OfertaDaIgrejaDao;
import entidades.ContaAPagar;
import entidades.ContasPagas;
import entidades.Grupo;
import entidades.OfertaDaIgreja;
import filtros.OfertaFilter;

@ManagedBean(name="ofertadaigrejaBean")
@ViewScoped
public class OfertaDaIgrejaBean {
	private OfertaDaIgreja oferta = new OfertaDaIgreja();
	private OfertaDaIgrejaDao oidao=new OfertaDaIgrejaDao();
	private GrupoDao gdao=new GrupoDao();
	private List<OfertaDaIgreja> ofertas = new ArrayList<OfertaDaIgreja>();
	private ContasPagas contasPagas=new ContasPagas();
	private List<ContasPagas>listaContasPagas=new ArrayList<ContasPagas>();
	private ContaAPagar contasAPagar=new ContaAPagar();
	private List<ContaAPagar>listaContasAPagar=new ArrayList<ContaAPagar>();
	private List<Grupo> grupos;
	private Date dataInical;
	private Date dataFinal;
	private Long codigo;
	@ManagedProperty(value="#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	public long pegarCodigo(){
		Long codigo=autenticacaoBean.getUsuarioLogado().getGrupo().getCodigo();
		return codigo;
		
	}
	
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
			 codigo=pegarCodigo();
			 ofertas=oidao.buscarPorTipoDeOfertaECodigo("LANCADO",codigo);
			
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}
	
	public void listarOfertaPorData(){
		try{
		ofertas=oidao.mostrarOfertaPorData(8L,oferta.getDataOfertaIgreja());
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
	public void listarOfertasPorPeriodo(){
		try{
		OfertaFilter filter=new OfertaFilter();
		filter.setDataInical(dataInical);
		filter.setDataFinal(dataFinal);
		ofertas=oidao.mostrarTodasOfertasDoMes(filter);
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar ");
		}
	}
	
	public void novo() {
		oferta=new OfertaDaIgreja();
		grupos=gdao.listar();
	}

	public void salvar() {
		try {
			Grupo grupo =new Grupo();
		    codigo=pegarCodigo();
			grupo.setCodigo(codigo);
			oferta.setGrupo(grupo);
			
			/*oferta.setValorOfertaCrianca(oferta.getValorOfertaCrianca());
			oferta.setDataOfertaCrianca(oferta.getDataOfertaCrianca());
			ocdao.salvarOferta(oferta);
			novo();
			ofertas=ocdao.buscarOfertaDasCriancasPorGrupo(2L);*/
			oferta.setTipodeoferta("NAOLANCADO");
			oferta.setValorOfertaIgreja(oferta.getValorOfertaIgreja());
			oferta.setDataOfertaIgreja(oferta.getDataOfertaIgreja());
			oidao.salvarOferta(oferta);
			novo();
			
			
			
			
			
			
			Utils.mostrarMensagemJsfSucesso("oferta  registrada com sucesso");
			Faces.redirect("./igreja/lancamentodeofertasdaigreja.xhtml");
		} catch (RuntimeException  e) {
			Utils.mostrarMensagemJsfErro("erro ao salvar");
			e.printStackTrace();
			System.out.println("onde est� o erro ? "+e.getMessage()+"\n"+e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		 ofertas=compraDao.listarofertasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a vista registrada com sucess");
	}
	
	public void savarCompraAPrazo() {
		contasAPagar.setDataVencmento(contasAPagar.getDataVencimento());
		contasAPagar.setValor(contasAPagar.getValor());
		compra.setContaAPagar(contasAPagar);
		Grupo grupopesquisado=gdao.buscar(8L);
		compra.setGrupo(grupopesquisado);
		compraDao.salvar(compra);
		 ofertas=compraDao.listarofertasPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("compra a prazo registrada com sucesso");
	}
	
	*/
	
	/*public void excluir(ActionEvent evento) {
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("compraDaIgrejaSelecionada");
			compraDao.excluir(compra);
			ofertas = compraDao.listar();
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
			ofertas=oidao.buscarOfertaPorData(oferta.getDataOfertaIgreja());
			
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

	public OfertaDaIgreja getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDaIgreja oferta) {
		this.oferta = oferta;
	}

	public List<OfertaDaIgreja> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaDaIgreja> ofertas) {
		this.ofertas = ofertas;
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

	
	

}
