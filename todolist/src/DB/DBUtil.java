package DB;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import todolist.Todolist;
import todolist.Todostatus;
import todolist.Todouser;

public class DBUtil {
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("todolist");
				public static EntityManagerFactory getEmFactory() {
					return emf;
				}

				public static Todouser selectByName(String name) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					String query = "SELECT e FROM Todouser e WHERE e.username = '" + name + "'";
					TypedQuery<Todouser> q = em.createQuery(query, Todouser.class);
					try {
						Todouser user = q.getSingleResult();
						return user;
					} catch (Exception e) {
						return null;
					} finally {
						em.close();
					}
				}
				
				public static void deleteitemtodi(long itemid) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					EntityTransaction trans = em.getTransaction();
					String query = "Delete from Todolist t  where t.id = " + itemid;
					TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);

					trans.begin();
					try {
						q.executeUpdate();
						trans.commit();
					} catch (Exception e) {

					} finally {
						em.close();
					}
				}
				
				public static Todouser selectByEmail(String email) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					String query = "SELECT e FROM Todouser e WHERE e.email = '" + email + "'";
					TypedQuery<Todouser> q = em.createQuery(query, Todouser.class);
					try {
						Todouser user = q.getSingleResult();
						return user;
					} catch (Exception e) {
						return null;
					} finally {
						em.close();
					}
				}
				
				public static Todostatus getstatus(int status) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					String query = "SELECT e FROM Todostatus e WHERE e.id = " + status + "";
					TypedQuery<Todostatus> q = em.createQuery(query, Todostatus.class);
					try {
						Todostatus retstatus = q.getSingleResult();
						System.out.println(retstatus.getStatus());
						return retstatus;
					} catch (Exception e) {
						return null;
					} finally {
						em.close();
					}
				}
				
public static void insertitem(Todolist item) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					EntityTransaction trans = em.getTransaction();
					trans.begin();
					try {
						em.persist(item);
						trans.commit();
					} catch (Exception e) {
						System.out.println(e);
						//trans.rollback();
					} finally {
						em.close();
					}
				}


//HERE TMOrrow
public static void updateitem(Todolist item) {
	EntityManager em = DBUtil.getEmFactory().createEntityManager();
	EntityTransaction trans = em.getTransaction();
	trans.begin();
	String sql = "Update Todolist c set c.itemInstock= c.itemInstock - 1 where c.itemId = "
			+ item.getId();
	TypedQuery<Todolist> query;

	query = em.createQuery(sql, Todolist.class);
	try {
		query.executeUpdate();
		trans.commit();
	} catch (Exception e) {
		System.out.println(e);
		//trans.rollback();
	} finally {
		em.close();
	}
}


	
public static void insert(Todouser user) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					EntityTransaction trans = em.getTransaction();
					trans.begin();
					try {
						System.out.print(""+user.getEmail());
						System.out.print(""+user.getPassword());
						System.out.print(""+user.getUsername());
						em.persist(user);
						trans.commit();
					} catch (Exception e) {
						System.out.println(e);
						//trans.rollback();
					} finally {
						em.close();
					}
				}
		
public static List<Todolist> getCompletedList(Todouser user, Todostatus status) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					String query = "SELECT t FROM Todolist t WHERE t.todouser=:user and t.todostatus=:status";
					System.out.println(query);
					List<Todolist> todo = null ;
					TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
					q.setParameter("user", user);
					q.setParameter("status", status);
					try {
						todo = q.getResultList();

					} catch (Exception e) {
					} finally {
						em.close();
					}
					return todo;
				}

				public static List<Todolist> getToDoList(Todouser user) {
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					
					String query = "SELECT t FROM Todolist t WHERE t.todouser=:user";
					System.out.println(query);
					List<Todolist> todo = null ;
					TypedQuery<Todolist> q = em.createQuery(query, Todolist.class);
					q.setParameter("user", user);
					try {
						todo= q.getResultList();
					
					} catch (Exception e) {

						System.out.println(e);
					} finally {
						em.close();
					}
					return todo;
				}
				
}
