package twitter;

import java.util.List;
import java.util.stream.Collectors;

public class TweetService {
	private static TweetService instance;

	// private final TweetRepository tweetRepository =
	// SerializationTweetRepository.getInstance("twitter_repository.dat");
	private final TweetRepository tweetRepository = ArrayListTweetRepository.getInstance();

	public static TweetService getInstance() {
		if (instance == null) {
			instance = new TweetService();
		}
		return instance;
	}

	public List<Tweet> findAll() {
		return tweetRepository.findAll().stream() //
				.sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())) //
				.collect(Collectors.toList());
	}

	public void save(String content) {
		Tweet tweet = new Tweet(content);
		tweetRepository.save(tweet);
	}

}
