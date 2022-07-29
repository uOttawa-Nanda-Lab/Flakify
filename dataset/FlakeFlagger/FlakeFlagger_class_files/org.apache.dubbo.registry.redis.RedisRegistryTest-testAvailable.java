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
package org.apache.dubbo.registry.redis;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.Registry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.embedded.RedisServer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.dubbo.common.Constants.BACKUP_KEY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedisRegistryTest {

    private String service = "org.apache.dubbo.test.injvmServie";
    private URL serviceUrl = URL.valueOf("redis://redis/" + service + "?notify=false&methods=test1,test2");
    private RedisServer redisServer;
    private RedisRegistry redisRegistry;
    private URL registryUrl;

    @Before
    public void setUp() throws Exception {
        int redisPort = NetUtils.getAvailablePort();
        this.redisServer = new RedisServer(redisPort);
        this.redisServer.start();
        this.registryUrl = URL.valueOf("redis://localhost:" + redisPort);

        redisRegistry = (RedisRegistry) new RedisRegistryFactory().createRegistry(registryUrl);
    }

    @Test
    public void testAvailable() {
        redisRegistry.register(serviceUrl);
        assertThat(redisRegistry.isAvailable(), is(true));

        redisRegistry.destroy();
        assertThat(redisRegistry.isAvailable(), is(false));
    }
}