package meucursoJPA.model.secretaria;

import javax.persistence.ManyToOne;

public class Foto {
	
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
