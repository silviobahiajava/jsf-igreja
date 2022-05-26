package daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Campanha;
import entidades.Contribuicao;

public class CampanhaDao extends GenericDaoHibernate<Campanha> {

	/*@SuppressWarnings("unchecked")
	public List<Campanha> buscarCamoanhaPorCodigo(Long codigo) {
		List<Campanha> campanhas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
//			Query consulta = sessao.createQuery("from Contribuicao where grupo.codigo=? ");
//			consulta.setParameter(0, codigo);
//			Contribuicaos = consulta.list();
			campanhas = sessao.createQuery( "from Campanha where grupo.codigo = :codigo").setLong("codigo",codigo).list();
			       
			      
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return campanhas;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Campanha> buscarCampanhaPorData(Date dataCampanha) {
		List<Campanha>   campanhas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Query consulta = sessao.createQuery("from Campanha where dataCampanha=? ");
			consulta.setParameter(0, dataCampanha);
			campanhas=consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return campanhas;
	}
	
	@SuppressWarnings("unchecked")
	public List<Campanha> buscarCampanhaPorCodigo(Long  codigo) {
		List<Campanha>   campanhas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Query consulta = sessao.createQuery("from Campanha where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			campanhas=consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return campanhas;
	}

}
