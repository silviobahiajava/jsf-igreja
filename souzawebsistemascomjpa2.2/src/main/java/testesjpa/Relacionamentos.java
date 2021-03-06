package testesjpa;



import java.util.List;

import javax.swing.JOptionPane;

import cadastrocursos.model.Aluno;
import cadastrocursos.model.Curso;
import cadastrocursos.model.Modulo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

public class Relacionamentos {

	 public static  EntityManager conectar() {
		   EntityManagerFactory emf = Persistence
	                .createEntityManagerFactory("Cursos-PU");
	        EntityManager em = emf.createEntityManager();
	        return em;
	 }
	 
    public static void main(String[] args) {
       EntityManager em = conectar();
        //cadastrarcursomodulo(em);
       // listarAlunos(em);
      //listarModulos(em);
       /// consultarAlunoPorCodigo(em,8);
      //consultarModuloDoCurso(em,1);
      // listarNomeDosModulos(em);
      // fazerProjecoes(em);
       ///testarjoin(em, 1);
       //testarCriteria(em);
       Modulo modulo = getModuloPorNome(em,"php");
       System.out.println("modulo "+modulo.getNome()+"  curso " +modulo.getCurso().getNome());
       
    }
    public static void cadastrarcursomodulo(EntityManager em) {
    	//exemplo de relacionamento pai filho
    	//um pai tem muitos filhos
    	//primeiro instancia o pai
    	//defini os atributos do pai
    	//inicia a transa??o
    	//persiste o pai
    	//num laco de repeti??o for  instancia os filhos define os atributos e persiste o filho
    	//fora do la?o de repeti??o d? o commit
    	//mostra mensagem do cadastro
    	Curso curso=new Curso();
    	//Modulo modulo = new Modulo();
    	String nomeCurso = JOptionPane.showInputDialog("nome do curso");
    	curso.setNome(nomeCurso);
    	 em.getTransaction().begin();
    	em.persist(curso);
    	String nomeModulo="";
    	for(int i=0;i<3;i++) {
    		Modulo modulo=new Modulo();
    	     nomeModulo=JOptionPane.showInputDialog("nome do m?dulo");
    	     modulo.setNome(nomeModulo);
    	     modulo.setCurso(curso);
    	     em.persist(modulo);
    	}
    	em.getTransaction().commit();
    		
    	JOptionPane.showMessageDialog(null,"cadastro feito com sucesso");	
    		  
    		  
    		 
    		 
    		  
    		

    	
    
    	 
        

         //curso.setModulos(Arrays.asList(modulo));

       

         
        
         em.getTransaction().commit();

         em.close();
         em.close();
    }
    
    public static void listarAlunos(EntityManager em) {
    	String jpql="select a from Aluno a";
    	TypedQuery<Aluno> typedquery = em.createQuery(jpql,Aluno.class);
    	List<Aluno> lista = typedquery.getResultList();
    	
    	for (Aluno aluno : lista) {
    		System.out.println(aluno.getId()+"? aluno ="+aluno.getNome());
		}
    }
    
    public static void consultarAlunoPorCodigo(EntityManager em,Integer id) {
    	String jpql="select a from Aluno a where a.id ='"+id+"' ";
    	TypedQuery<Aluno> typedQuery = em.createQuery(jpql,Aluno.class);
    	Aluno aluno = typedQuery.getSingleResult();
    	System.out.println("aluno escolhido "+aluno.getNome());
    }
    public static void consultarModuloDoCurso(EntityManager em,Integer id) {
    	//Modulo       x         Curso
    	//Many          to        One
    	//muitos modulos para um curso
    	//m representa a classe Modulo
    	//m.curso acessando o atributo curso da classe Modulo
    	//onde o id do Modulo seja igual ao id passado na pesquisa
    	String jpql="select m.curso from Modulo m where m.id='"+id+"' ";
    	TypedQuery<Curso> typedQuery = em.createQuery(jpql,Curso.class);
    	Curso curso = typedQuery.getSingleResult();
    	System.out.println("curso escolhido "+curso.getNome());
    }
    public static void listarModulos(EntityManager em) {
    	String jpql="select m from Modulo m";
    	TypedQuery<Modulo> typedquery = em.createQuery(jpql,Modulo.class);
    	List<Modulo> lista = typedquery.getResultList();
    	
    	for (Modulo modulo : lista) {
    		System.out.println(modulo.getId()+"? modulo ="+modulo.getNome());
		}
    }
    
    public static void listarNomeDosModulos(EntityManager em) {
    	String jpql="select m.nome from Modulo m";
    	TypedQuery<String> TQNomes = em.createQuery(jpql,String.class);
    	List<String> ListaNomes = TQNomes.getResultList();
    	for (String nome : ListaNomes) {
			System.out.println(nome+"\n");
		}
    }
    
    public static void fazerProjecoes(EntityManager em) {
    	String jpql="select nome,curso from Modulo";
    	 TypedQuery<Object[]> tqobj = em.createQuery(jpql,Object[].class);
    	 List<Object[]> resultList = tqobj.getResultList();
    	 for (Object[] obj : resultList) {
			System.out.println(obj.toString());
		}
    	 
    }
    
   public static void testarjoin(EntityManager em,Integer id) {
	   String jpql="select m from Modulo m join m.curso c where c.id='"+id+"' ";
	   TypedQuery<Modulo> tqModulo = em.createQuery(jpql, Modulo.class);
	   List<Modulo> ListModulo = tqModulo.getResultList();
	   for (Modulo modulo : ListModulo) {
		System.out.println(modulo.getNome());
	}
   }
   //funciona
   public static void testarCriteria(EntityManager em) {
	   CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Object> cq = cb.createQuery();
	Root<Aluno> modulo = cq.from(Aluno.class);
	//cq.where(cb.);
	 Query query = em.createQuery(cq);
	 @SuppressWarnings("unchecked")
	List <Aluno>result = query.getResultList();
	 for (Aluno modulo2 : result) {
		 System.out.println(modulo2.getNome()+"\n");
		
	}
	   
   }
   
   public static void testarCriteria2(EntityManager em) {
	   CriteriaBuilder cb = em.getCriteriaBuilder();
	   CriteriaQuery<Modulo> q = cb.createQuery(Modulo.class);
	   Root<Modulo> c = q.from(Modulo.class);
	   ParameterExpression<Integer> p = cb.parameter(Integer.class);
	  // q.select(c).where(cb.gt("curso", p));
   }
   
   //funciona
   public static Modulo getModuloPorNome(EntityManager em, String nome) {
	    TypedQuery<Modulo> query = em.createQuery(
	        "SELECT m FROM Modulo m WHERE m.nome = :nome", Modulo.class);
	    return query.setParameter("nome", nome).getSingleResult();
	  } 
}
