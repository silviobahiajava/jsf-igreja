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
import entidades.Cargo;
import entidades.Endereco;

import entidades.Membro;
import enums.TipoCadastroEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;


@ManagedBean(name="obreirodaigrejaBean")
@ViewScoped
public class ObreiroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Membro membro=new Membro();
	private Long codigomembro;
	private MembroDao membroDao=new MembroDao();
	private CargoDao cargoDao;
	private Map<String,Object>parametros=new HashMap<String,Object>();
	private EnderecoDao edao=new EnderecoDao();
	
	// private String
	// urlFoto="C:souzawebti_igreja2/modeloigreja2/src/main/webapp/resources/fotos/membros/";

	private String urlFoto2 = "C:/fotos/igrejas/missaoefe/";
	// private String urlFoto2="./fotosmembros/";

	// private String urlFoto2="C:/fotosdaigreja/";

	// C:/SPIASSEMBLEIATESTES/tgreja1/src/main/webapp/resources/fotos/membros/branco.jpg
	// private String
	// urlFoto3="C:/Users/souzawebti/Desktop/SPI/fotodosmembros/";

	private List<Membro> obreiros;
	private Cargo cargo;
	private List<Cargo> cargos;
	private Endereco endereco = new Endereco();
	private Membro membroCadastrado;

	@PostConstruct
	public void iniciar() {
		membro = new Membro();
		endereco = new Endereco();
		cargo = new Cargo();
		membroDao = new MembroDao();
		cargoDao = new CargoDao();
		cargos=cargoDao.listar();
		carregarMinistros();
	}

	public void listar() {
		obreiros = membroDao.buscarPorTipoDeCargo("OBREIRO");
	}

	public void carregarMinistros() {
		try {
			obreiros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","OBREIRO");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
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
			
			if(!membro.getNome().equals(null)){
				membro.setNome(membro.getNome().toUpperCase());
				membro.setNomedamae(membro.getNomedamae().toUpperCase());
				membro.setNomedopai(membro.getNomedopai().toUpperCase());
				membro.setEndereco(endereco);
				//membro.setCargo(membro.getCargo());
				membro.setCargoEnum(membro.getCargoEnum());
				membro.setDataNascimento(membro.getDataNascimento());
				membro.setNacionalidade("BRASILEIRA");
				membro.setNatualidade(membro.getNatualidade().toUpperCase());
				membro.setDataBatismo(membro.getDataBatismo());
				membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
				//membroDao.merge(membro);
				membroCadastrado = membroDao.salvarMembro(membro);
				//pegando o caminho do arquivo tempor�rio
				Path origem=Paths.get(membro.getCaminho());
				//Path destino=Paths.get("C:/fotosdaigreja/"+membroCadastrado.getCodigo()+".jpg");
				Path destino=Paths.get(urlFoto2+membroCadastrado.getCodigo()+".jpg");
				
				Files.copy(origem,destino, StandardCopyOption.REPLACE_EXISTING);
				membro=new Membro();
				CargoDao cargoDao=new CargoDao();
				cargos=cargoDao.listar();
				obreiros = membroDao.buscarPorTipoDeCargo("OBREIRO");
				Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucess\nnome da foto :"+membroCadastrado.getCodigo());
			}
			
		} catch (RuntimeException erro) {
			
			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
public void carregarEdicao(){
	cargos=cargoDao.listar();
	membro=membroDao.buscar(codigomembro);
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
					String parametroCargo="OBREIRO";
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				//String caminho = Faces.getRealPath("/reports/amarelo.jasper");
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				parametros.put("TIPOCARGO",parametroCargo);
				
				if(nome==null) {
					parametros.put("NOME","%%");
					}if(nome!=null) {
						//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", "%"+nome+"%");
			}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
			
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		//1-CARTAO DE membro VERDE
		public void gerarCarterinhaFundoVerde() {
			try {
					String arquivo="/reports/cartaodeobreiroverde.jasper";
					String parametroCargo="OBREIRO";
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String nome=(String)filtros.get("nome");
				//String caminho = Faces.getRealPath("/reports/amarelo.jasper");
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				parametros.put("TIPOCARGO",parametroCargo);
				
				if(nome==null) {
					parametros.put("NOME","%%");
					}if(nome!=null) {
						//parametros.put("NOME","%"+nome+"%");
					parametros.put("NOME", "%"+nome+"%");
			}
				
				
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
			
				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
			}
		}
		
		
		
		//2-CARTAO DE membro AMARELO
		public void gerarCarterinhaDeObreiroFundoAmarelo(){
			try {
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				String arquivo="/reports/amarelopreto.jasper";
				String parametroCargo="OBREIRO";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPOCARGO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso ");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
		}
		}
		
		//3-CARTAO DE membro AZUL
		public void gerarCarterinhaDeObreiroFundoAzul(){
			try {
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				String arquivo="/reports/lightbluepreto.jasper";
				String parametroCargo="membro";
			String caminho=Faces.getRealPath(arquivo);
			Map<String,Object>parametros=new HashMap<String,Object>();
			if(parametroNome==null) {
				parametros.put("NOME","%%");
			}else {
				parametros.put("NOME","%"+parametroNome+"%");
				
			}
			
			parametros.put("TIPOCARGO",parametroCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
		}
		}
		
		
		//4-CARTAO DE membro MARRON
				public void gerarCarterinhaDeObreiroFundoMarron(){
					try {
						
						//pegando o componente DataTable da �rvore de componentes do primefaces
						DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
						
						//pegando o mapa de filtros do componente datatable
						Map<String, Object> filtros = tabela.getFilters();
						//pegando o filtro da coluna nome
						String parametroNome=(String)filtros.get("nome");
						
						String arquivo="/reports/marronclaro.jasper";
						String parametroCargo="membro";
					String caminho=Faces.getRealPath(arquivo);
					Map<String,Object>parametros=new HashMap<String,Object>();
					if(parametroNome==null) {
						parametros.put("NOME","%%");
					}else {
						parametros.put("NOME","%"+parametroNome+"%");
						
					}
					
					parametros.put("TIPOCARGO",parametroCargo);
					Connection conexao = HibernateUtil.getConexao();
					JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperPrintManager.printReport(relatorio, true);
					Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso");
				}catch(JRException e) {
					Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
				}
				}
		
		
			
				//5-CARTAO DE membro CINZA
				public void gerarCarterinhaDeObreiroFundoCinza(){
					try {
						
						//pegando o componente DataTable da �rvore de componentes do primefaces
						DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
						
						//pegando o mapa de filtros do componente datatable
						Map<String, Object> filtros = tabela.getFilters();
						//pegando o filtro da coluna nome
						String parametroNome=(String)filtros.get("nome");
						
						String arquivo="/reports/lightcoral.jasper";
						String parametroCargo="membro";
					String caminho=Faces.getRealPath(arquivo);
					Map<String,Object>parametros=new HashMap<String,Object>();
					if(parametroNome==null) {
						parametros.put("NOME","%%");
					}else {
						parametros.put("NOME","%"+parametroNome+"%");
						
					}
					
					parametros.put("TIPO",parametroCargo);
					Connection conexao = HibernateUtil.getConexao();
					JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperPrintManager.printReport(relatorio, true);
					Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso");
				}catch(JRException e) {
					Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
				}
				}
		
		
				//6-CARTAO DE membro BRANCO
				public void gerarCarterinhaDeObreiroFundoBranco(){
					try {
						
						//pegando o componente DataTable da �rvore de componentes do primefaces
						DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
						
						//pegando o mapa de filtros do componente datatable
						Map<String, Object> filtros = tabela.getFilters();
						//pegando o filtro da coluna nome
						String parametroNome=(String)filtros.get("nome");
						
						String arquivo="/reports/brancopreto.jasper";
						String parametroCargo="membro";
					String caminho=Faces.getRealPath(arquivo);
					Map<String,Object>parametros=new HashMap<String,Object>();
					if(parametroNome==null) {
						parametros.put("NOME","%%");
					}else {
						parametros.put("NOME","%"+parametroNome+"%");
						
					}
					
					parametros.put("TIPOCARGO",parametroCargo);
					Connection conexao = HibernateUtil.getConexao();
					JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperPrintManager.printReport(relatorio, true);
					Utils.mostrarMensagemJsfSucesso("carterinha de membro gerada com sucesso");
				}catch(JRException e) {
					Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de membro");
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
		
		
		
		
		
		public void gerarCartaodemembro(ActionEvent ev) {
			try {
				//pegando o componente DataTable da �rvore de componentes do primefaces
				//DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				//Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				//String nome=(String)filtros.get("nome");
				membro =(Membro)ev.getComponent().getAttributes().get("membroSelecionado");
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
		
		
		
//		public void gerarCarterinhaFundoVerde() {
//			try {
//				//pegando o componente DataTable da �rvore de componentes do primefaces
//				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
//				
//				//pegando o mapa de filtros do componente datatable
//				Map<String, Object> filtros = tabela.getFilters();
//				//pegando o filtro da coluna nome
//				String nome=(String)filtros.get("nome");
//				String caminho = Faces.getRealPath("/reports/verdepreto.jasper");
//				Map<String,Object>parametros=new HashMap<>();
//				
//			   	if(nome==null) {
//					parametros.put("NOME","%%");
//				}else {
//					//parametros.put("NOME","%"+nome+"%");
//					parametros.put("NOME", membro.getNome());
//				}
//				
//				
//				Connection conexao = HibernateUtil.getConexao();
//				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
//				JasperPrintManager.printReport(relatorio, true);
//				Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
//			}catch(JRException e) {
//				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
//			}
//		}
		
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
		
		public void listarmembroPorCodigo(){
			obreiros=membroDao.buscarPorTipoDeCargoECodigo("OBREIRO",membro.getCodigo());
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
	
	
	
	

	public String getUrlFoto2() {
		return urlFoto2;
	}

	public void setUrlFoto2(String urlFoto2) {
		this.urlFoto2 = urlFoto2;
	}

	

	

	public Membro getmembro() {
		return membro;
	}

	public void setmembro(Membro membro) {
		this.membro = membro;
	}

	

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public List<Membro> getObreiros() {
		return obreiros;
	}

	public void setObreiros(List<Membro> obreiros) {
		this.obreiros = obreiros;
	}

	public Membro getmembroCadastrado() {
		return membroCadastrado;
	}

	public void setmembroCadastrado(Membro membroCadastrado) {
		this.membroCadastrado = membroCadastrado;
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

	public Long getCodigomembro() {
		return codigomembro;
	}

	public void setCodigomembro(Long codigomembro) {
		this.codigomembro = codigomembro;
	}
	
	/*public void convertermembroAtivoParamembroInativo(ActionEvent evento) {
		try {
			membro = (membro) evento.getComponent().getAttributes().get("membroSelecionado");
			

			membroInativo inativo=new membroInativo();
			
			membroInativoDao inativoDao=new membroInativoDao();
			if(membro.getStatusmembro().contains("ATIVO")){
				membro.setStatusmembro("INATIVO");
			}
			Utils.mostrarMensagemJsfSucesso("status do membro "+membro.getStatusmembro());
			membroDao.editar(membro);
			
			
			
			inativo.setMotivoSaida(membro.getMotivoSaida());
			if(inativo.getMotivoSaida().equals("SAUDADES ETERNAS")){
				membro.setCodigoMotivoInatividade(1L);
			}
			if(inativo.getMotivoSaida().equals("MUDOU DE IGREJA")){
				membro.setCodigoMotivoInatividade(2L);
			}
			
			if(inativo.getMotivoSaida().equals("MUDOU DE CIDADE")){
				membro.setCodigoMotivoInatividade(3L);
			}
			if(inativo.getMotivoSaida().equals("DISCIPLINA")){
				membro.setCodigoMotivoInatividade(4L);
			}
			
			if(inativo.getMotivoSaida().equals("DESVIADO")){
				membro.setCodigoMotivoInatividade(5L);
			}
			inativo.setCodigoMotivoInatividade(membro.getCodigoMotivoInatividade());
			inativo.setmembro(membro);
			inativoDao.salvar(inativo);
			
			
			membros=membroDao.buscarPorStatusmembro("ATIVO");
			Utils.mostrarMensagemJsfSucesso("membro inativo motivo "+inativo.getMotivoSaida());
			
		} catch (RuntimeException  erro) {
			
			Utils.mostrarMensagemJsfErro("nao foi possIivel excluir");
			erro.printStackTrace();
		}
	}*/
	
	public void verDetalhes(ActionEvent evento){
		membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
		membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
		CargoDao cargoDao=new CargoDao();
		cargos=cargoDao.listar();
		membro.setNome(membro.getNome().toUpperCase());
		membro.setNomedamae(membro.getNomedamae());
		membro.setNomedopai(membro.getNomedopai());
		membro.setEndereco(endereco);
	
		membro.setDataNascimento(membro.getDataNascimento());
		membro.setNacionalidade("BRASILEIRA");
		membro.setNatualidade(membro.getNatualidade());
		membro.setDataBatismo(membro.getDataBatismo());
		membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
	}
	
	
	
	public void gerarCarterinhaPorParametro(ActionEvent ev) {
		try {
			String arquivo="/reports/amarelopreto.jasper";
			String parametroCargo="OBREIRO";
			membro=(Membro) ev.getComponent().getAttributes().get("obreiroSelecionado");
		//pegando o componente DataTable da �rvore de componentes do primefaces
		
		
		//pegando o mapa de filtros do componente datatable
		
		//String caminho = Faces.getRealPath("/reports/amarelo.jasper");
		String caminho=Faces.getRealPath(arquivo);
		
		parametros.put("TIPOCARGO",parametroCargo);
		
		if(membro.getNome()==null) {
			parametros.put("NOME","%%");
			}if(membro.getNome()!=null) {
				//parametros.put("NOME","%"+nome+"%");
			parametros.put("NOME", "%"+membro.getNome()+"%");
	}
		
		
		Connection conexao = HibernateUtil.getConexao();
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		JasperPrintManager.printReport(relatorio, true);
	
		Utils.mostrarMensagemJsfSucesso("CARTEIRINHA GERARA PARA  "+membro.getNome());
	}catch(JRException e) {
		Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
	}
	}
	
	
	
	
}
