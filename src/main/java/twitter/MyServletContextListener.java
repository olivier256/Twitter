package twitter;

import java.io.Closeable;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
	private final Repository<Tweet> tweetRepository;
	private final TweetService tweetService;

	public MyServletContextListener() {
		tweetRepository = new SerializationTweetRepository("twitter_repository.dat");
		tweetService = new TweetService(tweetRepository);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("tweetRepository", tweetRepository);
		servletContext.setAttribute("tweetService", tweetService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (tweetRepository instanceof Closeable) {
			try {
				((Closeable) tweetRepository).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
