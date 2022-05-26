package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import br.com.testejsf.utils.Utils;
import daos.ContribuicaoDao;
import daos.GrupoDao;
import daos.MembroDao;
import entidades.Contribuicao;
import entidades.Grupo;
import entidades.Membro;
@ManagedBean(name="contriubicaoBean")
@ViewScoped
public class ContribuicaoBean {
	private Contribuicao contribuicao=new Contribuicao();
	private ContribuicaoDao contribuicaoDao=new ContribuicaoDao();
	private MembroDao membroDao=new MembroDao();
	private List<Contribuicao>contribuicoes;
	private List<Grupo>grupos;
	private List<Membro>contribuintes=new ArrayList<Membro>();
	private String grupo;
	private Long codigoGrupo;
	
	
	
	private UsuarioBean usuarioBean;
	
	
	
	@PostConstruct
	public void listar(){
		try{
		
		contribuicoes=contribuicaoDao.buscarContribuicaoPorCodigo(8L);
		GrupoDao grupoDao=new GrupoDao();
		grupos=grupoDao.listar();
		
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar");
		}
	}
	public void novo(){
		contribuicao=new Contribuicao();
		GrupoDao grupoDao=new GrupoDao();
		grupos=grupoDao.listar();
		contribuintes=membroDao.listar();
	}
    public void verificarGrupo(){
    	/*switch(contribuicao.getGrupo().getNome()){
    	case "Mocidade":
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
    	case "adolescentes":
    	{
    		Utils.mostrarMensagemJsfSucesso("grupo escolhido foi adolescentes");
    		break;
    	}
    	case "coreografia feminina adulto":
    	{
    		Utils.mostrarMensagemJsfSucesso("grupo escolhido foi coreografia feminina adulto");
    		break;
    	}
    	}
    	*/
    }
	public void salvar(){
		try{
			//oferta.setGrupo(oferta.getGrupo());
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			contribuicao.setContribuinte(contribuicao.getContribuinte());
			contribuicao.setGrupo(grupo);
			contribuicao.setValorContribuicao(contribuicao.getValorContribuicao());
			contribuicao.setDataContribuicao(contribuicao.getDataContribuicao());
			contribuicaoDao.merge(contribuicao);
			novo();
			contribuicoes=contribuicaoDao.buscarContribuicaoPorCodigo(1L);
			Utils.mostrarMensagemJsfSucesso(" oferta  foi   registrada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao registrar o d�zimo");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			contribuicao = (Contribuicao) evento.getComponent().getAttributes().get("contribuicaoSelecionada");
             contribuicaoDao.excluir(contribuicao);
			contribuicoes=contribuicaoDao.listar();
			Utils.mostrarMensagemJsfSucesso("contriui��o exclu�da ou cancelado");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		contribuicao = (Contribuicao) evento.getComponent().getAttributes().get("contribuicaoSelecionada");
	}
	
	
	public void buscar() {
		try {
				
				//ofertas=ofertaDao.buscarOfertaPorDataEGrupo(oferta.getDataOferta(),oferta.getGrupo().getNome());
			    contribuicoes = contribuicaoDao.buscarContribuicaoPorData(contribuicao.getDataContribuicao());
			//ofertas=ofertaDao.buscarOfertaPorGrupo(oferta.getGrupo().getNome());
			for (Contribuicao c :  contribuicoes) {
				System.out.println(c.getValorContribuicao());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	public void pegarGrupo(){
		 grupo=contribuicao.getGrupo().getNome();
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
	public Contribuicao getContribuicao() {
		return contribuicao;
	}
	public void setContribuicao(Contribuicao contribuicao) {
		this.contribuicao = contribuicao;
	}
	public List<Contribuicao> getContribuicoes() {
		return contribuicoes;
	}
	public void setContribuicoes(List<Contribuicao> contribuicoes) {
		this.contribuicoes = contribuicoes;
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
	
	
	
}
