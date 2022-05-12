package ru.olmart.model;

import java.util.concurrent.atomic.AtomicLong;

public class Post {
  private long id;
  private String content;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setId(AtomicLong id) {
    this.id = id.get();
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
