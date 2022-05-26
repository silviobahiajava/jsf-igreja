package meucursoJPA.model.secretaria;

import enums.Congregacao;
import enums.EstadoCivil;
import enums.EstadosBrasileirosEnum;
import enums.MotivoInatividadeEnum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import meucursoJPA.model.GenericDomain_;

@Generated(value="Dali", date="2022-05-19T18:35:23.537-0300")
@StaticMetamodel(MembroInativo.class)
public class MembroInativo_ extends GenericDomain_ {
	public static volatile SingularAttribute<MembroInativo, String> nome;
	public static volatile SingularAttribute<MembroInativo, String> nacionalidade;
	public static volatile SingularAttribute<MembroInativo, String> natualidade;
	public static volatile SingularAttribute<MembroInativo, EstadosBrasileirosEnum> estadosBrasileirosEnum;
	public static volatile SingularAttribute<MembroInativo, Long> codigoMotivoInatividade;
	public static volatile SingularAttribute<MembroInativo, MotivoInatividadeEnum> motivoSaida;
	public static volatile SingularAttribute<MembroInativo, Date> dataNascimento;
	public static volatile SingularAttribute<MembroInativo, String> rg;
	public static volatile SingularAttribute<MembroInativo, Membro> membro;
	public static volatile SingularAttribute<MembroInativo, String> cpf;
	public static volatile SingularAttribute<MembroInativo, byte[]> foto;
	public static volatile SingularAttribute<MembroInativo, String> tipoCargo;
	public static volatile SingularAttribute<MembroInativo, String> nomedamae;
	public static volatile SingularAttribute<MembroInativo, String> nomedopai;
	public static volatile SingularAttribute<MembroInativo, Endereco> endereco;
	public static volatile SingularAttribute<MembroInativo, Date> dataBatismo;
	public static volatile SingularAttribute<MembroInativo, Contato> contato;
	public static volatile SingularAttribute<MembroInativo, Filiacao> filiacao;
	public static volatile SingularAttribute<MembroInativo, EstadoCivil> estadoCivil;
	public static volatile SingularAttribute<MembroInativo, Congregacao> congregacao;
}
