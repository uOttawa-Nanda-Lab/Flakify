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
    public void testToString() {
        HttpHost host1 = new HttpHost("somehost");
        Assert.assertEquals("http://somehost", host1.toString());
        HttpHost host2 = new HttpHost("somehost", -1);
        Assert.assertEquals("http://somehost", host2.toString());
        HttpHost host3 = new HttpHost("somehost", -1);
        Assert.assertEquals("http://somehost", host3.toString());
        HttpHost host4 = new HttpHost("somehost", 8888);
        Assert.assertEquals("http://somehost:8888", host4.toString());
        HttpHost host5 = new HttpHost("somehost", -1, "myhttp");
        Assert.assertEquals("myhttp://somehost", host5.toString());
        HttpHost host6 = new HttpHost("somehost", 80, "myhttp");
        Assert.assertEquals("myhttp://somehost:80", host6.toString());
    }

}
