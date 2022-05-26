package daos;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.Campanha;
import entidades.Compra;
import entidades.ContasParaPagar;
import entidades.Contribuicao;

import entidades.DespesaCrianca;
import entidades.Dizimo;
import entidades.Lancamento;
import entidades.LancamentoLivroCaixa;
import entidades.Oferta;

public class LancamentoLivroCaixaDao extends GenericDaoHibernate<LancamentoLivroCaixa>{
	/*public long buscarquantidadeRegistros() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select count(*) from LancamentoLivroCaixa");
			long contador = (long) query.uniqueResult();
			return contador;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}*/

	

	public BigDecimal buscarSomaGeralReceitas() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select sum(receita) from LancamentoLivroCaixa");
			// long contador=(long) query.uniqueResult();
			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public BigDecimal buscarSomaGeralDespesas() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select sum(despesa) from LancamentoLivroCaixa");
			// long contador=(long) query.uniqueResult();
			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public BigDecimal buscarSaldo() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select sum(despesa) -sum(receita) from LancamentoLivroCaixa");
			// long contador=(long) query.uniqueResult();
			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public BigDecimal buscarTotalDespesaPorGrupo(Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select sum(despesa)  from LancamentoLivroCaixa lancamento where lancamento.grupo.codigo=:codigo");
			// long contador=(long) query.uniqueResult();
			//query.setParameter("codigo",codigo);
			query.setLong(0,codigo);
			BigDecimal totalDespesa = (BigDecimal) query.uniqueResult();
			return totalDespesa;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public BigDecimal buscarTotalDespesa(Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select sum(despesa)  from LancamentoLivroCaixa where grupo.codgio=:codigo");
			query.setParameter(0,codigo);
			// long contador=(long) query.uniqueResult();
			
			BigDecimal totalDespesa = (BigDecimal) query.uniqueResult();
			return totalDespesa;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	public BigDecimal buscarSaldoPorGrupo(String nome) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao
					.createQuery("select sum(despesa) -sum(receita) from LancamentoLivroCaixa l where l.nome=:nome");
			// long contador=(long) query.uniqueResult();
			query.setString("nome", nome);
			BigDecimal somaReceita = (BigDecimal) query.uniqueResult();
			return somaReceita;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	//DEU CERTO ESSE METODO
	@SuppressWarnings("unchecked")
	public List<LancamentoLivroCaixa> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(LancamentoLivroCaixa.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<LancamentoLivroCaixa> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	// saldo por ggupo

	@SuppressWarnings("unchecked")
	public List<LancamentoLivroCaixa> buscarSaldoPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(LancamentoLivroCaixa.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<LancamentoLivroCaixa> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}

	/*
	 * public List<Lancamento> buscarSaldoPorGrupo(String grupo) { String hql =
	 * "select from Restaurante r where r.nome like :nome";
	 * 
	 * return session.createQuery(hql) .setParameter("nome",
	 * restaurante.getNome()) .setParameter("endereco",
	 * restaurante.getEndereco()) .setParameter("tipoDeComida",
	 * restaurante.getTipoDeComida().getNome()) .list() }
	 */

	public void salvarLancamentoCompra(LancamentoLivroCaixa lancamento, List<Compra> compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				Compra c = compras.get(posicao);
				c.setLancamentoLivroCaixa(lancamento);

				sessao.save(c);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	

	public void salvarLancamentoDizimos(Lancamento lancamento, List<Dizimo> dizimos) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < dizimos.size(); posicao++) {
				Dizimo d = dizimos.get(posicao);
				d.setLancamento(lancamento);

				sessao.save(d);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void salvarLancamentoOfertas(LancamentoLivroCaixa lancamento, List<Oferta> ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				Oferta o = ofertas.get(posicao);
				o.setLancamentoLivroCaixa(lancamento);

				sessao.save(o);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void salvarLancamentoContribuicao(LancamentoLivroCaixa lancamento, List<Contribuicao> contribuicoes) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < contribuicoes.size(); posicao++) {
				Contribuicao contribuicao = contribuicoes.get(posicao);
				//contribuicao.setLancamentoLivroCaixa(lancamento);

				sessao.save(contribuicao);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	public void salvarLancamentoCampanha(LancamentoLivroCaixa lancamento, List<Campanha> campanhas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < campanhas.size(); posicao++) {
				Campanha campanha = campanhas.get(posicao);
				//campanha.setLancamentoLivroCaixa(lancamento);

				sessao.save(campanha);
			}
			transacao.commit();
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
	public List<LancamentoLivroCaixa> buscarLancamentoOfertaPorGrupo(String nome) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select l from Oferta l where l.grupo.nome=:nome");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			// query.setDate("data", data);
			query.setString("nome", nome);
			// BigDecimal somaOferta=(BigDecimal) query.uniqueResult();
			List<LancamentoLivroCaixa> lancamentos = query.list();
			return lancamentos;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<LancamentoLivroCaixa> buscarLancamentoPorCodigoDoGrupo(Long codigo) {
		List<LancamentoLivroCaixa> lancamentos = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
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
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LancamentoLivroCaixa> buscarLancamentoPorGrupo(Long codigo) {
		List<LancamentoLivroCaixa> lancamentos = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from LancamentoLivroCaixa where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			lancamentos = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return lancamentos;
	}



	public void salvarLancamentoDespesaCrianca(LancamentoLivroCaixa lancamento, List<DespesaCrianca> listaDespesaCriancaLancamento) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaDespesaCriancaLancamento.size(); posicao++) {
				DespesaCrianca despesaCrianca = listaDespesaCriancaLancamento.get(posicao);
				//despesaEBF.setLancamentoLivroCaixa(lancamento);
				sessao.save(despesaCrianca);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
		
	}



	public void registrarContasParaPagar(LancamentoLivroCaixa lancamento, List<ContasParaPagar> listaContasPagarLancamento) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaContasPagarLancamento.size(); posicao++) {
				ContasParaPagar contasPagar = listaContasPagarLancamento.get(posicao);
				//contasPagar.setLancamentoLivroCaixa(lancamento);

				sessao.save(contasPagar);
			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
		
	}

	
	//ESTES  M�TODO DERAM  CERTO FUNCIONA PERFEITAMENTE
	
	//exemplo de chamada destes metodos
	/*public void mostrarTotalDespesa() {
	 * Long codigo;
	 * LancamentoDao ldao=new LancamentoDao();
		Grupo grupo=gdao.buscar(codigo);
		valorReceita=ldao.mostrarReceitaPorGrupo(grupo.getCodigo());
		valorDespesa=ldao.mostrarDespesaPorGrupo(grupo.getCodigo());
		valorSaldo=ldao.mostrarSaldoPorGrupo(grupo.getCodigo());
		Utils.mostrarMensagemJsfSucesso("total das receitas "+valorReceita+"\ntotal das despesa "+valorDespesa+
				"saldo "+valorSaldo);
		if(valorDespesa.compareTo(valorReceita)==1) {
			Utils.mostrarMensagemJsfSucesso("saldo negativo");
		}else {
			Utils.mostrarMensagemJsfSucesso("saldo positivo");
		}
		if(valorReceita==null || valorDespesa==null) {
			Utils.mostrarMensagemJsfSucesso("n�o existe receita ou despesa para este lancamento");
		}
	}*/
	
	//1 CALCULDO DA RECEITA
		
	
	@SuppressWarnings("unchecked")
	public BigDecimal mostrarReceitaPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita)from LancamentoLivroCaixa where grupo.codigo=:codigo");
			busca.setLong("codigo",grupoCodigo);
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	//CALCULO DA DESPESA
	@SuppressWarnings("unchecked")
	public BigDecimal mostrarDespesaPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(despesa)from LancamentoLivroCaixa where grupo.codigo=:codigo");
			busca.setLong("codigo",grupoCodigo);
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}

	//CALCULO DO SALDO
	public BigDecimal mostrarSaldoPorGrupo(Long grupoCodigo) {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from LancamentoLivroCaixa where grupo.codigo=:codigo");
			busca.setLong("codigo",grupoCodigo);
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
}
