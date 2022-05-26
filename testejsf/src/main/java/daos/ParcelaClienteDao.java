package daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Pagamento;
import entidades.PagamentoCliente;
import entidades.Parcela;
import entidades.ParcelaCliente;

public class ParcelaClienteDao extends GenericDaoHibernate<ParcelaCliente>{
	
	@SuppressWarnings("unchecked")
	public ParcelaCliente buscarParcela(Date dataVencimentoDaParcela,double valorDaParcela,int numerodaparcela,String emailCliente) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
			Query query = sessao.createQuery("from ParcelaCliente where dataVencimentoDaParcela = ? and valorDaParcela=? and numerodaparcela =? "
					+ "and pagamento.emailCliente = ? ");
			query.setParameter(0,dataVencimentoDaParcela);
			query.setParameter(1, valorDaParcela);
			query.setParameter(2, numerodaparcela);
			query.setParameter(3, emailCliente);
			ParcelaCliente parcela=(ParcelaCliente) query.uniqueResult();
			return parcela;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	//ESTE M�TODO FUNCIONA PERFEITAMENTE
		@SuppressWarnings("unchecked")
		public ParcelaCliente buscarParcelaPorDataVencimentoEmail(Date dataVencimentoDaParcela,String emailCliente) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
				Query query = sessao.createQuery("from ParcelaCliente p where p.dataVencimentoDaParcela =? and p.pagamento.emailCliente = ?");
				query.setParameter(0,dataVencimentoDaParcela);
				query.setParameter(1,emailCliente);
				ParcelaCliente parcela=(ParcelaCliente) query.uniqueResult();
				return parcela;

			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		
		
		public ParcelaCliente buscarPorCpfNumeroParcela(String cpf,int numerodaparcela) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(ParcelaCliente.class);
				consulta.add(Restrictions.eq("pagamento.cpf", cpf));
				consulta.add(Restrictions.eq("numerodaparcela",numerodaparcela));
				ParcelaCliente parcela=(ParcelaCliente) consulta.uniqueResult();
				
				return parcela;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
	
		public List<ParcelaCliente> buscarPorPagamento(Long codigopagamento) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(ParcelaCliente.class);
				consulta.add(Restrictions.eq("pagamento.codigo",codigopagamento));
				
				List<ParcelaCliente> parcelas=consulta.list();
				
				return parcelas;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		public PagamentoCliente buscarPorCpf(String cpf) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(PagamentoCliente.class);
				//consulta.add(Restrictions.eq("pagamento.cpfCliente",cpf));
				consulta.add(Restrictions.ilike("cpfCliente",cpf));
				PagamentoCliente pagamento=(PagamentoCliente) consulta.uniqueResult();
				
				return pagamento;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		
		//eq � usado pra numeros ilike � usado pra String
		public ParcelaCliente buscarPorCodigoPagamentoNumeroParcela(Long codigopagamento,int numeroparcela) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(ParcelaCliente.class);
				//consulta.add(Restrictions.eq("pagamento.cpfCliente",cpf));
				consulta.add(Restrictions.eq("pagamento.codigo",codigopagamento));
				consulta.add(Restrictions.eq("numerodaparcela",numeroparcela));
				ParcelaCliente parcela=(ParcelaCliente) consulta.uniqueResult();
				
				return parcela;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		
		
		public ParcelaCliente buscarPorNomeNumeroParcela(String nomeCliente,int numeroparcela) {
			Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(ParcelaCliente.class);
				//consulta.add(Restrictions.eq("pagamento.cpfCliente",cpf));
				consulta.add(Restrictions.eqOrIsNull("pagamento.nomeCliente",nomeCliente));
				consulta.add(Restrictions.eq("numerodaparcela",numeroparcela));
				ParcelaCliente parcela=(ParcelaCliente) consulta.uniqueResult();
				
				return parcela;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
		
		
		
		
		
		
		
		
		
		
		/*public List<Cidade> buscarPorEstado(Long estadoCodigo) {
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(Cidade.class);
				consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));	
				consulta.addOrder(Order.asc("nome"));
				List<Cidade> resultado = consulta.list();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}
	*/
	
}
