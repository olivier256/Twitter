package twitter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final TweetService tweetService = TweetService.getInstance();

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Tweet> tweets = tweetService.findAll();
		request.setAttribute("tweets", tweets);
		try {
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (ServletException | IOException se) {
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
