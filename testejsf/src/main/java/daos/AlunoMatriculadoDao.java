package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.AlunosMatriculadosEscolaDominical;
import entidades.AulaEscolaDominical;

public class AlunoMatriculadoDao extends GenericDaoHibernate<AlunosMatriculadosEscolaDominical> {

	@SuppressWarnings("unchecked")
	public List<AlunosMatriculadosEscolaDominical> buscarPorClasse(Long classeCodigo) {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(AlunosMatriculadosEscolaDominical.class);
			consulta.add(Restrictions.eq("classe.codigo", classeCodigo));
			// consulta.addOrder(Order.asc("nome"));
			List<AlunosMatriculadosEscolaDominical> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
