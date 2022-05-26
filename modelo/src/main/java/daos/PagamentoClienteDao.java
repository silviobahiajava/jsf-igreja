package daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Pagamento;
import entidades.PagamentoCliente;
import entidades.Parcela;
import entidades.ParcelaCliente;

public class PagamentoClienteDao extends GenericDaoHibernate<PagamentoCliente>{
	public void salvarPagamento(PagamentoCliente pagamento,List<ParcelaCliente>parcelas) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(pagamento);
			for (int posicao = 0; posicao < parcelas.size(); posicao++) {
				ParcelaCliente parcela = parcelas.get(posicao);
				parcela.setPagamento(pagamento);
				sessao.save(parcela);
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
	public List<ParcelaCliente>buscarParcelasComVencimentoPraHoje(Date dataVencimentoDaParcela){
		
		List<ParcelaCliente>parcelas=new ArrayList<ParcelaCliente>();
		StringBuilder sbd=new StringBuilder();
		//sbd.append("select membro from Membro membro ");
		sbd.append("from ParcelaCliente ");
		if(dataVencimentoDaParcela!=null){
			//sbd.append(" where day(membro.dataNascimento) between :dataInicial and :dataFinal ");
			sbd.append(" where dataVencimentoDaParcela  =:dataVencimentoDaParcela");
		}
		//sbd.append(" order by day(membro.dataNascimento) ");
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query consulta=sessao.createQuery(sbd.toString());
			consulta.setDate("dataVencimentoDaParcela",dataVencimentoDaParcela);
			
			parcelas=consulta.list();
			return parcelas;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
