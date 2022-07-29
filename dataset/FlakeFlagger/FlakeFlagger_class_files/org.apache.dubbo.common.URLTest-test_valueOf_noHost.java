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
package org.apache.dubbo.common;

import org.apache.dubbo.common.utils.CollectionUtils;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class URLTest {

    @Test public void test_valueOf_noHost() throws Exception{URL url=URL.valueOf("file:///home/user1/router.js");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertNull(url.getHost());assertEquals(0,url.getPort());assertEquals("home/user1/router.js",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("file://home/user1/router.js");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertEquals("home",url.getHost());assertEquals(0,url.getPort());assertEquals("user1/router.js",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("file:/home/user1/router.js");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertNull(url.getHost());assertEquals(0,url.getPort());assertEquals("home/user1/router.js",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("file:///d:/home/user1/router.js");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertNull(url.getHost());assertEquals(0,url.getPort());assertEquals("d:/home/user1/router.js",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("file:///home/user1/router.js?p1=v1&p2=v2");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertNull(url.getHost());assertEquals(0,url.getPort());assertEquals("home/user1/router.js",url.getPath());assertEquals(2,url.getParameters().size());Map<String, String> params=new HashMap<String, String>();params.put("p1","v1");params.put("p2","v2");assertEquals(params,url.getParameters());url=URL.valueOf("file:/home/user1/router.js?p1=v1&p2=v2");assertEquals("file",url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertNull(url.getHost());assertEquals(0,url.getPort());assertEquals("home/user1/router.js",url.getPath());assertEquals(2,url.getParameters().size());params=new HashMap<String, String>();params.put("p1","v1");params.put("p2","v2");assertEquals(params,url.getParameters());}
}