package twitter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tweet implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String content;
	private final LocalDateTime dateTime;

	public Tweet(String content) {
		this.content = content;
		this.dateTime = LocalDateTime.now();
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return "Tweet [content=" + content + ", dateTime=" + dateTime + "]";
	}

}
