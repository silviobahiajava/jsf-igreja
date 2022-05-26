package daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.CampanhaDasCriancas;
import entidades.CompraDasCriancas;
import entidades.OfertaDaIgreja;
import filtros.OfertaFilter;
import utils.HibernateUtil;

public class CampanhaDasCriancasDao extends GenericDaoHibernate<CampanhaDasCriancas>{
	
	public void salvarCampanhaDasCriancas(CampanhaDasCriancas campanha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(campanha);
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

	public BigDecimal buscarValorDaCampanhaPorData(Date dataCampanha) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCampanhaDasCriancas from CampanhaDasCriancas c where c.dataCampanha=:dataCampanha");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCampanha", dataCampanha);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public List<BigDecimal> buscarValorDaCampanhaPorDataUnitario(Date dataCampanha) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCampanhaDasCriancas from CampanhaDasCriancas c where c.dataCampanha=:dataCampanha");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCampanha", dataCampanha);
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
	public List<CampanhaDasCriancas> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CampanhaDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CampanhaDasCriancas> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CampanhaDasCriancas> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CampanhaDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CampanhaDasCriancas> resultado = consulta.list();
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
	public List<CampanhaDasCriancas> buscarCampanhaPorData(Date dataCampanha) {
		List<CampanhaDasCriancas> lista = null;
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCampanha != null) {
		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDasCriancas where dataCampanha=? ");
			consulta.setParameter(0, dataCampanha);
			lista = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CampanhaDasCriancas> buscarCampanhaPorGrupo(Long codigo) {
		List<CampanhaDasCriancas> lista = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDasCriancas where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			lista = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return lista;
	}
	
	
	public CampanhaDasCriancas salvarContasPagas(CampanhaDasCriancas campanha) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			CampanhaDasCriancas resposta = (CampanhaDasCriancas) sessao.merge(campanha);
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
	public List<CampanhaDasCriancas> buscarCampanhaDasCriancasPorGrupo(Long codigo) {
		List<CampanhaDasCriancas> lista = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDasCriancas where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			lista = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return lista;
	}
	
	
	
	
	
}
