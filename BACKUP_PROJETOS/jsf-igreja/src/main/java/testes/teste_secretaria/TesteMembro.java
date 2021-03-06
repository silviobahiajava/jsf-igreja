package testes.teste_secretaria;

import javax.swing.JOptionPane;

import br.com.jsf_igreja.secretaria.model.*;
import br.com.jsf_igreja.secretaria.repository.ContatoRepository;
import br.com.jsf_igreja.secretaria.repository.DocumentacaoRepository;
import br.com.jsf_igreja.secretaria.repository.EnderecoRepository;
import br.com.jsf_igreja.secretaria.repository.FiliacaoRepository;
import br.com.jsf_igreja.secretaria.repository.MembroRepository;
import enums.CargoEnum;
import enums.Congregacao;
import enums.EstadoCivilEnum;
import enums.EstadosBrasileirosEnum;
import jakarta.persistence.EntityManager;
import testes.teste_igreja.GeradorDeMensagens;
import utils.ConexaoBanco;


public class TesteMembro {

	   //fazendo cadastros one to one
	   public static void cadastrarMembro() {
		  
		   try {
		  EntityManager em=ConexaoBanco.conectar();
		  em.getTransaction().begin();
		  //cadastrando o endereco
		   Endereco endereco=new Endereco();
		   endereco.setEndreco("Rua Padre Angelo");
		   endereco.setNumero("75");
		   endereco.setBairro("Sao Fernando");
		   endereco.setCidade("Rolandia");
		   endereco.setEstadoEnum(EstadosBrasileirosEnum.PR);
		  // EnderecoRepository.salvar(endereco);
		   em.persist(endereco);
		   
		   //cadastrando os nomes dos pais
		   Filiacao filiacao=new Filiacao();
		   filiacao.setNomeMae("Maria Izabel da Silva");
		   filiacao.setNomePai("Jacinto Moreira");
		  //  FiliacaoRepository.salvar(filiacao);
		   em.persist(filiacao);
		   
		   //cadastrando a documentação
		    Documentacao documentacao=new Documentacao();
		    documentacao.setCpf("21682485");
		    documentacao.setRg("3856");
		  //  DocumentacaoRepository.salvar(documentacao);
		    em.persist(documentacao);
		    
		    //cadastrando o contato
		    Contato contato=new Contato();
		    contato.setCelular("43 9985779985");
		    contato.setEmail("izabela@gmail.com");
		    contato.setFacebook("izabel@hotmail.com");
		    contato.setSkype("7889bel74");
		    em.persist(contato);
		   // ContatoRepository.salvar(contato);
		    
		    //cadastrando o membro
		   Membro membro=new Membro();
		   membro.setNome("Silvio Silva de Souza");
		   membro.setNacionalidade("BRASILEIRA");
		   membro.setNatualidade("Itabuna");
		   membro.setEstadosBrasileirosEnum(EstadosBrasileirosEnum.BA);
		   membro.setCargoEnum(CargoEnum.DIACONIZA);
		   membro.setCongregacaoEnum(Congregacao.MONTECARLOS);
		   
		   membro.setEndereco(endereco);
		  membro.setFiliacao(filiacao);
		  membro.setDocumentacao(documentacao);
		  membro.setContato(contato);
		  em.persist(membro);
		  em.getTransaction().commit();
		  GeradorDeMensagens.mostrarMensagemSucesso("cadastro feito com sucesso");
		   }
		   catch(Exception e) {
			   GeradorDeMensagens.mostrarMensagemErro("erro ao cadastrar o membro ");
			   e.printStackTrace();
		   }
 	   }

}
