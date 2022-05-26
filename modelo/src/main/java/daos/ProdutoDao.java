package daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateUtil;
import entidades.Produto;

public class ProdutoDao extends GenericDaoHibernate<Produto> {
	public Produto salvarproduto(Produto prod) throws Exception {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			sessao.save(prod);
			return prod;
		} catch (Exception e) {

			throw e;
		}

	}
}
