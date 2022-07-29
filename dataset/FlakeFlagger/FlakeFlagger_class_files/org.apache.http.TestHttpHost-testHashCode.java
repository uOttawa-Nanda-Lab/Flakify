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

    @Test public void testHashCode(){HttpHost host1=new HttpHost("somehost",8080,"http");HttpHost host2=new HttpHost("somehost",80,"http");HttpHost host3=new HttpHost("someotherhost",8080,"http");HttpHost host4=new HttpHost("somehost",80,"http");HttpHost host5=new HttpHost("SomeHost",80,"http");HttpHost host6=new HttpHost("SomeHost",80,"myhttp");Assert.assertTrue(host1.hashCode() == host1.hashCode());Assert.assertTrue(host1.hashCode() != host2.hashCode());Assert.assertTrue(host1.hashCode() != host3.hashCode());Assert.assertTrue(host2.hashCode() == host4.hashCode());Assert.assertTrue(host2.hashCode() == host5.hashCode());Assert.assertTrue(host5.hashCode() != host6.hashCode());}

}
