package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.DespesaDaIgrejaDao;
import daos.DespesasDaIgrejaPagasDao;
import entidades.DespesaDaIgreja;
import entidades.DespesasPagasDaIgreja;
import entidades.Grupo;
import enums.SitucaoDaConta;

@ManagedBean(name="despesaspagasdaigrejabean")
public class DespesasPagasDaIgrejaBean {
	
	private DespesasPagasDaIgreja despesaPagaDaIgreja=new DespesasPagasDaIgreja();
	private List<DespesasPagasDaIgreja>listaDeDespesasPagas=new ArrayList<DespesasPagasDaIgreja>();
	private List<DespesasPagasDaIgreja>listadespesasPagasLancamento=new ArrayList<DespesasPagasDaIgreja>();
	private DespesasDaIgrejaPagasDao dao=new DespesasDaIgrejaPagasDao();
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
			
			listaDeDespesasPagas=dao.buscarPorGrupo(8L);
			 
		 }catch(RuntimeException e){
		 Utils.mostrarMensagemJsfErro("erro ao listar");
		 e.printStackTrace();
		 }
	}
	
	
	public void novo() {
		despesaPagaDaIgreja=new DespesasPagasDaIgreja();
		
	}
	
	
	
	
	public void alterarDadosDaDespesa(ActionEvent evento){
		despesaPagaDaIgreja= (DespesasPagasDaIgreja) evento.getComponent().getAttributes().get("despesaSelecionada");
		try{
			Grupo grupo=new Grupo();
			grupo.setCodigo(8L);
			
			despesaPagaDaIgreja.setGrupo(grupo);
			despesaPagaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaPagaDaIgreja.getDataPagamentoDespesaDaIgreja());
			despesaPagaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.PAGO);
			despesaPagaDaIgreja.setValorDespescaDaIgreja(despesaPagaDaIgreja.getValorDespescaDaIgreja());
			despesaPagaDaIgreja.setTipoDespesaEnum(despesaPagaDaIgreja.getTipoDespesaEnum());
			dao.editar(despesaPagaDaIgreja);
			listaDeDespesasPagas=dao.buscarPorGrupo(8L);
			//listaDeDespesasPagas=dao.buscar(codigo)
			Utils.mostrarMensagemJsfSucesso("despesa da igreja paga alterada com sucesso");
		}catch(RuntimeException e){
			Utils.mostrarMensagemJsfSucesso("erro ao alterar os dados desta despesa "+e.getMessage());
		}
		
	}
	
	public void registrarDespesaPaga() {
		try {   
			    
					DespesaDaIgreja despesaDaIgreja=new DespesaDaIgreja();
					DespesaDaIgrejaDao despesaDaIgrejaDao=new DespesaDaIgrejaDao();
					Grupo grupo=new Grupo();
					grupo.setCodigo(8L);
					despesaPagaDaIgreja.setGrupo(grupo);
					despesaPagaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaPagaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaPagaDaIgreja.setSituacaoDaDespesaEnum(SitucaoDaConta.PAGO);
					despesaPagaDaIgreja.setValorDespescaDaIgreja(despesaPagaDaIgreja.getValorDespescaDaIgreja());
					despesaPagaDaIgreja.setTipoDespesaEnum(despesaPagaDaIgreja.getTipoDespesaEnum());
					
					
					despesaPagaDaIgreja=dao.merge2(despesaPagaDaIgreja);
					despesaDaIgreja.setDataPagamentoDespesaDaIgreja(despesaPagaDaIgreja.getDataPagamentoDespesaDaIgreja());
					despesaDaIgreja.setGrupo(grupo);
					despesaDaIgreja.setSituacaoDaDespesaEnum(despesaPagaDaIgreja.getSituacaoDaDespesaEnum());
					despesaDaIgreja.setTipoDespesaEnum(despesaPagaDaIgreja.getTipoDespesaEnum());
					despesaDaIgreja.setDescricaoDespesaDaIgreja(despesaPagaDaIgreja.getDescricaoDespesaDaIgreja());
					despesaDaIgreja.setValorDespescaDaIgreja(despesaPagaDaIgreja.getValorDespescaDaIgreja());
					despesaDaIgrejaDao.salvar(despesaDaIgreja);
					listaDeDespesasPagas=dao.buscarPorGrupo(8L);
				Utils.mostrarMensagemJsfSucesso("despesa da igreja paga registrada com sucesso");
				
		} catch (RuntimeException e) {
			Utils.mostrarMensagemJsfErro("erro ao  registrar esta despesa");
		}
	}
	public DespesasPagasDaIgreja getDespesaPagaDaIgreja() {
		return despesaPagaDaIgreja;
	}
	public void setDespesaPagaDaIgreja(DespesasPagasDaIgreja despesaPagaDaIgreja) {
		this.despesaPagaDaIgreja = despesaPagaDaIgreja;
	}
	public List<DespesasPagasDaIgreja> getListaDeDespesasPagas() {
		return listaDeDespesasPagas;
	}
	public void setListaDeDespesasPagas(List<DespesasPagasDaIgreja> listaDeDespesasPagas) {
		this.listaDeDespesasPagas = listaDeDespesasPagas;
	}
	public List<DespesasPagasDaIgreja> getListadespesasPagasLancamento() {
		return listadespesasPagasLancamento;
	}
	public void setListadespesasPagasLancamento(List<DespesasPagasDaIgreja> listadespesasPagasLancamento) {
		this.listadespesasPagasLancamento = listadespesasPagasLancamento;
	}
	
	
	
}
