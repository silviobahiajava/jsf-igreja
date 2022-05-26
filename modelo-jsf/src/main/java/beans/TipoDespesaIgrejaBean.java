package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import daos.TipoDespesaIgrejaDao;
import entidades.TipoDespesaIgreja;
import utils.Utils;

@ManagedBean(name="tipodespesaigrejaBean")
@ViewScoped
public class TipoDespesaIgrejaBean {
	private Long codigoTipoDespesa;
	private TipoDespesaIgreja tipoDespesaIgreja=new TipoDespesaIgreja();
	private List<TipoDespesaIgreja>listaTipoDesesasIgreja=new ArrayList<TipoDespesaIgreja>();
	private TipoDespesaIgrejaDao tipoDespesaIgrejaDao=new TipoDespesaIgrejaDao();
	
	@PostConstruct
	public void init(){
		
		tipoDespesaIgreja=new TipoDespesaIgreja();
	}
	
	public void listar(){
		try{
			listaTipoDesesasIgreja=tipoDespesaIgrejaDao.listar();
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao listar as despesas "+e.getMessage());
		}
	}
	
	public void carregarEdicao(){
		try{
		tipoDespesaIgreja=tipoDespesaIgrejaDao.buscar(codigoTipoDespesa);
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao carregar a despesa "+e.getMessage());
	}
		
	}
		
		
	
	public void cadastrar(){
		try{
			tipoDespesaIgreja.setDescricao(tipoDespesaIgreja.getDescricao());
			tipoDespesaIgrejaDao.salvar(tipoDespesaIgreja);
			Utils.mostrarMensagemJsfSucesso("despesa registrada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfErro("erro ao carregar a despesa "+e.getMessage());
		}
	}

	public Long getCodigoTipoDespesa() {
		return codigoTipoDespesa;
	}

	public void setCodigoTipoDespesa(Long codigoTipoDespesa) {
		this.codigoTipoDespesa = codigoTipoDespesa;
	}

	public TipoDespesaIgreja getTipoDespesaIgreja() {
		return tipoDespesaIgreja;
	}

	public void setTipoDespesaIgreja(TipoDespesaIgreja tipoDespesaIgreja) {
		this.tipoDespesaIgreja = tipoDespesaIgreja;
	}

	public List<TipoDespesaIgreja> getListaTipoDesesasIgreja() {
		return listaTipoDesesasIgreja;
	}

	public void setListaTipoDesesasIgreja(List<TipoDespesaIgreja> listaTipoDesesasIgreja) {
		this.listaTipoDesesasIgreja = listaTipoDesesasIgreja;
	}
	
	
}
