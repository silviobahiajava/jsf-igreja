package beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.CampanhaDao;
import daos.GrupoDao;
import entidades.Campanha;
import entidades.Grupo;

@ManagedBean(name="campanhaBean")
@ViewScoped
public class CampanhaBean {
	private Campanha campanha=new Campanha();
	private CampanhaDao campanhaDao=new CampanhaDao();
	private List<Campanha>campanhas;
	private List<Grupo>grupos;
	private String grupo;
	private Long codigoGrupo;
	
	
	
	private UsuarioBean usuarioBean;
	
	
	
	@PostConstruct
	public void listar(){
		try{
		
		
		campanhas=campanhaDao.buscarCampanhaPorCodigo(8L);
		
		
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar");
		}
	}
	public void novo(){
		campanha=new Campanha();
		GrupoDao grupoDao=new GrupoDao();
		grupos=grupoDao.listar();
	}
    public void verificarGrupo(){
    	int numero=Integer.parseInt(campanha.getGrupo().getNome());
    	switch(numero){
    	
    	case 1:
    	{
    		Utils.mostrarMensagemJsfSucesso("grupo escolhido foi Mocidade");
    		try {
    			if(usuarioBean.getUsuario().getFuncaoUsuario().equals("TESOUREIROMOCIDADE")){
				Faces.redirect("./pages/lancamentos_mocidade.xhtml");
    			}
    			else
    			{
    				Utils.mostrarMensagemJsfSucesso("voce n�o � tesoureiro da mocidade");
    			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	}
    	case 2:
    	{
    		Utils.mostrarMensagemJsfSucesso("grupo escolhido foi adolescentes");
    		break;
    	}
    	case 3:
    	{
    		Utils.mostrarMensagemJsfSucesso("grupo escolhido foi coreografia feminina adulto");
    		break;
    	}
    	}
    	
    }
	public void salvar(){
		try{
			//oferta.setGrupo(oferta.getGrupo());
			campanhaDao.merge(campanha);
			novo();
			campanhas=campanhaDao.buscarCampanhaPorCodigo(8L);
			Utils.mostrarMensagemJsfSucesso(" campanha  foi   registrada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao registrar o d�zimo");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			campanha = (Campanha) evento.getComponent().getAttributes().get("campanhaSelecionada");
             campanhaDao.excluir(campanha);
			campanhas=campanhaDao.listar();
			Utils.mostrarMensagemJsfSucesso("campanha exclu�da ou cancelado");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		campanha = (Campanha) evento.getComponent().getAttributes().get("campanhaSelecionada");
	}
	
	
	public void buscar() {
		try {
				
				//ofertas=ofertaDao.buscarOfertaPorDataEGrupo(oferta.getDataOferta(),oferta.getGrupo().getNome());
			    campanhas = campanhaDao.buscarCampanhaPorData(campanha.getDataCampanha());
			//ofertas=ofertaDao.buscarOfertaPorGrupo(oferta.getGrupo().getNome());
			for (Campanha c :  campanhas) {
				System.out.println(c.getValorCampanha());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	public void pegarGrupo(){
		 grupo=campanha.getGrupo().getNome();
	}
	
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public Long getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(Long codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Campanha getCampanha() {
		return campanha;
	}
	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	public List<Campanha> getCampanhas() {
		return campanhas;
	}
	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}
	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	
	
}
