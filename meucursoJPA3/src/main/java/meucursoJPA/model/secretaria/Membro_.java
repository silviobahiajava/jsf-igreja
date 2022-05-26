package meucursoJPA.model.secretaria;

import enums.BairroCidadeEnum;
import enums.CargoEnum;
import enums.CidadesNorteParanaEnum;
import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import enums.MotivoExclusaodeMembrosEnum;
import enums.MotivoInatividadeEnum;
import enums.TipoCadastroEnum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.535-0300")
@StaticMetamodel(Membro.class)
public class Membro_ extends GenericDomain_ {
	public static volatile SingularAttribute<Membro, String> nome;
	public static volatile SingularAttribute<Membro, String> nacionalidade;
	public static volatile SingularAttribute<Membro, String> natualidade;
	public static volatile SingularAttribute<Membro, String> localDeBatismo;
	public static volatile SingularAttribute<Membro, String> estadoBatismo;
	public static volatile SingularAttribute<Membro, EstadosBrasileirosEnum> estadosBrasileirosEnum;
	public static volatile SingularAttribute<Membro, String> status;
	public static volatile SingularAttribute<Membro, MotivoExclusaodeMembrosEnum> motivoExclusaoDeMembrosEnum;
	public static volatile SingularAttribute<Membro, String> justificativaDeExclusao;
	public static volatile SingularAttribute<Membro, Long> codigoMotivoInatividade;
	public static volatile SingularAttribute<Membro, MotivoInatividadeEnum> motivoSaida;
	public static volatile SingularAttribute<Membro, CidadesNorteParanaEnum> cidadeEnum;
	public static volatile SingularAttribute<Membro, Date> validadeCarterinha;
	public static volatile SingularAttribute<Membro, Date> dataNascimento;
	public static volatile SingularAttribute<Membro, BairroCidadeEnum> bairroEnum;
	public static volatile SingularAttribute<Membro, String> rg;
	public static volatile SingularAttribute<Membro, String> statusMembro;
	public static volatile SingularAttribute<Membro, String> cpf;
	public static volatile SingularAttribute<Membro, byte[]> foto;
	public static volatile SingularAttribute<Membro, String> tipoCargo;
	public static volatile SingularAttribute<Membro, String> nomedamae;
	public static volatile SingularAttribute<Membro, String> nomedopai;
	public static volatile SingularAttribute<Membro, Integer> statusCadastro;
	public static volatile SingularAttribute<Membro, TipoCadastroEnum> tipoCadastro;
	public static volatile SingularAttribute<Membro, Endereco> endereco;
	public static volatile SingularAttribute<Membro, Date> dataBatismo;
	public static volatile SingularAttribute<Membro, String> cidadeBatismo;
	public static volatile SingularAttribute<Membro, String> localBatismo;
	public static volatile SingularAttribute<Membro, String> nomeMae;
	public static volatile SingularAttribute<Membro, String> nomePai;
	public static volatile SingularAttribute<Membro, Contato> contato;
	public static volatile SingularAttribute<Membro, Filiacao> filiacao;
	public static volatile SingularAttribute<Membro, EstadoCivil> estadoCivil;
	public static volatile SingularAttribute<Membro, CargoEnum> cargoEnum;
	public static volatile SingularAttribute<Membro, Congregacao> congregacao;
}
