# SpringCacheExamples
This is a small spring boot project depicting both spring cache and caffeine cache 

- Configuring caffeine cache: [Source Code Link](CacheExample/src/main/java/com/example/cache/config/CacheConfig.java#L39)
- Using caffeine cache: [Source Code Link](CacheExample/src/main/java/com/example/cache/service/TargetResource.java#L31)

**Bonus Alert**
- Custom cache implementation [Source Code Link](CacheExample/src/main/java/com/example/cache/cacheImpl/CustomCaffeineCache.java)
- Custom key generator 
  * Implementation: [Source Code Link](CacheExample/src/main/java/com/example/cache/config/CacheConfig.java#L65)
  * Consumption: [Source Code Link](CacheExample/src/main/java/com/example/cache/service/TargetResource.java#L17)
