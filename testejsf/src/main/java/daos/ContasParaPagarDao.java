package daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Compra;
import entidades.ContasParaPagar;

public class ContasParaPagarDao extends GenericDaoHibernate<ContasParaPagar>{
	
	@SuppressWarnings("unchecked")
	public List<ContasParaPagar> buscarContasPorGrupo(Long codigo) {
		List<ContasParaPagar> contas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from Compra where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			contas = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return contas;
	}
	
}
