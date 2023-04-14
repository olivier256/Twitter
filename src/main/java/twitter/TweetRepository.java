package twitter;

import java.util.List;

public interface TweetRepository {

	List<Tweet> findAll();

	void save(Tweet tweet);

}