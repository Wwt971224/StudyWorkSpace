package com.ipinyou.user.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@EnableCaching
@Configuration
public class CacheConfig {

    public static final String USER_CACHE = "USER_CACHE";

    @Bean
    public Cache regionByRegionIdCache() {
        return new CaffeineCache(USER_CACHE, Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(7, TimeUnit.DAYS)
                .build()
        );
    }

}
