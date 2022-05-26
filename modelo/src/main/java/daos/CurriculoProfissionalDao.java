package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import entidades.CurriculoProfissional;
import entidades.CursoProfissional;
import entidades.ExperienciaProfissional;
import entidades.Lancamento;

public class CurriculoProfissionalDao extends GenericDaoHibernate<CurriculoProfissional>{
	
	public Long buscaUlitmoCodigo() {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Criteria c=sessao.createCriteria(CurriculoProfissional.class);
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
	
	
	
	public CurriculoProfissional mostrarCurriculoPorEmpresa(Long empresaCodigo) {
		
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query busca=sessao.createQuery("select * from CurriculoProfissional where experienciaProfissional.codigo=:codigo ");
			busca.setLong("codigo",empresaCodigo);
			CurriculoProfissional resultado=(CurriculoProfissional) busca.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			sessao.close();
		}
	}
	
	public void salvarCurriculoProfissional(CurriculoProfissional curriculo, List<ExperienciaProfissional> listaExperiencia,List<CursoProfissional>listaCurso) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(curriculo);
			
			for (int posicao = 0; posicao < listaExperiencia.size(); posicao++) {
				ExperienciaProfissional experiencia = listaExperiencia.get(posicao);
				experiencia.setCurriculo(curriculo);
				sessao.save(experiencia);
			}
			
			
			for (int posicao = 0; posicao < listaCurso.size(); posicao++) {
				CursoProfissional curso = listaCurso.get(posicao);
				curso.setCurriculo(curriculo);
				sessao.save(curso);
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
