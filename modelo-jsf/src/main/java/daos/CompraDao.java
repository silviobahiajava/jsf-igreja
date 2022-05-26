package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.Compra;
import entidades.Lancamento;
import utils.HibernateUtil;

public class CompraDao extends GenericDaoHibernate<Compra> {
	/*
	 * public void salvarVenda(Venda venda, List<ItemVenda> itensVenda){ Session
	 * sessao = HibernateUtil.getFabricaDeSessoes().openSession(); Transaction
	 * transacao = null;
	 * 
	 * try { transacao = sessao.beginTransaction();
	 * 
	 * sessao.save(venda);
	 * 
	 * for(int posicao = 0; posicao < itensVenda.size(); posicao++){ ItemVenda
	 * itemVenda = itensVenda.get(posicao); itemVenda.setVenda(venda);
	 * 
	 * sessao.save(itemVenda); }
	 * 
	 * transacao.commit(); } catch (RuntimeException erro) { if (transacao !=
	 * null) { transacao.rollback(); } throw erro; } finally { sessao.close(); }
	 * }
	 */

	/*
	 * public void salvarCompras(Compras compras, List<ItemCompra> itensCompra){
	 * Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	 * Transaction transacao = null;
	 * 
	 * try { transacao = sessao.beginTransaction();
	 * 
	 * sessao.save(compras);
	 * 
	 * for(int posicao = 0; posicao < itensCompra.size(); posicao++){ ItemCompra
	 * itemCompra = itensCompra.get(posicao); itemCompra.setCompras(compras);
	 * 
	 * sessao.save(itemCompra); }
	 * 
	 * transacao.commit(); } catch (RuntimeException erro) { if (transacao !=
	 * null) { transacao.rollback(); } throw erro; } finally { sessao.close(); }
	 * }
	 */
	public void salvarCompra(Compra compra) {
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

	public BigDecimal buscarValorDaCompraPorData(Date dataCompra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompra from Compra c where c.dataCompra=:dataCompra");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompra", dataCompra);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<BigDecimal> buscarValorDaCompraPorDataUnitario(Date dataCompra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompra from Compra c where c.dataCompra=:dataCompra");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompra", dataCompra);
			// BigDecimal somaReceita=(BigDecimal) query.uniqueResult();
			// BigDecimal somaReceita=(BigDecimal) query.list();
			List<BigDecimal> lista = query.list();
			return lista;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Compra> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Compra.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Compra> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Compra> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Compra.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Compra> resultado = consulta.list();
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
	public List<Compra> buscarCompraPorData(Date dataCompra) {
		List<Compra> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCompra != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Compra where dataCompra=? ");
			consulta.setParameter(0, dataCompra);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> buscarCompraPorGrupo(Long codigo) {
		List<Compra> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Compra where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	
	public Compra salvarContasPagas(Compra compra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Compra resposta = (Compra) sessao.merge(compra);
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
