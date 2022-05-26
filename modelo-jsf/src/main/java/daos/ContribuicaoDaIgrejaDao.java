package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.ContribuicaoDaIgreja;
import utils.HibernateUtil;

public class ContribuicaoDaIgrejaDao extends GenericDaoHibernate<ContribuicaoDaIgreja>{
	public void salvarContribuicao(ContribuicaoDaIgreja contribuicao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(contribuicao);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	public BigDecimal buscarValorDaContribuicaoPorData(Date dataPrevistaPraContribuir) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorQuePodeContribuir from ContribuicaoDaIgreja c where c.dataOfertaIgreja=:dataOfertaIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPrevistaPraContribuir", dataPrevistaPraContribuir);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaContribuicaoDataUnitario(Date dataPrevistaPraContribuir) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorQuePodeContribuir from ContribuicaoDaIgreja where c.dataPrevistaPraContribuir=:dataPrevistaPraContribuir");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPrevistaPraContribuir", dataPrevistaPraContribuir);
			// BigDecimal somaReceita=(BigDecimal) query.uniqueResult();
			// BigDecimal somaReceita=(BigDecimal) query.list();
			@SuppressWarnings("unchecked")
			List<BigDecimal> lista = query.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContribuicaoDaIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ContribuicaoDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<ContribuicaoDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ContribuicaoDaIgreja> listarContribuicaoPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ContribuicaoDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<ContribuicaoDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	// saldo por ggupo

	/*@SuppressWarnings("unchecked")
	public List<Lancamento> buscarSaldoPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Lancamento.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Lancamento> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}*/

	@SuppressWarnings("unchecked")
	public List<ContribuicaoDaIgreja> buscarContribuicaoPorData(Date dataPrevistaPraContribuir) {
		List<ContribuicaoDaIgreja> contribuicoes = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataPrevistaPraContribuir != null) {

		}
		try {
			Query consulta = sessao.createQuery("from ContribuicaoDaIgreja where dataPrevistaPraContribuir=? ");
			consulta.setParameter(0, dataPrevistaPraContribuir);
			contribuicoes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contribuicoes;
	}
	
	@SuppressWarnings("unchecked")
	public List<ContribuicaoDaIgreja> buscarContribuicaoPorGrupo(Long codigo) {
		List<ContribuicaoDaIgreja> contribuicoes= null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from ContribuicaoDaIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			contribuicoes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contribuicoes;
	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ContribuicaoDaIgreja> buscarContribuicaoDaIgrejaPorGrupo(Long codigo) {
		List<ContribuicaoDaIgreja> contribuicoes = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from ContribuicaoDaIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			contribuicoes = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contribuicoes;
	}
}
