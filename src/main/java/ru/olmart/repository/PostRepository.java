package ru.olmart.repository;

import org.springframework.stereotype.Repository;
import ru.olmart.exception.NotFoundException;
import ru.olmart.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@Repository
public class PostRepository {
  private Map<Long, Post> mapPosts = new ConcurrentHashMap<>();
  private long key = 0L;

  public List<Post> all() throws NotFoundException {
    if (mapPosts != null) {
      return mapPosts.values().stream().collect(Collectors.toList());
    } else {
      throw new NotFoundException("Nit yet any post");
    }
  }

  public Optional<Post> getById(long id) throws NotFoundException {
    if (mapPosts.containsKey(id)) {
      return Optional.ofNullable(mapPosts.get(id));
    } else {
      throw new NotFoundException("Post not found");
    }
  }

  public Post save(Post post) throws NotFoundException {
    if (post.getId() == 0) {
      synchronized (this) {
        key++;
      }
      post.setId(key);
      mapPosts.put(key, post);
      return post;
    }
    if ((post.getId() != 0) && (mapPosts.containsKey(post.getId()))) {
      mapPosts.replace(post.getId(), post);
      return post;
    } else {
      throw new NotFoundException("Post not found");
    }
  }

  public void removeById(long id) {
      mapPosts.remove(id);
  }
}
