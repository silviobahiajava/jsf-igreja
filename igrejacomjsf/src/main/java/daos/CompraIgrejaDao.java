package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.CompraIgreja;
import entidades.Lancamento;
import utils.HibernateUtil;

public class CompraIgrejaDao extends GenericDaoHibernate<CompraIgreja>{
	public void salvarCompra(CompraIgreja compra) {
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

	public BigDecimal buscarValorDaCompraPorData(Date dataCompraIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompraIgreja from CompraIgreja c where c.dataCompraIgreja=:dataCompraIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompra", dataCompraIgreja);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public List<BigDecimal> buscarValorDaCompraPorDataUnitario(Date dataCompraIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompraIgreja from CompraIgreja c where c.dataCompraIgreja=:dataCompraIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraIgreja", dataCompraIgreja);
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
	public List<CompraIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CompraIgreja> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraIgreja> resultado = consulta.list();
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
	public List<CompraIgreja> buscarCompraPorData(Date dataCompraIgreja) {
		List<CompraIgreja> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCompraIgreja != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraIgreja where dataCompraIgreja=? ");
			consulta.setParameter(0, dataCompraIgreja);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<CompraIgreja> buscarCompraPorGrupo(Long codigo) {
		List<CompraIgreja> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	
	public CompraIgreja salvarContasPagas(CompraIgreja compra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			CompraIgreja resposta = (CompraIgreja) sessao.merge(compra);
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
	public List<CompraIgreja> buscarCompraDaIgrejaPorGrupo(Long codigo) {
		List<CompraIgreja> comprasDaIgreja = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			comprasDaIgreja = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return comprasDaIgreja;
	}
}
