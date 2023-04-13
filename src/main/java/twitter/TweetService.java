package twitter;

import java.util.Collection;

public interface TweetService {
	public Collection<Tweet> findAll();

	void save(String content);

}
