package daos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.CompraIgreja;
import entidades.DespesaDaIgreja;

public class DespesaDaIgrejaDao extends GenericDaoHibernate<DespesaDaIgreja>{
	public BigDecimal buscarValorDaDespesaDaIgrejaPorData(Date dataPagamentoDespesaDaIgreja) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorDespescaDaIgreja from DespesaDaIgreja c where c.dataPagamentoDespesaDaIgreja=:dataPagamentoDespesaDaIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPagamentoDespesaDaIgreja", dataPagamentoDespesaDaIgreja);

			BigDecimal somaDespesa = (BigDecimal) query.uniqueResult();
			return somaDespesa;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public List<BigDecimal> buscarValorDaDespesaDaIgrejaPorDataUnitario(Date dataPagamentoDespesaDaIgreja) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorDespescaDaIgreja from DespesaDaIgreja c where c.dataPagamentoDespesaDaIgreja=:dataPagamentoDespesaDaIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataPagamentoDespesaDaIgreja", dataPagamentoDespesaDaIgreja);
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
	public List<DespesaDaIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DespesaDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<DespesaDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DespesaDaIgreja> listarDespesaDaIgrejaPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DespesaDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<DespesaDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DespesaDaIgreja> buscarDespesaDaIgrejaPorDataDePagamento(Date dataPagamentoDespesaDaIgreja) {
		List<DespesaDaIgreja> despesasDaIgreja = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataPagamentoDespesaDaIgreja != null) {

		}
		try {
			Query consulta = sessao.createQuery("from DespesaDaIgreja where dataPagamentoDespesaDaIgreja=? ");
			consulta.setParameter(0, dataPagamentoDespesaDaIgreja);
			despesasDaIgreja = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return despesasDaIgreja;
	}
	
	@SuppressWarnings("unchecked")
	public List<DespesaDaIgreja> buscarDespesaDaIgrejaPorGrupo(Long codigo) {
		List<DespesaDaIgreja> despesasDaIgreja = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null ) {

		}
		try {
			Query consulta = sessao.createQuery("from DespesaDaIgreja where grupo.codigo=?");
			consulta.setParameter(0, codigo);
			
			despesasDaIgreja = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return despesasDaIgreja;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<DespesaDaIgreja> buscarDespesaDaIgrejaPagaPorGrupo(Long codigo,String situacaoDespesa) {
		List<DespesaDaIgreja> despesasDaIgreja = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null ) {

		}
		try {
			Query consulta = sessao.createQuery("from DespesaDaIgreja where grupo.codigo=? and situacaoDaDespesaEnum=?");
			consulta.setParameter(0, codigo);
			consulta.setParameter(1, situacaoDespesa);
			despesasDaIgreja = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return despesasDaIgreja;
	}
	
	
	public DespesaDaIgreja registrarDespesaPaga(DespesaDaIgreja despesa) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			DespesaDaIgreja resposta = (DespesaDaIgreja) sessao.merge(despesa);
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
	public List<DespesaDaIgreja>buscarDespesaPagaDaIgreja(Long codigo,String pago) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			List<DespesaDaIgreja> despesasDaIgreja = null;
			Query query = sessao.createQuery("select * from DespesaDaIgreja c where c.grupo.codigo=:codigo and c.situacaoDaDespesaEnum.descrcao=:situacaoDaDespesaEnum");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setLong("codigo",codigo);
			query.setString("situacaoDaDespesaEnum",pago);

			despesasDaIgreja=query.list();
			
			return despesasDaIgreja;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
}
