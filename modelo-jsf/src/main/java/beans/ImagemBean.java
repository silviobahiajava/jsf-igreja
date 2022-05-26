package beans;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="imagemBean")
@RequestScoped
public class ImagemBean {
	@ManagedProperty("#{param.caminho}")
	private String caminho;
	private StreamedContent foto;
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()){
			//Path path = Paths.get("C:/fotosdaigreja/membros/branco.jpg");
			//C:\fotos\igrejas\missaoefe
			Path path = Paths.get("C:/fotos/igrejas/missaoefe/branco.jpg");
			
			//Path path = Paths.get("./resources/fotos/branco.jpg");
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}else{
			Path path = Paths.get(caminho);
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}
		return foto;
	}
	
}
