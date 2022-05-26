package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.testejsf.utils.HibernateUtil;
import entidades.OfertaDasCriancas;

public class OfertaDasCriancasDao extends GenericDaoHibernate<OfertaDasCriancas>{
	public void salvarOferta(OfertaDasCriancas oferta) {
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

	public BigDecimal buscarValorDaOfertaPorData(Date dataOfertaCrianca) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaCrianca from OfertaDasCriancas c where c.dataOfertaCrianca=:dataOfertaCrianca");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaCrianca", dataOfertaCrianca);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaOfertaorDataUnitario(Date dataOfertaCrianca) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaCrianca from OfertaDasCriancas where c.dataOfertaCrianca=:dataOfertaCrianca");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaCrianca", dataOfertaCrianca);
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
	public List<OfertaDasCriancas> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDasCriancas> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDasCriancas> listarOfertasPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDasCriancas> resultado = consulta.list();
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
	public List<OfertaDasCriancas> buscarOfertaPorData(Date dataOfertaCrianca) {
		List<OfertaDasCriancas> ofertas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataOfertaCrianca != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDasCriancas where dataOfertaCrianca=? ");
			consulta.setParameter(0, dataOfertaCrianca);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfertaDasCriancas> buscarOfertaPorGrupo(Long codigo) {
		List<OfertaDasCriancas> ofertasDasCriancas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDasCriancas where grupo.codigo=? ");
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
	public List<OfertaDasCriancas> buscarOfertaDasCriancasPorGrupo(Long codigo) {
		List<OfertaDasCriancas> ofertasDasCriancas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDasCriancas where grupo.codigo=? ");
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
