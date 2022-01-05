public void testScanResistance() throws Exception {
  long maxSize=100000;
  long blockSize=calculateBlockSize(maxSize,10);
  LruBlockCache cache=new LruBlockCache(maxSize,blockSize,false,(int)Math.ceil(1.2 * maxSize / blockSize),LruBlockCache.DEFAULT_LOAD_FACTOR,LruBlockCache.DEFAULT_CONCURRENCY_LEVEL,0.66f,0.99f,0.33f,0.33f,0.34f);
  Block[] singleBlocks=generateFixedBlocks(20,blockSize,"single");
  Block[] multiBlocks=generateFixedBlocks(5,blockSize,"multi");
  for (  Block block : multiBlocks) {
    cache.cacheBlock(block.blockName,block.buf);
    cache.getBlock(block.blockName);
  }
  for (int i=0; i < 5; i++) {
    cache.cacheBlock(singleBlocks[i].blockName,singleBlocks[i].buf);
  }
  assertEquals(1,cache.getEvictionCount());
  assertEquals(4,cache.getEvictedCount());
  assertEquals(null,cache.getBlock(singleBlocks[0].blockName));
  assertEquals(null,cache.getBlock(singleBlocks[1].blockName));
  assertEquals(null,cache.getBlock(multiBlocks[0].blockName));
  assertEquals(null,cache.getBlock(multiBlocks[1].blockName));
  for (int i=5; i < 18; i++) {
    cache.cacheBlock(singleBlocks[i].blockName,singleBlocks[i].buf);
  }
  assertEquals(4,cache.getEvictionCount());
  assertEquals(16,cache.getEvictedCount());
  assertEquals(7,cache.size());
}
