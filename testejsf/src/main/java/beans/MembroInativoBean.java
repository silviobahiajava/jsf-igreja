package beans;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.testejsf.utils.HibernateUtil;
import br.com.testejsf.utils.Utils;
import br.com.testejsf.utils.WebServiceCep;
import daos.CargoDao;
import daos.EnderecoDao;
import daos.MembroDao;
import daos.MembroInativoDao;
import entidades.Cargo;
import entidades.Endereco;
import entidades.Membro;
import entidades.MembroInativo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@ManagedBean(name = "membroinativoBean")
@ViewScoped

public class MembroInativoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long codigoMembro;
	private MembroDao membroDao;
	private CargoDao cargoDao;
	private Membro membro;
	private EnderecoDao edao=new EnderecoDao();
	private String motivoDeSaida;
	
	// private String
	// urlFoto="C:souzawebti_igreja2/modeloigreja2/src/main/webapp/resources/fotos/membros/";

	private String urlFoto2 = "C:/fotosdaigreja/membros/";
	// private String urlFoto2="./fotosmembros/";

	// private String urlFoto2="C:/fotosdaigreja/";

	// C:/SPIASSEMBLEIATESTES/tgreja1/src/main/webapp/resources/fotos/membros/branco.jpg
	// private String
	// urlFoto3="C:/Users/souzawebti/Desktop/SPI/fotodosmembros/";

	private List<Membro> membros;
	private Cargo cargo;
	private List<Cargo> cargos;
	private Endereco endereco = new Endereco();

	@PostConstruct
	public void iniciar() {
		membro = new Membro();
		endereco = new Endereco();
		cargo = new Cargo();
		membroDao = new MembroDao();
		cargoDao = new CargoDao();
		cargos=cargoDao.listar();
		listar();
	}

	public void listar() {
		membros = membroDao.buscarPorStatusMembro("INATIVO");
	}

	public void buscarMembroInativoPorJustificativaDeExclusao(){
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("SAUDOSA LEMBRANÇA")){
			motivoDeSaida="saudosa lembrança";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("MUDANÇA DE IGREJA")){
			motivoDeSaida="mudança de igreja";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("MUDANÇA DE CIDADE")){
			motivoDeSaida="mudança de cidade";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("DISCIPLINA")){
			motivoDeSaida="disciplina";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("DESVIADO")){
			motivoDeSaida="desviado";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		
		
		
	
		if(membro.getMotivoExclusaoDeMembrosEnum().getDescricao().contains("OUTROS MOTIVOS")){
			motivoDeSaida="outros motivos";
			Utils.mostrarMensagemJsfSucesso("motivo da saída "+motivoDeSaida);
			membro.setJustificativaDeExclusao(motivoDeSaida.toUpperCase());
		}
		
		membros=membroDao.buscarPorJustificativaDeExclusao(membro.getJustificativaDeExclusao());
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
			membro.setCaminho(arquivoTemporario.toString());
			Utils.mostrarMensagemJsfSucesso("upload realizado com sucesso");
			
		} catch (IOException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao fazer o upload");
		}
	}
	
	
	public void salvar() {
		try {
			if(membro.getCaminho()==null){
				Utils.mostrarMensagemJsfErro("o campo foto � obrigat�rio");
				return;
			}
			MembroDao membroDao=new MembroDao();
			if(!membro.getNome().equals(null)){
				membro.setNome(membro.getNome().toUpperCase());
				membro.setNomedamae(membro.getNomedamae().toUpperCase());
				membro.setNomedopai(membro.getNomedopai().toUpperCase());
				membro.setEndereco(endereco);
//				if(membro.getCargo().getDescricao()!="MEMBRO"){
//					membro.setTipoCargo("OBREIRO");
//				}
				//membro.setCargo(membro.getCargo());
				membro.setStatusMembro("INATIVO");
				
				membro.setDataNascimento(membro.getDataNascimento());
				membro.setNacionalidade("BRASILEIRA");
				membro.setNatualidade(membro.getNatualidade().toUpperCase());
				membro.setDataBatismo(membro.getDataBatismo());
				membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
				//membroDao.merge(membro);
				Membro membroCadastrado = membroDao.salvarMembro(membro);
				//pegando o caminho do arquivo tempor�rio
				Path origem=Paths.get(membro.getCaminho());
				//Path destino=Paths.get("C:/fotosdaigreja/"+membroCadastrado.getCodigo()+".jpg");
				Path destino=Paths.get(urlFoto2+membroCadastrado.getCodigo()+".jpg");
				
				Files.copy(origem,destino, StandardCopyOption.REPLACE_EXISTING);
				membro=new Membro();
				CargoDao cargoDao=new CargoDao();
				cargos=cargoDao.listar();
				membros=membroDao.listar();
				Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucess\nnome da foto :"+membroCadastrado.getCodigo());
			}
			
		} catch (RuntimeException  erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
public void carregarEdicao(){
	cargos=cargoDao.listar();
	membro=membroDao.buscar(codigoMembro);
	membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
}

public void novo(){
	membro=new Membro();
}

	public void pegarEndereco() {
		Utils.mostrarMensagemJsfSucesso("pegando endere�o");
		WebServiceCep wc = WebServiceCep.searchCep(endereco.getCep());
		endereco.setCep(endereco.getCep());

		endereco.setEndereco(wc.getLogradouro().toUpperCase());
		endereco.setNumero(endereco.getNumero());
		endereco.setBairro(wc.getBairro().toUpperCase());
		endereco.setCidade(wc.getCidade().toUpperCase());
		endereco.setEstado(wc.getUf().toUpperCase());
	}

	
	//85.60 � 53.98 mm (= 3.370 � 2.125 polegadas medida do cart�o
		public void gerarCarterinhaFundoAmarelo() {
			try {
					String arquivo="/reports/amarelopreto.jasper";
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				//String caminho = Faces.getRealPath("/reports/amarelo.jasper");
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		
		public void gerarCarterinhaFundoAzul1() {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				String caminho = Faces.getRealPath("/reports/lightbluepreto.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		
		
		
		
		public void gerarCartaodeMembro(ActionEvent ev) {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				//DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				//Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				//String nome=(String)filtros.get("nome");
				Membro membro=(Membro)ev.getComponent().getAttributes().get("membroSelecionado");
				String caminho = Faces.getRealPath("/reports/verdepreto.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(membro.getNome()==null) {
					//parametros.put("NOME","%%");
					parametros.put("NOME",membro.getNome());
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso para "+membro.getNome());
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		//-----------------------------------------------------------------------
		
		
		
		public void gerarCarterinhaFundoVerde() {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				String caminho = Faces.getRealPath("/reports/verdepreto.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		//----------------------------------------------------------------------------
		
		//marrom claro-----------------------------------------------------
		
		
		public void gerarCarterinhaFundoMarrom() {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				String caminho = Faces.getRealPath("/reports/marronclaro.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		//---------------------------------------------------------------------
		
		//-----------lightcoral
		public void gerarCarterinhaFundoLightCoral() {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				String caminho = Faces.getRealPath("/reports/lightcoral.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//branco preto	
		
		public void gerarCarterinhaFundoBranco() {
			try {
				String arquivo="/reports/brancopreto.jasper";
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				//String caminho = Faces.getRealPath("/reports/fundobranco.jasper");
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo branco gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		
		public void gerarCarterinhaFundoVermelho() {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				String caminho = Faces.getRealPath("/reports/fundovermelho.jasper");
				Map<String,Object>parametros=new HashMap<String,Object>();
				
			   	if(nome==null) {
					parametros.put("NOME","%%");
				}else {
					//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", membro.getNome());
				}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo vermelho gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
	
		
		public void converterMembroAtivoParaMembroInativo(ActionEvent evento) {
			try {
				membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
				

				MembroInativo inativo=new MembroInativo();
				
				MembroInativoDao inativoDao=new MembroInativoDao();
				membro.setStatusMembro("INATIVO");
				inativo.setMembro(membro);
				inativoDao.salvar(inativo);
				
				
				membros=membroDao.listar();
				Utils.mostrarMensagemJsfSucesso("membro inativo ");
				
			} catch (RuntimeException  erro) {
				
				Utils.mostrarMensagemJsfErro("nao foi possIivel excluir");
				erro.printStackTrace();
			}
		}
	
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public String getUrlFoto2() {
		return urlFoto2;
	}

	public void setUrlFoto2(String urlFoto2) {
		this.urlFoto2 = urlFoto2;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getCodigoMembro() {
		return codigoMembro;
	}

	public void setCodigoMembro(Long codigoMembro) {
		this.codigoMembro = codigoMembro;
	}
	
	
	public void excluirMembroInativo(ActionEvent evento) {
		try {
			membro = (Membro) evento.getComponent().getAttributes().get("membroInativoSelecionado");

			MembroDao membroDao = new MembroDao();
			membroDao.excluir(membro);
			Path path = Paths.get(urlFoto2 + membro.getCodigo() + ".jpg");
			Files.deleteIfExists(path);
			membros = membroDao.listar();
			Utils.mostrarMensagemJsfSucesso("membro excluido com sucesso");

		} catch (RuntimeException erro) {

			Utils.mostrarMensagemJsfErro("n�o foi poss�vel excluir");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
