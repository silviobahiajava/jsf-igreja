package meucursoJPA.model.financeiro;

import javax.persistence.*;

import meucursoJPA.model.GenericDomain;
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
