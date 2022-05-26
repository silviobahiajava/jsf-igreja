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

import entidades.DizimoDaIgreja;
import entidades.Membro;
import entidades.OfertaDaIgreja;
import filtros.AniversarioFilter;
import filtros.OfertaFilter;
import utils.HibernateUtil;

public class OfertaDaIgrejaDao extends GenericDaoHibernate<OfertaDaIgreja>{
	public void salvarOferta(OfertaDaIgreja oferta) {
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

	public BigDecimal buscarValorDaOfertaPorData(Date dataOfertaIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaIgreja from OfertaDaIgreja c where c.dataOfertaIgreja=:dataOfertaIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaIgreja", dataOfertaIgreja);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaOfertaorDataUnitario(Date dataOfertaIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorOfertaIgreja from OfertaDaIgreja where c.dataOfertaIgreja=:dataOfertaIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataOfertaIgreja", dataOfertaIgreja);
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
	public List<OfertaDaIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> listarOfertasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<OfertaDaIgreja> resultado = consulta.list();
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
	public List<OfertaDaIgreja> buscarOfertaPorData(Date dataOfertaIgreja) {
		List<OfertaDaIgreja> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataOfertaIgreja != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDaIgreja where dataOfertaIgreja=? ");
			consulta.setParameter(0, dataOfertaIgreja);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> buscarOfertaPorGrupo(Long codigo) {
		List<OfertaDaIgreja> ofertasDaIgreja = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDaIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			ofertasDaIgreja = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertasDaIgreja;
	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> buscarOfertaDaIgrejaPorGrupo(Long codigo) {
		List<OfertaDaIgreja> ofertasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDaIgreja where grupo.codigo=? and  dataOfertaIgreja=?");
			consulta.setParameter(0, codigo);
			consulta.setParameter(1,new Date());
			ofertasDasCriancas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertasDasCriancas;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> mostrarOfertaPorData(Long codigo,Date data) {
		List<OfertaDaIgreja> ofertasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from OfertaDaIgreja where grupo.codigo=? and  dataOfertaIgreja=?");
			consulta.setParameter(0, codigo);
			consulta.setParameter(1,data);
			ofertasDasCriancas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertasDasCriancas;
	}
	
	
	public List<OfertaDaIgreja>mostrarTodasOfertasDoMes(OfertaFilter filter){
		
		List<OfertaDaIgreja>ofertas=new ArrayList<OfertaDaIgreja>();
		StringBuilder sbd=new StringBuilder();
		
		sbd.append("from OfertaDaIgreja ");
		if(filter.getDataInical()!=null && filter.getDataFinal()!=null){
		
			sbd.append(" where dataOfertaIgreja between :dataInicial and :dataFinal ");
		}
		
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query consulta=sessao.createQuery(sbd.toString());
			consulta.setDate("dataInicial", filter.getDataInical());
			consulta.setDate("dataFinal",filter.getDataFinal());
			ofertas=consulta.list();
			return ofertas;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> buscarPorTipoDeDizimo(String tipodeoferta) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipodeoferta",tipodeoferta));
			List<OfertaDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OfertaDaIgreja> buscarPorTipoDeOfertaECodigo(String tipodeoferta,Long codigogrupo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(OfertaDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipodizimo",tipodeoferta));
			consulta.add(Restrictions.eq("grupo.codigo",codigogrupo));
			List<OfertaDaIgreja>ofertas=consulta.list();
			//Membro resultado = (Membro) consulta.uniqueResult();
			return ofertas;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
}
