package meucursoJPA.model.secretaria;
import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
@Entity
@Table(name="foto_membro")
public class FotoMembro extends GenericDomain {
	
	private static final long serialVersionUID = 1L;
	
	@Lob
	private byte[]imagem;
	
	@ManyToOne
	private Membro membro;

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	
	

}
