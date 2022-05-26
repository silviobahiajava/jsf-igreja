package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.MembroDao;
import entidades.CertificadoApresentacaoRecemNascido;
import entidades.CertificadoBatismo;
import entidades.CertificadoDeApresentacao;
import entidades.Membro;
import entidades.Relatorios;
import enums.DiaEnum;
import enums.MesEnum;

@ManagedBean(name="certificadodeapresentacaoBean")
@ViewScoped
public class CertificadoDeApresentacaoBean {
	private CertificadoDeApresentacao certificado=new CertificadoDeApresentacao();
	private List<CertificadoDeApresentacao>lista=new ArrayList<CertificadoDeApresentacao>();
	private DiaEnum dia;
	private MesEnum mes;
	public void novo(){
		certificado=new CertificadoDeApresentacao();
		//lista=new ArrayList<CertificadoBatismo>();
	}
	
	
	public void registrarCertificadoDeApresentacao(){
		lista=new ArrayList<CertificadoDeApresentacao>();
		certificado.setNomeDoRecemNascido(certificado.getNomeDoRecemNascido());
		certificado.setDataDeNascimentoDoRecemNascido(certificado.getDataDeNascimentoDoRecemNascido());
		certificado.setNomeDoPai(certificado.getNomeDoPai());
		certificado.setNomeDaMae(certificado.getNomeDaMae());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		certificado.setDiaDeApresentacao(diaDeApresentacao);
		certificado.setMesDeApresentacao(mesDeApresentacao);
		certificado.setAnoDeApresentacao(certificado.getAnoDeApresentacao());

		
		
		
		
		lista.add(certificado);
		Relatorios.gerarCertificadoDeAPresentacao(lista);
		novo();
	}

   public void mostrarCertificadoDeApresentacaoNaWeb(){
	   lista=new ArrayList<CertificadoDeApresentacao>();
		certificado.setNomeDoRecemNascido(certificado.getNomeDoRecemNascido());
		certificado.setDataDeNascimentoDoRecemNascido(certificado.getDataDeNascimentoDoRecemNascido());
		certificado.setNomeDoPai(certificado.getNomeDoPai());
		certificado.setNomeDaMae(certificado.getNomeDaMae());
		String diaDeApresentacao = dia.getDescricao();
		String mesDeApresentacao=mes.getDescricao();
		certificado.setDiaDeApresentacao(diaDeApresentacao);
		certificado.setMesDeApresentacao(mesDeApresentacao);
		certificado.setAnoDeApresentacao(certificado.getAnoDeApresentacao());

		
		//rdo melo peixoto br 369 km 167 cambé 
		//inquimica
		//Romanelli dia 05 quarta feira as 14:00
		
		
		lista.add(certificado);
		Relatorios.mostrarCartaDeApresentacaoNaWeb(lista);
   }


	public CertificadoDeApresentacao getCertificado() {
		return certificado;
	}




	public void setCertificado(CertificadoDeApresentacao certificado) {
		this.certificado = certificado;
	}




	public List<CertificadoDeApresentacao> getLista() {
		return lista;
	}




	public void setLista(List<CertificadoDeApresentacao> lista) {
		this.lista = lista;
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
