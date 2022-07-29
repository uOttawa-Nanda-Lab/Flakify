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


import org.junit.Ignore;
import org.junit.Test;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetUtilsTest {
    @Test public void testFilterLocalHost() throws Exception{assertNull(NetUtils.filterLocalHost(null));assertEquals(NetUtils.filterLocalHost(""),"");String host=NetUtils.filterLocalHost("dubbo://127.0.0.1:8080/foo");assertThat(host,equalTo("dubbo://" + NetUtils.getLocalHost() + ":8080/foo"));host=NetUtils.filterLocalHost("127.0.0.1:8080");assertThat(host,equalTo(NetUtils.getLocalHost() + ":8080"));host=NetUtils.filterLocalHost("0.0.0.0");assertThat(host,equalTo(NetUtils.getLocalHost()));host=NetUtils.filterLocalHost("88.88.88.88");assertThat(host,equalTo(host));}
}