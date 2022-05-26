package beans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.testejsf.utils.Utils;
import br.com.testejsf.utils.WebServiceCep;
import daos.CurriculoDao;
import entidades.Curriculo;
import entidades.EmpresaCurriculo;
import entidades.Endereco;

@ManagedBean(name="curriculoBean")
@ViewScoped
public class CurriculoBean {
	private Curriculo curriculo=new Curriculo();
	private String cep;
	private String numero;
	
	private List<Curriculo>curriculos=new ArrayList<Curriculo>();
	private Endereco endereco=new Endereco();
	private CurriculoDao curriculoDao=new CurriculoDao();
	
	private EmpresaCurriculo empresa=new EmpresaCurriculo();
	private EmpresaCurriculo empresa2=new EmpresaCurriculo();
	private EmpresaCurriculo empresa3=new EmpresaCurriculo();
	
	@PostConstruct
	public void listar() {
		try {
			curriculos=curriculoDao.listar();
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}
	public void novo(){
		curriculo=new Curriculo();
		endereco=new Endereco();
		 empresa=new EmpresaCurriculo();
		 empresa2=new EmpresaCurriculo();
		  empresa3=new EmpresaCurriculo();
	}
	
	public void pegarEndereco(){
		Utils.mostrarMensagemJsfSucesso("pegando endere�o");
		WebServiceCep wc=WebServiceCep.searchCep(curriculo.getCep());
		curriculo.setCep(curriculo.getCep());
		curriculo.setEndereco(wc.getLogradouro());
		curriculo.setNumeroEndereco(curriculo.getNumeroEndereco());
		curriculo.setBairro(wc.getBairro());
		curriculo.setCidade(wc.getCidade());
		curriculo.setEstado(wc.getUf());
		
	}
	
	
	public void salvar() {
		try {
			if(curriculo.getCaminho()==null){
				Utils.mostrarMensagemJsfErro("o campo foto � obrigat�rio");
				return;
			}
			
			if(!curriculo.getNomeCompleto().equals(null)){
				curriculo.setNomeCompleto(curriculo.getNomeCompleto().toUpperCase());
				empresa.setProfissaoUltimaEmpresa(empresa.getProfissaoUltimaEmpresa());
				empresa.setUltimaEmpresa(empresa.getUltimaEmpresa());
				empresa.setDataAdmissaoultimaEmpresa(empresa.getDataDemissaoultimaEmpresa());
				empresa.setDataDemissaoultimaEmpresa(empresa.getDataDemissaoultimaEmpresa());
				empresa.setFuncoesUltimaEmpresa(empresa.getFuncoesUltimaEmpresa());
				curriculo.setEmpresa(empresa);
				
				empresa2.setProfissaoPenultimaEmpresa(empresa2.getProfissaoPenultimaEmpresa());
				empresa2.setPenultimaEmpresa(empresa2.getPenultimaEmpresa());
				empresa2.setDataAdmissaoPenultimaEmpresa(empresa2.getDataAdmissaoPenultimaEmpresa());
				empresa2.setDataDemissaoPenultimaEmpresa(empresa2.getDataDemissaoPenultimaEmpresa());
				empresa2.setFuncoesPenultimaEmpresa(empresa.getFuncoesPenultimaEmpresa());
				curriculo.setEmpresa(empresa2);
				
		       
				
				
				
				empresa3.setProfissaoAntipenultimaEmpresa(empresa3.getProfissaoAntipenultimaEmpresa());
				empresa3.setAntipenultimaEmpresa(empresa3.getAntipenultimaEmpresa());
				empresa3.setDataAdmissaoAntipenultimaEmpresa(empresa3.getDataAdmissaoAntipenultimaEmpresa());
				empresa3.setDataDemissaoAntipenultimaEmpresa(empresa3.getDataDemissaoAntipenultimaEmpresa());
				empresa3.setFuncoesAntiPenultimaEmpresa(empresa3.getFuncoesAntiPenultimaEmpresa());
				
				curriculo.setEmpresa(empresa3);
				
				
				curriculo.setNomeCompleto(curriculo.getNomeCompleto());
				curriculo.setEmail(curriculo.getEmail());
				curriculo.setTelefoneCelular(curriculo.getTelefoneCelular());
				
				curriculo.setObjetivoCurriculo(curriculo.getObjetivoCurriculo());
				curriculo.setPerfilProfissional(curriculo.getPerfilProfissional());
				curriculo.setNomeEscola(curriculo.getNomeEscola());
				curriculo.setNomeCurso(curriculo.getNomeCurso());
				curriculo.setInformacoesAdicionais(curriculo.getInformacoesAdicionais());
				
				
				
			
			}
			
			Curriculo curriculoCadastrado=curriculoDao.salvarCurriculo(curriculo);
			//pegando o caminho do arquivo tempor�rio
			Path origem=Paths.get(curriculo.getCaminho());
			Path destino=Paths.get("C:/fotosdaigreja/"+curriculoCadastrado.getCodigo()+".jpg");
			
			//copiando o arquivo que est� no camimho origem para o caminho destino
			
			Files.copy(origem,destino, StandardCopyOption.REPLACE_EXISTING);
			curriculo=new Curriculo();
			curriculos=curriculoDao.listar();
			
			Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucess\nnome da foto :"+curriculoCadastrado.getCodigo());
			
		} catch (RuntimeException  erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			curriculo = (Curriculo) evento.getComponent().getAttributes().get("curriculoSelecionado");

			curriculoDao.excluir(curriculo);
			Path path=Paths.get("C:/fotosdaigeja/"+curriculo.getCodigo()+".jpg");
			Files.deleteIfExists(path);
			curriculos=curriculoDao.listar();
			Utils.mostrarMensagemJsfSucesso("curriculo excluido com sucesso");
			
		} catch (RuntimeException  erro) {
			
			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		curriculo = (Curriculo) evento.getComponent().getAttributes().get("curriculoSelecionado");
		//Path path=Paths.get("C:/fotosdaigejamodelo2/"+curriculo.getMembro().getCodigo()+".jpg");
		
		
		
		
		curriculo.setCaminho("C:/fotosdaigeja/"+curriculo.getCodigo()+".jpg");
		
		
		empresa.setProfissaoUltimaEmpresa(curriculo.getEmpresa().getProfissaoUltimaEmpresa());
		empresa.setUltimaEmpresa(curriculo.getEmpresa().getUltimaEmpresa());
		empresa.setDataAdmissaoultimaEmpresa(curriculo.getEmpresa().getDataAdmissaoultimaEmpresa());
		empresa.setDataDemissaoultimaEmpresa(curriculo.getEmpresa().getDataDemissaoultimaEmpresa());
		empresa.setFuncoesUltimaEmpresa(curriculo.getEmpresa().getFuncoesUltimaEmpresa());
		curriculo.setEmpresa(empresa);
		
		empresa2.setProfissaoPenultimaEmpresa(curriculo.getEmpresa().getProfissaoPenultimaEmpresa());
		empresa2.setPenultimaEmpresa(curriculo.getEmpresa().getPenultimaEmpresa());
		empresa2.setDataAdmissaoPenultimaEmpresa(curriculo.getEmpresa().getDataAdmissaoPenultimaEmpresa());
		empresa2.setDataDemissaoPenultimaEmpresa(curriculo.getEmpresa().getDataDemissaoPenultimaEmpresa());
		empresa2.setFuncoesPenultimaEmpresa(curriculo.getEmpresa().getFuncoesPenultimaEmpresa());
		curriculo.setEmpresa(empresa2);
		
       
		
		
		
		empresa3.setProfissaoAntipenultimaEmpresa(curriculo.getEmpresa().getProfissaoAntipenultimaEmpresa());
		empresa3.setAntipenultimaEmpresa(curriculo.getEmpresa().getAntipenultimaEmpresa());
		empresa3.setDataAdmissaoAntipenultimaEmpresa(curriculo.getEmpresa().getDataAdmissaoAntipenultimaEmpresa());
		empresa3.setDataDemissaoAntipenultimaEmpresa(curriculo.getEmpresa().getDataDemissaoAntipenultimaEmpresa());
		empresa3.setFuncoesAntiPenultimaEmpresa(curriculo.getEmpresa().getFuncoesAntiPenultimaEmpresa());
		
		curriculo.setEmpresa(empresa3);
		
		empresa.setProfissaoUltimaEmpresa(empresa.getProfissaoUltimaEmpresa());
		empresa.setUltimaEmpresa(curriculo.getEmpresa().getUltimaEmpresa());
		curriculo.setEmpresa(empresa);
		curriculo.setNomeCompleto(curriculo.getNomeCompleto());
		curriculo.setEmail(curriculo.getEmail());
		curriculo.setTelefoneCelular(curriculo.getTelefoneCelular());
		pegarEndereco();
		curriculo.setObjetivoCurriculo(curriculo.getObjetivoCurriculo());
		curriculo.setPerfilProfissional(curriculo.getPerfilProfissional());
		curriculo.setNomeEscola(curriculo.getNomeEscola());
		curriculo.setNomeCurso(curriculo.getNomeCurso());
		curriculo.setInformacoesAdicionais(curriculo.getInformacoesAdicionais());
	}
	

		
		
		
	
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
	
	
	public void upload(FileUploadEvent evento){
		String nomeDoArquivo=evento.getFile().getFileName();
		String tipoDeArquivo=evento.getFile().getContentType();
		Long tamanhoDoArquivo=evento.getFile().getSize();
		Utils.mostrarMensagemJsfSucesso("chamou o metodo\nnome :"+nomeDoArquivo+"\ntipo :"+tipoDeArquivo+"\ntamanho :"+tamanhoDoArquivo);
		
		
		
		//pegando o arquivo de upload escolhido pelo usu�rio
		UploadedFile arquivoUpload = evento.getFile();//arquivo original
		
		
		
		try {
			//armazenando este arquivo no arquivo tempor�rio
			Path arquivoTemporario=Files.createTempFile(null,null);//arquivo destino
			
			//copiando o arquivo os stream do arquivo original no arquivo destino
			Files.copy(arquivoUpload.getInputstream(),arquivoTemporario,StandardCopyOption.REPLACE_EXISTING);
			curriculo.setCaminho(arquivoTemporario.toString());
			Utils.mostrarMensagemJsfSucesso("upload realizado com sucesso");
			
		} catch (IOException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao fazer o upload");
		}
	}
	public Curriculo getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	public List<Curriculo> getCurriculos() {
		return curriculos;
	}
	public void setCurriculos(List<Curriculo> curriculos) {
		this.curriculos = curriculos;
	}
	public EmpresaCurriculo getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaCurriculo empresa) {
		this.empresa = empresa;
	}
	public EmpresaCurriculo getEmpresa2() {
		return empresa2;
	}
	public void setEmpresa2(EmpresaCurriculo empresa2) {
		this.empresa2 = empresa2;
	}
	public EmpresaCurriculo getEmpresa3() {
		return empresa3;
	}
	public void setEmpresa3(EmpresaCurriculo empresa3) {
		this.empresa3 = empresa3;
	}
	
	public void gerarpdf(ActionEvent evento) {
		curriculo = (Curriculo) evento.getComponent().getAttributes().get("curriculoSelecionado");
		curriculo.setNomeCompleto(curriculo.getNomeCompleto().toUpperCase());
		empresa.setProfissaoUltimaEmpresa(empresa.getProfissaoUltimaEmpresa());
		empresa.setUltimaEmpresa(empresa.getUltimaEmpresa());
		empresa.setDataAdmissaoultimaEmpresa(empresa.getDataDemissaoultimaEmpresa());
		empresa.setDataDemissaoultimaEmpresa(empresa.getDataDemissaoultimaEmpresa());
		empresa.setFuncoesUltimaEmpresa(empresa.getFuncoesUltimaEmpresa());
		curriculo.setEmpresa(empresa);
		
		empresa2.setProfissaoPenultimaEmpresa(empresa2.getProfissaoPenultimaEmpresa());
		empresa2.setPenultimaEmpresa(empresa2.getPenultimaEmpresa());
		empresa2.setDataAdmissaoPenultimaEmpresa(empresa2.getDataAdmissaoPenultimaEmpresa());
		empresa2.setDataDemissaoPenultimaEmpresa(empresa2.getDataDemissaoPenultimaEmpresa());
		empresa2.setFuncoesPenultimaEmpresa(empresa.getFuncoesPenultimaEmpresa());
		curriculo.setEmpresa(empresa2);
		
       
		
		
		
		empresa3.setProfissaoAntipenultimaEmpresa(empresa3.getProfissaoAntipenultimaEmpresa());
		empresa3.setAntipenultimaEmpresa(empresa3.getAntipenultimaEmpresa());
		empresa3.setDataAdmissaoAntipenultimaEmpresa(empresa3.getDataAdmissaoAntipenultimaEmpresa());
		empresa3.setDataDemissaoAntipenultimaEmpresa(empresa3.getDataDemissaoAntipenultimaEmpresa());
		empresa3.setFuncoesAntiPenultimaEmpresa(empresa3.getFuncoesAntiPenultimaEmpresa());
		
		curriculo.setEmpresa(empresa3);
		
		
		curriculo.setNomeCompleto(curriculo.getNomeCompleto());
		curriculo.setEmail(curriculo.getEmail());
		curriculo.setTelefoneCelular(curriculo.getTelefoneCelular());
		
		curriculo.setObjetivoCurriculo(curriculo.getObjetivoCurriculo());
		curriculo.setPerfilProfissional(curriculo.getPerfilProfissional());
		curriculo.setNomeEscola(curriculo.getNomeEscola());
		curriculo.setNomeCurso(curriculo.getNomeCurso());
		curriculo.setInformacoesAdicionais(curriculo.getInformacoesAdicionais());
		
		
	}
	
	
}
