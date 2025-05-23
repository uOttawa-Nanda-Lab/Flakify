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
package org.apache.dubbo.config.cache;

import org.apache.dubbo.cache.Cache;
import org.apache.dubbo.cache.CacheFactory;
import org.apache.dubbo.cache.support.threadlocal.ThreadLocalCache;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.RpcInvocation;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * CacheTest
 */
public class CacheTest extends TestCase {

    @Test public void testCacheProvider() throws Exception{CacheFactory cacheFactory=ExtensionLoader.getExtensionLoader(CacheFactory.class).getAdaptiveExtension();Map<String, String> parameters=new HashMap<String, String>();parameters.put("findCache.cache","threadlocal");URL url=new URL("dubbo","127.0.0.1",29582,"org.apache.dubbo.config.cache.CacheService",parameters);Invocation invocation=new RpcInvocation("findCache",new Class[]{String.class},new String[]{"0"},null,null);Cache cache=cacheFactory.getCache(url,invocation);assertTrue(cache instanceof ThreadLocalCache);}

}
