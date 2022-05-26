package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.CampanhaDoPeCriancas;

import utils.HibernateUtil;

public class CampanhaDoPeCriancasDao extends GenericDaoHibernate<CampanhaDoPeCriancas>{
	public void salvarContribuicaoCampanhaDoPe(CampanhaDoPeCriancas campanha) {
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

	public BigDecimal buscarValorDaOfertaPorData(Date dataPagamento) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorPraPagar from CampanhaDoPeCriancas c where c.dataPagamento=:dataPagamento");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPagamento", dataPagamento);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaOfertaorDataUnitario(Date dataPagamento) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorPraPagar from CampanhaDoPeCriancas where c.dataPagamento=:dataPagamento");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPagamento", dataPagamento);
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
	public List<CampanhaDoPeCriancas> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CampanhaDoPeCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CampanhaDoPeCriancas> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CampanhaDoPeCriancas> listarOfertasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CampanhaDoPeCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CampanhaDoPeCriancas> resultado = consulta.list();
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
	public List<CampanhaDoPeCriancas> buscarOfertaPorData(Date dataPagamento) {
		List<CampanhaDoPeCriancas> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataPagamento != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDoPeCriancas where dataPagamento=? ");
			consulta.setParameter(0, dataPagamento);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	@SuppressWarnings("unchecked")
	public List<CampanhaDoPeCriancas> buscarOfertaPorGrupo(Long codigo) {
		List<CampanhaDoPeCriancas> ofertasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDoPeCriancas where grupo.codigo=? ");
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
	public List<CampanhaDoPeCriancas> buscarCampanhaDoPeCriancasPorGrupo(Long codigo) {
		List<CampanhaDoPeCriancas> lista = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CampanhaDoPeCriancas where grupo.codigo=? ");
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
