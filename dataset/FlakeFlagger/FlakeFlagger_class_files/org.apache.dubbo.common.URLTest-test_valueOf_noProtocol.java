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

    @Test public void test_valueOf_noProtocol() throws Exception{URL url=URL.valueOf("10.20.130.230");assertNull(url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertEquals("10.20.130.230",url.getHost());assertEquals(0,url.getPort());assertEquals(null,url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("10.20.130.230:20880");assertNull(url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertEquals("10.20.130.230",url.getHost());assertEquals(20880,url.getPort());assertEquals(null,url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("10.20.130.230/context/path");assertNull(url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertEquals("10.20.130.230",url.getHost());assertEquals(0,url.getPort());assertEquals("context/path",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("10.20.130.230:20880/context/path");assertNull(url.getProtocol());assertNull(url.getUsername());assertNull(url.getPassword());assertEquals("10.20.130.230",url.getHost());assertEquals(20880,url.getPort());assertEquals("context/path",url.getPath());assertEquals(0,url.getParameters().size());url=URL.valueOf("admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan");assertNull(url.getProtocol());assertEquals("admin",url.getUsername());assertEquals("hello1234",url.getPassword());assertEquals("10.20.130.230",url.getHost());assertEquals(20880,url.getPort());assertEquals("context/path",url.getPath());assertEquals(2,url.getParameters().size());assertEquals("1.0.0",url.getParameter("version"));assertEquals("morgan",url.getParameter("application"));}
}