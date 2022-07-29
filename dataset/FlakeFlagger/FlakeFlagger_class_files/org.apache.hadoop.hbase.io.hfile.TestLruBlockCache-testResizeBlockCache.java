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

  public void testResizeBlockCache() throws Exception {
	long maxSize = 300000;
	long blockSize = calculateBlockSize(maxSize, 31);
	LruBlockCache cache = new LruBlockCache(maxSize, blockSize, false, (int) Math.ceil(1.2 * maxSize / blockSize),
			LruBlockCache.DEFAULT_LOAD_FACTOR, LruBlockCache.DEFAULT_CONCURRENCY_LEVEL, 0.98f, 0.99f, 0.33f, 0.33f,
			0.34f);
	Block[] singleBlocks = generateFixedBlocks(10, blockSize, "single");
	Block[] multiBlocks = generateFixedBlocks(10, blockSize, "multi");
	Block[] memoryBlocks = generateFixedBlocks(10, blockSize, "memory");
	for (int i = 0; i < 10; i++) {
		cache.cacheBlock(singleBlocks[i].blockName, singleBlocks[i].buf);
		cache.cacheBlock(multiBlocks[i].blockName, multiBlocks[i].buf);
		cache.getBlock(multiBlocks[i].blockName);
		cache.cacheBlock(memoryBlocks[i].blockName, memoryBlocks[i].buf, true);
	}
	assertEquals(0, cache.getEvictionCount());
	cache.setMaxSize((long) (maxSize * 0.5f));
	assertEquals(1, cache.getEvictionCount());
	assertEquals(15, cache.getEvictedCount());
	for (int i = 0; i < 5; i++) {
		assertEquals(null, cache.getBlock(singleBlocks[i].blockName));
		assertEquals(null, cache.getBlock(multiBlocks[i].blockName));
		assertEquals(null, cache.getBlock(memoryBlocks[i].blockName));
	}
	for (int i = 5; i < 10; i++) {
		assertEquals(singleBlocks[i].buf, cache.getBlock(singleBlocks[i].blockName));
		assertEquals(multiBlocks[i].buf, cache.getBlock(multiBlocks[i].blockName));
		assertEquals(memoryBlocks[i].buf, cache.getBlock(memoryBlocks[i].blockName));
	}
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
