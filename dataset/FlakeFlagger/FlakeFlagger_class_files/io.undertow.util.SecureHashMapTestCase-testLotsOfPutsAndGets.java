/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.util;

import io.undertow.server.session.SecureRandomSessionIdGenerator;
import io.undertow.server.session.SessionIdGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stuart Douglas
 */
public class SecureHashMapTestCase {

    @Test public void testLotsOfPutsAndGets(){SessionIdGenerator generator=new SecureRandomSessionIdGenerator();final Map<String, String> reference=new HashMap<String, String>();final SecureHashMap<String, String> map=new SecureHashMap<String, String>();for (int i=0;i < 10000;++i){String key=generator.createSessionId();String value=generator.createSessionId();map.put(key,value);reference.put(key,value);}for (Map.Entry<String, String> entry:reference.entrySet()){Assert.assertEquals(entry.getValue(),map.get(entry.getKey()));Assert.assertEquals(entry.getValue(),map.remove(entry.getKey()));}Assert.assertEquals(0,map.size());}


}
