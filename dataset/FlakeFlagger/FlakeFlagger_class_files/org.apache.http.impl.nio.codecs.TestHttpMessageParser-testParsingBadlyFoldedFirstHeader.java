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

package org.apache.http.impl.nio.codecs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpVersion;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.nio.reactor.SessionInputBufferImpl;
import org.apache.http.nio.NHttpMessageParser;
import org.apache.http.nio.reactor.SessionInputBuffer;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Simple tests for {@link AbstractMessageParser}.
 */
public class TestHttpMessageParser {

    private static ReadableByteChannel newChannel(final String s, final String charset)
            throws UnsupportedEncodingException {
        return Channels.newChannel(new ByteArrayInputStream(s.getBytes(charset)));
    }

    private static ReadableByteChannel newChannel(final String s)
            throws UnsupportedEncodingException {
        return newChannel(s, "US-ASCII");
    }

    @Test public void testParsingBadlyFoldedFirstHeader() throws Exception{HttpParams params=new BasicHttpParams();SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);HttpRequestFactory requestFactory=new DefaultHttpRequestFactory();NHttpMessageParser<HttpRequest> requestParser=new DefaultHttpRequestParser(inbuf,null,requestFactory,params);requestParser.fillBuffer(newChannel("GET /whatev"));HttpRequest request=requestParser.parse();Assert.assertNull(request);requestParser.fillBuffer(newChannel("er HTTP/1.1\r"));request=requestParser.parse();Assert.assertNull(request);requestParser.fillBuffer(newChannel("\n  Some header: stuff\r\n"));request=requestParser.parse();Assert.assertNull(request);requestParser.fillBuffer(newChannel("   more stuff\r\n"));request=requestParser.parse();Assert.assertNull(request);requestParser.fillBuffer(newChannel("\r\n"));request=requestParser.parse();Assert.assertNotNull(request);Assert.assertEquals("/whatever",request.getRequestLine().getUri());Assert.assertEquals(1,request.getAllHeaders().length);Assert.assertEquals("stuff more stuff",request.getFirstHeader("Some header").getValue());}

}
