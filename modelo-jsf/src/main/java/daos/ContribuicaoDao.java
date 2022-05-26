package daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Contribuicao;
import entidades.Oferta;
import entidades.Vendas;

public class ContribuicaoDao extends GenericDaoHibernate<Contribuicao>{
	@SuppressWarnings("unchecked")
	public List<Contribuicao> buscarContribuicaoPorCodigo(Long codigo) {
		List<Contribuicao> contribuicaos = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
//			Query consulta = sessao.createQuery("from Contribuicao where grupo.codigo=? ");
//			consulta.setParameter(0, codigo);
//			Contribuicaos = consulta.list();
			contribuicaos = sessao.createQuery( "from Contribuicao where grupo.codigo = :codigo").setLong("codigo",codigo).list();
			       
			      
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contribuicaos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contribuicao> buscarContribuicaoPorData(Date dataContribuicao) {
		List<Contribuicao>   contribuicoes = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Query consulta = sessao.createQuery("from Contribuicao where dataContribuicao=? ");
			consulta.setParameter(0, dataContribuicao);
			contribuicoes=consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contribuicoes;
	}


}
