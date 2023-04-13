package twitter;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final TweetService tweetService = Injector.getTweetService();

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<Tweet> tweets = //
				tweetService.findAll() //
						.stream() //
						.sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())) //
						.collect(Collectors.toList());
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
