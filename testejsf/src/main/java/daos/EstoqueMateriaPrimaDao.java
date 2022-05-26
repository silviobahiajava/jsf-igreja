package daos;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.EstoqueMateriaPrima;
import entidades.Lancamento;

public class EstoqueMateriaPrimaDao extends GenericDaoHibernate<EstoqueMateriaPrima>{
	@SuppressWarnings("unchecked")
	public EstoqueMateriaPrima buscarPorDescricao(String descricao) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
			Query query = sessao.createQuery("from EstoqueMateriaPrima where descricao=?");
			query.setParameter(0,descricao);
			
			EstoqueMateriaPrima estoque=(EstoqueMateriaPrima) query.uniqueResult();
			return estoque;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
