package br.com.souzawebsistemas.model;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="convidado")
public class Convidado implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
private String nomeConvidado;
 private String rg;
 @ManyToOne
 private Evento evento;
public String getNomeConvidado() {
	return nomeConvidado;
}
public void setNomeConvidado(String nomeConvidado) {
	this.nomeConvidado = nomeConvidado;
}
public String getRg() {
	return rg;
}
public void setRg(String rg) {
	this.rg = rg;
}
public Evento getEvento() {
	return evento;
}
public void setEvento(Evento evento) {
	this.evento = evento;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
 
 
}
