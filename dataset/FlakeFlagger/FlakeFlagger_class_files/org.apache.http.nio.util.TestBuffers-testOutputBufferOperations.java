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

    @Test public void testOutputBufferOperations() throws IOException{ByteArrayOutputStream outstream=new ByteArrayOutputStream();WritableByteChannel channel=Channels.newChannel(outstream);HttpParams params=new BasicHttpParams();SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();ContentEncoder encoder=new ContentEncoderMock(channel,outbuf,metrics);SimpleOutputBuffer buffer=new SimpleOutputBuffer(4,new DirectByteBufferAllocator());buffer.write(EncodingUtils.getAsciiBytes("stuff"));buffer.write(';');buffer.produceContent(encoder);buffer.write(EncodingUtils.getAsciiBytes("more "));buffer.write(EncodingUtils.getAsciiBytes("stuff"));buffer.produceContent(encoder);byte[] content=outstream.toByteArray();Assert.assertEquals("stuff;more stuff",EncodingUtils.getAsciiString(content));}

}
