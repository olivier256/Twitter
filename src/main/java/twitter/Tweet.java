package twitter;

import java.time.LocalDateTime;

public class Tweet {
	private long id;
	private String content;
	private LocalDateTime dateTime;

	public Tweet(long id, String content) {
		super();
		this.id = id;
		this.content = content;
		this.dateTime = LocalDateTime.now();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime date) {
		this.dateTime = date;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", content=" + content + ", time=" + dateTime + "]";
	}

}
