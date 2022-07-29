/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link HttpHost}.
 *
 */
public class TestHttpHost {

    @Test
    public void testConstructor() {
        HttpHost host1 = new HttpHost("somehost");
        Assert.assertEquals("somehost", host1.getHostName());
        Assert.assertEquals(-1, host1.getPort());
        Assert.assertEquals("http", host1.getSchemeName());
        HttpHost host2 = new HttpHost("somehost", 8080);
        Assert.assertEquals("somehost", host2.getHostName());
        Assert.assertEquals(8080, host2.getPort());
        Assert.assertEquals("http", host2.getSchemeName());
        HttpHost host3 = new HttpHost("somehost", -1);
        Assert.assertEquals("somehost", host3.getHostName());
        Assert.assertEquals(-1, host3.getPort());
        Assert.assertEquals("http", host3.getSchemeName());
        HttpHost host4 = new HttpHost("somehost", 443, "https");
        Assert.assertEquals("somehost", host4.getHostName());
        Assert.assertEquals(443, host4.getPort());
        Assert.assertEquals("https", host4.getSchemeName());
        try {
            new HttpHost(null, -1, null);
            Assert.fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException ex) {
            //expected
        }
    }

}
