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

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UrlUtilsTest {

    String localAddress = "127.0.0.1";

    @Test public void testParseUrl2(){String address="192.168.0.1";String backupAddress1="192.168.0.2";String backupAddress2="192.168.0.3";Map<String, String> parameters=new HashMap<String, String>();parameters.put("username","root");parameters.put("password","alibaba");parameters.put("port","10000");parameters.put("protocol","dubbo");URL url=UrlUtils.parseURL(address + "," + backupAddress1 + "," + backupAddress2,parameters);assertEquals("192.168.0.1:10000",url.getAddress());assertEquals("root",url.getUsername());assertEquals("alibaba",url.getPassword());assertEquals(10000,url.getPort());assertEquals("dubbo",url.getProtocol());assertEquals("192.168.0.2" + "," + "192.168.0.3",url.getParameter("backup"));}
}