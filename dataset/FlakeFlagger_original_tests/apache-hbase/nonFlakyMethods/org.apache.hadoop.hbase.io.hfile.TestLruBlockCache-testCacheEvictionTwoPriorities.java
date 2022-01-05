public void testCacheEvictionTwoPriorities() throws Exception {
  long maxSize=100000;
  long blockSize=calculateBlockSizeDefault(maxSize,10);
  LruBlockCache cache=new LruBlockCache(maxSize,blockSize,false);
  Block[] singleBlocks=generateFixedBlocks(5,10000,"single");
  Block[] multiBlocks=generateFixedBlocks(5,10000,"multi");
  long expectedCacheSize=cache.heapSize();
  for (  Block block : multiBlocks) {
    cache.cacheBlock(block.blockName,block.buf);
    expectedCacheSize+=block.heapSize();
    assertEquals(cache.getBlock(block.blockName),block.buf);
  }
  for (  Block block : singleBlocks) {
    cache.cacheBlock(block.blockName,block.buf);
    expectedCacheSize+=block.heapSize();
  }
  assertEquals(cache.getEvictionCount(),1);
  assertEquals(cache.getEvictedCount(),2);
  assertTrue(expectedCacheSize > (maxSize * LruBlockCache.DEFAULT_ACCEPTABLE_FACTOR));
  assertTrue(cache.heapSize() <= maxSize);
  assertTrue(cache.heapSize() <= (maxSize * LruBlockCache.DEFAULT_ACCEPTABLE_FACTOR));
  assertTrue(cache.getBlock(singleBlocks[0].blockName) == null);
  assertTrue(cache.getBlock(multiBlocks[0].blockName) == null);
  for (int i=1; i < 4; i++) {
    assertEquals(cache.getBlock(singleBlocks[i].blockName),singleBlocks[i].buf);
    assertEquals(cache.getBlock(multiBlocks[i].blockName),multiBlocks[i].buf);
  }
}
