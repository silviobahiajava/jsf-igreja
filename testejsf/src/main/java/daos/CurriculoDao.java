package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Curriculo;

public class CurriculoDao extends GenericDaoHibernate<Curriculo>{
	public Curriculo salvarCurriculo(Curriculo curriculo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Curriculo resposta = (Curriculo) sessao.merge(curriculo);
			transacao.commit();
			return resposta;
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
