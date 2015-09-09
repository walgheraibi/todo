

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ServeletCompleted
 */
@WebServlet("/ServeletCompleted")
public class ServeletCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String productListMsg="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletCompleted() {
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
		productListMsg = "";
		HttpSession session = request.getSession();
		Todouser user = (Todouser) session.getAttribute("user");
		Todostatus status = new Todostatus();
		status.setStatus("Completed");
		status.setId(3);
		List<Todolist> todoList = DBUtil.getCompletedList(user, status);

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
						+todoList.get(i).getDatecompleted()
						+ "</td></tr>";
			}
		}
		request.setAttribute("todpListMsg", productListMsg);
		getServletContext().getRequestDispatcher("/Completed.jsp").forward(
				request, response);
	}

}
