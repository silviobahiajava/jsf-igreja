package daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;



import entidades.DespesaCrianca;

public class DespesaCriancaDao extends GenericDaoHibernate<DespesaCrianca>{
	@SuppressWarnings("unchecked")
	public List<DespesaCrianca> buscarDespesasDasCriancasPorGrupo(Long codigo) {
		List<DespesaCrianca> despesasDasCriancas = null;

		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		if (codigo != null) {

		}
		try {
			Query consulta = sessao.createQuery("from DespesaCrianca where grupo.codigo=? ");
			consulta.setParameter(0, codigo);
			despesasDasCriancas = consulta.list();
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
		return despesasDasCriancas;
	}
	
	
	
}
