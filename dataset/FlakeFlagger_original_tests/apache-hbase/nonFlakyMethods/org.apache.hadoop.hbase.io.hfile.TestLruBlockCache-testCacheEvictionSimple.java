public void testCacheEvictionSimple() throws Exception {
  long maxSize=100000;
  long blockSize=calculateBlockSizeDefault(maxSize,10);
  LruBlockCache cache=new LruBlockCache(maxSize,blockSize,false);
  Block[] blocks=generateFixedBlocks(10,blockSize,"block");
  long expectedCacheSize=cache.heapSize();
  for (  Block block : blocks) {
    cache.cacheBlock(block.blockName,block.buf);
    expectedCacheSize+=block.heapSize();
  }
  assertEquals(1,cache.getEvictionCount());
  assertTrue(expectedCacheSize > (maxSize * LruBlockCache.DEFAULT_ACCEPTABLE_FACTOR));
  assertTrue(cache.heapSize() < maxSize);
  assertTrue(cache.heapSize() < (maxSize * LruBlockCache.DEFAULT_ACCEPTABLE_FACTOR));
  assertTrue(cache.getBlock(blocks[0].blockName) == null);
  assertTrue(cache.getBlock(blocks[1].blockName) == null);
  for (int i=2; i < blocks.length; i++) {
    assertEquals(cache.getBlock(blocks[i].blockName),blocks[i].buf);
  }
}
