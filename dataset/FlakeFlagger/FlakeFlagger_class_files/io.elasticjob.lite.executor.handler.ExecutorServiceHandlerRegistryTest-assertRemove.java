/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.elasticjob.lite.executor.handler;

import io.elasticjob.lite.executor.handler.impl.DefaultExecutorServiceHandler;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public final class ExecutorServiceHandlerRegistryTest {
    
    @Test public void assertRemove(){ExecutorService actual=ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler());ExecutorServiceHandlerRegistry.remove("test_job");assertThat(actual,not(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler())));}
    
    @RequiredArgsConstructor
    class GetExecutorServiceHandlerTask implements Runnable {
        
        private final CyclicBarrier barrier;
        
        private final CountDownLatch latch;
        
        private final Set<ExecutorService> set;
        
        @Override
        public void run() {
            try {
                barrier.await();
            } catch (final InterruptedException | BrokenBarrierException ex) {
                ex.printStackTrace();
            }
            set.add(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job", new DefaultExecutorServiceHandler()));
            latch.countDown();
        }
    }
}
