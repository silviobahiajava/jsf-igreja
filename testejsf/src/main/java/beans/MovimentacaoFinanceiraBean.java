package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import entidades.MovimentacaoFinanceira;

@ManagedBean(name="livroCaixa")
public class MovimentacaoFinanceiraBean {
	private MovimentacaoFinanceira movimentacaoMensal;
	private List<MovimentacaoFinanceira>movimentosDoMes;
	public MovimentacaoFinanceira getMovimentacaoMensal() {
		return movimentacaoMensal;
	}
	public void setMovimentacaoMensal(MovimentacaoFinanceira movimentacaoMensal) {
		this.movimentacaoMensal = movimentacaoMensal;
	}
	public List<MovimentacaoFinanceira> getMovimentosDoMes() {
		return movimentosDoMes;
	}
	public void setMovimentosDoMes(List<MovimentacaoFinanceira> movimentosDoMes) {
		this.movimentosDoMes = movimentosDoMes;
	}
	
	
	
}
