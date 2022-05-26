package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.CartaDeMudanca;
import entidades.Relatorios;
import enums.DiaEnum;
import enums.MesEnum;
import utils.Utils;


@ManagedBean(name="cartamudancaBean")
@ViewScoped
public class CartaMudancaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartaDeMudanca cartaMudanca=new CartaDeMudanca();
	private boolean casado;
	private boolean temFilhos;
	private List<CartaDeMudanca>lista=new ArrayList<CartaDeMudanca>();
	private DiaEnum dia;
	private MesEnum mes;
	
	
	public void novo(){
		cartaMudanca=new CartaDeMudanca();
		//lista=new ArrayList<CartaConvite>();
	}
	
	public void verificarEstadoCivil(){
		if(cartaMudanca.getEstadoCivilEnum().equals("SIM")){
		
		  casado=true;
		  Utils.mostrarMensagemJsfSucesso("membro casado");
		}if(cartaMudanca.getEstadoCivilEnum().equals("NAO")){
			casado=false;
			  Utils.mostrarMensagemJsfSucesso("não é casado");
		}
	}
	
	
	public void verificarEstadoFilhos(){
		if(cartaMudanca.getTemFilhosEnum().equals("SIM")){
		  temFilhos=true;
		  Utils.mostrarMensagemJsfSucesso("possui filhos");
		}if(cartaMudanca.getTemFilhosEnum().equals("NAO")){
		 temFilhos=false;
		  Utils.mostrarMensagemJsfSucesso("não possui filhos");
		}
	}
	public void gerarCartaDeMundanca(){
		try{
		if(casado){
			registrarCartaMudancaCasado();
		}
		if(!casado){
			registrarCartaMudancaSolteiro();
		}
		if(casado && temFilhos){
			registrarCartaMudancaCasadoComFilhos();
		}
		
		if(casado && !temFilhos){
			registrarCartaMudancaCasado();
		}
		}catch(Exception e){
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("ocorreu erro em "+e.getMessage()+"\n "+e.getLocalizedMessage());
		}
	}
	
	public void registrarCartaMudancaSolteiro(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoDeApresentacao();
		cartaMudanca.setDataCarta(dataCarta);
		
		lista.add(cartaMudanca);
		Relatorios.gerarCartaMudanca(lista);
		novo();
	}
	
	public void mostrarCartaMudancaSolteiroNaWeb(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoDeApresentacao();
		cartaMudanca.setDataCarta(dataCarta);
		lista.add(cartaMudanca);
		Relatorios.mostrarCartaDeMudancaSolteiroNaaWeb(lista);
	}
	
	public void registrarCartaMudancaCasado(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setEsposa(cartaMudanca.getEsposa());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoAdmissao();
		cartaMudanca.setDataCarta(dataCarta);
		lista.add(cartaMudanca);
		Relatorios.gerarCartaMudancaCasal(lista);
		novo();
	}
	public void mostrarCartaMudancaCasalNaWeb(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setEsposa(cartaMudanca.getEsposa());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoAdmissao();
		cartaMudanca.setDataCarta(dataCarta);
		lista.add(cartaMudanca);
		Relatorios.mostrarCartaDeMudancaCasalNaaWeb(lista);
	}
	
	
	public void registrarCartaMudancaCasadoComFilhos(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setEsposa(cartaMudanca.getEsposa());
		cartaMudanca.setFilhos(cartaMudanca.getFilhos());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoAdmissao();
		cartaMudanca.setDataCarta(dataCarta);
		lista.add(cartaMudanca);
		Relatorios.gerarCartaMudancaCasalComFilhos(lista);
		novo();
	}
	
	public void mostrarCartaMudancaCasalEFilhosNaWeb(){
		lista=new ArrayList<CartaDeMudanca>();
		cartaMudanca.setNome(cartaMudanca.getNome());
		cartaMudanca.setEsposa(cartaMudanca.getEsposa());
		cartaMudanca.setFilhos(cartaMudanca.getFilhos());
		cartaMudanca.setCargo(cartaMudanca.getCargo());
		cartaMudanca.setAnoAdmissao(cartaMudanca.getAnoAdmissao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		String dataCarta=diaDeApresentacao+" de "+mesDeApresentacao+" de "+cartaMudanca.getAnoAdmissao();
		cartaMudanca.setDataCarta(dataCarta);
		lista.add(cartaMudanca);
		Relatorios.mostrarCartaDeMudancaCasaFilhosNalaWeb(lista);
	}
	public CartaDeMudanca getCartaMudanca() {
		return cartaMudanca;
	}
	public void setCartaMudanca(CartaDeMudanca cartaMudanca) {
		this.cartaMudanca = cartaMudanca;
	}
	public boolean isCasado() {
		return casado;
	}
	public void setCasado(boolean casado) {
		this.casado = casado;
	}
	public boolean isTemFilhos() {
		return temFilhos;
	}
	public void setTemFilhos(boolean temFilhos) {
		this.temFilhos = temFilhos;
	}
	public List<CartaDeMudanca> getLista() {
		return lista;
	}
	public void setLista(List<CartaDeMudanca> lista) {
		this.lista = lista;
	}

	public DiaEnum getDia() {
		return dia;
	}

	public void setDia(DiaEnum dia) {
		this.dia = dia;
	}

	public MesEnum getMes() {
		return mes;
	}

	public void setMes(MesEnum mes) {
		this.mes = mes;
	}
	
	
	

	
}
