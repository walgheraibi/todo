

import java.awt.List;
import java.io.IOException;
import java.text.DateFormat;
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
 * Servlet implementation class ServletAdditem
 */
@WebServlet("/ServletAdditem")
public class ServletAdditem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String msg="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdditem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			DBUtil.insertitem(item);
			/*java.util.List<Todolist> toshow= DBUtil.getToDoList(user);
			for(Todolist t:toshow)
			{
				msg+=t.getDescription()+" "+t.getTodostatus().getStatus()+" " +t.getTodouser().getUsername()+"\n";
			}
			request.setAttribute("temp", msg);
			getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);*/
			response.sendRedirect("ServletToDoList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
