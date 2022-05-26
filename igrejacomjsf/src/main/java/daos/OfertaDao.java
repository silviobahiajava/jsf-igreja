package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Compra;
import entidades.Lancamento;
import entidades.Oferta;
import utils.Utils;

public class OfertaDao extends GenericDaoHibernate<Oferta> {
	public BigDecimal buscarValorDaOfertaPorDataEGrupo(Date dataOferta, String nome) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery(
					"select valorOferta from Oferta o where o.dataOferta=:dataOferta and o.grupo.nome=:nome");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOferta", dataOferta);
			query.setString("nome", nome);
			BigDecimal somaOferta = (BigDecimal) query.uniqueResult();
			return somaOferta;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorData(Date dataOferta) {
		List<Oferta> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataOferta != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Oferta where dataOferta=? ");
			//consulta.setParameter(0, dataCompra);
			//compras = consulta.list();
			consulta.setParameter(0,dataOferta);
			ofertas=consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}

	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorCodigo(Long codigo) {
		List<Oferta> ofertas = null;
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
//			Query consulta = sessao.createQuery("from Oferta where grupo.codigo=? ");
//			consulta.setParameter(0, codigo);
//			ofertas = consulta.list();
			ofertas = sessao.createQuery( "from Oferta where grupo.codigo = :codigo").setLong("codigo",codigo).list();
			       
			      
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorGrupo(String nome) {
		List<Oferta> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			if (nome != null) {
				Query consulta = sessao.createQuery("from Oferta where grupo.nome=? ");
				consulta.setParameter(0, nome);
				ofertas = consulta.list();
			}

		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}

	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorDataEGrupo(Date dataOferta, String nome) {
		List<Oferta> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (nome != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Oferta   where   dataOferta=? and  grupo.nome=?");

			// consulta.setParameter(0, dataOferta);
			// consulta.setParameter(1,nome);

			consulta.setParameter(0, dataOferta);
			consulta.setParameter(1, nome);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorCodigo(Long codigo) {
		List<Lancamento> lancamentos = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Lancamento where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			lancamentos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return lancamentos;
	}*/

	
	public List<Oferta> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Oferta.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Oferta> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Oferta> listarOfertasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Oferta.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<Oferta> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertaPorGrupo(Long codigo) {
		List<Oferta>ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Oferta where grupo.codigo=? ");
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
