package twitter;

public class Injector {

	private static final TweetService tweetService = new MockTweetService();

	private Injector() {

	}

	public static TweetService getTweetService() {
		return tweetService;
	}

}
