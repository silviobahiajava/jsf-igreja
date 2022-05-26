package daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.testejsf.utils.HibernateUtil;
import entidades.Usuario;

public class UsuarioDao extends GenericDaoHibernate<Usuario> {
	

	/*public Usuario autenticarUsuario(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.add(Restrictions.eq("cpf", cpf));

			// SimpleHash hash = new SimpleHash("md5", senha);
			// consulta.add(Restrictions.eq("senha", hash.toHex()));

			consulta.add(Restrictions.eq("senha", senha));
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	*/
	
	
	/*@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		Session sessao = br.com.testejsf.utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = ((Criteria) sessao).createCriteria(Usuario.class);
			List<Usuario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}*/
	
	
	
	
	
	
	//=============================================================================================
	//=======================================================================================
	
	/*public EntityManager gerenciarEntidades() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory(null);
		 EntityManager em=emf.createEntityManager();
		 return em;
	}*/
	
	
	
	
	public List<Usuario>findAllUsuarios(){
		Session sessao =utils.HibernateUtil.getFabricaDeSessoes().openSession();
		@SuppressWarnings("unchecked")
		List<Usuario>funcoes=(List<Usuario>) sessao.createQuery("from Usuario");
		return funcoes;
	}
	
	
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = ((Object) sessao).createCriteria(Usuario.class);
			
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			////SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", senha));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	
	
	
	
}
