package daos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import entidades.CompraMateriaPrima;
import entidades.MateriaPrima;

public class MateriaPrimaDao extends GenericDaoHibernate<MateriaPrima>{
	
	public Long buscaUlitmoCodigo() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Criteria c=sessao.createCriteria(MateriaPrima.class);
			Long codigo=(Long) c.setProjection(Projections.max("codigo")).uniqueResult();
			return codigo;
			
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
}
