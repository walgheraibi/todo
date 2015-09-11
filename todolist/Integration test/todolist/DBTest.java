package todolist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class DBTest {
	@Test
	   public void test_Users() {
	      System.out.println("Test Users") ;
	      DB.DBUtil testuser = new DB.DBUtil() ;
	      Todouser user = new Todouser();
	      user = testuser.selectByEmail("weaam@gmail.com");
	     assertNotNull(user) ;
	     user = testuser.selectByEmail("wrong email");
	     assertNull(user) ;
	     user = testuser.selectByName("weaam");
	     assertNotNull(user) ;
	     user = testuser.selectByName("wrong username");
	     assertNull(user) ;
	   }

	   @Test
	   public void test_todolist() {
		   System.out.println("Test to do list") ;
		      DB.DBUtil testlist = new DB.DBUtil();
		      List<Todolist> lsit= new ArrayList<Todolist>(); 
		      Todolist item = new Todolist();
		      Todouser user = testlist.selectByName("weaam");
		      Todostatus status = testlist.getstatus(3);
		     assertNotNull(testlist.getitem(2)) ;
		     assertNull(testlist.getitem(10)) ;
		     lsit = testlist.getToDoList(user);
		     assertFalse(lsit.isEmpty());
		     lsit = testlist.getCompletedList(user, status);
		     assertFalse(lsit.isEmpty());
		   
		   }
	   
	   @Test
	   public void test_status() {
		   System.out.println("Test to do status") ;
		      DB.DBUtil teststatus = new DB.DBUtil() ;
		      Todostatus status = new Todostatus();
		      System.out.println();
		      status = teststatus.getstatus(2);
		      assertTrue(status.getStatus().equals("In Progress"));
		     assertFalse(status.getStatus().equals("Not Started")) ;
		     assertNull(teststatus.getstatus(5)) ;
		   
		   }
}
