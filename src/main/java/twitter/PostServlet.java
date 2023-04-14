package twitter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "post", urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");

		TweetService tweetService = (TweetService) request.getServletContext().getAttribute("tweetService");
		tweetService.save(content);
		try {
			response.sendRedirect(request.getContextPath() + "/");
		} catch (IOException e) {
			request.getRequestDispatcher("error.html");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (ServletException | IOException se) {
			request.getRequestDispatcher("error.html");
		}
	}

}
