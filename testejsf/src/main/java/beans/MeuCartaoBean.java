package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.testejsf.utils.Utils;
import daos.MembroDao;
import entidades.Membro;

@ManagedBean(name="meucartaoBean")
@SessionScoped
public class MeuCartaoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Membro m=new Membro();
	MembroDao mdao=new MembroDao();
	private List<Membro>membros=new ArrayList<Membro>();
	private Date data;
	
	@PostConstruct
	public void novo(){
		m=new Membro();
		membros=new ArrayList<Membro>();
		data=new Date();
	}
	public Membro getM() {
		return m;
	}

	public void setM(Membro m) {
		this.m = m;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
	
	public void buscar(){
		try{
		
		membros=mdao.buscarPorCpfENumeroCartao(m.getCpf(), m.getCodigo());
		for(Membro membro:membros){
			System.out.println(m.getNome());
		}
		}catch(RuntimeException e){
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar "+e.getMessage());
		}
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
