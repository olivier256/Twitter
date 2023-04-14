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

	private final SerializationTweetRepository tweetRepository;

	public SerializationTweetRepositoryTest(File file) {
		random = new Random();
		tweetRepository = new SerializationTweetRepository(file.getAbsolutePath());
	}

	public void test() {
		testInit();
		testVolume();
	}

	public void testInit() {
		tweetRepository.save(new Tweet(HELLO_WORLD));
		sleep(10);
		tweetRepository.save(new Tweet("Ceci est mon premier tweet"));
		sleep(10);
		tweetRepository.save(new Tweet(TREMBLE_ELON_MUSK));
		List<Tweet> tweets = tweetRepository.findAll();
		tweets = tweets.stream() //
				.sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())) //
				.collect(Collectors.toList());
		assert tweets.get(0).getContent().equals(TREMBLE_ELON_MUSK) : tweets.get(0);
		assert tweets.get(2).getContent().equals(HELLO_WORLD) : tweets.get(2);
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void testVolume() {
		List<Tweet> tweets = new ArrayList<>();
		for (int i = 0; i < 7000; i++) {
			Tweet tweet = generateTweet();
			System.out.println(tweet.toString());
			tweets.add(tweet);
		}
		for (Tweet tweet : tweets) {
			tweetRepository.save(tweet);
		}
	}

	private Tweet generateTweet() {
		StringBuilder sb = new StringBuilder();
		int bound = random.nextInt(141 - 60);
		for (int i = 0; i < 60 + bound; i++) {
			char c = (char) (32 + random.nextInt(128 - 32));
			sb.append(c);
		}
		return new Tweet(sb.toString());
	}

	public void tearDown() {
		try {
			tweetRepository.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		boolean assertEnabled = false;
		assert assertEnabled = true;
		if (!assertEnabled) {
			throw new AssertionError("Lancer avec l'argument VM -ea");
		}
		File file = File.createTempFile("TweetRepositoryTest", ".dat");

		SerializationTweetRepositoryTest test = new SerializationTweetRepositoryTest(file);
		test.test();
		test.tearDown();
	}

}
