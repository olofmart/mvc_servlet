package ru.olmart.repository;

import org.springframework.stereotype.Repository;
import ru.olmart.exception.NotFoundException;
import ru.olmart.model.Post;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@Repository
public class PostRepository {
  private final Map<Long, Post> mapPosts;
  private AtomicLong key = new AtomicLong(0L);

  public PostRepository() {
    this.mapPosts =  new HashMap<>();
  }

  public List<Post> all() throws NotFoundException {
    return new ArrayList<>(mapPosts.values());
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
      post.setId(key.incrementAndGet());
      mapPosts.put(key.get(), post);
      return post;
    }
    if ((post.getId() != 0) && (mapPosts.containsKey(key.get()))) {
      mapPosts.replace(key.get(), post);
      return post;
    } else {
      throw new NotFoundException("Post not found");
    }
  }

  public void removeById(long id) {
      mapPosts.remove(id);
  }
}
