package daos;

import java.util.ArrayList;
import java.util.List;

import entidades.CartaConvite;

public class CartConviteFactory {
	public static List load(){
		CartaConvite cartaConvite=new CartaConvite();
		List<CartaConvite>lista=new ArrayList<CartaConvite>();
		lista.add(cartaConvite);
		return lista;
	}
}
