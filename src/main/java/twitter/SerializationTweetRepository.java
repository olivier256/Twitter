package twitter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializationTweetRepository implements Repository<Tweet>, Closeable {
	private File file;
	private final List<Tweet> tweets;

	SerializationTweetRepository(String fileName) {
		file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("file=" + file.getAbsolutePath());
		if (file.length() == 0) {
			tweets = new ArrayList<>();
		} else {
			try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
				tweets = (List<Tweet>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		System.out.println(tweets.size() + " tweet(s) already present");
	}

	@Override
	public List<Tweet> findAll() {
		return tweets;
	}

	@Override
	public void save(Tweet tweet) {
		tweets.add(tweet);
	}

	@Override
	public void close() throws IOException {
		System.out.println("Now closing…");
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			oos.writeObject(tweets);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
