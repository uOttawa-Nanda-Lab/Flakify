/**
 * Copyright 2009 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.io.hfile;

import java.nio.ByteBuffer;
import java.util.Random;

import org.apache.hadoop.hbase.io.HeapSize;
import org.apache.hadoop.hbase.util.ClassSize;

import junit.framework.TestCase;

/**
 * Tests the concurrent LruBlockCache.<p>
 *
 * Tests will ensure it grows and shrinks in size properly,
 * evictions run when they're supposed to and do what they should,
 * and that cached blocks are accessible when expected to be.
 */
public class TestLruBlockCache extends TestCase {

  public void testCacheEvictionThreePriorities() throws Exception {
	long maxSize = 100000;
	long blockSize = calculateBlockSize(maxSize, 10);
	LruBlockCache cache = new LruBlockCache(maxSize, blockSize, false, (int) Math.ceil(1.2 * maxSize / blockSize),
			LruBlockCache.DEFAULT_LOAD_FACTOR, LruBlockCache.DEFAULT_CONCURRENCY_LEVEL, 0.98f, 0.99f, 0.33f, 0.33f,
			0.34f);
	Block[] singleBlocks = generateFixedBlocks(5, blockSize, "single");
	Block[] multiBlocks = generateFixedBlocks(5, blockSize, "multi");
	Block[] memoryBlocks = generateFixedBlocks(5, blockSize, "memory");
	long expectedCacheSize = cache.heapSize();
	for (int i = 0; i < 3; i++) {
		cache.cacheBlock(singleBlocks[i].blockName, singleBlocks[i].buf);
		expectedCacheSize += singleBlocks[i].heapSize();
		cache.cacheBlock(multiBlocks[i].blockName, multiBlocks[i].buf);
		expectedCacheSize += multiBlocks[i].heapSize();
		cache.getBlock(multiBlocks[i].blockName);
		cache.cacheBlock(memoryBlocks[i].blockName, memoryBlocks[i].buf, true);
		expectedCacheSize += memoryBlocks[i].heapSize();
	}
	assertEquals(0, cache.getEvictionCount());
	assertEquals(expectedCacheSize, cache.heapSize());
	cache.cacheBlock(singleBlocks[3].blockName, singleBlocks[3].buf);
	assertEquals(1, cache.getEvictionCount());
	assertEquals(1, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(singleBlocks[0].blockName));
	cache.getBlock(singleBlocks[1].blockName);
	cache.cacheBlock(singleBlocks[4].blockName, singleBlocks[4].buf);
	assertEquals(2, cache.getEvictionCount());
	assertEquals(2, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(multiBlocks[0].blockName));
	cache.cacheBlock(memoryBlocks[3].blockName, memoryBlocks[3].buf, true);
	assertEquals(3, cache.getEvictionCount());
	assertEquals(3, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(memoryBlocks[0].blockName));
	Block[] bigBlocks = generateFixedBlocks(3, blockSize * 3, "big");
	cache.cacheBlock(bigBlocks[0].blockName, bigBlocks[0].buf);
	assertEquals(4, cache.getEvictionCount());
	assertEquals(6, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(singleBlocks[2].blockName));
	assertEquals(null, cache.getBlock(singleBlocks[3].blockName));
	assertEquals(null, cache.getBlock(singleBlocks[4].blockName));
	cache.getBlock(bigBlocks[0].blockName);
	cache.cacheBlock(bigBlocks[1].blockName, bigBlocks[1].buf);
	assertEquals(5, cache.getEvictionCount());
	assertEquals(9, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(singleBlocks[1].blockName));
	assertEquals(null, cache.getBlock(multiBlocks[1].blockName));
	assertEquals(null, cache.getBlock(multiBlocks[2].blockName));
	cache.cacheBlock(bigBlocks[2].blockName, bigBlocks[2].buf, true);
	assertEquals(6, cache.getEvictionCount());
	assertEquals(12, cache.getEvictedCount());
	assertEquals(null, cache.getBlock(memoryBlocks[1].blockName));
	assertEquals(null, cache.getBlock(memoryBlocks[2].blockName));
	assertEquals(null, cache.getBlock(memoryBlocks[3].blockName));
}

  private Block [] generateFixedBlocks(int numBlocks, int size, String pfx) {
    Block [] blocks = new Block[numBlocks];
    for(int i=0;i<numBlocks;i++) {
      blocks[i] = new Block(pfx + i, size);
    }
    return blocks;
  }

  private Block [] generateFixedBlocks(int numBlocks, long size, String pfx) {
    return generateFixedBlocks(numBlocks, (int)size, pfx);
  }

  private Block [] generateRandomBlocks(int numBlocks, long maxSize) {
    Block [] blocks = new Block[numBlocks];
    Random r = new Random();
    for(int i=0;i<numBlocks;i++) {
      blocks[i] = new Block("block" + i, r.nextInt((int)maxSize)+1);
    }
    return blocks;
  }

  private long calculateBlockSize(long maxSize, int numBlocks) {
    long roughBlockSize = maxSize / numBlocks;
    int numEntries = (int)Math.ceil((1.2)*maxSize/roughBlockSize);
    long totalOverhead = LruBlockCache.CACHE_FIXED_OVERHEAD +
        ClassSize.CONCURRENT_HASHMAP +
        (numEntries * ClassSize.CONCURRENT_HASHMAP_ENTRY) +
        (LruBlockCache.DEFAULT_CONCURRENCY_LEVEL * ClassSize.CONCURRENT_HASHMAP_SEGMENT);
    long negateBlockSize = (long)(totalOverhead/numEntries);
    negateBlockSize += CachedBlock.PER_BLOCK_OVERHEAD;
    return ClassSize.align((long)Math.floor((roughBlockSize - negateBlockSize)*0.99f));
  }

  private long calculateBlockSizeDefault(long maxSize, int numBlocks) {
    long roughBlockSize = maxSize / numBlocks;
    int numEntries = (int)Math.ceil((1.2)*maxSize/roughBlockSize);
    long totalOverhead = LruBlockCache.CACHE_FIXED_OVERHEAD +
        ClassSize.CONCURRENT_HASHMAP +
        (numEntries * ClassSize.CONCURRENT_HASHMAP_ENTRY) +
        (LruBlockCache.DEFAULT_CONCURRENCY_LEVEL * ClassSize.CONCURRENT_HASHMAP_SEGMENT);
    long negateBlockSize = totalOverhead / numEntries;
    negateBlockSize += CachedBlock.PER_BLOCK_OVERHEAD;
    return ClassSize.align((long)Math.floor((roughBlockSize - negateBlockSize)*
        LruBlockCache.DEFAULT_ACCEPTABLE_FACTOR));
  }

  private static class Block implements HeapSize {
    String blockName;
    ByteBuffer buf;

    Block(String blockName, int size) {
      this.blockName = blockName;
      this.buf = ByteBuffer.allocate(size);
    }

    public long heapSize() {
      return CachedBlock.PER_BLOCK_OVERHEAD +
      ClassSize.align(blockName.length()) +
      ClassSize.align(buf.capacity());
    }
  }
}
