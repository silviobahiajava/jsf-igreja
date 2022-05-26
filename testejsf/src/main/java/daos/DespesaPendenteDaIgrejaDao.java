package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.DespesaDaIgreja;
import entidades.DespesasPagasDaIgreja;
import entidades.DespesasPendentesDaIgreja;

public class DespesaPendenteDaIgrejaDao extends GenericDaoHibernate<DespesasPendentesDaIgreja>{
	@SuppressWarnings("unchecked")
	public List<DespesasPendentesDaIgreja> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DespesasPendentesDaIgreja.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<DespesasPendentesDaIgreja> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
