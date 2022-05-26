package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.DizimoDaIgreja;
import entidades.OfertaEscolaDominical;
import utils.HibernateUtil;

public class OfertaEscolaDominicalDao extends GenericDaoHibernate<OfertaEscolaDominical>{
	public void OfertaEscolaDominical(OfertaEscolaDominical oferta) {
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

	public BigDecimal buscarValorOfertaEscolaDominicalPorData(Date dataOfertaEescolaDominical) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaEscolaDominical from OfertaEscolaDominical c where c.dataOfertaEescolaDominical=:dataOfertaEescolaDominical");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaEescolaDominical",dataOfertaEescolaDominical);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorOfertaEscolaDominicalDataUnitario(Date dataOfertaEescolaDominical) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaEscolaDominical from OfertaEscolaDominical where c.dataOfertaEescolaDominical=:dataOfertaEescolaDominical");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaEescolaDominical",dataOfertaEescolaDominical);
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
	public List<DizimoDaIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DizimoDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<DizimoDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DizimoDaIgreja> listarDizimoPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DizimoDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<DizimoDaIgreja> resultado = consulta.list();
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
	public List<OfertaEscolaDominical> buscarOfertaEscolaDominicalPorData(Date dataOfertaEescolaDominical) {
		List<OfertaEscolaDominical> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataOfertaEescolaDominical != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaEscolaDominical where dataOfertaEescolaDominical=? ");
			consulta.setParameter(0, dataOfertaEescolaDominical);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaEscolaDominical> buscarOfertaEscolaDominicalPorGrupo(Long codigo) {
		List<OfertaEscolaDominical> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaEscolaDominical where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			ofertas= consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaEscolaDominical> buscarOfertaDaEscolaDominicalPorGrupo(Long codigo) {
		List<OfertaEscolaDominical> ofertas= null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaEscolaDominical where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			ofertas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}

}
