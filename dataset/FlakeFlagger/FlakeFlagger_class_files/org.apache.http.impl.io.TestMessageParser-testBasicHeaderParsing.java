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

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.impl.SessionInputBufferMock;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BufferedHeader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link AbstractMessageParser}.
 */
public class TestMessageParser {

    @Test public void testBasicHeaderParsing() throws Exception{String s="header1: stuff\r\n" + "header2  : stuff \r\n" + "header3: stuff\r\n" + "     and more stuff\r\n" + "\t and even more stuff\r\n" + "     \r\n" + "\r\n";SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");Header[] headers=AbstractMessageParser.parseHeaders(receiver,-1,-1,null);Assert.assertNotNull(headers);Assert.assertEquals(3,headers.length);Assert.assertEquals("header1",headers[0].getName());Assert.assertEquals("stuff",headers[0].getValue());Assert.assertEquals("header2",headers[1].getName());Assert.assertEquals("stuff",headers[1].getValue());Assert.assertEquals("header3",headers[2].getName());Assert.assertEquals("stuff and more stuff and even more stuff",headers[2].getValue());Header h=headers[0];Assert.assertTrue(h instanceof BufferedHeader);Assert.assertNotNull(((BufferedHeader)h).getBuffer());Assert.assertEquals("header1: stuff",((BufferedHeader)h).toString());Assert.assertEquals(8,((BufferedHeader)h).getValuePos());}

}

