package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.ContasPagas;
import entidades.ContasPagas;

public class ContasPagasDao extends GenericDaoHibernate<ContasPagas>{
	public ContasPagas salvarContasPagas(ContasPagas contasPagas) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			ContasPagas resposta = (ContasPagas) sessao.merge(contasPagas);
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
