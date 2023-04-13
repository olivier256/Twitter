package twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MockTweetService implements TweetService {

	private Collection<Tweet> tweets;
	private static int idMax = 3;

	public MockTweetService() {
		tweets = new ArrayList<>();
		tweets.addAll( //
				Arrays.asList( //
						new Tweet(1, "Hello, World!"), //
						new Tweet(2, "Ceci est mon premier tweet"), //
						new Tweet(3, "Tremble, Elon Musk !") //
				) //
		);
	}

	@Override
	public void save(String content) {
		int id;
		synchronized (this) {
			id = ++idMax;
		}
		Tweet tweet = new Tweet(id, content);
		tweets.add(tweet);
	}

	@Override
	public Collection<Tweet> findAll() {
		return tweets;
	}

}
