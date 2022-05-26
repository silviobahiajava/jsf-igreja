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

import br.com.testejsf.utils.HibernateUtil;
import entidades.CompraDasCriancas;
import filtros.OfertaFilter;

public class ComprasDasCriancasDao extends GenericDaoHibernate<CompraDasCriancas>{
	public void salvarCompra(CompraDasCriancas compra) {
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

	public BigDecimal buscarValorDaCompraPorData(Date dataCompraCrianca) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompraCrianca from CompraDasCriancas c where c.dataCompraCrianca=:dataCompraCrianca");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraCrianca", dataCompraCrianca);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDaCompraPorDataUnitario(Date dataCompraCrianca) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorCompraCrianca from CompraDasCriancas where c.dataCompraCrianca=:dataCompraCrianca");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataCompraCrianca", dataCompraCrianca);
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
	public List<CompraDasCriancas> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraDasCriancas> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CompraDasCriancas> listarComprasPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(CompraDasCriancas.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<CompraDasCriancas> resultado = consulta.list();
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
	public List<CompraDasCriancas> buscarCompraPorData(Date dataCompraCrianca) {
		List<CompraDasCriancas> compras = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataCompraCrianca != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDasCriancas where dataCompraCrianca=? ");
			consulta.setParameter(0, dataCompraCrianca);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<CompraDasCriancas> buscarCompraPorGrupo(Long codigo) {
		List<CompraDasCriancas> compras = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDasCriancas where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			compras = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return compras;
	}
	
	
	public CompraDasCriancas salvarContasPagas(CompraDasCriancas compra) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			CompraDasCriancas resposta = (CompraDasCriancas) sessao.merge(compra);
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
	public List<CompraDasCriancas> buscarCompraDasCriancasPorGrupo(Long codigo) {
		List<CompraDasCriancas> comprasDasCriancas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDasCriancas where grupo.codigo=? ");
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
	public List<CompraDasCriancas> mostrarCompraDasCriancasPorData(Long codigo,Date data) {
		List<CompraDasCriancas> compras = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from CompraDasCriancas where grupo.codigo=? and  dataCompraCrianca=? ");
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
	public List<CompraDasCriancas>mostrarTodasComprasDasCriancasPorPeriodo(OfertaFilter filter){
		
		List<CompraDasCriancas>compras=new ArrayList<CompraDasCriancas>();
		StringBuilder sbd=new StringBuilder();
		
		sbd.append("from CompraDasCriancas ");
		if(filter.getDataInical()!=null && filter.getDataFinal()!=null){
		
			sbd.append(" where dataCompraCrianca between :dataInicial and :dataFinal ");
		}
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
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
 	public List<CompraDasCriancas> buscarCompraDasCriancasPorGrupoEDataAtual(Long codigo) {
 		List<CompraDasCriancas> compras = null;

 		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
 		if (codigo != null) {

 		}
 		try {
 			Query consulta = sessao.createQuery("from CompraDasCriancas where grupo.codigo=? and  dataCompraCrianca=? ");
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
