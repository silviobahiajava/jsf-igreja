package daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import entidades.AulaEscolaDominical;
import entidades.Campanha;
import entidades.CampanhaDaIgreja;
import entidades.CampanhaDasCriancas;
import entidades.CampanhaDoPeCriancas;
import entidades.Compra;
import entidades.CompraDaMissao;
import entidades.CompraDasCriancas;
import entidades.CompraDosVaroes;
import entidades.CompraIgreja;
import entidades.ContasParaPagar;
import entidades.Contribuicao;
import entidades.ContribuicaoDaIgreja;
import entidades.ContribuicaoDasCriancas;

import entidades.DespesaCrianca;
import entidades.DespesaDaIgreja;
import entidades.DespesasPagasDaIgreja;
import entidades.Dizimo;
import entidades.DizimoDaIgreja;
import entidades.DizimoLancado;
import entidades.Lancamento;
import entidades.Membro;
import entidades.Oferta;
import entidades.OfertaDaIgreja;
import entidades.OfertaDasCriancas;
import entidades.OfertaDosVaroes;
import entidades.OfertaEscolaDominical;
import filtros.AniversarioFilter;
import filtros.LancamentoIgrejaFilter;

public class LancamentoDao extends GenericDaoHibernate<Lancamento> {
/*
 * author SILVIO SLVA DE SOUZA
 * 
 */
	
	public Long buscaUlitmoCodigo() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Criteria c=sessao.createCriteria(Lancamento.class);
			Long codigo=(Long) c.setProjection(Projections.max("codigo")).uniqueResult();
			return codigo;
			
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	//deu certo passo como parametro o primeiro dia e o ultimo dia do mes atual fatos que
	//aconteceram no mes atual e ano atual
	
	//Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from Lancamento where grupo.codigo=:codigo");
	//busca.setLong("codigo",grupoCodigo);
public BigDecimal mostrarSaldoMensalAPorGrupo(Long grupoCodigo,LancamentoIgrejaFilter filter) {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from Lancamento where grupo.codigo=:codigo "
					+ " and data between :dataInicial and :dataFinal");
			busca.setLong("codigo",grupoCodigo);
			busca.setDate("dataInicial", filter.getDataInical());
			busca.setDate("dataFinal",filter.getDataFinal());
			
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}







public BigDecimal mostrarSaldoAtualPorGrupo(Long grupoCodigo,Date data) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from Lancamento where grupo.codigo=:codigo "
				+ " and data  =:data");
		busca.setLong("codigo",grupoCodigo);
		busca.setDate("data", data);
		
		
		BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}




public BigDecimal mostrarReceitaAtualPorGrupo(Long grupoCodigo,Date data) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select sum(receita) from Lancamento where grupo.codigo=:codigo "
				+ " and data  =:data");
		busca.setLong("codigo",grupoCodigo);
		busca.setDate("data", data);
		
		
		BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}

public BigDecimal mostrarDespesaAtualPorGrupo(Long grupoCodigo,Date data) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select sum(despesa) from Lancamento where grupo.codigo=:codigo "
				+ " and data  =:data");
		busca.setLong("codigo",grupoCodigo);
		busca.setDate("data", data);
		
		
		BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}





















public BigDecimal mostrarReceitaMensalAPorGrupo(Long grupoCodigo,LancamentoIgrejaFilter filter) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select sum(receita) from Lancamento where grupo.codigo=:codigo "
				+ " and data between :dataInicial and :dataFinal");
		busca.setLong("codigo",grupoCodigo);
		busca.setDate("dataInicial", filter.getDataInical());
		busca.setDate("dataFinal",filter.getDataFinal());
		
		BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}

