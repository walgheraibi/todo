

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBUtil;
import todolist.Todolist;
import todolist.Todouser;

/**
 * Servlet implementation class ServletToDoList
 */
@WebServlet("/ServletToDoList")
public class ServletToDoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String productListMsg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletToDoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productListMsg = "";
		HttpSession session = request.getSession();
		Todouser user = (Todouser) session.getAttribute("user"); 
		System.out.println(user.getId());

		List<Todolist> todoList = DBUtil.getToDoList(user);

		if (todoList!= null && !todoList.isEmpty()) {
			for (int i = 0; i < todoList.size(); i++) {
				System.out.println("product list check");
				productListMsg += "<tr><td>"
						+ todoList.get(i).getId()
						+"</td>"
						+ "<td>"
						+ todoList.get(i).getDescription()
						+"</td>"
						+ "<td>"
						+ todoList.get(i).getDuedate()
						+"</td>"
						+ "<td>"
						+ todoList.get(i).getTodostatus().getStatus();
						if(todoList.get(i).getTodostatus().getStatus().equals("Completed") && todoList.get(i).getTodostatus().getStatus()!= null)
						{
							productListMsg+=" On the "+todoList.get(i).getDatecompleted();
						}
						productListMsg	+= "</td><td>"
								+"<a href='Updateitem?itemid="+todoList.get(i).getId()+"' class='btn pull-left btn-primary '>Edit</a></td>"
								+"<td><a href='Deleteitem?itemid="+todoList.get(i).getId()+"' class='btn pull-left btn-primary '>Delete</a></td></tr>";
						}
		}
		request.setAttribute("todpListMsg", productListMsg);
		getServletContext().getRequestDispatcher("/ToDoList.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);

	}
}
