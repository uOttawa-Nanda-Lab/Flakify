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
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.URL;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RpcContextTest {

    @Test public void testObject(){RpcContext context=RpcContext.getContext();Map<String, Object> map=new HashMap<String, Object>();map.put("_11","1111");map.put("_22","2222");map.put(".33","3333");map.forEach(context::set);Assert.assertEquals(map,context.get());Assert.assertEquals("1111",context.get("_11"));context.set("_11","11.11");Assert.assertEquals("11.11",context.get("_11"));context.set(null,"22222");context.set("_22",null);Assert.assertEquals("22222",context.get(null));Assert.assertNull(context.get("_22"));Assert.assertNull(context.get("_33"));Assert.assertEquals("3333",context.get(".33"));map.keySet().forEach(context::remove);Assert.assertNull(context.get("_11"));}

}
