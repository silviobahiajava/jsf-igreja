package br.com.souzawebsistemas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.souzawebsistemas.enums.BairroCidadeEnum;
import br.com.souzawebsistemas.enums.CargoEnum;
import br.com.souzawebsistemas.enums.CidadesNorteParanaEnum;
import br.com.souzawebsistemas.enums.Congregacao;
import br.com.souzawebsistemas.enums.EstadosBrasileirosEnum;

@Entity
@Table(name="cartaomembro")
public class CartaoMembro extends GenericDomain {
	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dataBatismo;
	private String localDeBatismo;
	@Enumerated(EnumType.STRING)
	private BairroCidadeEnum bairroEnum;
	
	@Enumerated(EnumType.STRING)
	private CidadesNorteParanaEnum cidadeEnum;
	
	@Enumerated(EnumType.STRING)
	private EstadosBrasileirosEnum estadosBrasileirosEnum;
	
	@Enumerated(EnumType.STRING)
	private CargoEnum cargoEnum;//enum
	
	@Enumerated(EnumType.STRING)
	private Congregacao congregacao;//enum

	public Date getDataBatismo() {
		return dataBatismo;
	}

	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}

	public String getLocalDeBatismo() {
		return localDeBatismo;
	}

	public void setLocalDeBatismo(String localDeBatismo) {
		this.localDeBatismo = localDeBatismo;
	}

	public BairroCidadeEnum getBairroEnum() {
		return bairroEnum;
	}

	public void setBairroEnum(BairroCidadeEnum bairroEnum) {
		this.bairroEnum = bairroEnum;
	}

	public CidadesNorteParanaEnum getCidadeEnum() {
		return cidadeEnum;
	}

	public void setCidadeEnum(CidadesNorteParanaEnum cidadeEnum) {
		this.cidadeEnum = cidadeEnum;
	}

	public EstadosBrasileirosEnum getEstadosBrasileirosEnum() {
		return estadosBrasileirosEnum;
	}

	public void setEstadosBrasileirosEnum(EstadosBrasileirosEnum estadosBrasileirosEnum) {
		this.estadosBrasileirosEnum = estadosBrasileirosEnum;
	}

	public CargoEnum getCargoEnum() {
		return cargoEnum;
	}

	public void setCargoEnum(CargoEnum cargoEnum) {
		this.cargoEnum = cargoEnum;
	}

	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}
	
	
	
}
