package twitter;

import java.util.List;
import java.util.stream.Collectors;

public class TweetService {

	private final Repository<Tweet> tweetRepository;

	public TweetService(Repository<Tweet> tweetRepository) {
		this.tweetRepository = tweetRepository;
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
