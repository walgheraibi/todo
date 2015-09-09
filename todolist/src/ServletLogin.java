

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todolist.Todouser;
import DB.DBUtil;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginErr;
	private String signupErr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (request.getParameter("logout") != null) {

			session.invalidate();

			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		loginErr = "";
		if (request.getParameter("LoginSub") != null) {
			if (request.getParameter("name") != null) {
				String inputUserN = request.getParameter("name");

				if (DBUtil.selectByName(inputUserN) == null) {
					loginErr += "<script type=\"text/javascript\">validateName()</script>";
					request.setAttribute("loginErr", loginErr);
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				} else {
					if (DBUtil.selectByName(inputUserN).getPassword().equals(request.getParameter("password"))) {

						Todouser user = DBUtil.selectByName(inputUserN);
						session.setAttribute("user", user);
						System.out.println("user name login " + user.getUsername());

						//session.setAttribute("cartCheckout", EtsycartDB.selectByUserStatus(inputUserN, 0));
						response.sendRedirect("Login.jsp");
					} else {
						loginErr += "<script type=\"text/javascript\">validatePassword()</script>";
						request.setAttribute("loginErr", loginErr);
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					}
				}
			}
		}
		
		signupErr = "";
		if (request.getParameter("SignupSub") != null) {
			if ((request.getParameter("name") != null) && (request.getParameter("email") != null)) {
				String inputUserE = request.getParameter("email");
				
				if (DBUtil.selectByEmail(inputUserE) == null) {
					Todouser user = new Todouser();
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					user.setUsername(name);
					user.setEmail(email);
					String password = request.getParameter("password");
					user.setPassword(password);
					DBUtil.insert(user);
					user = DBUtil.selectByName(user.getUsername());
					
					session.setAttribute("user", user);


					response.sendRedirect("Login.jsp");
				} else {
					signupErr += "<script type=\"text/javascript\">validateEmail()</script>";
					request.setAttribute("signupErr", signupErr);
					getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);
				}
			}
		}
	}

}