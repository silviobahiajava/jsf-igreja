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

import entidades.CompraEscolaDominical;
import entidades.CompraEscolaDominical;
import filtros.OfertaFilter;
import utils.HibernateUtil;

public class CompraEscolaDominicalDao extends GenericDaoHibernate<CompraEscolaDominical>{
	public void salvarCompra(CompraEscolaDominical compra) {
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

	public BigDecimal buscarValorDaCompraPorData(Date dataCompraEBD) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valor from CompraEscolaDominical c where c.dataCompraEBD=:dataCompraEBD");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraEBD", dataCompraEBD);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaCompraPorDataUnitario(Date dataCompraEBD) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valor from CompraEscolaDominical where c.dataCompraEBD=:dataCompraEBD");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraEBD", dataCompraEBD);
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
	public List<CompraEscolaDominical> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraEscolaDominical.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraEscolaDominical> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CompraEscolaDominical> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraEscolaDominical.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraEscolaDominical> resultado = consulta.list();
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
	public List<CompraEscolaDominical> buscarCompraPorData(Date dataCompraEBD) {
		List<CompraEscolaDominical> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCompraEBD != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraEscolaDominical where dataCompraEBD=? ");
			consulta.setParameter(0, dataCompraEBD);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<CompraEscolaDominical> buscarCompraPorGrupo(Long codigo) {
		List<CompraEscolaDominical> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraEscolaDominical where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	
	public CompraEscolaDominical salvarContasPagas(CompraEscolaDominical compra) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			CompraEscolaDominical resposta = (CompraEscolaDominical) sessao.merge(compra);
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
	public List<CompraEscolaDominical> buscarCompraEscolaDominicalPorGrupo(Long codigo) {
		List<CompraEscolaDominical> comprasDasCriancas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraEscolaDominical where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			comprasDasCriancas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return comprasDasCriancas;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CompraEscolaDominical> mostrarCompraEscolaDominicalPorData(Long codigo,Date data) {
		List<CompraEscolaDominical> compras = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraEscolaDominical where grupo.codigo=? and  dataCompraEBD=? ");
			consulta.setParameter(0, codigo);
			consulta.setParameter(1,data);
			compras = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	
     @SuppressWarnings("unchecked")
	public List<CompraEscolaDominical>mostrarTodasComprasDasCriancasPorPeriodo(OfertaFilter filter){
		
		List<CompraEscolaDominical>compras=new ArrayList<CompraEscolaDominical>();
		StringBuilder sbd=new StringBuilder();
		
		sbd.append("from CompraEscolaDominical ");
		if(filter.getDataInical()!=null && filter.getDataFinal()!=null){
		
			sbd.append(" where dataCompraEBD between :dataInicial and :dataFinal ");
		}
		
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query consulta=sessao.createQuery(sbd.toString());
			consulta.setDate("dataInicial", filter.getDataInical());
			consulta.setDate("dataFinal",filter.getDataFinal());
			compras=consulta.list();
			return compras;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
     @SuppressWarnings("unchecked")
 	public List<CompraEscolaDominical> buscarCompraEscolaDominicalPorGrupoEDataAtual(Long codigo) {
 		List<CompraEscolaDominical> compras = null;

 		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
 		if (codigo != null) {

 		}
 		try {
 			Query consulta = sessao.createQuery("from CompraEscolaDominical where grupo.codigo=? and  dataCompraEBD=? ");
 			consulta.setParameter(0, codigo);
 			consulta.setParameter(1,new Date());
 			compras = consulta.list();
 			
 		} catch (RuntimeException e) {
 			throw e;
 		} finally {
 			sessao.close();
 		}
 		return compras;
 	}
}
