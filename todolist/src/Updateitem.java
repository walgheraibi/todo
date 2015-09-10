

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todolist.Todolist;
import todolist.Todostatus;
import todolist.Todouser;
import DB.DBUtil;

/**
 * Servlet implementation class Updateitem
 */
@WebServlet("/Updateitem")
public class Updateitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long itemid = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updateitem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	
		if(request.getParameter("isEdit") != null && request.getParameter("isEdit").equals("false"))
		{
			itemid = Long.parseLong(request.getParameter("itemid"));
			session.setAttribute("itemid", itemid);
			getServletContext().getRequestDispatcher("/Edititem.jsp").forward(
					request, response);
			
			
		}
		else
		{
			
			String inputduedate = request.getParameter("duedate");
			int inputstatus = Integer.parseInt(request.getParameter("status"));
			 session = request.getSession();
			Todouser user = (Todouser) session.getAttribute("user"); 
			Date dueDate = null;
			Date completDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
			Todolist item = DBUtil.getitem((long)session.getAttribute("itemid"));
			if(inputduedate!= null && inputduedate!="")
			{
			try {
				dueDate = formatter.parse(inputduedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(dueDate);
			item.setDuedate(dueDate);
		}
				
				Todostatus state  = DBUtil.getstatus(inputstatus);
				item.setTodostatus(state);
				if(inputstatus == 3)
					item.setDatecompleted(completDate);
				item.setTodouser(user);
				DBUtil.updateitem(item);
			getServletContext().getRequestDispatcher("/ServletToDoList").forward(
					request, response);
		}
	}

}
