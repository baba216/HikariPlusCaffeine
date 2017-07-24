package com.shubham.cache.repository;

import com.shubham.cache.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBookRepository.class);

  @Autowired
  CacheManager cacheManager;

  @Override
  @Cacheable(value = "books", key = "#isbn") public Book getByIsbn(String isbn) {
    long startTime = System.currentTimeMillis();
    simulateSlowService();
    LOGGER.info("Found book for isbn:{} at:{}",isbn,(System.currentTimeMillis() - startTime));
    return new Book(isbn, "some Book" + isbn);
  }

  @Override
  @CacheEvict(value = "books",key = "#isbn")
  public void deleteBook(String isbn) {
    LOGGER.info("Removing isbn:{}",isbn);
  }

  @Override
  @CacheEvict(value = "greetings",allEntries = true)
  public void evictCache() {
    LOGGER.info("Evicts greetings cache");
    LOGGER.info("Cache Content:",cacheManager.getCache("greetings").get("isbn-1234"));
  }

  private void simulateSlowService() {
    try{
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
