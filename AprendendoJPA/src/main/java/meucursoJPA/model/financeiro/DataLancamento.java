package meucursoJPA.model.financeiro;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import meucursoJPA.model.GenericDomain;
@Entity
@Table(name="data_lancamento")
public class DataLancamento extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	private Date dataLancamento;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
}
