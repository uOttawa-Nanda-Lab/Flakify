/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.metrics.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.boot.actuate.metrics.util.SimpleInMemoryRepository.Callback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dave Syer
 */
public class InMemoryRepositoryTests {

	private final SimpleInMemoryRepository<String> repository = new SimpleInMemoryRepository<String>();

	@Test public void updateConcurrent() throws Exception{final SimpleInMemoryRepository<Integer> repository=new SimpleInMemoryRepository<Integer>();Collection<Callable<Boolean>> tasks=new ArrayList<Callable<Boolean>>();for (int i=0;i < 1000;i++){tasks.add(new Callable<Boolean>(){@Override public Boolean call() throws Exception{repository.update("foo",new Callback<Integer>(){@Override public Integer modify(Integer current){if (current == null){return 1;}return current + 1;}});return true;}});tasks.add(new Callable<Boolean>(){@Override public Boolean call() throws Exception{repository.update("foo",new Callback<Integer>(){@Override public Integer modify(Integer current){if (current == null){return -1;}return current - 1;}});return true;}});}List<Future<Boolean>> all=Executors.newFixedThreadPool(10).invokeAll(tasks);for (Future<Boolean> future:all){assertTrue(future.get(1,TimeUnit.SECONDS));}assertEquals(new Integer(0),repository.findOne("foo"));}

}
