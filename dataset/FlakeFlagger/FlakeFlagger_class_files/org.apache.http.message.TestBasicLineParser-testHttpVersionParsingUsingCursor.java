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

    @Test public void testHttpVersionParsingUsingCursor() throws Exception{String s="HTTP/1.1";CharArrayBuffer buffer=new CharArrayBuffer(16);buffer.append(s);ParserCursor cursor=new ParserCursor(0,s.length());LineParser parser=BasicLineParser.DEFAULT;HttpVersion version=(HttpVersion)parser.parseProtocolVersion(buffer,cursor);Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());Assert.assertEquals("HTTP major version number",1,version.getMajor());Assert.assertEquals("HTTP minor version number",1,version.getMinor());Assert.assertEquals("HTTP version number","HTTP/1.1",version.toString());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="HTTP/1.123 123";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());version=(HttpVersion)parser.parseProtocolVersion(buffer,cursor);Assert.assertEquals("HTTP protocol name","HTTP",version.getProtocol());Assert.assertEquals("HTTP major version number",1,version.getMajor());Assert.assertEquals("HTTP minor version number",123,version.getMinor());Assert.assertEquals("HTTP version number","HTTP/1.123",version.toString());Assert.assertEquals(' ',buffer.charAt(cursor.getPos()));Assert.assertEquals(s.length() - 4,cursor.getPos());Assert.assertFalse(cursor.atEnd());}

}
