package com.shubham.cache;


import com.shubham.cache.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  CacheManager cacheManager;

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info(".... Fetching books");
    bookRepository.getByIsbn("isbn-1234");
    bookRepository.getByIsbn("isbn-4567");
    bookRepository.getByIsbn("isbn-8910");
    bookRepository.getByIsbn("isbn-1234");
    bookRepository.getByIsbn("isbn-4567");
    bookRepository.getByIsbn("isbn-1234");
    bookRepository.getByIsbn("isbn-1234");
    bookRepository.getByIsbn("isbn-8910");
    bookRepository.deleteBook("isbn-1234");
    LOGGER.info("Cache Key:{}, Value:{}","isbn-1234",cacheManager.getCache("books").get("isbn-1234"));
    bookRepository.getByIsbn("isbn-1234");

  }
}
