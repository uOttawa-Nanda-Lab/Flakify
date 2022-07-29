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
package org.apache.dubbo.common.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class AtomicPositiveIntegerTest {
    private AtomicPositiveInteger i1 = new AtomicPositiveInteger();

    private AtomicPositiveInteger i2 = new AtomicPositiveInteger(127);

    private AtomicPositiveInteger i3 = new AtomicPositiveInteger(Integer.MAX_VALUE);

    @Test public void testGetAndIncrement() throws Exception{int get=i1.getAndIncrement();assertEquals(0,get);assertEquals(1,i1.get());get=i2.getAndIncrement();assertEquals(127,get);assertEquals(128,i2.get());get=i3.getAndIncrement();assertEquals(Integer.MAX_VALUE,get);assertEquals(0,i3.get());}
}