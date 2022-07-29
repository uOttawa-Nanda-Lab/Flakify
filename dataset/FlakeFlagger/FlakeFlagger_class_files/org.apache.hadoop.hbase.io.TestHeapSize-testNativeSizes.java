/*
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

package org.apache.hadoop.hbase.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.hfile.CachedBlock;
import org.apache.hadoop.hbase.io.hfile.LruBlockCache;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.MemStore;
import org.apache.hadoop.hbase.regionserver.Store;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.ClassSize;

/**
 * Testing the sizing that HeapSize offers and compares to the size given by
 * ClassSize.
 */
public class TestHeapSize extends TestCase {
  static final Log LOG = LogFactory.getLog(TestHeapSize.class);
  // List of classes implementing HeapSize
  // BatchOperation, BatchUpdate, BlockIndex, Entry, Entry<K,V>, HStoreKey
  // KeyValue, LruBlockCache, LruHashMap<K,V>, Put, HLogKey

  /**
 * Test our hard-coded sizing of native java objects
 */@SuppressWarnings("unchecked") public void testNativeSizes() throws IOException{Class cl=null;long expected=0L;long actual=0L;cl=ArrayList.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.ARRAYLIST;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=ByteBuffer.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.BYTE_BUFFER;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=Integer.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.INTEGER;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=Object.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.OBJECT;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=TreeMap.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.TREEMAP;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=String.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.STRING;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=ConcurrentHashMap.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.CONCURRENT_HASHMAP;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=ConcurrentSkipListMap.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.CONCURRENT_SKIPLISTMAP;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=ReentrantReadWriteLock.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.REENTRANT_LOCK;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=AtomicLong.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.ATOMIC_LONG;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=AtomicInteger.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.ATOMIC_INTEGER;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=AtomicBoolean.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.ATOMIC_BOOLEAN;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=CopyOnWriteArraySet.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.COPYONWRITE_ARRAYSET;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}cl=CopyOnWriteArrayList.class;expected=ClassSize.estimateBase(cl,false);actual=ClassSize.COPYONWRITE_ARRAYLIST;if (expected != actual){ClassSize.estimateBase(cl,true);assertEquals(expected,actual);}}

}
