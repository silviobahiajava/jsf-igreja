package daos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.testejsf.utils.HibernateUtil;
import entidades.ObreirosDaIgreja;
import entidades.ObreirosDaIgreja;

public class ObreiroDaIgrejaDao extends GenericDaoHibernate<ObreirosDaIgreja>{
	/*
	 * EntityManager em=Utils.conecar();
	 * 
	 * @SuppressWarnings("unchecked") public List<ObreirosDaIgreja>listar(){
	 * List<ObreirosDaIgreja>ObreirosDaIgrejas=null; Query query = em.createQuery(
	 * "select o from ObreirosDaIgreja o"); ObreirosDaIgrejas = query.getResultList(); return
	 * ObreirosDaIgrejas; }
	 */

	public ObreirosDaIgreja salvarObreirosDaIgreja(ObreirosDaIgreja ObreirosDaIgreja) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			ObreirosDaIgreja resposta = (ObreirosDaIgreja) sessao.merge(ObreirosDaIgreja);
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

	// @SuppressWarnings("unchecked")
	// public List<ObreirosDaIgreja>buscarObreirosDaIgreja(){
	// Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
	// try {
	// Query query = sessao.createQuery("select l from ObreirosDaIgreja l where
	// l.grupo.nome=:nome");
	// //long contador=(long) query.uniqueResult();
	// //query.setString("nome",nome);
	// //query.setDate("data", data);
	// query.setString("nome",nome);
	// //BigDecimal somaOferta=(BigDecimal) query.uniqueResult();
	// List<Lancamento>lancamentos=query.list();
	// return lancamentos;
	// } catch (RuntimeException erro) {
	// throw erro;
	// } finally {
	// sessao.close();
	// }
	// }

	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> listarObreirosDaIgreja() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			List<ObreirosDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarPorCargo(Long cargoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			
			List<ObreirosDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarPorTipoDeCargo(String tipodecargo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipoCargo",tipodecargo));
			List<ObreirosDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarPorStatusObreirosDaIgreja(String statusObreirosDaIgreja) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("statusObreirosDaIgreja",statusObreirosDaIgreja));
			List<ObreirosDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarPorTipoDeCargoECodigo(String tipodecargo,Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("tipoCargo",tipodecargo));
			consulta.add(Restrictions.eq("codigo",codigo));
			List<ObreirosDaIgreja>obreiros=consulta.list();
			//ObreirosDaIgreja resultado = (ObreirosDaIgreja) consulta.uniqueResult();
			return obreiros;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarPorCpfENumeroCartao(String cpf,Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ObreirosDaIgreja.class);
			//consulta.add(Restrictions.eq("cargo.codigo", cargoCodigo));	
			consulta.add(Restrictions.ilike("cpf",cpf));
			consulta.add(Restrictions.eq("codigo",codigo));
			List<ObreirosDaIgreja>obreiros=consulta.list();
			//ObreirosDaIgreja resultado = (ObreirosDaIgreja) consulta.uniqueResult();
			return obreiros;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarAniversarioAdultosPorData(Date dataNascimento) {
		
		List<ObreirosDaIgreja> aniversarios = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataNascimento != null) {

		}
		
		try {  
		
			
			Query consulta = sessao.createQuery("from ObreirosDaIgreja where dataNascimento=?");
			consulta.setParameter(0, dataNascimento);
			aniversarios = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return aniversarios;
	}
	
	//SELECT * aniversariantes WHERE Month(data_nascimento) = Month(Now())
	
	@SuppressWarnings("unchecked")
	public List<ObreirosDaIgreja> buscarAniversariantesDoMes() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		List<ObreirosDaIgreja> aniversarios = null;
		try {
			Query query = sessao.createSQLQuery("select * from ObreirosDaIgreja where Month(dataNascimento)=Month(Now()");
			
			aniversarios=query.list();
			return aniversarios;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	/*
	 * 
	 * public List<Pessoa> listaAniversariantes(int mes) {
  return this.session.createQuery("select pessoa from Pessoa pessoa " +
    "where month(pessoa.dtnascimento) = " + mes +
    "order by day(pessoa.dtnascimento), pessoa.nome").list();
}


public List<SuaClasse>BuscarPorDiaEMes(Date dataInformada){
 Calendar cal = Calendar.getInstance();
 cal.setTime(dataInformada);
 Integer dia = cal.get(Calendar.DAY_OF_MONTH);
 Integer mes = cal.get(Calendar.DAY_OF_MONTH)
 Query q = session.createQuery("SELECT a FROM SuaClasse a WHERE DAY(a.seuBeanData) = ? AND MONTH(a.seuBeanData) = ? " );
 q.setInteger(0,dia);
 q.setInteger(1,mes);
 return q.list();
}


	 * 
	 * 
	 * 
	 * public List<SuaClasse>BuscarPorDiaEMes(Date dataInformada){
 Calendar cal = Calendar.getInstance();
 cal.setTime(dataInformada);
 Integer dia = cal.get(Calendar.DAY_OF_MONTH);
 Integer mes = cal.get(Calendar.DAY_OF_MONTH)
 Query q = session.createQuery("SELECT a FROM SuaClasse a WHERE DAY(a.seuBeanData) = ? AND MONTH(a.seuBeanData) = ? " );
 q.setInteger(0,dia);
 q.setInteger(1,mes);
 return q.list();
}

	 * 
	 * */
	 
	public List<ObreirosDaIgreja>buscarPorDiaMes(Date dataNascimento){
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		
		Calendar calendario=Calendar.getInstance();
		calendario.setTime(dataNascimento);
		Integer dia=calendario.get(Calendar.DAY_OF_MONTH);
		Integer mes=calendario.get(Calendar.MONTH);
		Query busca=sessao.createQuery("select m from ObreirosDaIgreja m where DAY(m.dataNascimento)=? and MONTH(m.dataNascimento)=?");
		
		busca.setInteger(0, dia);
		busca.setInteger(1,mes);
		return busca.list();
		
	}
	
	
	
	
		@SuppressWarnings("unchecked")
		public List<ObreirosDaIgreja> listarAniversariantesDoMes(int mes){  
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
            try {
            	
                return sessao.createQuery("from ObreirosDaIgreja where month(dataNascimento) = "+mes).list();   
            } catch(Exception e){
                try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}            
            } finally {
                if(sessao != null && sessao.isOpen()){}
                sessao.close();
            }
			return null;
        }
		
		
		//este comando  deu certo no sql
		//select nome from ObreirosDaIgreja where day(dataNascimento)=16 and month(dataNascimento)=05;
		
		//este m�todo deu certo  no JUnit
		@SuppressWarnings("unchecked")
		public List<ObreirosDaIgreja> buscarAniversariantes(int dia,int mes) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				//Query query = sessao.createQuery("select nome from ObreirosDaIgreja where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
				Query query = sessao.createQuery("from ObreirosDaIgreja where day(dataNascimento)=? and  month(dataNascimento)=?");
				query.setParameter(0,dia);
				query.setParameter(1,mes);
				List<ObreirosDaIgreja>ObreirosDaIgrejas = query.list();
				return ObreirosDaIgrejas;

			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
}
