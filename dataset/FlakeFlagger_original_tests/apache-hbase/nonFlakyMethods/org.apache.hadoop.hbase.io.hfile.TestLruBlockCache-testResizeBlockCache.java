public void testResizeBlockCache() throws Exception {
  long maxSize=300000;
  long blockSize=calculateBlockSize(maxSize,31);
  LruBlockCache cache=new LruBlockCache(maxSize,blockSize,false,(int)Math.ceil(1.2 * maxSize / blockSize),LruBlockCache.DEFAULT_LOAD_FACTOR,LruBlockCache.DEFAULT_CONCURRENCY_LEVEL,0.98f,0.99f,0.33f,0.33f,0.34f);
  Block[] singleBlocks=generateFixedBlocks(10,blockSize,"single");
  Block[] multiBlocks=generateFixedBlocks(10,blockSize,"multi");
  Block[] memoryBlocks=generateFixedBlocks(10,blockSize,"memory");
  for (int i=0; i < 10; i++) {
    cache.cacheBlock(singleBlocks[i].blockName,singleBlocks[i].buf);
    cache.cacheBlock(multiBlocks[i].blockName,multiBlocks[i].buf);
    cache.getBlock(multiBlocks[i].blockName);
    cache.cacheBlock(memoryBlocks[i].blockName,memoryBlocks[i].buf,true);
  }
  assertEquals(0,cache.getEvictionCount());
  cache.setMaxSize((long)(maxSize * 0.5f));
  assertEquals(1,cache.getEvictionCount());
  assertEquals(15,cache.getEvictedCount());
  for (int i=0; i < 5; i++) {
    assertEquals(null,cache.getBlock(singleBlocks[i].blockName));
    assertEquals(null,cache.getBlock(multiBlocks[i].blockName));
    assertEquals(null,cache.getBlock(memoryBlocks[i].blockName));
  }
  for (int i=5; i < 10; i++) {
    assertEquals(singleBlocks[i].buf,cache.getBlock(singleBlocks[i].blockName));
    assertEquals(multiBlocks[i].buf,cache.getBlock(multiBlocks[i].blockName));
    assertEquals(memoryBlocks[i].buf,cache.getBlock(memoryBlocks[i].blockName));
  }
}
