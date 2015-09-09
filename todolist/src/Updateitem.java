

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
		
		long itemid = Long.parseLong(request.getParameter("itemid"));
		
		if(request.getAttribute("isEdit").equals("true"))
		{
			String inputdes = request.getParameter("description");
			String inputduedate = request.getParameter("duedate");
			int inputstatus = Integer.parseInt(request.getParameter("status"));
			HttpSession session = request.getSession();
			Todouser user = (Todouser) session.getAttribute("user"); 
			Date dueDate = null;
			SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
			try {
				dueDate = formatter.parse(inputduedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				Todolist item = new Todolist();
				item.setDescription(inputdes);
				Todostatus state  = DBUtil.getstatus(inputstatus);
				item.setTodostatus(state);
				item.setDuedate(dueDate);
				item.setTodouser(user);
				item.setId(itemid);
				DBUtil.updateitem(item);
			getServletContext().getRequestDispatcher("/ServletToDoList").forward(
					request, response);
		}
		else
		{
		getServletContext().getRequestDispatcher("/Edititem.jsp").forward(
				request, response);
		}
	}

}
