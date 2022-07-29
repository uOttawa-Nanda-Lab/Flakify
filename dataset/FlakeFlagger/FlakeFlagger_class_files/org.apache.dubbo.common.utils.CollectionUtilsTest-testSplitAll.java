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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.apache.dubbo.common.utils.CollectionUtils.isEmpty;
import static org.apache.dubbo.common.utils.CollectionUtils.isNotEmpty;
import static org.apache.dubbo.common.utils.CollectionUtils.toMap;
import static org.apache.dubbo.common.utils.CollectionUtils.toStringMap;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest {
    @Test public void testSplitAll() throws Exception{assertNull(CollectionUtils.splitAll(null,null));assertNull(CollectionUtils.splitAll(null,"-"));assertTrue(CollectionUtils.splitAll(new HashMap<String, List<String>>(),"-").isEmpty());Map<String, List<String>> input=new HashMap<String, List<String>>();input.put("key1",Arrays.asList("1:a","2:b","3:c"));input.put("key2",Arrays.asList("1:a","2:b"));input.put("key3",null);input.put("key4",new ArrayList<String>());Map<String, Map<String, String>> expected=new HashMap<String, Map<String, String>>();expected.put("key1",CollectionUtils.toStringMap("1","a","2","b","3","c"));expected.put("key2",CollectionUtils.toStringMap("1","a","2","b"));expected.put("key3",null);expected.put("key4",new HashMap<String, String>());assertEquals(expected,CollectionUtils.splitAll(input,":"));}
}