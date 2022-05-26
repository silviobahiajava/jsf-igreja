package beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import daos.ObreiroDaIgrejaDao;
import entidades.Cargo;
import entidades.CertificadoBatismo;
import entidades.Endereco;
import entidades.Membro;
import entidades.MembroInativo;
import entidades.ObreirosDaIgreja;
import entidades.Relatorios;
import enums.DiaEnum;
import enums.EstadosBrasileirosEnum;
import enums.MesEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "membroBean3")
@ViewScoped

public class MembroBean3 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long codigoMembro;
	private MembroDao membroDao;
	private CargoDao cargoDao;
	private Membro membro=new Membro();
	private String motivoDeSaida;
	private CertificadoBatismo certificadoBatismo;
	private List<CertificadoBatismo>listaCertificadoBatismo;
	private String tipoCargo;
	
	
	private HttpServletResponse response;
	private FacesContext context;
	private ByteArrayOutputStream  baos=new ByteArrayOutputStream();
	private InputStream stream;
	
	
	
	private EnderecoDao edao=new EnderecoDao();
	
	// private String
	// urlFoto="C:souzawebti_igreja2/modeloigreja2/src/main/webapp/resources/fotos/membros/";

	//private String urlFoto2 = "./resources/fotos";
	
	private String urlFoto2 = "C:/fotos/igrejas/missaoefe/";
	
	
	
	// private String urlFoto2="./fotosmembros/";

	// private String urlFoto2="C:/fotosdaigreja/";

	// C:/SPIASSEMBLEIATESTES/tgreja1/src/main/webapp/resources/fotos/membros/branco.jpg
	// private String
	// urlFoto3="C:/Users/souzawebti/Desktop/SPI/fotodosmembros/";

	private List<Membro> membros;
	private Cargo cargo;
	private List<Cargo> cargos;
	private Endereco endereco = new Endereco();
	
	
	
	private String nomeBatizador;

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
		membros = membroDao.buscarPorStatusMembro("ATIVO");
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
	
	
	
	public void verDetalhes(ActionEvent evento){
		membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
		membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
		CargoDao cargoDao=new CargoDao();
		cargos=cargoDao.listar();
		membro.setNome(membro.getNome().toUpperCase());
		membro.setNomeMae(membro.getNomeMae());
		membro.setNomePai(membro.getNomePai());
		membro.setEndereco(endereco);
		cargo.setDescricao(cargo.getDescricao());
		//membro.setCargoEnum(cargo);
		
		membro.setDataNascimento(membro.getDataNascimento());
		membro.setNacionalidade("BRASILEIRA");
		membro.setNatualidade(membro.getNatualidade());
		membro.setDataBatismo(membro.getDataBatismo());
		membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
	}
	
	
	public void verificarCargo(){
		if(cargo.getDescricao().equals("COOPERADOR")
				|| cargo.getDescricao().equals("DIACONO")
				||cargo.getDescricao().equals("PRESBITERO")
				||cargo.getDescricao().equals("EVANGELISTA")
				||cargo.getDescricao().equals("PASTOR")){
			
			membro.setTipoCargo("OBREIRO");
		}else{
			membro.setTipoCargo("MEMBRO");
		}
		Utils.mostrarMensagemJsfSucesso("tipo de cargo "+membro.getTipoCargo());
	}
	public void salvar() {
		try {
			if(membro.getCaminho()==null){
				Utils.mostrarMensagemJsfErro("o campo foto  obrigat�rio");
				return;
			}
			MembroDao membroDao=new MembroDao();
			//ObreiroDaIgrejaDao obreiroDao=new ObreiroDaIgrejaDao();
			//ObreirosDaIgreja obreiro=new ObreirosDaIgreja();
			
				membro.setCargoEnum(membro.getCargoEnum());
				membro.setStatusMembro("ATIVO");
				
				membro.setNome(membro.getNome().toUpperCase());
				membro.setNomeMae(membro.getNomeMae());
				membro.setNomePai(membro.getNomePai());
				membro.setEndereco(endereco);
				
			if(!cargo.getDescricao().equals("MEMBRO")){
					tipoCargo="OBREIRO";
					membro.setTipoCargo(tipoCargo);
					Utils.mostrarMensagemJsfSucesso("tipo de cargo "+membro.getTipoCargo()+"ou "+tipoCargo);
				}
				if(cargo.getDescricao().equals("MEMBRO")){
					tipoCargo="MEMBRO";
					membro.setTipoCargo(tipoCargo);
					Utils.mostrarMensagemJsfSucesso("tipo de cargo "+membro.getTipoCargo()+"ou "+tipoCargo);
				}
			
//				if(
//							(membro.getCargo().getDescricao().contains("DIACONO")
//						|| (membro.getCargo().getDescricao().contains("COOPERADOR")
//						|| (membro.getCargo().getDescricao().contains("PRESBITERO")
//						|| (membro.getCargo().getDescricao().contains("EVANGELISTA")
//						|| (membro.getCargo().getDescricao().contains("PASTOR")	)
//					 {		
//						membro.setTipoCargo("OBREIRO");
//					}
//				
//				
//				if(membro.getCargo().getDescricao().contains("NEMBRO")){
//					membro.setTipoCargo("MEMBRO");
//				}
				
				membro.setDataNascimento(membro.getDataNascimento());
				//endereco de nascimento
				membro.setNacionalidade("BRASILEIRA");
				membro.setNatualidade(membro.getNatualidade());
				
				membro.setDataBatismo(membro.getDataBatismo());
				//endereco do membro
				membro.setBairroEnum(membro.getBairroEnum());
				membro.setCidadeEnum(membro.getCidadeEnum());
				membro.setEstadosBrasileirosEnum(EstadosBrasileirosEnum.PR);
				
				//membroDao.merge(membro);
				Membro membroCadastrado = membroDao.salvarMembro(membro);
				//membro=membroDao.salvarMembro(membro);
				//pegando o caminho do arquivo tempor�rio
				Path origem=Paths.get(membro.getCaminho());
				//Path destino=Paths.get("C:/fotosdaigreja/"+membroCadastrado.getCodigo()+".jpg");
				Path destino=Paths.get(urlFoto2+membroCadastrado.getCodigo()+".jpg");
				
				Files.copy(origem,destino, StandardCopyOption.REPLACE_EXISTING);
				//membro=new Membro();
				CargoDao cargoDao=new CargoDao();
				cargos=cargoDao.listar();
				listar();
				Utils.mostrarMensagemJsfSucesso("cadastrado "+membroCadastrado.getCodigo());
				Utils.mostrarMensagemJsfSucesso("tipo de cargo "+membro.getTipoCargo());
				Utils.mostrarMensagemJsfSucesso(" cargo "+membro.getCargoEnum());
			
			
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

public void carregarMembroPraExclusao(){
	cargos=cargoDao.listar();
	membro=membroDao.buscar(codigoMembro);
	membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
	Utils.mostrarMensagemJsfSucesso("membro pra excluir"+membro.getNome().toUpperCase());
	Utils.mostrarMensagemJsfSucesso(membro.getNome()+" é natural de "+membro.getNatualidade().toUpperCase()+"-"+membro.getEstadosBrasileirosEnum());
	System.out.println("membro pra excluir "+membro.getNome());
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
	//FILTRO FUNCIONOU
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
					String arquivo="/reports/lightcoral.jasper";
				
				//pegando o componente DataTable da �rvore de componentes do primefaces
				DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
				
				//pegando o mapa de filtros do componente datatable
				Map<String, Object> filtros = tabela.getFilters();
				//pegando o filtro da coluna nome
				String parametroNome=(String)filtros.get("nome");
				
				
				String parametroCargo="MEMBRO";
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
			Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
		}catch(JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
		}
		}
		
		
		public void carregarMembroPraCertificadoDeBatismo(){
			cargos=cargoDao.listar();
			membro=membroDao.buscar(codigoMembro);
			membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
			Utils.mostrarMensagemJsfSucesso("gerando certificado de batismo para "+membro.getNome());
		}
		
		public void gerarCertificaoDeBatismo(){
			try{
				listaCertificadoBatismo=new ArrayList<CertificadoBatismo>();
				certificadoBatismo.setCidadeBatismo(membro.getCidadeBatismo());
				
				
				certificadoBatismo.setNomeBatizando(membro.getNome());
				certificadoBatismo.setDataBatismo(membro.getDataBatismo());
				certificadoBatismo.setNomeBatizador(nomeBatizador);
				certificadoBatismo.setLocalBatismo(membro.getLocalDeBatismo());
				certificadoBatismo.setEstadoBatismo(membro.getEstadoBatismo());
				listaCertificadoBatismo.add(certificadoBatismo);
				
			    Relatorios.gerarCertificadoDeBatismo(listaCertificadoBatismo);
			    Utils.mostrarMensagemJsfSucesso("certificado gerado com sucesso");
				
			}catch(RuntimeException e){
				Utils.mostrarMensagemJsfErro("erro ao gerar o certificado de batismo para "+membro.getNome());
				e.printStackTrace();
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
				if(membro.getStatusMembro().contains("ATIVO")){
					membro.setStatusMembro("INATIVO");
				}
				Utils.mostrarMensagemJsfSucesso("status do membro "+membro.getStatusMembro());
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
				inativo.setMembro(membro);
				inativoDao.salvar(inativo);
				
				
				membros=membroDao.buscarPorStatusMembro("ATIVO");
				Utils.mostrarMensagemJsfSucesso("membro inativo motivo "+inativo.getMotivoSaida());
				
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
	
	
	public String getMotivoDeSaida() {
		return motivoDeSaida;
	}

	public void setMotivoDeSaida(String motivoDeSaida) {
		this.motivoDeSaida = motivoDeSaida;
	}

	public CertificadoBatismo getCertificadoBatismo() {
		return certificadoBatismo;
	}

	public void setCertificadoBatismo(CertificadoBatismo certificadoBatismo) {
		this.certificadoBatismo = certificadoBatismo;
	}

	public List<CertificadoBatismo> getListaCertificadoBatismo() {
		return listaCertificadoBatismo;
	}

	public void setListaCertificadoBatismo(List<CertificadoBatismo> listaCertificadoBatismo) {
		this.listaCertificadoBatismo = listaCertificadoBatismo;
	}

	public String getNomeBatizador() {
		return nomeBatizador;
	}

	public void setNomeBatizador(String nomeBatizador) {
		this.nomeBatizador = nomeBatizador;
	}

	public void editar(ActionEvent evento){
		membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
		/*
		 * membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
		 
		CargoDao cargoDao=new CargoDao();
		cargos=cargoDao.listar();
		membro.setNome(membro.getNome().toUpperCase());
		membro.setNomeMae(membro.getNomeMae());
		membro.setNomePai(membro.getNomePai());
		membro.setEndereco(endereco);
		
		membro.setDataNascimento(membro.getDataNascimento());
		membro.setNacionalidade("BRASILEIRA");
		membro.setNatualidade(membro.getNatualidade());
		membro.setDataBatismo(membro.getDataBatismo());
		membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
		
		/*CargoDao cargoDao=new CargoDao();
		cargos=cargoDao.listar();
		membro.setNome(membro.getNome());
		membro.setRg(membro.getRg());
		membro.setCpf(membro.getCpf());
		filiacao.setNomeDaMae(filiacao.getNomeDaMae());
		filiacao.setNomeDoPai(filiacao.getNomeDoPai());
		membro.setFiliacao(filiacao);
		membro.setEndereco(endereco);
		membro.setDataNascimento(membro.getDataNascimento());
		membro.setNacionalidade("BRASILEIRA");
		membro.setNatualidade(membro.getNatualidade());
		
		membro.setDataBatismo(membro.getDataBatismo());
		*/
	
	}
	
	//1-CARTAO DE MEMBRO VERDE
	
	public void gerarCarterinhaMembroFundoVerde() {
		try {
				String arquivo="/reports/verdepreto.jasper";
			
			//pegando o componente DataTable da �rvore de componentes do primefaces
			DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			
			//pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			//pegando o filtro da coluna nome
			String parametroNome=(String)filtros.get("nome");
			
			
			String parametroCargo="MEMBRO";
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
		Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
	}catch(JRException e) {
		Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
	}
	}
	
	
	//2-CARTAO DE MEMBRO MARRON
			public void gerarCarterinhaDeMembroFundoMarron(){
				try {
					
					String arquivo="/reports/marronclaro.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					
					String parametroCargo="MEMBRO";
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
				Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
			}
			}

		
			//3CARTAO DE MEMBRO AZUL
		/*	public void gerarCartaodeMembroIadmif(){
				try {
					
					String arquivo="/reports/cartaodemembro_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					String parametroTipoCargo="MEMBRO";
					String parametroCargo="MEMBRO";
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
			}
			}
			
			*/
			//=================================================================================

			
			//3CARTAO DE MEMBRO AZUL
		/*	public void gerarCartaodeObreiroIadmif(){
				try {
					
					String arquivo="/reports/cartaodeobreiro_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					String parametroCargo=(String)filtros.get("cargo");
					
					String parametroTipoCargo="OBREIRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				if(parametroCargo==null) {
					parametros.put("CARGO","%%");
				}else {
					parametros.put("CARGO","%"+parametroCargo+"%");
					
				}
				
				//parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
				e.printStackTrace();
			}
			}
*/
			
			
			
			
			
			
			
			
			
			///==================================================================================
			
			//4-CARTAO DE MEMBRO AMARELO
			public void gerarCarterinhaDeMembroFundoAmarelo(){
				try {
					
					String arquivo="/reports/amarelopreto.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					
					String parametroCargo="MEMBRO";
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
				Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
			}
			}


			
			//5-CARTAO DE MEMBRO BRANCO
			public void gerarCarterinhaDeMembroFundoBranco(){
				try {
					
					String arquivo="/reports/brancopreto.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					
					String parametroCargo="MEMBRO";
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
				Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de obreiro");
			}
			}

	
			//6-CARTAO DE MEMBRO CINZA
			public void gerarCarterinhaDeMembroFundoCinza(){
				try {
					
					String arquivo="/reports/lightcoral.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					
					String parametroCargo="MEMBRO";
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
				Utils.mostrarMensagemJsfSucesso("carterinha de obreiro gerada com sucesso");
			}catch(JRException e) {
				
			}
			}


			public void excluirMembro(){
				
				Utils.mostrarMensagemJsfSucesso("membro escolhido "+membro.getNome());
				Utils.mostrarMensagemJsfSucesso("nova cidade do membro "+membro.getNatualidade()+"-"+membro.getEstadosBrasileirosEnum());
				Utils.mostrarMensagemJsfSucesso("motivo da saida "+membro.getMotivoExclusaoDeMembrosEnum());
				membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
				CargoDao cargoDao=new CargoDao();
				cargos=cargoDao.listar();
				
				
				
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
				
				membro.setStatusMembro("INATIVO");
				membro.setMotivoExclusaoDeMembrosEnum(membro.getMotivoExclusaoDeMembrosEnum());
				
				try{
					membroDao.editar(membro);
					Utils.mostrarMensagemJsfSucesso(membro.getNome()+"não faz mais parte do ROL DE MEMBROS");
				}catch(RuntimeException e){
					Utils.mostrarMensagemJsfErro("erro ao realizar este processo "+e.getMessage());
					e.printStackTrace();
				}
			
			}

			
			
			
			public void imprimirCartaoDeObreiro(){
				try{
					Relatorios.gerarCartaodeObreiroIadmif();
				}catch(RuntimeException e){
					e.printStackTrace();
				}
			}
			
			
			public void imprimirCartaoDeMembro(){
				try{
					
					Relatorios.gerarCartaodeMembroIadmif();
				}catch(RuntimeException e){
					e.printStackTrace();
				}
			}
			
			
			//3CARTAO DE MEMBRO AZUL
			public  void gerarCartaodeMembroIadmif(){
				try {
					
					String arquivo="/reports/cartaodemembro_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					String parametroTipoCargo="MEMBRO";
					String parametroCargo="MEMBRO";
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
			}
			}
			//=================================================================================
			//FUNCIONA PERFEITAMENTE
			public void mostrarCartaoDeMembrosNaWeb(){
				String arquivo="/reports/cartaodemembro_iadmif.jasper";
				  FacesContext facesContext = FacesContext.getCurrentInstance();
				  
			        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			 
			        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
			 
			        response.setContentType("application/pdf");
			 
			        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
			        DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			        Map<String, Object> filtros = tabela.getFilters();
			        	String parametroNome=(String)filtros.get("nome");
					
					String parametroTipoCargo="MEMBRO";
					String parametroCargo="MEMBRO";
					//String caminho=Faces.getRealPath(arquivo);
					Map<String,Object>parametros=new HashMap<String,Object>();
					if(parametroNome==null) {
						parametros.put("NOME","%%");
					}else {
						parametros.put("NOME","%"+parametroNome+"%");
						
					}
					
					parametros.put("CARGO",parametroCargo);
					parametros.put("TIPOCARGO",parametroTipoCargo);
					Connection conexao = HibernateUtil.getConexao();
					
			 
			        try {
			            ServletOutputStream servletOutputStream = response.getOutputStream();
			 
			          //  JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(arrayList);
			 
			            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conexao);
			 
			            servletOutputStream.flush();
			            servletOutputStream.close();
			        } catch (JRException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally{
			            facesContext.responseComplete();
			        }
			}
			
			
			
			public void mostrarCartaoDeObreirosNaWeb(){
				String arquivo="/reports/cartaodeobreiro_iadmif.jasper";
				  FacesContext facesContext = FacesContext.getCurrentInstance();
				  
			        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			 
			        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);
			 
			        response.setContentType("application/pdf");
			 
			        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
			        DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
			        Map<String, Object> filtros = tabela.getFilters();
			        	String parametroNome=(String)filtros.get("nome");
			        	String parametroCargo=(String)filtros.get("cargo");
						
						String parametroTipoCargo="OBREIRO";
					
					
					
					//String caminho=Faces.getRealPath(arquivo);
					Map<String,Object>parametros=new HashMap<String,Object>();
					if(parametroNome==null) {
						parametros.put("NOME","%%");
					}else {
						parametros.put("NOME","%"+parametroNome+"%");
						
					}
					
					if(parametroCargo==null) {
						parametros.put("CARGO","%%");
					}else {
						parametros.put("CARGO","%"+parametroCargo+"%");
						
					}
					
					parametros.put("TIPOCARGO",parametroTipoCargo);
					Connection conexao = HibernateUtil.getConexao();
					
			 
			        try {
			            ServletOutputStream servletOutputStream = response.getOutputStream();
			 
			          //  JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(arrayList);
			 
			            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conexao);
			 
			            servletOutputStream.flush();
			            servletOutputStream.close();
			        } catch (JRException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally{
			            facesContext.responseComplete();
			        }
			 




			}
			
			

			public void gerarCartaodeMembroIadmif2(){
				try {
					
					String arquivo="/reports/cartaodemembro2_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					
					String parametroTipoCargo="MEMBRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de MEMBRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de MEMBRO");
			}
			}
			
			
			//==================================================================================
			//3CARTAO DE MEMBRO AZUL
			public  void gerarCartaodeObreiroIadmif(){
				try {
					
					String arquivo="/reports/cartaodeobreiro_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					String parametroCargo=(String)filtros.get("cargo");
					
					String parametroTipoCargo="OBREIRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				if(parametroCargo==null) {
					parametros.put("CARGO","%%");
				}else {
					parametros.put("CARGO","%"+parametroCargo+"%");
					
				}
				
				//parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
				e.printStackTrace();
			}
			}

			
			public  void gerarCarteirinhaDeObreiro(){
				try {
					
					String arquivo="/reports/cartaodeobreiro2_iadmif.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					String parametroCargo=(String)filtros.get("cargo");
					
					String parametroTipoCargo="OBREIRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				if(parametroCargo==null) {
					parametros.put("CARGO","%%");
				}else {
					parametros.put("CARGO","%"+parametroCargo+"%");
					
				}
				
				//parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
				e.printStackTrace();
			}
			}
			
			
			
			
			
			public void imprimirCartaoDeObreiro2(){
				try{
					Relatorios.gerarCartaodeObreiroIadmif2();
				}catch(RuntimeException e){
					e.printStackTrace();
				}
			}
			
			
			public void imprimirCartaoDeMembro2(){
				try{
					
					Relatorios.gerarCartaodeMembroIadmif2();
				}catch(RuntimeException e){
					e.printStackTrace();
				}
			}
			
			//novos relatorios atualizados
			// 85.60 � 53.98 mm (= 3.370 � 2.125 polegadas medida do cart�o
			public void gerarCarterinhaDeObreiro() {
				try {
					String arquivo = "/reports/membroigreja2.jasper";
					// pegando o componente DataTable da �rvore de componentes do
					// primefaces
					DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

					// pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					// pegando o filtro da coluna nome
					String nome = (String) filtros.get("nome");
					// String caminho = Faces.getRealPath("/reports/amarelo.jasper");
					String caminho = Faces.getRealPath(arquivo);
					Map<String, Object> parametros = new HashMap<String, Object>();

					if (nome == null) {
						parametros.put("NOME", "%%");
					} else {
						// parametros.put("NOME","%"+nome+"%");
						parametros.put("NOME", membro.getNome());
					}

					Connection conexao = HibernateUtil.getConexao();
					JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperPrintManager.printReport(relatorio, true);
					Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
				} catch (JRException e) {
					Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
				}
			}
			
			
			//novos relatorios atualizados
			// 85.60 � 53.98 mm (= 3.370 � 2.125 polegadas medida do cart�o
			public void gerarCarterinhaDeMembro() {
				try {
					String arquivo = "/reports/membroigreja2.jasper";
					// pegando o componente DataTable da �rvore de componentes do
					// primefaces
					DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

					// pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					// pegando o filtro da coluna nome
					String nome = (String) filtros.get("nome");
					// String caminho = Faces.getRealPath("/reports/amarelo.jasper");
					String caminho = Faces.getRealPath(arquivo);
					Map<String, Object> parametros = new HashMap<String, Object>();

					if (nome == null) {
						parametros.put("NOME", "%%");
					} else {
						// parametros.put("NOME","%"+nome+"%");
						parametros.put("NOME", membro.getNome());
					}

					Connection conexao = HibernateUtil.getConexao();
					JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperPrintManager.printReport(relatorio, true);
					Utils.mostrarMensagemJsfSucesso("carterinha com fundo azul gerada com sucesso");
				} catch (JRException e) {
					Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
				}
			}
			
			

			//relatorio de obreiro
	//=======================================================================================================
	//=======================================================================================================
	//===================================================================================================		
			//CART�O DE OBREIRO PARA IGREJA
			public  void gerarCartaodeMembro(){
				try {
					
					String arquivo = "/reports/cartaodemembro.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					String parametroCargo=(String)filtros.get("cargo");
					
					String parametroTipoCargo="MEMBRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				if(parametroCargo==null) {
					parametros.put("CARGO","%%");
				}else {
					parametros.put("CARGO","%"+parametroCargo+"%");
					
				}
				
				//parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
				e.printStackTrace();
			}
			}
			
			
			
			//CART�O DE OBREIRO PARA IGREJA
			public  void gerarCartaodeObreiro(){
				try {
					
					String arquivo = "/reports/cartaodeobreiro.jasper";
					
					//pegando o componente DataTable da �rvore de componentes do primefaces
					DataTable tabela=(DataTable)Faces.getViewRoot().findComponent("formListagem:tabela");
					
					//pegando o mapa de filtros do componente datatable
					Map<String, Object> filtros = tabela.getFilters();
					//pegando o filtro da coluna nome
					String parametroNome=(String)filtros.get("nome");
					String parametroCargo=(String)filtros.get("cargo");
					
					String parametroTipoCargo="OHREIRO";
					
				String caminho=Faces.getRealPath(arquivo);
				Map<String,Object>parametros=new HashMap<String,Object>();
				if(parametroNome==null) {
					parametros.put("NOME","%%");
				}else {
					parametros.put("NOME","%"+parametroNome+"%");
					
				}
				
				if(parametroCargo==null) {
					parametros.put("CARGO","%%");
				}else {
					parametros.put("CARGO","%"+parametroCargo+"%");
					
				}
				
				//parametros.put("CARGO",parametroCargo);
				parametros.put("TIPOCARGO",parametroTipoCargo);
				Connection conexao = HibernateUtil.getConexao();
				JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
				JasperPrintManager.printReport(relatorio, true);
				Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
			}catch(JRException e) {
				Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
				e.printStackTrace();
			}
			}
			
			
			

}
