package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.Lancamento;
import entidades.Oferta;
import entidades.Usuario;
import javax.persistence.*;

import java.lang.reflect.*;

public class GenericDaoHibernate<Entidade> {

	private Class<Entidade> classe;
	private EntityManagerFactory emf;
	private EntityManager em=emf.createEntityManager();

	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/*@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		//Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = em.createQuery(String sql);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			emf.close();
			em.close();
		}
	}


*/

	
	/*List<Person> persons = session.createQuery(
			"from Person", Person.class)
		.getResultList();
	*/
	public List<Entidade>buscartodos(){
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		@SuppressWarnings("unchecked")
		List<Entidade>resultado=(List<Entidade>) sessao.createQuery("from Usuario", classe);
		return resultado;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Entidade> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Oferta.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

*/

	public void excluir(Entidade entidade) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void merge(Entidade entidade) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.merge(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade merge2(Entidade entidade) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Entidade retorno = (Entidade) sessao.merge(entidade);
			transacao.commit();
			return retorno;
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
