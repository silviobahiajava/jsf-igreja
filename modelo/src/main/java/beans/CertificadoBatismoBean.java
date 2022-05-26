package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import daos.MembroDao;
import entidades.CertificadoBatismo;
import entidades.Membro;
import entidades.Relatorios;
import enums.DiaEnum;
import enums.MesEnum;
import utils.Utils;

@ManagedBean(name="certificadobatismoBean")
@ViewScoped
public class CertificadoBatismoBean {
	private CertificadoBatismo certificado=new CertificadoBatismo();
	private List<CertificadoBatismo>lista=new ArrayList<CertificadoBatismo>();
	private Membro membro=new Membro();
	private Long codigoMembro;
	private DiaEnum dia;
	private MesEnum mes;
	
	
	public void novo(){
		certificado=new CertificadoBatismo();
		membro=new Membro();
		//lista=new ArrayList<CertificadoBatismo>();
	}
	
	
	public void carregarMembroPraCertificadoDeBatismo(){
		MembroDao membroDao=new MembroDao();
		membro=membroDao.buscar(codigoMembro);
		
		Utils.mostrarMensagemJsfSucesso("gerando certificado de batismo para "+membro.getNome());
	}
	
	public void registrarCertificadoBatismo(){
		lista=new ArrayList<CertificadoBatismo>();
		certificado.setNomeBatizando(membro.getNome());
		certificado.setNomeBatizador(certificado.getNomeBatizador());
		certificado.setDataBatismo(membro.getDataBatismo());
		certificado.setLocalBatismo(certificado.getLocalBatismo());
		certificado.setCidadeBatismo(certificado.getCidadeBatismo());
		certificado.setEstadoBatismo(certificado.getEstadoBatismo());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		certificado.setDiaDeApresentacao(diaDeApresentacao);
		certificado.setMesDeApresentacao(mesDeApresentacao);
		certificado.setAnoDeApresentacao(certificado.getAnoDeApresentacao());
		lista.add(certificado);
		Relatorios.gerarCertificadoDeBatismo(lista);
		novo();
	}
    
	public void mostrarCertificadoDeBatismoNaWeb(){
		lista=new ArrayList<CertificadoBatismo>();
		certificado.setNomeBatizando(membro.getNome());
		certificado.setNomeBatizador(certificado.getNomeBatizador());
		certificado.setDataBatismo(membro.getDataBatismo());
		certificado.setLocalBatismo(certificado.getLocalBatismo());
		certificado.setCidadeBatismo(certificado.getCidadeBatismo());
		certificado.setEstadoBatismo(certificado.getEstadoBatismo());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=dia.getDescricao();
		certificado.setDiaDeApresentacao(diaDeApresentacao);
		certificado.setMesDeApresentacao(mesDeApresentacao);
		certificado.setAnoDeApresentacao(certificado.getAnoDeApresentacao());
		lista.add(certificado);
		Relatorios.mostrarCertificadoDeBatismoNaWeb(lista);
	}

	public CertificadoBatismo getCertificado() {
		return certificado;
	}


	public void setCertificado(CertificadoBatismo certificado) {
		this.certificado = certificado;
	}


	public List<CertificadoBatismo> getLista() {
		return lista;
	}


	public void setLista(List<CertificadoBatismo> lista) {
		this.lista = lista;
	}


	public Membro getMembro() {
		return membro;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}


	public Long getCodigoMembro() {
		return codigoMembro;
	}


	public void setCodigoMembro(Long codigoMembro) {
		this.codigoMembro = codigoMembro;
	}


	public DiaEnum getDia() {
		return dia;
	}


	public void setDia(DiaEnum dia) {
		this.dia = dia;
	}


	public MesEnum getMes() {
		return mes;
	}


	public void setMes(MesEnum mes) {
		this.mes = mes;
	}
	
	
	
	
	
}
