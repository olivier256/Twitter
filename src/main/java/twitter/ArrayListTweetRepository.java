package twitter;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTweetRepository implements TweetRepository {
	private static TweetRepository instance;
	private final List<Tweet> tweets;

	private ArrayListTweetRepository() {
		tweets = new ArrayList<>();
		tweets.add(new Tweet("Hello, World!"));
		sleep(10);
		tweets.add(new Tweet("Ceci est mon premier tweet"));
		sleep(10);
		tweets.add(new Tweet("Tremble, Elon Musk !"));
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public static TweetRepository getInstance() {
		if (instance == null) {
			instance = new ArrayListTweetRepository();
		}
		return instance;
	}

	@Override
	public List<Tweet> findAll() {
		return tweets;
	}

	@Override
	public void save(Tweet tweet) {
		tweets.add(tweet);
	}

}
