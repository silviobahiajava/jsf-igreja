package daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import entidades.Cliente;
import utils.HibernateUtil;

public class ClienteDao extends GenericDaoHibernate<Cliente> {
	/*
	 * EntityManager em=Utils.conecar();
	 * 
	 * @SuppressWarnings("unchecked") public List<Membro>listar(){
	 * List<Membro>membros=null; Query query = em.createQuery(
	 * "select o from Membro o"); membros = query.getResultList(); return
	 * membros; }
	 */

	public Cliente salvarCiente(Cliente cliente) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Cliente resposta = (Cliente) sessao.merge(cliente);
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
	// public List<Membro>buscarMembro(){
	// Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
	// try {
	// Query query = sessao.createQuery("select l from Membro l where
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
	public List<Cliente> listarCliente() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			List<Cliente> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarAniversarioAdultosPorData(Date dataNascimento) {
		
		List<Cliente> aniversarios = null;

		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (dataNascimento != null) {

		}
		
		try {  
		
			
			Query consulta = sessao.createQuery("from cliente where dataNascimento=?");
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
	public List<Cliente> buscarAniversariantesDoMes() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		List<Cliente> aniversarios = null;
		try {
			Query query = sessao.createSQLQuery("select * from cliente where Month(dataNascimento)=Month(Now()");
			
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
	 
	public List<Cliente>buscarPorDiaMes(Date dataNascimento){
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		
		
		Calendar calendario=Calendar.getInstance();
		calendario.setTime(dataNascimento);
		Integer dia=calendario.get(Calendar.DAY_OF_MONTH);
		Integer mes=calendario.get(Calendar.MONTH);
		Query busca=sessao.createQuery("select m from Cliente m where DAY(m.dataNascimento)=? and MONTH(m.dataNascimento)=?");
		
		busca.setInteger(0, dia);
		busca.setInteger(1,mes);
		return busca.list();
		
	}
	
	
	
	
		@SuppressWarnings("unchecked")
		public List<Cliente> listarAniversariantesDoMes(int mes){  
			Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
            try {
            	List<Cliente>clientes=new ArrayList<Cliente>();
                clientes= sessao.createQuery("from Membro where month(dataNascimento) = "+mes).list(); 
                return clientes;
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
		//select nome from membro where day(dataNascimento)=16 and month(dataNascimento)=05;
		
		//este método deu certo  no JUnit
		@SuppressWarnings("unchecked")
		public List<Cliente> buscarAniversariantes(int dia,int mes) {
			Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
				Query query = sessao.createQuery("from Cliente where day(dataNascimento)=? and  month(dataNascimento)=?");
				query.setParameter(0,dia);
				query.setParameter(1,mes);
				List<Cliente>membros = query.list();
				return membros;

			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		
	}
	
	
	
		
	
	
	


