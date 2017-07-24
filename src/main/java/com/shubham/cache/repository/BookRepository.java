package com.shubham.cache.repository;

import com.shubham.cache.model.Book;

public interface BookRepository {

  Book getByIsbn(String isbn);

  void deleteBook(String isbn);

  void evictCache();

}
