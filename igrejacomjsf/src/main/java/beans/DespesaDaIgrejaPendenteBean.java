package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import daos.CompraIgrejaDao;
import daos.DespesaDaIgrejaDao;
import daos.GrupoDao;
import entidades.CompraIgreja;
import entidades.DespesaDaIgreja;
import entidades.Grupo;
import entidades.Membro;
import enums.SitucaoDaConta;
import utils.Utils;

@ManagedBean(name="despesadaigrejapendenteBean")
@ViewScoped
public class DespesaDaIgrejaPendenteBean {
	private DespesaDaIgreja despesaDaIgreja;
	private DespesaDaIgrejaDao despesaDao=new DespesaDaIgrejaDao();
	private List<DespesaDaIgreja>despesasDaIgreja;
	private List<Grupo>grupos=new ArrayList<Grupo>();
	public DespesaDaIgreja getDespesaDaIgreja() {
		return despesaDaIgreja;
	}
	public void setDespesaDaIgreja(DespesaDaIgreja despesaDaIgreja) {
		this.despesaDaIgreja = despesaDaIgreja;
	}
	public List<DespesaDaIgreja> getDespesasDaIgreja() {
		return despesasDaIgreja;
	}
	public void setDespesasDaIgreja(List<DespesaDaIgreja> despesasDaIgreja) {
		this.despesasDaIgreja = despesasDaIgreja;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}	
	
	
	@PostConstruct
	public void listar() {
		 try{
		 despesasDaIgreja=despesaDao.buscarDespesaDaIgrejaPagaPorGrupo(8L,SitucaoDaConta.APAGAR.getDescricao());
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 }
	}
	
	
	public void novo() {
		despesaDaIgreja = new DespesaDaIgreja();
		//GrupoDao grupoDao = new GrupoDao();
		//grupos = grupoDao.listar();
	}
	
	public void registrarDespesaAPagar() {
		try {
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				despesaDaIgreja.setGrupo(grupo);
				despesaDaIgreja.setDataVencimentoDespesaDaIgreja(despesaDaIgreja.getDataVencimentoDespesaDaIgreja());
				despesaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.APAGAR);
				despesaDaIgreja.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
				despesaDaIgreja.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
				despesaDao.salvar(despesaDaIgreja);
				 despesasDaIgreja=despesaDao.buscarDespesaDaIgrejaPagaPorGrupo(8L,SitucaoDaConta.APAGAR.getDescricao());
				Utils.mostrarMensagemJsfSucesso("despesa da igreja a pagar registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao registrar esta despesa");
		}
	}
	
	
	public void alterarDadosDaDespesa(ActionEvent evento){
		despesaDaIgreja= (DespesaDaIgreja) evento.getComponent().getAttributes().get("despesaSelecionada");
		try{
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			despesaDaIgreja.setGrupo(grupo);
			despesaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
			despesaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.PAGO);
			despesaDaIgreja.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
			despesaDaIgreja.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
			despesaDao.editar(despesaDaIgreja);
			Utils.mostrarMensagemJsfSucesso("despesa da igreja paga alterada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfSucesso("erro ao alterar os dados desta despesa "+e.getMessage());
		}
		
	}
	
	public void registrarDespesaPaga() {
		try {
				Grupo grupo=new Grupo();
				grupo.setCodigo(8L);
				despesaDaIgreja.setGrupo(grupo);
				despesaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
				despesaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.PAGO);
				despesaDaIgreja.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
				despesaDaIgreja.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
				despesaDao.salvar(despesaDaIgreja);
				Utils.mostrarMensagemJsfSucesso("despesa da igreja paga registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
}
