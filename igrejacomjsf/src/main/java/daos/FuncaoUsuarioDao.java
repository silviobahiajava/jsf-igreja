package daos;

import java.util.List;

import org.hibernate.Session;

import entidades.FuncaoUsuario;

public class FuncaoUsuarioDao extends GenericDaoHibernate<FuncaoUsuario> {
	public List<FuncaoUsuario>findAllFuncoes(){
		Session sessao =utils.HibernateUtil.getFabricaDeSessoes().openSession();
		@SuppressWarnings("unchecked")
		List<FuncaoUsuario>funcoes=(List<FuncaoUsuario>) sessao.createQuery("from FuncaoUsuario");
		return funcoes;
	}
}
