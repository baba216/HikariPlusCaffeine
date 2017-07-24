package com.shubham.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CacheConfig {

  /*@Bean
  public CacheManager cacheManager() {
    ConcurrentMapCacheManager concurrentMapCacheManager =
        new ConcurrentMapCacheManager("books", "customer");
    return concurrentMapCacheManager;
  }*/
}
