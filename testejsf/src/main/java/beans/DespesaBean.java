package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.DespesaDaIgrejaDao;
import daos.DespesaPendenteDaIgrejaDao;
import daos.DespesasDaIgrejaPagasDao;
import entidades.DespesaDaIgreja;
import entidades.DespesasPagasDaIgreja;
import entidades.DespesasPendentesDaIgreja;
import entidades.Grupo;
import enums.SitucaoDaConta;

@ManagedBean(name="despesaBean")
@ViewScoped
public class DespesaBean {
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
			
			 despesasDaIgreja=despesaDao.buscarPorGrupo(8L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 e.printStackTrace();
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
				 despesasDaIgreja=despesaDao.buscarPorGrupo(8L);
				
			
			 
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
			 despesasDaIgreja=despesaDao.buscarPorGrupo(8L);
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
				 despesasDaIgreja=despesaDao.buscarPorGrupo(8L);
				Utils.mostrarMensagemJsfSucesso("despesa da igreja paga registrada com sucesso");
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
	
	
	public void registrarDespesaPagaTeste() {
		try {   
			    
					DespesasPagasDaIgreja despesaPaga=new DespesasPagasDaIgreja();
					DespesasDaIgrejaPagasDao despesaPagaDao=new DespesasDaIgrejaPagasDao();
					
					Grupo grupo=new Grupo();
					grupo.setCodigo(8L);
					despesaDaIgreja.setGrupo(grupo);
					despesaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.PAGO);
					despesaDaIgreja.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
					despesaDaIgreja.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
					despesaDaIgreja=despesaDao.merge2(despesaDaIgreja);
					
					despesaPaga.setDespesaDaIgreja(despesaDaIgreja);
					despesaPaga.setDataPagamentoDespesaDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaPaga.setGrupo(grupo);
					despesaPaga.setSituacaoDaDespesaEnum(despesaDaIgreja.getSituacaoDaDespesaEnum());
					despesaPaga.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
					despesaPaga.setDescricaoDespesaDaIgreja(despesaDaIgreja.getDescricaoDespesaDaIgreja());
					despesaPaga.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
					despesaPagaDao.salvar(despesaPaga);
					despesasDaIgreja=despesaDao.listar();
					
				Utils.mostrarMensagemJsfSucesso("despesa paga registrada com sucesso");
				
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
	
	public void registrarDespesaPendenteTeste() {
		try {   
			    
					DespesasPendentesDaIgreja despesaPendente=new DespesasPendentesDaIgreja();
					DespesaPendenteDaIgrejaDao despesaPendenteDaIgrejaDaoDao=new DespesaPendenteDaIgrejaDao();
					
					Grupo grupo=new Grupo();
					grupo.setCodigo(8L);
					despesaDaIgreja.setGrupo(grupo);
					despesaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.APAGAR);
					despesaDaIgreja.setValorDespescaDaIgreja(despesaDaIgreja.getValorDespescaDaIgreja());
					despesaDaIgreja.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
					despesaDaIgreja=despesaDao.merge2(despesaDaIgreja);
					
					despesaPendente.setDespesaDaIgreja(despesaDaIgreja);
					despesaPendente.setDataPagamentoDespesaPendenteDaIgreja(despesaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaPendente.setGrupo(grupo);
					despesaPendente.setSituacaoDaDespesaPendenteEnum(despesaDaIgreja.getSituacaoDaDespesaEnum());
					despesaPendente.setTipoDespesaEnum(despesaDaIgreja.getTipoDespesaEnum());
					despesaPendente.setDescricaoDespesaPendenteDaIgreja(despesaDaIgreja.getDescricaoDespesaDaIgreja());
					despesaPendente.setValorDespesaPendenteDaIgeja(despesaDaIgreja.getValorDespescaDaIgreja());
					despesaPendenteDaIgrejaDaoDao.salvar(despesaPendente);
				
					despesasDaIgreja=despesaDao.listar();
					
				Utils.mostrarMensagemJsfSucesso("despesa pendente registrada com sucesso");
				
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
	
	
}
