package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import web.EmailHelper;
import web.UserDAO;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Setting it to avoid inserting data to mysql char-set problem
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		try {
			switch (action) {
			case "dologin":
				processLogin(request, response);
				break;
			case "dologout":
				doLogout(request, response);
				break;
			case "sendEmail":
				processSendEmail(request, response);
				break;
			case "new":
				showEditor(request, response);
				break;
			case "listReceived":
				listReceived(request, response);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void showEditor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (isLogedIn(request)) {
			request.getRequestDispatcher("editor.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	private void listReceived(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		// TODO Auto-generated method stub
		if (isLogedIn(request)) {
			request.getSession().setAttribute("listEmail", EmailHelper.listEmail(request));
			request.getRequestDispatcher("receivedEmails.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	private void processSendEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AddressException {
		// TODO Auto-generated method stub
		if (isLogedIn(request)) {
			request.setAttribute("isSucess", EmailHelper.sendEmail(request));
			request.getRequestDispatcher("editor.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("logedinUser") != null) {
			request.getSession(false).invalidate();
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();

		User user = UserDAO.findUser(userName, password);
		if (user != null) {
			request.setAttribute("userName", "");
			request.setAttribute("password", "");
			request.setAttribute("errorMessage", "");
			// signed in user using session to store info
			HttpSession session = request.getSession();
			session.setAttribute("logedinUser", new User(userName, password));
			request.getRequestDispatcher("/editor.jsp").forward(request, response);
		} else {
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("errorMessage", "Sai tên người dùng/mật khẩu!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	private boolean isLogedIn(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return request.getSession().getAttribute("logedinUser") != null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
