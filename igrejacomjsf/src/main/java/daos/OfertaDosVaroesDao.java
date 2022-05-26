package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.OfertaDosVaroes;
import utils.HibernateUtil;

public class OfertaDosVaroesDao extends GenericDaoHibernate<OfertaDosVaroes>{
	public void salvarOferta(OfertaDosVaroes oferta) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(oferta);
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

	public BigDecimal buscarValorDaOfertaPorData(Date dataOfertaVarao) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaVarao from OfertaDosVaroes c where c.dataOfertaVarao=:dataOfertaVarao");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaVarao", dataOfertaVarao);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaOfertaorDataUnitario(Date dataOfertaVarao) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaVarao from OfertaDosVaroes where c.dataOfertaVarao=:dataOfertaVarao");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaVarao", dataOfertaVarao);
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
	public List<OfertaDosVaroes> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDosVaroes.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDosVaroes> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDosVaroes> listarOfertasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDosVaroes.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDosVaroes> resultado = consulta.list();
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
	public List<OfertaDosVaroes> buscarOfertaPorData(Date dataOfertaVarao) {
		List<OfertaDosVaroes> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataOfertaVarao != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDosVaroes where dataOfertaVarao=? ");
			consulta.setParameter(0, dataOfertaVarao);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfertaDosVaroes> buscarOfertaPorGrupo(Long codigo) {
		List<OfertaDosVaroes> ofertasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDosVaroes where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			ofertasDasCriancas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertasDasCriancas;
	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDosVaroes> buscarOfertaDosVaroesPorGrupo(Long codigo) {
		List<OfertaDosVaroes> ofertasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDosVaroes where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			ofertasDasCriancas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertasDasCriancas;
	}

}
