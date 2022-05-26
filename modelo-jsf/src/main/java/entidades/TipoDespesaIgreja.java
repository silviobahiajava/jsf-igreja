package entidades;

import javax.persistence.*;
@Entity
public class TipoDespesaIgreja extends GenericDomain{
	private static final long serialVersionUID = 1L;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
