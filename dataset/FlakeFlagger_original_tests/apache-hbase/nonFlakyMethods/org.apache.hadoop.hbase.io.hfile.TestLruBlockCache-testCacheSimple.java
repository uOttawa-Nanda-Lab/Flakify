public void testCacheSimple() throws Exception {
  long maxSize=1000000;
  long blockSize=calculateBlockSizeDefault(maxSize,101);
  LruBlockCache cache=new LruBlockCache(maxSize,blockSize);
  Block[] blocks=generateRandomBlocks(100,blockSize);
  long expectedCacheSize=cache.heapSize();
  for (  Block block : blocks) {
    assertTrue(cache.getBlock(block.blockName) == null);
  }
  for (  Block block : blocks) {
    cache.cacheBlock(block.blockName,block.buf);
    expectedCacheSize+=block.heapSize();
  }
  assertEquals(expectedCacheSize,cache.heapSize());
  for (  Block block : blocks) {
    ByteBuffer buf=cache.getBlock(block.blockName);
    assertTrue(buf != null);
    assertEquals(buf.capacity(),block.buf.capacity());
  }
  for (  Block block : blocks) {
    try {
      cache.cacheBlock(block.blockName,block.buf);
      assertTrue("Cache should not allow re-caching a block",false);
    }
 catch (    RuntimeException re) {
    }
  }
  assertEquals(expectedCacheSize,cache.heapSize());
  for (  Block block : blocks) {
    ByteBuffer buf=cache.getBlock(block.blockName);
    assertTrue(buf != null);
    assertEquals(buf.capacity(),block.buf.capacity());
  }
  assertEquals(0,cache.getEvictionCount());
}
