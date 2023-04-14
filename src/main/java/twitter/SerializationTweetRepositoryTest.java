package twitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SerializationTweetRepositoryTest {
	private static final String TREMBLE_ELON_MUSK = "Tremble, Elon Musk !";
	private static final String HELLO_WORLD = "Hello, World!";
	private final Random random;

	private final TweetRepository tweetRepository;

	public SerializationTweetRepositoryTest() {
		random = new Random();
		File file;
		try {
			file = File.createTempFile("TweetRepositoryTest", ".dat");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		tweetRepository = SerializationTweetRepository.getInstance(file.getAbsolutePath());
	}

	public void test() {
		testInit();
		testVolume();
	}

	public void testInit() {
		tweetRepository.save(new Tweet(HELLO_WORLD));
		tweetRepository.save(new Tweet("Ceci est mon premier tweet"));
		tweetRepository.save(new Tweet(TREMBLE_ELON_MUSK));
		List<Tweet> tweets = tweetRepository.findAll();
		tweets = tweets.stream() //
				.sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())) //
				.collect(Collectors.toList());
		assert tweets.get(0).getContent().equals(TREMBLE_ELON_MUSK) : tweets.get(0);
		assert tweets.get(2).getContent().equals(HELLO_WORLD) : tweets.get(2);
	}

	public void testVolume() {
		List<Tweet> tweets = new ArrayList<>();
		for (int i = 0; i < 7000; i++) {
			Tweet tweet = generateTweet();
			System.out.println(i + "\t" + tweet);
			tweets.add(tweet);
		}
		for (Tweet tweet : tweets) {
			tweetRepository.save(tweet);
		}
	}

	private Tweet generateTweet() {
		StringBuilder sb = new StringBuilder();
		int bound = random.nextInt(141);
		for (int i = 0; i < bound; i++) {
			char c = (char) (32 + random.nextInt(128 - 32));
			sb.append(c);
		}
		Tweet tweet = new Tweet(sb.toString());
		return tweet;
	}

	public static void main(String[] args) {
		boolean assertEnabled = false;
		assert assertEnabled = true;
		if (!assertEnabled) {
			throw new AssertionError("Lancer avec l'argument VM -ea");
		}
		new SerializationTweetRepositoryTest().test();
	}

}
