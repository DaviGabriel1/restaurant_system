package com.davi.restaurant_burguer.infrastructure.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class CacheCleaner {
    private static final Logger logger = LoggerFactory.getLogger(CacheCleaner.class);

    @Scheduled(fixedDelay = 12, timeUnit = TimeUnit.HOURS)
    @CacheEvict("products")
    public void cleanCacheProduct() {
        logger.info("Cache de produtos foi limpado. Data: {}", LocalDateTime.now());
    }
}
