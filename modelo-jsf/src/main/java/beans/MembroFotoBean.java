package beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import daos.MembroDao;
import entidades.Membro;
import utils.Utils;

@ManagedBean(name="membrofotoBean")
@ViewScoped
public class MembroFotoBean {
	private Part arquivo;
	private Membro membro=new Membro();
	private List<Membro>membros;
	private MembroDao membroDao=new MembroDao();
	public void salvarMembroComFoto(){
		try {
			InputStream entrada = arquivo.getInputStream();
			byte[] bytesDoArquivo = IOUtils.toByteArray(entrada);
			membro.setFoto(bytesDoArquivo);
			membro.setNome(membro.getNome());
			membroDao.salvar(membro);
			Utils.mostrarMensagemJsfSucesso("cliente cadastrado com suceso");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@PostConstruct
	public void listar() {
		try {
			
			membros=membroDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	public void novo(){
		membro=new Membro();
	}

	public Part getArquivo() {
		return arquivo;
	}


	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}


	public Membro getMembro() {
		return membro;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}


	public List<Membro> getMembros() {
		return membros;
	}


	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}
	
	
}
