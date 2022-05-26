package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.CompraDaMissao;
import utils.HibernateUtil;

public class CompraDaMissaoDao extends GenericDaoHibernate<CompraDaMissao> {
	public void salvarCompra(CompraDaMissao compra) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(compra);
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

	public BigDecimal buscarValorDaCompraPorData(Date dataCompraMissao) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery(
					"select valorCompraVarao from CompraDaMissao c where c.dataCompraMissao=:dataCompraMissao");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraMissao", dataCompraMissao);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaCompraPorDataUnitario(Date dataCompraMissao) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery(
					"select valorCompraMissao from CompraDaMissao where c.dataCompraMissao=:dataCompraMissao");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraMissao", dataCompraMissao);
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
	public List<CompraDaMissao> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraDaMissao.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraDaMissao> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CompraDaMissao> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraDaMissao.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraDaMissao> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	// saldo por ggupo

	/*
	 * @SuppressWarnings("unchecked") public List<Lancamento>
	 * buscarSaldoPorGrupo(Long grupoCodigo) { Session sessao =
	 * utils.HibernateUtil.getFabricaDeSessoes().openSession(); try { Criteria
	 * consulta = sessao.createCriteria(Lancamento.class);
	 * consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo)); //
	 * consulta.addOrder(Order.asc("nome")); List<Lancamento> resultado =
	 * consulta.list(); return resultado; } catch (RuntimeException erro) { throw
	 * erro; } finally { sessao.close(); } }
	 */

	@SuppressWarnings("unchecked")
	public List<CompraDaMissao> buscarCompraPorData(Date dataCompraMissao) {
		List<CompraDaMissao> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCompraMissao != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDaMissao where dataCompraMissao=? ");
			consulta.setParameter(0, dataCompraMissao);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<CompraDaMissao> buscarCompraPorGrupo(Long codigo) {
		List<CompraDaMissao> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDaMissao where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}

	public CompraDaMissao salvarContasPagas(CompraDaMissao compra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			CompraDaMissao resposta = (CompraDaMissao) sessao.merge(compra);
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

	@SuppressWarnings("unchecked")
	public List<CompraDaMissao> buscarCompraDaMissaoPorGrupo(Long codigo) {
		List<CompraDaMissao> comprasDosVaroes = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDaMissao where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			comprasDosVaroes = consulta.list();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return comprasDosVaroes;
	}
}
