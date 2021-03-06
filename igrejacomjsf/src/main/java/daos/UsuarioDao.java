package daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.FuncaoUsuario;
import entidades.Usuario;
import utils.HibernateUtil;

public class UsuarioDao extends GenericDaoHibernate<Usuario> {
	public Usuario autenticar(String login, String senha) {
		Session sessao = utils.HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Query query = sessao.createQuery("select Usuario  from Usuario u where u.login=:login and u.senha=:senha");
			// long contador=(long) query.uniqueResult();
			query.setString("login", login);
			query.setString("senha", senha);
			Usuario user = (Usuario) query.uniqueResult();
			return user;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Usuario autenticarUsuario(String cpf, String senha) {
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
	
	
	public List<Usuario>findAllUsuarios(){
		Session sessao =utils.HibernateUtil.getFabricaDeSessoes().openSession();
		@SuppressWarnings("unchecked")
		List<Usuario>funcoes=(List<Usuario>) sessao.createQuery("from Usuario");
		return funcoes;
	}
}
