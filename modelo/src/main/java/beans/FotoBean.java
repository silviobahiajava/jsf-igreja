package beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import daos.FotoDao;
import daos.MembroDao;
import entidades.FotoMembro;
import entidades.Membro;
import utils.Utils;
@ManagedBean(name="fotoBean")
@ViewScoped
public class FotoBean implements Serializable{
	private FotoMembro foto=new FotoMembro();
	private Part part;
	private FotoDao fotoDao=new FotoDao();
	private List<Membro>membros=new ArrayList<Membro>();
	private List<FotoMembro>fotos=new ArrayList<FotoMembro>();
	private MembroDao membroDao=new MembroDao();

	private String image;
	public void salvarImagem() {
		try {
			InputStream entrada = part.getInputStream();
			byte[]bytes=IOUtils.toByteArray(entrada);
			foto.setImagem(bytes);
			fotoDao.salvar(foto);
			//Utils.mostrarMensagemJsfSucesso("foto salva com sucesso");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Utils.mostrarMensagemSwing("DEU ERRADO COM JSF PURO");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		
		fotos=fotoDao.listar();
		membros=membroDao.listar();
		geraImagemServidor();
	}
	
	private void geraImagemServidor() {
		try {
		if (foto.getImagem()!=null) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String imageUsers = servletContext.getRealPath("/resources/fotos/membros");
		File dirImageUsers = new File(imageUsers);
		if (!dirImageUsers.exists()) {
		dirImageUsers.createNewFile();
		}
		 
		
		byte[] bytes = foto.getImagem();
		FileImageOutputStream imageOutput = new FileImageOutputStream(new File(dirImageUsers, foto.getMembro().getNome() + ".jpg"));
		
		imageOutput.write(bytes, 0, bytes.length);
		imageOutput.flush();
		imageOutput.close();
		setImage("/resources/fotos/membros/" +foto.getMembro().getNome()+".jpg");
		}
		} catch (Exception ex) {
		System.out.println("Erro " + ex.getMessage());
		}
		}
		 
	
	private void setImage(String image) {
		this.image=image;
		
	}
   

	public String getImage() {
		return image;
	}


	public FotoMembro getFoto() {
		return foto;
	}
	public void setFoto(FotoMembro foto) {
		this.foto = foto;
	}
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public List<FotoMembro> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoMembro> fotos) {
		this.fotos = fotos;
	}
	
	
	
	}
