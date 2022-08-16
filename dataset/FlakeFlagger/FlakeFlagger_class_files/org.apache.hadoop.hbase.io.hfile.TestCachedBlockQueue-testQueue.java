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
import junit.framework.TestCase;

public class TestCachedBlockQueue extends TestCase {

  public void testQueue() throws Exception {
	CachedBlock cb1 = new CachedBlock(1000, "cb1", 1);
	CachedBlock cb2 = new CachedBlock(1500, "cb2", 2);
	CachedBlock cb3 = new CachedBlock(1000, "cb3", 3);
	CachedBlock cb4 = new CachedBlock(1500, "cb4", 4);
	CachedBlock cb5 = new CachedBlock(1000, "cb5", 5);
	CachedBlock cb6 = new CachedBlock(1750, "cb6", 6);
	CachedBlock cb7 = new CachedBlock(1000, "cb7", 7);
	CachedBlock cb8 = new CachedBlock(1500, "cb8", 8);
	CachedBlock cb9 = new CachedBlock(1000, "cb9", 9);
	CachedBlock cb10 = new CachedBlock(1500, "cb10", 10);
	CachedBlockQueue queue = new CachedBlockQueue(10000, 1000);
	queue.add(cb1);
	queue.add(cb2);
	queue.add(cb3);
	queue.add(cb4);
	queue.add(cb5);
	queue.add(cb6);
	queue.add(cb7);
	queue.add(cb8);
	queue.add(cb9);
	queue.add(cb10);
	long expectedSize = cb1.heapSize() + cb2.heapSize() + cb3.heapSize() + cb4.heapSize() + cb5.heapSize()
			+ cb6.heapSize() + cb7.heapSize() + cb8.heapSize();
	assertEquals(queue.heapSize(), expectedSize);
	org.apache.hadoop.hbase.io.hfile.CachedBlock[] blocks = queue.get();
	assertEquals(blocks[0].getName(), "cb1");
	assertEquals(blocks[1].getName(), "cb2");
	assertEquals(blocks[2].getName(), "cb3");
	assertEquals(blocks[3].getName(), "cb4");
	assertEquals(blocks[4].getName(), "cb5");
	assertEquals(blocks[5].getName(), "cb6");
	assertEquals(blocks[6].getName(), "cb7");
	assertEquals(blocks[7].getName(), "cb8");
}

  private static class CachedBlock extends org.apache.hadoop.hbase.io.hfile.CachedBlock
  {
    public CachedBlock(long heapSize, String name, long accessTime) {
      super(name,
          ByteBuffer.allocate((int)(heapSize - CachedBlock.PER_BLOCK_OVERHEAD)),
          accessTime,false);
    }
  }

}
