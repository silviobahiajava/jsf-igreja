package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.DespesaDaIgrejaDao;
import entidades.DespesaDaIgreja;
import entidades.Grupo;
import enums.SitucaoDaConta;
import enums.TipoDespesa;

@ManagedBean(name="despesadaigrejapagaBean")
@ViewScoped
public class DespesaDaIgrejaPagaBean {
	private DespesaDaIgreja despesaDaIgreja;
	private DespesaDaIgrejaDao despesaDao=new DespesaDaIgrejaDao();
	private List<DespesaDaIgreja>despesasDaIgreja;
	private List<DespesaDaIgreja>despesasPagasDaIgreja;
	private List<DespesaDaIgreja>despesasPendentesDaIgreja;
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
	
	
	
	public List<DespesaDaIgreja> getDespesasPagasDaIgreja() {
		return despesasPagasDaIgreja;
	}
	public void setDespesasPagasDaIgreja(List<DespesaDaIgreja> despesasPagasDaIgreja) {
		this.despesasPagasDaIgreja = despesasPagasDaIgreja;
	}
	public List<DespesaDaIgreja> getDespesasPendentesDaIgreja() {
		return despesasPendentesDaIgreja;
	}
	public void setDespesasPendentesDaIgreja(List<DespesaDaIgreja> despesasPendentesDaIgreja) {
		this.despesasPendentesDaIgreja = despesasPendentesDaIgreja;
	}
	@PostConstruct
	public void listar() {
		 try{
			 String situacao = SitucaoDaConta.PAGO.getDescricao();
			 despesasDaIgreja=despesaDao.buscarDespesaDaIgrejaPorGrupo(8L);
		 for(DespesaDaIgreja despesa:despesasDaIgreja){
			 if(despesa.getSituacaoDaDespesaEnum().equals(situacao)){
				 despesasPagasDaIgreja.add(despesa);
			 }
		 }
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
				 String situacao = SitucaoDaConta.PAGO.getDescricao();
				 despesasDaIgreja=despesaDao.buscarDespesaDaIgrejaPorGrupo(8L);
			 for(DespesaDaIgreja despesa:despesasDaIgreja){
				 if(despesa.getSituacaoDaDespesaEnum().equals(situacao)){
					 despesasPagasDaIgreja.add(despesa);
				 }
			 }
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
