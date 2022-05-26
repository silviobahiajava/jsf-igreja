package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import daos.DespesaPendenteDaIgrejaDao;
import entidades.DespesasPagasDaIgreja;
import entidades.DespesasPendentesDaIgreja;
import entidades.Grupo;
import enums.SitucaoDaConta;
import utils.Utils;

@ManagedBean(name="despesaspendentesdaigrejaBean")
public class DespesasPendentesDaIgrejaBean {
	
	private DespesasPendentesDaIgreja despesasPendentesDaIgreja=new DespesasPendentesDaIgreja();
	private List<DespesasPendentesDaIgreja>listaDespesasPendentesDaIgreja=new ArrayList<DespesasPendentesDaIgreja>();
	private List<DespesasPagasDaIgreja>listaDespesasPendentesDaIgrejaLancamento=new ArrayList<DespesasPagasDaIgreja>();
	private DespesaPendenteDaIgrejaDao dao=new DespesaPendenteDaIgrejaDao();
	private List<Grupo>grupos=new ArrayList<Grupo>();
	
	
	
	
	
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}	
	
	
	
	
	@PostConstruct
	public void listar() {
		 try{
			listaDespesasPendentesDaIgreja=dao.buscarPorGrupo(8L);
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 e.printStackTrace();
		 }
	}
	
	
	public void novo() {
		despesasPendentesDaIgreja=new DespesasPendentesDaIgreja();
	}
	
	
	
	
	public void alterarDadosDaDespesa(ActionEvent evento){
		despesasPendentesDaIgreja= (DespesasPendentesDaIgreja) evento.getComponent().getAttributes().get("despesaSelecionada");
		try{
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			
			despesasPendentesDaIgreja.setGrupo(grupo);
			despesasPendentesDaIgreja.setDataPagamentoDespesaPendenteDaIgreja(despesasPendentesDaIgreja.getDataPagamentoDespesaPendenteDaIgreja());
			despesasPendentesDaIgreja.setSituacaoDaDespesaPendenteEnum(SitucaoDaConta.APAGAR);
			despesasPendentesDaIgreja.setValorDespesaPendenteDaIgeja(despesasPendentesDaIgreja.getValorDespesaPendenteDaIgeja());
			despesasPendentesDaIgreja.setTipoDespesaEnum(despesasPendentesDaIgreja.getTipoDespesaEnum());
			dao.editar(despesasPendentesDaIgreja);
			listaDespesasPendentesDaIgreja=dao.buscarPorGrupo(8L);
			Utils.mostrarMensagemJsfSucesso("despesa da igreja paga alterada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfSucesso("erro ao alterar os dados desta despesa "+e.getMessage());
		}
		
	}
	
	public void registrarDespesaPendente() {
		try {Grupo grupo=new Grupo();
		grupo.setCodigo(8L);
		
		despesasPendentesDaIgreja.setGrupo(grupo);
		despesasPendentesDaIgreja.setDataPagamentoDespesaPendenteDaIgreja(despesasPendentesDaIgreja.getDataPagamentoDespesaPendenteDaIgreja());
		despesasPendentesDaIgreja.setSituacaoDaDespesaPendenteEnum(SitucaoDaConta.APAGAR);
		despesasPendentesDaIgreja.setValorDespesaPendenteDaIgeja(despesasPendentesDaIgreja.getValorDespesaPendenteDaIgeja());
		despesasPendentesDaIgreja.setTipoDespesaEnum(despesasPendentesDaIgreja.getTipoDespesaEnum());
		dao.salvar(despesasPendentesDaIgreja);
		listaDespesasPendentesDaIgreja=dao.buscarPorGrupo(8L);
		Utils.mostrarMensagemJsfSucesso("despesa da igreja paga alterada com sucesso");
				
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
	public DespesasPendentesDaIgreja getDespesasPendentesDaIgreja() {
		return despesasPendentesDaIgreja;
	}
	public void setDespesasPendentesDaIgreja(DespesasPendentesDaIgreja despesasPendentesDaIgreja) {
		this.despesasPendentesDaIgreja = despesasPendentesDaIgreja;
	}
	public List<DespesasPendentesDaIgreja> getListaDespesasPendentesDaIgreja() {
		return listaDespesasPendentesDaIgreja;
	}
	public void setListaDespesasPendentesDaIgreja(List<DespesasPendentesDaIgreja> listaDespesasPendentesDaIgreja) {
		this.listaDespesasPendentesDaIgreja = listaDespesasPendentesDaIgreja;
	}
	public List<DespesasPagasDaIgreja> getListaDespesasPendentesDaIgrejaLancamento() {
		return listaDespesasPendentesDaIgrejaLancamento;
	}
	public void setListaDespesasPendentesDaIgrejaLancamento(
			List<DespesasPagasDaIgreja> listaDespesasPendentesDaIgrejaLancamento) {
		this.listaDespesasPendentesDaIgrejaLancamento = listaDespesasPendentesDaIgrejaLancamento;
	}
	
	
	
	
}
