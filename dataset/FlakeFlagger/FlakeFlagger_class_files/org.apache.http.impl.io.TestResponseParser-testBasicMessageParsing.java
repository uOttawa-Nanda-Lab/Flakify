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

package org.apache.http.impl.io;

import java.io.InterruptedIOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NoHttpResponseException;
import org.apache.http.StatusLine;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.SessionInputBufferMock;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.params.BasicHttpParams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link HttpResponseParser}.
 */
public class TestResponseParser {

    @Test
    public void testBasicMessageParsing() throws Exception {
        String s =
            "HTTP/1.1 200 OK\r\n" +
            "Server: whatever\r\n" +
            "Date: some date\r\n" +
            "Set-Cookie: c1=stuff\r\n" +
            "\r\n";
        SessionInputBuffer inbuffer = new SessionInputBufferMock(s, "US-ASCII");

        HttpResponseParser parser = new HttpResponseParser(
                inbuffer,
                BasicLineParser.DEFAULT,
                new DefaultHttpResponseFactory(),
                new BasicHttpParams());

        HttpResponse httpresponse = parser.parse();

        StatusLine statusline = httpresponse.getStatusLine();
        Assert.assertNotNull(statusline);
        Assert.assertEquals(200, statusline.getStatusCode());
        Assert.assertEquals("OK", statusline.getReasonPhrase());
        Assert.assertEquals(HttpVersion.HTTP_1_1, statusline.getProtocolVersion());
        Header[] headers = httpresponse.getAllHeaders();
        Assert.assertEquals(3, headers.length);
    }

}

