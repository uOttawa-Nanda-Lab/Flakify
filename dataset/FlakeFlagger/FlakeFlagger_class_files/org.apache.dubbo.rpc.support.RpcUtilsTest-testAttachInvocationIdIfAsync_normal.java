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
package org.apache.dubbo.rpc.support;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcInvocation;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RpcUtilsTest {

    /**
	 * regular scenario: async invocation in URL verify: 1. whether invocationId is set correctly, 2. idempotent or not
	 */@Test public void testAttachInvocationIdIfAsync_normal(){URL url=URL.valueOf("dubbo://localhost/?test.async=true");Map<String, String> attachments=new HashMap<String, String>();attachments.put("aa","bb");Invocation inv=new RpcInvocation("test",new Class[]{},new String[]{},attachments);RpcUtils.attachInvocationIdIfAsync(url,inv);long id1=RpcUtils.getInvocationId(inv);RpcUtils.attachInvocationIdIfAsync(url,inv);long id2=RpcUtils.getInvocationId(inv);assertTrue(id1 == id2);assertTrue(id1 >= 0);assertEquals("bb",attachments.get("aa"));}
}
