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

  public void testBackgroundEvictionThread() throws Exception{long maxSize=100000;long blockSize=calculateBlockSizeDefault(maxSize,9);LruBlockCache cache=new LruBlockCache(maxSize,blockSize);Block[] blocks=generateFixedBlocks(10,blockSize,"block");for (Block block:blocks){cache.cacheBlock(block.blockName,block.buf);}int n=0;while (cache.getEvictionCount() == 0){System.out.println("sleep");Thread.sleep(1000);assertTrue(n++ < 2);}System.out.println("Background Evictions run: " + cache.getEvictionCount());assertEquals(cache.getEvictionCount(),1);}

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
