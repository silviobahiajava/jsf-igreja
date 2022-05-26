package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.testejsf.utils.Utils;
import daos.AniversarioDao;
import daos.CriancaDao;
import entidades.Aniversario;
import entidades.Crianca;

@ManagedBean(name="aniversariocriancaBean")
@ViewScoped
public class AniversarioCriancaBean {
	private List<Crianca>aniversariantes=new ArrayList<Crianca>();
	private Aniversario aniversario=new Aniversario();
	private CriancaDao cdao=new CriancaDao();
	private AniversarioDao dao=new AniversarioDao();
	private List<Aniversario>listaDeAniversarios;
	
	
	@PostConstruct
	public void init() {
		listaDeAniversarios=dao.listar();
	}
	
	public void novo() {
		aniversario=new Aniversario();
		aniversariantes=cdao.listar();
	}
	
	public void registrarAniversario() {
		try {
			aniversario.setDataAniversario(aniversario.getDataAniversario());
			aniversario.setAniversarianteAdulto(aniversario.getAniversarianteAdulto());
			dao.salvar(aniversario);
			listaDeAniversarios=dao.listar();
			
		Utils.mostrarMensagemJsfSucesso("aniversario registrado com sucesso");
		
		}catch(Exception e) {
			Utils.mostrarMensagemJsfErro("erro ao registrar o aniversario");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		aniversario=(Aniversario) evento.getComponent().getAttributes().get("aniversarioSelecionado");
		aniversario.setDataAniversario(aniversario.getDataAniversario());
		dao.editar(aniversario);
		Utils.mostrarMensagemJsfSucesso("aniversario editado  com sucesso");
		}catch(Exception e) {
			Utils.mostrarMensagemJsfErro("erro ao editar o aniversario");
			e.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
		aniversario=(Aniversario) evento.getComponent().getAttributes().get("aniversarioSelecionado");
		dao.excluir(aniversario);
		
		Utils.mostrarMensagemJsfSucesso("aniversario excluido com sucesso");
		}catch(Exception e) {
			Utils.mostrarMensagemJsfErro("erro ao excluir o aniversario");
			e.printStackTrace();
		}
	}

	

	

	public List<Crianca> getAniversariantes() {
		return aniversariantes;
	}

	public void setAniversariantes(List<Crianca> aniversariantes) {
		this.aniversariantes = aniversariantes;
	}

	public Aniversario getAniversario() {
		return aniversario;
	}

	public void setAniversario(Aniversario aniversario) {
		this.aniversario = aniversario;
	}

	public List<Aniversario> getListaDeAniversarios() {
		return listaDeAniversarios;
	}

	public void setListaDeAniversarios(List<Aniversario> listaDeAniversarios) {
		this.listaDeAniversarios = listaDeAniversarios;
	}
	
	
	
	public void buscar() {
		try {
			
			//listaDeAniversarios=dzdao.buscarValorDoDizimoPorData(dizimo.getDataDizimoIgreja());
			//listaDeAniversarios=
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}
	

}
