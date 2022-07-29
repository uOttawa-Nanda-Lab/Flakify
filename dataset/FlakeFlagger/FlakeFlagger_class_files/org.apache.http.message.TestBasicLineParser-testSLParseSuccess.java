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

package org.apache.http.message;

import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link BasicLineParser}.
 *
 */
public class TestBasicLineParser {

    @Test public void testSLParseSuccess() throws Exception{StatusLine statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 200 OK",null);Assert.assertEquals("HTTP/1.1 200 OK",statusLine.toString());Assert.assertEquals(HttpVersion.HTTP_1_1,statusLine.getProtocolVersion());Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("OK",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 404 Not Found",null);Assert.assertEquals(404,statusLine.getStatusCode());Assert.assertEquals("Not Found",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 404 Non Trouve",null);Assert.assertEquals("Non Trouve",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 404 Not Found\r\n",null);Assert.assertEquals("Not Found",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 200 ",null);Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1 200",null);Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("HTTP/1.1     200 OK",null);Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("OK",statusLine.getReasonPhrase());statusLine=BasicLineParser.parseStatusLine("\rHTTP/1.1 200 OK",null);Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("OK",statusLine.getReasonPhrase());Assert.assertEquals(HttpVersion.HTTP_1_1,statusLine.getProtocolVersion());statusLine=BasicLineParser.parseStatusLine("  HTTP/1.1 200 OK",null);Assert.assertEquals(200,statusLine.getStatusCode());Assert.assertEquals("OK",statusLine.getReasonPhrase());Assert.assertEquals(HttpVersion.HTTP_1_1,statusLine.getProtocolVersion());}

}
