package beans;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import daos.GrupoDao;
import daos.LancamentoDao;
import entidades.Grupo;
import utils.Utils;

@ManagedBean(name="cxBean")
@ViewScoped
public class CaixaBean {
//private Lancamento lancamento;
private GrupoDao gdao=new GrupoDao();
private LancamentoDao ldao=new LancamentoDao();
private Long codigo;
private BigDecimal valorDespesa;
private BigDecimal valorReceita;
private BigDecimal valorSaldo;

public void mostrarTotalDespesa() {
	Grupo grupo=gdao.buscar(codigo);
	valorReceita=ldao.mostrarReceitaPorGrupo(grupo.getCodigo());
	valorDespesa=ldao.mostrarDespesaPorGrupo(grupo.getCodigo());
	valorSaldo=ldao.mostrarSaldoPorGrupo(grupo.getCodigo());
	Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
			"saldo "+valorSaldo);
	if(valorDespesa.compareTo(valorReceita)==1) {
		Utils.mostrarMensagemJsfSucesso("saldo negativo");
	}else {
		Utils.mostrarMensagemJsfSucesso("saldo positivo");
	}
	if(valorReceita==null || valorDespesa==null) {
		Utils.mostrarMensagemJsfSucesso("não existe receita ou despesa para este lancamento");
	}
}
public BigDecimal getValorDespesa() {
	return valorDespesa;
}
public void setValorDespesa(BigDecimal valorDespesa) {
	this.valorDespesa = valorDespesa;
}
public Long getCodigo() {
	return codigo;
}
public void setCodigo(Long codigo) {
	this.codigo = codigo;
}
public BigDecimal getValorReceita() {
	return valorReceita;
}
public void setValorReceita(BigDecimal valorReceita) {
	this.valorReceita = valorReceita;
}
public BigDecimal getValorSaldo() {
	return valorSaldo;
}
public void setValorSaldo(BigDecimal valorSaldo) {
	this.valorSaldo = valorSaldo;
}



}
