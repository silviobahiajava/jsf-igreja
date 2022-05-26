
package beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import daos.CargoDao;
import daos.MembroDao;
import entidades.Cargo;
import entidades.Endereco;
import entidades.Filiacao;
import entidades.Membro;
import enums.TipoCadastroEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperRunManager;
import utils.HibernateUtil;
import utils.Utils;
import utils.WebServiceCep;

@ManagedBean(name = "membroBean")
@ViewScoped
public class MembroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Membro membro = new Membro();
	private String cargoDescricao;
	// private String
	// urlFoto="C:souzawebti_igreja2/modeloigreja2/src/main/webapp/resources/fotos/membros/";

	private String urlFoto2 = "C:/fotos/igrejas/missaoefe/";
	// <Context docBase="C:/fotos/igrejas/missaoefe/" path="/uploads"/>
	// C:\fotos\igrejas\missaoefe
	// private String urlFoto2="./fotosmembros/";

	// private String urlFoto2="C:/fotosdaigreja/";

	// C:/SPIASSEMBLEIATESTES/tgreja1/src/main/webapp/resources/fotos/membros/branco.jpg
	// private String
	// urlFoto3="C:/Users/souzawebti/Desktop/SPI/fotodosmembros/";
	private String cep;
	private String numero;
	private List<Membro> membros;
	private Cargo cargo = new Cargo();
	private List<Cargo> cargos;
	private Endereco endereco = new Endereco();
	private Filiacao filiacao = new Filiacao();
	private boolean sabeocep;
	private boolean cadastroCompleto;
	private boolean cadastroIncompleto;
	private MembroDao membroDao = new MembroDao();
	private String tipoCadastro;
	CargoDao cargoDao = new CargoDao();

	@PostConstruct
	public void listar() {
		try {
			// membros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","MEMBRO");
			  //membros = membroDao.buscarPorStatusMembroTipo("ATIVO");
			// membros=membroDao.buscarPorTipoDeCargo("MEMBRO");
		      membros=membroDao.listar();
			

		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	public void carregarMinistros() {
		try {
			membros = membroDao.buscarPorStatusCadastroTipoCargo(TipoCadastroEnum.ATIVO, "OBREIRO");
		} catch (RuntimeException erro) {
			Utils.mostrarMensagemJsfErro("erro ao listar");
			erro.printStackTrace();
		}
	}

	public void verDetalhes(ActionEvent evento) {
		membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
		membro.setCaminho(urlFoto2 + membro.getCodigo() + ".jpg");
		membro.setNome(membro.getNome().toUpperCase());
		membro.setRg(membro.getRg());
		membro.setCpf(membro.getCpf());

		membro.setNomeMae(membro.getNomeMae());
		membro.setNomePai(membro.getNomePai());

		membro.setDataNascimento(membro.getDataNascimento());
		membro.setNacionalidade("BRASILEIRA");
		membro.setNatualidade(membro.getNatualidade());
		membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
		membro.setCargoEnum(membro.getCargoEnum());
		cargo.setDescricao(cargo.getDescricao());
		// membro.setCargo(cargo);
		membro.setCongregacao(membro.getCongregacao());
	}

	public void buscarAniversariantesDoMes() {
		try {
			// Membro membro=new Membro();
			// aniversariantes=dao.buscarPorDiaMes(membro.getDataNascimento());

			Date dataAtual = new Date();
			GregorianCalendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);
			int mesAtual = calendario.get(Calendar.MONTH);
			Utils.mostrarMensagemJsfSucesso("mes atual " + mesAtual);
			membros = membroDao.listarAniversariantesDoMes(mesAtual);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao listar " + e.getMessage());
		}
	}

	public void novo() {

		membro = new Membro();
		membro.setCaminho(urlFoto2 + "branco.jpg");
		endereco = new Endereco();
		//cargos = cargoDao.listar();
	}

	public void pegarEndereco() {
		Utils.mostrarMensagemJsfSucesso("pegando endereço");
		WebServiceCep wc = WebServiceCep.searchCep(endereco.getCep());
		endereco.setCep(endereco.getCep());
		endereco.setEndereco(wc.getLogradouro().toUpperCase());
		endereco.setNumero(endereco.getNumero());
		endereco.setBairro(wc.getBairro().toUpperCase());
		endereco.setCidade(wc.getCidade().toUpperCase());
		endereco.setEstado(wc.getUf().toUpperCase());
	}

	public String pegarTipoCadastro(){
		if(membro.getTipoCadastro().getDescricao()=="ATIVO"){
			Utils.mostrarMensagemJsfSucesso("cadastro ATIVO");
			tipoCadastro="COMPLETO";
			
		}if(membro.getTipoCadastro().getDescricao()=="INATIVO"){
			Utils.mostrarMensagemJsfAlerta("cadastro INATIVO");
			tipoCadastro="INCOMPLETO";
		}
		return tipoCadastro;
	}
	public void salvar() {

		try {
			if (membro.getCaminho() == null) {
				Utils.mostrarMensagemJsfErro("o campo foto  é obrigatório");
				return;
			}
			MembroDao membroDao = new MembroDao();
			if (!membro.getNome().equals(null)) {
				membro.setNome(membro.getNome().toUpperCase());

				/*
				 * String nomeCompleto = membro.getNome(); String[]
				 * nomeFracionado = nomeCompleto.split(" ");
				 */

				/*
				 * if (nomeFracionado.length == 3) { String primeiroNome =
				 * nomeFracionado[0]; char segundoNome =
				 * nomeFracionado[1].charAt(1); String terceiroNome =
				 * nomeFracionado[2]; String nomeBanco = primeiroNome + " " +
				 * segundoNome + "." + " " + terceiroNome;
				 * membro.setNome(nomeBanco); }
				 */

				/*
				 * if (nomeFracionado.length == 4) { String primeiroNome =
				 * nomeFracionado[0]; char segundoNome =
				 * nomeFracionado[1].charAt(1); String terceiroNome =
				 * nomeFracionado[2]; String quartoNome = nomeFracionado[3];
				 * String nomeBanco = primeiroNome + " " + segundoNome + "." +
				 * " " + terceiroNome + "." + quartoNome;
				 * membro.setNome(nomeBanco); }
				 * 
				 * if (nomeFracionado.length == 5) { String primeiroNome =
				 * nomeFracionado[0]; char segundoNome =
				 * nomeFracionado[1].charAt(1); char terceiroNome =
				 * nomeFracionado[2].charAt(1); char quartoNome =
				 * nomeFracionado[3].charAt(1); String quintoNome =
				 * nomeFracionado[4]; String nomeBanco = primeiroNome + " " +
				 * segundoNome + "." + " '''''''''''" + terceiroNome + "." +
				 * quartoNome + "." + quintoNome; membro.setNome(nomeBanco); }
				 * /* boolean cadastroCompleto = this.cadastroCompleto ? true :
				 * false; if (cadastroCompleto) { membro.setStatusCadastro(1); }
				 * boolean cadastroIncompleto = this.cadastroCompleto ? true :
				 * false; if (cadastroIncompleto) { membro.setStatusCadastro(1);
				 * }
				 */

				membro.setNomeMae(membro.getNomeMae().toUpperCase());
				membro.setNomePai(membro.getNomePai().toUpperCase());
				membro.setEndereco(endereco);

				membro.setDataNascimento(membro.getDataNascimento());
				membro.setNacionalidade("BRASILEIRA");
				membro.setNatualidade(membro.getNatualidade().toUpperCase());
				membro.setEstadosBrasileirosEnum(membro.getEstadosBrasileirosEnum());
				membro.setCongregacao(membro.getCongregacao());

				
				if (membro.getCargoEnum().getDescricao()=="MEMBRO") {
					membro.setTipoCargo("MEMBRO");
					membro.setCargoEnum(membro.getCargoEnum());
				}
				if(membro.getCargoEnum().getDescricao()!="MEMBRO"){
					membro.setTipoCargo("MINISTRO");
					membro.setCargoEnum(membro.getCargoEnum());
				}
			//	membro.setCargo(cargo);

				/*
				 * if (membro.getNome() != "" && membro.getNomedamae() != "" &&
				 * membro.getNomedopai() != "null" && membro.getDataNascimento()
				 * != null && membro.getNacionalidade() != "" &&
				 * membro.getNatualidade() != "" && membro.getCargo() != null) {
				 * membro.setTipoCadastro(TipoCadastroEnum.ATIVO);
				 * cadastroCompleto = true; cadastroIncompleto = false;
				 * 
				 * } else { membro.setTipoCadastro(TipoCadastroEnum.INATIVO);
				 * cadastroIncompleto = true; cadastroCompleto = false; }
				 */
				tipoCadastro=this.pegarTipoCadastro();
				
				if (tipoCadastro=="COMPLETO") {
					cadastroCompleto=true;
					//cadastroIncompleto=false;
					membro.setStatusCadastro(1);
					membro.setTipoCadastro(TipoCadastroEnum.ATIVO);
					membro.setStatusMembro("ATIVO");
					membro.setStatus("ATIVO");
					
				}
				if (tipoCadastro=="INCOMPLETO") {
					cadastroIncompleto=true;
					//cadastroCompleto=false;
					membro.setStatusCadastro(0);
					membro.setTipoCadastro(TipoCadastroEnum.INATIVO);
					membro.setStatusMembro("INATIVO");
				    membro.setStatus("INATIVO");
				}

				// membroDao.merge(membro);
				Membro membroCadastrado = membroDao.merge2(membro);
				// pegando o caminho do arquivo temporï¿½rio
				Path origem = Paths.get(membro.getCaminho());
				// Path
				// destino=Paths.get("C:/fotosdaigreja/"+membroCadastrado.getCodigo()+".jpg");
				Path destino = Paths.get(urlFoto2 + membroCadastrado.getCodigo() + ".jpg");

				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				membro=new Membro();
				
				Utils.mostrarMensagemJsfSucesso("cadastro realizado com sucesso MEMBRO "+membroCadastrado.getStatus());
				//membros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","MEMBRO");
				//membros=membroDao.buscarPorStatusMembroTipo("ATIVO");
				membros=membroDao.listar();
				//membros=membroDao.buscarPorTipoDeCargo("MEMBRO");
			}

		} catch (RuntimeException erro) {

			Utils.mostrarMensagemJsfErro("ocorreu um erro ao cadastrar");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");

			MembroDao membroDao = new MembroDao();
			membro.setStatusMembro("INATIVO");
			Path path = Paths.get(urlFoto2 + membro.getCodigo() + ".jpg");
			Files.deleteIfExists(path);
			Utils.mostrarMensagemJsfSucesso(membro.getNome() + " não faz mais parte do ROL de MEMBROS");
			//membros=membroDao.buscarPorStatusMembroTipoCargo("ATIVO","MEMBRO");
			membros=membroDao.buscarPorStatusMembroTipo("ATIVO");
			//membros=membroDao.buscarPorTipoDeCargo("MEMBRO");

		} catch (RuntimeException erro) {

			Utils.mostrarMensagemJsfErro("nï¿½o foi possï¿½vel excluir");
			erro.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void editar(ActionEvent evento) {
		membro = (Membro) evento.getComponent().getAttributes().get("membroSelecionado");
		membro.setCaminho(urlFoto2+membro.getCodigo()+".jpg");
	//	cargos = cargoDao.listar();
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

	// colocar essa linha no server.xml do tomcat
	// <Context docBase="C:/fotosdaigreja/" path="/uploads"/>
	public void upload(FileUploadEvent evento) {
		String nomeDoArquivo = evento.getFile().getFileName();
		String tipoDeArquivo = evento.getFile().getContentType();
		Long tamanhoDoArquivo = evento.getFile().getSize();
		Utils.mostrarMensagemJsfSucesso("chamou o metodo\nnome :" + nomeDoArquivo + "\ntipo :" + tipoDeArquivo
				+ "\ntamanho :" + tamanhoDoArquivo);

		// pegando o arquivo de upload escolhido pelo usuï¿½rio
		UploadedFile arquivoUpload = evento.getFile();// arquivo original

		try {
			// armazenando este arquivo no arquivo temporï¿½rio
			Path arquivoTemporario = Files.createTempFile(null, null);// arquivo
																		// destino

			// copiando o arquivo os stream do arquivo original no arquivo
			// destino
			Files.copy(arquivoUpload.getInputstream(), arquivoTemporario, StandardCopyOption.REPLACE_EXISTING);
			membro.setCaminho(arquivoTemporario.toString());
			Utils.mostrarMensagemJsfSucesso("upload realizado com sucesso");

		} catch (IOException e) {
			e.printStackTrace();
			Utils.mostrarMensagemJsfErro("erro ao fazer o upload");
		}
	}

	public Filiacao getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(Filiacao filiacao) {
		this.filiacao = filiacao;
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

	// 85.60 ï¿½ 53.98 mm (= 3.370 ï¿½ 2.125 polegadas medida do cartï¿½o
	public void gerarCarterinhaDeMembro() {
		try {
			String arquivo = "/reports/membroigreja2.jasper";
			// pegando o componente DataTable da ï¿½rvore de componentes do
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

	public void gerarCarterinhaFundoAzul1() {
		try {
			// pegando o componente DataTable da ï¿½rvore de componentes do
			// primefaces
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			// pegando o filtro da coluna nome
			String nome = (String) filtros.get("nome");
			String caminho = Faces.getRealPath("/reports/cartaodemembrofundoazul1.jasper");
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

	
	public void gerarCarterinhaFundoBranco() {
		try {
			String arquivo = "/reports/brancopreto.jasper";
			// pegando o componente DataTable da ï¿½rvore de componentes do
			// primefaces
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			// pegando o filtro da coluna nome
			String nome = (String) filtros.get("nome");
			// String caminho =
			// Faces.getRealPath("/reports/fundobranco.jasper");
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
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo branco gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}

	public void gerarCarterinhaFundoVermelho() {
		try {
			// pegando o componente DataTable da ï¿½rvore de componentes do
			// primefaces
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			// pegando o filtro da coluna nome
			String nome = (String) filtros.get("nome");
			String caminho = Faces.getRealPath("/reports/fundovermelho.jasper");
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
			Utils.mostrarMensagemJsfSucesso("carterinha com fundo vermelho gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha");
		}
	}

	public boolean isSabeocep() {
		return sabeocep;
	}

	public void setSabeocep(boolean sabeocep) {
		this.sabeocep = sabeocep;
	}

	public String getUrlFoto2() {
		return urlFoto2;
	}

	public void setUrlFoto2(String urlFoto2) {
		this.urlFoto2 = urlFoto2;
	}

	public boolean isCadastroCompleto() {
		return cadastroCompleto;
	}

	public void setCadastroCompleto(boolean cadastroCompleto) {
		this.cadastroCompleto = cadastroCompleto;
	}

	public boolean isCadastroIncompleto() {
		return cadastroIncompleto;
	}

	public void setCadastroIncompleto(boolean cadastroIncompleto) {
		this.cadastroIncompleto = cadastroIncompleto;
	}

	// av higienÃ³polis 32 sala 204 falar com CÃ©lio

	// CARTÃO DE OBREIRO PARA IGREJA
	public void gerarCartaodeMembro() {
		try {

			String arquivo = "/reports/cartaodemembro2.jasper";

			// pegando o componente DataTable da ï¿½rvore de componentes do
			// primefaces
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			// pegando o filtro da coluna nome
			String parametroNome = (String) filtros.get("nome");
			String parametroCargo = (String) filtros.get("cargo");

			String parametroTipoCargo = "MEMBRO";

			String caminho = Faces.getRealPath(arquivo);
			Map<String, Object> parametros = new HashMap<String, Object>();
			if (parametroNome == null) {
				parametros.put("NOME", "%%");
			} else {
				parametros.put("NOME", "%" + parametroNome + "%");

			}

			if (parametroCargo == null) {
				parametros.put("CARGO", "%%");
			} else {
				parametros.put("CARGO", "%" + parametroCargo + "%");

			}

			// parametros.put("CARGO",parametroCargo);
			parametros.put("TIPOCARGO", parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
			e.printStackTrace();
		}
	}

	// CARTÃO DE OBREIRO PARA IGREJA
	public void gerarCartaodeObreiro() {
		try {

			String arquivo = "/reports/cartaodeobreiro2.jasper";

			// pegando o componente DataTable da ï¿½rvore de componentes do
			// primefaces
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

			// pegando o mapa de filtros do componente datatable
			Map<String, Object> filtros = tabela.getFilters();
			// pegando o filtro da coluna nome
			String parametroNome = (String) filtros.get("nome");
			String parametroCargo = (String) filtros.get("cargo");

			String parametroTipoCargo = "MINISTRO";

			String caminho = Faces.getRealPath(arquivo);
			Map<String, Object> parametros = new HashMap<String, Object>();
			if (parametroNome == null) {
				parametros.put("NOME", "%%");
			} else {
				parametros.put("NOME", "%" + parametroNome + "%");

			}

			if (parametroCargo == null) {
				parametros.put("CARGO", "%%");
			} else {
				parametros.put("CARGO", "%" + parametroCargo + "%");

			}

			// parametros.put("CARGO",parametroCargo);
			parametros.put("TIPOCARGO", parametroTipoCargo);
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio, true);
			Utils.mostrarMensagemJsfSucesso("carterinha de OBREIRO gerada com sucesso");
		} catch (JRException e) {
			Utils.mostrarMensagemJsfSucesso("erro ao gerar carterinha de OBREIRO");
			e.printStackTrace();
		}
	}

	// FUNCIONA PERFEITAMENTE
	public void mostrarCartaoDeMembrosNaWeb() {
		String arquivo = "/reports/cartaodemembro2.jasper";
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);

		response.setContentType("application/pdf");

		response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
		DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
		Map<String, Object> filtros = tabela.getFilters();
		String parametroNome = (String) filtros.get("nome");

		String parametroTipoCargo = "MEMBRO";
		String parametroCargo = "MEMBRO";
		// String caminho=Faces.getRealPath(arquivo);
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (parametroNome == null) {
			parametros.put("NOME", "%%");
		} else {
			parametros.put("NOME", "%" + parametroNome + "%");

		}

		parametros.put("CARGO", parametroCargo);
		parametros.put("TIPOCARGO", parametroTipoCargo);
		Connection conexao = HibernateUtil.getConexao();

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();

			// JRBeanCollectionDataSource datasource = new
			// JRBeanCollectionDataSource(arrayList);

			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conexao);

			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			facesContext.responseComplete();
		}
	}

	public void mostrarCartaoDeObreirosNaWeb() {
		String arquivo = "/reports/cartaodeobreiro2.jasper";
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(arquivo);

		response.setContentType("application/pdf");

		response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
		DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
		Map<String, Object> filtros = tabela.getFilters();
		String parametroNome = (String) filtros.get("nome");
		String parametroCargo = (String) filtros.get("cargo");

		String parametroTipoCargo = "MINISTRO";

		// String caminho=Faces.getRealPath(arquivo);
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (parametroNome == null) {
			parametros.put("NOME", "%%");
		} else {
			parametros.put("NOME", "%" + parametroNome + "%");

		}

		if (parametroCargo == null) {
			parametros.put("CARGO", "%%");
		} else {
			parametros.put("CARGO", "%" + parametroCargo + "%");

		}

		parametros.put("TIPOCARGO", parametroTipoCargo);
		Connection conexao = HibernateUtil.getConexao();

		try {
			ServletOutputStream servletOutputStream = response.getOutputStream();

			// JRBeanCollectionDataSource datasource = new
			// JRBeanCollectionDataSource(arrayList);

			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametros, conexao);

			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			facesContext.responseComplete();
		}

	}

	public String getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(String tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	
	
}
