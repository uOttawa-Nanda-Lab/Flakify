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

package org.apache.http.nio.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.apache.http.ReadableByteChannelMock;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.nio.reactor.SessionOutputBufferImpl;
import org.apache.http.io.BufferInfo;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.reactor.SessionOutputBuffer;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Buffer tests.
 */
public class TestBuffers {

    @Test public void testHeapByteBufferAllocator(){HeapByteBufferAllocator allocator=new HeapByteBufferAllocator();ByteBuffer buffer=allocator.allocate(1);Assert.assertNotNull(buffer);Assert.assertFalse(buffer.isDirect());Assert.assertEquals(0,buffer.position());Assert.assertEquals(1,buffer.limit());Assert.assertEquals(1,buffer.capacity());buffer=allocator.allocate(2048);Assert.assertFalse(buffer.isDirect());Assert.assertEquals(0,buffer.position());Assert.assertEquals(2048,buffer.limit());Assert.assertEquals(2048,buffer.capacity());buffer=allocator.allocate(0);Assert.assertFalse(buffer.isDirect());Assert.assertEquals(0,buffer.position());Assert.assertEquals(0,buffer.limit());Assert.assertEquals(0,buffer.capacity());}

}
