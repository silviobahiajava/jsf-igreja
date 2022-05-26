package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import daos.PagamentoClienteDao;
import daos.PagamentoDao;
//import daos.ParcelaDao;
import entidades.Parcela;
import entidades.ParcelaCliente;
import utils.Utils;

@ManagedBean(name="vencimentoBean")
@ViewScoped
public class VencimentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//private ParcelaDao parcelaDao=new ParcelaDao();
	private PagamentoDao pagamentoDao=new PagamentoDao();
	private List<Parcela>parcelas=new ArrayList<Parcela>();
	private PagamentoClienteDao pagamentoClienteDao=new PagamentoClienteDao();
	private List<ParcelaCliente>parcelasdeclientes=new ArrayList<ParcelaCliente>();
	private Parcela parcela=new Parcela();
	private ParcelaCliente parcelaDeCliente=new ParcelaCliente();
	@PostConstruct
	public void init(){
		try{
			parcelas=pagamentoDao.buscarParcelasComVencimentoPraHoje(new Date());
			parcelasdeclientes=pagamentoClienteDao.buscarParcelasComVencimentoPraHoje(new Date());
			
		}catch(RuntimeException e){
			e.printStackTrace();
		}
	}
	
	public void enviarEmailDeAvisoParaCliente(ActionEvent e){
		parcelaDeCliente=(ParcelaCliente) e.getComponent().getAttributes().get("parceladeclienteSelecionada");
		String mensagemDeAviso="prezado cliente "+parcelaDeCliente.getPagamento().getCliente().getNomeCliente()
				+  "sua parcela  nº "+parcelaDeCliente.getNumerodaparcela()+" vence hoje "+new Date();
		
		Utils.mostrarMensagemJsfSucesso(mensagemDeAviso);
				
	}
	
	public void enviaEmailSimples(String servidorSmtp,String emaildestinatario,String emailRemitente,String assuntoEmail,
			String conteudoEmail
			) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(servidorSmtp); // o servidor SMTP para envio do e-mail
		email.addTo(emaildestinatario); //destinatário
		email.setFrom(emailRemitente); // remetente
		email.setSubject(assuntoEmail); // assunto do e-mail
		email.setMsg(conteudoEmail); //conteudo do e-mail
		
		email.setSmtpPort(465);
		email.setSSL(true);
		email.setTLS(true);
		email.send();		
	}
	
	public void enviarEmailDeAviso(ActionEvent e){
		parcela=(Parcela) e.getComponent().getAttributes().get("parcelaSelecionada");
		String mensagemDeAviso="prezado cliente "+parcela.getPagamento().getCliente().getNomeCliente()
				+  "sua parcela  nº "+parcela.getNumerodaparcela()+" vence hoje "+new Date();
		String emaildestinatario=parcela.getPagamento().getEmailCliente();
		String emailRemitente="souzawebti@gmail.com";
		String servidorSmtp="smtp.gmail.com";
		String assuntoEmail="aviso de cobrança";
				
		
		try {
			enviaEmailSimples(servidorSmtp, emaildestinatario, emailRemitente, assuntoEmail,mensagemDeAviso);
			Utils.mostrarMensagemJsfSucesso("email de aviso de cobrança enviado com sucesso");
		} catch (EmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Utils.mostrarMensagemJsfSucesso(mensagemDeAviso);
				
	}
	

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public List<ParcelaCliente> getParcelasdeclientes() {
		return parcelasdeclientes;
	}

	public void setParcelasdeclientes(List<ParcelaCliente> parcelasdeclientes) {
		this.parcelasdeclientes = parcelasdeclientes;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public ParcelaCliente getParcelaDeCliente() {
		return parcelaDeCliente;
	}

	public void setParcelaDeCliente(ParcelaCliente parcelaDeCliente) {
		this.parcelaDeCliente = parcelaDeCliente;
	}
	
	
}
