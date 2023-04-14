package twitter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerializationTweetRepository implements TweetRepository {
	private String fileName;
	private static TweetRepository instance;

	private SerializationTweetRepository(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("SerializationTweetRepository <init>: file=" + file.getAbsolutePath());
	}

	public static TweetRepository getInstance(String fileName) {
		if (instance == null) {
			instance = new SerializationTweetRepository(fileName);
		}
		return instance;
	}

	@Override
	public List<Tweet> findAll() {
		List<Tweet> tweets;
		if (new File(fileName).length() == 0) {
			return new ArrayList<>();
		}
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
			tweets = (List<Tweet>) ois.readObject();
			return tweets;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public void save(Tweet tweet) {
		List<Tweet> tweets = findAll();
		tweets.add(tweet);
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			oos.writeObject(tweets);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