public BigDecimal mostrarDespesaMensalAPorGrupo(Long grupoCodigo,LancamentoIgrejaFilter filter) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select sum(despesa) from Lancamento where grupo.codigo=:codigo "
				+ " and data between :dataInicial and :dataFinal");
		busca.setLong("codigo",grupoCodigo);
		busca.setDate("dataInicial", filter.getDataInical());
		busca.setDate("dataFinal",filter.getDataFinal());
		
		BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamento>buscarLancamentoMensalPorGrupo(LancamentoIgrejaFilter filter,Long codigo){
		
		List<Lancamento>lancamentos=new ArrayList<Lancamento>();
		StringBuilder sbd=new StringBuilder();
		//sbd.append("select membro from Membro membro ");
		sbd.append("from Lancamento ");
		if(filter.getDataInical()!=null && filter.getDataFinal()!=null){
			//sbd.append(" where day(membro.dataNascimento) between :dataInicial and :dataFinal ");
			sbd.append(" where data between :dataInicial and :dataFinal ");
			sbd.append("and grupo.codigo =:codigo ");
		}
		//sbd.append(" order by day(membro.dataNascimento) ");
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query consulta=sessao.createQuery(sbd.toString());
			consulta.setDate("dataInicial", filter.getDataInical());
			consulta.setDate("dataFinal",filter.getDataFinal());
			consulta.setLong("codigo", codigo);
			lancamentos=consulta.list();
			return lancamentos;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
	/*public long buscarquantidadeRegistros() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select count(*) from Lancamento");
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
			Query query = sessao.createQuery("select sum(receita) from Lancamento");
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
			Query query = sessao.createQuery("select sum(despesa) from Lancamento");
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
			Query query = sessao.createQuery("select sum(despesa) -sum(receita) from Lancamento");
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
			Query query = sessao.createQuery("select sum(despesa)  from Lancamento lancamento where lancamento.grupo.codigo=:codigo");
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
			Query query = sessao.createQuery("select sum(despesa)  from Lancamento where grupo.codgio=:codigo");
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
					.createQuery("select sum(despesa) -sum(receita) from Lancamento l where l.nome=:nome");
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
	
	
	
	
	
	public List<Lancamento> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Lancamento.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			@SuppressWarnings("unchecked")
			List<Lancamento> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	// saldo por ggupo

	
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamento> buscarSaldoPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
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

	public void salvarLancamentoCompra(Lancamento lancamento, List<Compra> compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				Compra c = compras.get(posicao);
				c.setLancamento(lancamento);

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
	
	
	
	public void salvarLancamentoDespesaPagaDaIgreja(Lancamento lancamento, List<DespesaDaIgreja> despesaspagas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < despesaspagas.size(); posicao++) {
				DespesaDaIgreja despesa = despesaspagas.get(posicao);
				despesa.setLancamento(lancamento);

				sessao.save(despesa);
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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	public void salvarLancamentoOferta(Lancamento lancamento, List<Oferta> ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				Oferta o = ofertas.get(posicao);
				o.setLancamento(lancamento);

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
	
	public void salvarLancamentoOfertaEscolaDominical(Lancamento lancamento, List<AulaEscolaDominical> aulas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < aulas.size(); posicao++) {
				AulaEscolaDominical aula = aulas.get(posicao);
				//o.setLancamento(lancamento);
				aula.setLancamento(lancamento);
				sessao.save(aula);
				
				//sessao.save(o);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void salvarLancamentoDespesaCrianca(Lancamento lancamento, List<DespesaCrianca> listaDespesasCrianca) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaDespesasCrianca.size(); posicao++) {
				DespesaCrianca despesasCrianca = listaDespesasCrianca.get(posicao);
				despesasCrianca.setLancamento(lancamento);
				

				sessao.save(despesasCrianca);
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

	public void salvarLancamentoOfertas(Lancamento lancamento, List<Oferta> ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				Oferta o = ofertas.get(posicao);
				o.setLancamento(lancamento);

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

	public void salvarLancamentoContribuicao(Lancamento lancamento, List<Contribuicao> contribuicoes) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < contribuicoes.size(); posicao++) {
				Contribuicao contribuicao = contribuicoes.get(posicao);
				contribuicao.setLancamento(lancamento);

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

	
	public void salvarLancamentoCampanha(Lancamento lancamento, List<Campanha> campanhas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < campanhas.size(); posicao++) {
				Campanha campanha = campanhas.get(posicao);
				campanha.setLancamento(lancamento);

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
	public List<Lancamento> buscarLancamentoPorGrupo(String nome) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select l from Lancamento l where l.grupo.nome=:nome");
			// long contador=(long) query.uniqueResult();
			// query.setString("nome",nome);
			// query.setDate("data", data);
			query.setString("nome", nome);
			// BigDecimal somaOferta=(BigDecimal) query.uniqueResult();
			List<Lancamento> lancamentos = query.list();
			return lancamentos;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Lancamento> buscarLancamentoPorCodigo(Long codigo) {
		List<Lancamento> lancamentos = null;

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
	public List<Lancamento> buscarLancamentoPorGrupo(Long codigo) {
		List<Lancamento> lancamentos = null;

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



	



	public void registrarContasParaPagar(Lancamento lancamento, List<ContasParaPagar> listaContasPagarLancamento) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaContasPagarLancamento.size(); posicao++) {
				ContasParaPagar contasPagar = listaContasPagarLancamento.get(posicao);
				contasPagar.setLancamento(lancamento);

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
	
	//1 CALCULDO DA RECEITA POR GRUPO
		
	
	public BigDecimal mostrarReceitaPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita)from Lancamento where grupo.codigo=:codigo");
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
	public BigDecimal mostrarDespesaPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(despesa)from Lancamento where grupo.codigo=:codigo");
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
			Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from Lancamento where grupo.codigo=:codigo");
			busca.setLong("codigo",grupoCodigo);
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	
	
public BigDecimal mostrarSaldoPorGrupoEData(Long grupoCodigo,Date data) {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select saldo from Lancamento where grupo.codigo=:codigo and data=:data");
			busca.setLong("codigo",grupoCodigo);
			busca.setDate("data", data);
				BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	



public BigDecimal mostrarSaldoPorCodigo(Long codigo) {
	
	Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
	try {
		Query busca=sessao.createQuery("select saldo from Lancamento where codigo=:codigo ");
		busca.setLong("codigo",codigo);
		
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
		return resultado;
	} catch (RuntimeException erro) {
		throw erro;

	} finally {
		sessao.close();
	}
}



	//CALCULO GERAL
	
	public BigDecimal mostrarReceitaGeral() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita)from Lancamento ");
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	//CALCULO DA DESPESA
	public BigDecimal mostrarDespesaGeral() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(despesa)from Lancamento ");
			
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}

	//CALCULO DO SALDO
	public BigDecimal mostrarSaldoGeral() {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select sum(receita) - sum(despesa)from Lancamento ");
			BigDecimal resultado=(BigDecimal) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	//OPERA��ES POR GRUPO
	
	@Autowired
	public void salvarLancamentoCompraDaIgreja(Lancamento lancamento, List<CompraIgreja> compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				CompraIgreja compra = compras.get(posicao);
				compra.setLancamento(lancamento);

				sessao.save(compra);
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
	
	
	
	public void salvarLancamentoDespesasPagasDaIgreja(Lancamento lancamento, List<DespesasPagasDaIgreja> despesas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < despesas.size(); posicao++) {
				DespesasPagasDaIgreja despesa = despesas.get(posicao);
				
				despesa.setLancamento(lancamento);
				sessao.save(despesa);
				
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
	
	
	public void salvarLancamentoCampanhaDaIgreja(Lancamento lancamento,List<CampanhaDaIgreja>lista) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < lista.size(); posicao++) {
				CampanhaDaIgreja campanha = lista.get(posicao);
				
				campanha.setLancamento(lancamento);
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
	
	
	@Autowired
	public void salvarLancamentoCompraDasVaroes(Lancamento lancamento, List<CompraDosVaroes> compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				CompraDosVaroes compra = compras.get(posicao);
				compra.setLancamento(lancamento);

				sessao.save(compra);
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
	
	public void salvarLancamentoCompraDasCriancas(Lancamento lancamento,List<CompraDasCriancas>compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				CompraDasCriancas compra = compras.get(posicao);
				compra.setLancamento(lancamento);

				sessao.save(compra);
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
	
	
	public void registrarLancamentoDespesaCrianca(Lancamento lancamento,List<DespesaCrianca>despesas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < despesas.size(); posicao++) {
				DespesaCrianca despesa = despesas.get(posicao);
				despesa.setLancamento(lancamento);

				sessao.save(despesa);
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
	
	
	
	
	public void salvarLancamentoOfertaDasCriancas(Lancamento lancamento,List<OfertaDasCriancas>ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				OfertaDasCriancas oferta = ofertas.get(posicao);
				
				oferta.setLancamento(lancamento);

				sessao.save(oferta);
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
	
	
	public void salvarLancamentoOfertaDosVaroes(Lancamento lancamento,List<OfertaDosVaroes>ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				OfertaDosVaroes oferta = ofertas.get(posicao);
				
				oferta.setLancamento(lancamento);

				sessao.save(oferta);
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
	
	
	
	@Autowired
	public void salvarLancamentoCompraDaMissao(Lancamento lancamento, List<CompraDaMissao> compras) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < compras.size(); posicao++) {
				CompraDaMissao compra = compras.get(posicao);
				compra.setLancamento(lancamento);

				sessao.save(compra);
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
	
	
	//ofertas no livro caixa da igreja
	public void salvarLancamentoOfertaDaIgreja(Lancamento lancamento,List<OfertaDaIgreja>ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				OfertaDaIgreja oferta = ofertas.get(posicao);
				
				oferta.setLancamento(lancamento);

				sessao.save(oferta);
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
	
	public void salvarLancamentoDeOfertaDaEscolaDominical(Lancamento lancamento,List<OfertaEscolaDominical>ofertas) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < ofertas.size(); posicao++) {
				OfertaEscolaDominical oferta = ofertas.get(posicao);
				
				oferta.setLancamento(lancamento);

				sessao.save(oferta);
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
	
	
	//contribuicoes no livro caixa da igreja
	public void salvarLancamentoContribuicaoDaIgreja(Lancamento lancamento,List<ContribuicaoDaIgreja>contribuicoes) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao <contribuicoes.size(); posicao++) {
				ContribuicaoDaIgreja contribuicao = contribuicoes.get(posicao);
				
				contribuicao.setLancamento(lancamento);

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
	
	
	//dizimo no livro caixa da igreja
	public void salvarLancamentoDizimoDaIgreja(Lancamento lancamento,List<DizimoDaIgreja>dizimos) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < dizimos.size(); posicao++) {
				DizimoDaIgreja dizimo = dizimos.get(posicao);
				dizimo.setTipodizimo("LANCADO");
				dizimo.setLancamento(lancamento);

				sessao.save(dizimo);
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
	
	
	public void registrarDizimosLancados(List<DizimoLancado>dizimoslancados) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			
			for (int posicao = 0; posicao < dizimoslancados.size(); posicao++) {
				DizimoLancado dizimo = dizimoslancados.get(posicao);
				
				

				sessao.save(dizimo);
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
	
	
	
	
	//este m�todo deu certo  no JUnit
	@SuppressWarnings("unchecked")
	public Lancamento buscarLancamentoPorData(Date data,Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
			Query query = sessao.createQuery("from Lancamento where data=? and grupo.codigo=?");
			query.setParameter(0,data);
			query.setParameter(1, codigo);
			Lancamento lancamento=(Lancamento) query.uniqueResult();
			return lancamento;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamento> buscarLancamentoPorData2(Date data,Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			//Query query = sessao.createQuery("select nome from membro where day(dataNascimento)=:dia and  month(dataNascimento)=:mes");
			Query query = sessao.createQuery("from Lancamento where data=? and grupo.codigo=?");
			query.setParameter(0,data);
			query.setParameter(1, codigo);
			List<Lancamento>lancamentos=query.list();
			return lancamentos;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public void abrirCaixa(Lancamento lancamento) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
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
	
	
	public void fecharCaixa(Lancamento lancamento) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			
			
			
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
	
	
	
	
	//TRABALHANDO COM INTERVALO DE DATAS
	@SuppressWarnings("unchecked")
	public List<Lancamento> buscarLancamentoPorGrupoIntervaloDatas(LancamentoIgrejaFilter filtro,Long codigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		StringBuilder sb=new StringBuilder();
		sb.append("select l from Lancamento l");
		List<Lancamento>lancamentos=null;
		if(filtro.getDataInical()!=null && filtro.getDataFinal()!=null){
			//sb.append("where )
		}
		try {
			
			return lancamentos;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}



	public void salvarLancamentoContribuicaoDasCriancas(Lancamento lancamento,
			List<ContribuicaoDasCriancas> listaDeContribuicoesPraLancamento) {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao <listaDeContribuicoesPraLancamento.size(); posicao++) {
				ContribuicaoDasCriancas contribuicao = listaDeContribuicoesPraLancamento.get(posicao);
				
				contribuicao.setLancamento(lancamento);

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

		
		
		// TODO Auto-generated method stub
		
	}



	public void salvarLancamentoCampanhaDasCriancas(Lancamento lancamento,
			List<CampanhaDasCriancas> listaCampanhaLancamento) {
		// TODO Auto-generated method stub
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaCampanhaLancamento.size(); posicao++) {
				CampanhaDasCriancas campanha = listaCampanhaLancamento.get(posicao);
				
				campanha.setLancamento(lancamento);
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
	

	public void  salvarLancamentoCampanhaDoPeCriancas(Lancamento lancamento,
			List<CampanhaDoPeCriancas> listaContribuicoesPraLancamento) {
		
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(lancamento);
			for (int posicao = 0; posicao < listaContribuicoesPraLancamento.size(); posicao++) {
				CampanhaDoPeCriancas campanha = listaContribuicoesPraLancamento.get(posicao);
				campanha.setLancamento(lancamento);
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

}
