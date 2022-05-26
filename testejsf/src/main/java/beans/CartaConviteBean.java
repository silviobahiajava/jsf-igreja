package beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.CartaConvite;
import entidades.Relatorios;
import enums.DiaEnum;
import enums.MesEnum;


@ManagedBean(name="cartaconviteBean")
@ViewScoped
public class CartaConviteBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartaConvite cartaConvite=new CartaConvite();
	private List<CartaConvite>lista=new ArrayList<CartaConvite>();
	private DiaEnum dia;
	private MesEnum mes;
	
	
	
	public void novo(){
		cartaConvite=new CartaConvite();
		//lista=new ArrayList<CartaConvite>();
	}
	
	public void registrarCartaConvite(){
		lista=new ArrayList<CartaConvite>();
		cartaConvite.setConvidado(cartaConvite.getConvidado());
		cartaConvite.setNomeDoEvento(cartaConvite.getNomeDoEvento());
		cartaConvite.setDataDoEvento(cartaConvite.getDataDoEvento());
		cartaConvite.setTelefoneConfirmacao(cartaConvite.getTelefoneConfirmacao());
		cartaConvite.setEmailConfirmacao(cartaConvite.getEmailConfirmacao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		cartaConvite.setDiaDeApresentacao(diaDeApresentacao);
		cartaConvite.setMesDeApresentacao(mesDeApresentacao);
		cartaConvite.setAnoDeApresentacao(cartaConvite.getAnoDeApresentacao());
		
		
		lista.add(cartaConvite);
		Relatorios.gerarCartaConvite(lista);
		novo();
	}
	public void mostrarCartaConviteNaWeb(){
		
		lista=new ArrayList<CartaConvite>();
		cartaConvite.setConvidado(cartaConvite.getConvidado());
		cartaConvite.setNomeDoEvento(cartaConvite.getNomeDoEvento());
		cartaConvite.setDataDoEvento(cartaConvite.getDataDoEvento());
		cartaConvite.setTelefoneConfirmacao(cartaConvite.getTelefoneConfirmacao());
		cartaConvite.setEmailConfirmacao(cartaConvite.getEmailConfirmacao());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		cartaConvite.setDiaDeApresentacao(diaDeApresentacao);
		cartaConvite.setMesDeApresentacao(mesDeApresentacao);
		cartaConvite.setAnoDeApresentacao(cartaConvite.getAnoDeApresentacao());
		lista.add(cartaConvite);
		Relatorios.mostrarCartaConviteNaWeb(lista);
	}
	
	
	public CartaConvite getCartaConvite() {
		return cartaConvite;
	}

	public void setCartaConvite(CartaConvite cartaConvite) {
		this.cartaConvite = cartaConvite;
	}

	public List<CartaConvite> getLista() {
		return lista;
	}

	public void setLista(List<CartaConvite> lista) {
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
