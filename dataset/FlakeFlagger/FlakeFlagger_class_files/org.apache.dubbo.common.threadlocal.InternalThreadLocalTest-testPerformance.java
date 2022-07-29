/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.common.threadlocal;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class InternalThreadLocalTest {

    private static final int THREADS = 10;

    private static final int PERFORMANCE_THREAD_COUNT = 1000;

    private static final int GET_COUNT = 1000000;

    /**
	 * print take[14]ms <p></p> This test is based on a Machine with 4 core and 16g memory.
	 */@Test public void testPerformance(){final InternalThreadLocal<String>[] caches=new InternalThreadLocal[PERFORMANCE_THREAD_COUNT];final Thread mainThread=Thread.currentThread();for (int i=0;i < PERFORMANCE_THREAD_COUNT;i++){caches[i]=new InternalThreadLocal<String>();}Thread t=new InternalThread(new Runnable(){@Override public void run(){for (int i=0;i < PERFORMANCE_THREAD_COUNT;i++){caches[i].set("float.lu");}long start=System.nanoTime();for (int i=0;i < PERFORMANCE_THREAD_COUNT;i++){for (int j=0;j < GET_COUNT;j++){caches[i].get();}}long end=System.nanoTime();System.out.println("take[" + TimeUnit.NANOSECONDS.toMillis(end - start) + "]ms");LockSupport.unpark(mainThread);}});t.start();LockSupport.park(mainThread);}
}