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
import entidades.Membro;
import entidades.OfertaEscolaDominical;
import utils.HibernateUtil;

public class DizimoDaIgrejaDao extends GenericDaoHibernate<DizimoDaIgreja>{
	public void salvarDizimoDaIgreja(DizimoDaIgreja dizimo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(dizimo);
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

	public BigDecimal buscarValorDoDizimoPorData(Date dataDizimoIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorDizimoIgreja from DizimoDaIgreja c where c.dataDizimoIgreja=:dataDizimoIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataDizimoIgreja", dataDizimoIgreja);

			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public List<BigDecimal> buscarValorDoDizimoDataUnitario(Date dataDizimoIgreja) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select valorDizimoIgreja from DizimoDaIgreja where c.dataDizimoIgreja=:dataDizimoIgreja");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			query.setDate("dataDizimoIgreja", dataDizimoIgreja);
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
	public List<DizimoDaIgreja> buscarOfertaPorData(Date dataDizimoIgreja) {
		List<DizimoDaIgreja> ofertas = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataDizimoIgreja != null) {

		}
		try {
			Query consulta = sessao.createQuery("from DizimoDaIgreja where dataDizimoIgreja=? ");
			consulta.setParameter(0, dataDizimoIgreja);
			ofertas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return ofertas;
	}
	
	@SuppressWarnings("unchecked")
	public List<DizimoDaIgreja> buscarOfertaPorGrupo(Long codigo) {
		List<DizimoDaIgreja> dizimos = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from DizimoDaIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			dizimos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return dizimos;
	}
	@SuppressWarnings("unchecked")
	public List<DizimoDaIgreja> buscarDizimoDaIgrejaPorGrupo(Long codigo) {
		List<DizimoDaIgreja> dizimos = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from DizimoDaIgreja where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			dizimos = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return dizimos;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DizimoDaIgreja> buscarPorTipoDeDizimo(String tipodizimo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Membro.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipodizimo",tipodizimo));
			List<DizimoDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DizimoDaIgreja> buscarPorTipoDeDizimoECodigo(String tipodizimo,Long codigogrupo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DizimoDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipodizimo",tipodizimo));
			consulta.add(Restrictions.eq("grupo.codigo",codigogrupo));
			List<DizimoDaIgreja>dizimos=consulta.list();
			//Membro resultado = (Membro) consulta.uniqueResult();
			return dizimos;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
	
}
