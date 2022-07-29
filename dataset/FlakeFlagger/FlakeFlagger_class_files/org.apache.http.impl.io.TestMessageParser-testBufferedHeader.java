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

    @Test public void testBufferedHeader() throws Exception{String s="header1  : stuff; param1 = value1; param2 = \"value 2\" \r\n" + "\r\n";SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");Header[] headers=AbstractMessageParser.parseHeaders(receiver,-1,-1,null);Assert.assertNotNull(headers);Assert.assertEquals(1,headers.length);Assert.assertEquals("header1  : stuff; param1 = value1; param2 = \"value 2\" ",headers[0].toString());HeaderElement[] elements=headers[0].getElements();Assert.assertNotNull(elements);Assert.assertEquals(1,elements.length);Assert.assertEquals("stuff",elements[0].getName());Assert.assertEquals(null,elements[0].getValue());NameValuePair[] params=elements[0].getParameters();Assert.assertNotNull(params);Assert.assertEquals(2,params.length);Assert.assertEquals("param1",params[0].getName());Assert.assertEquals("value1",params[0].getValue());Assert.assertEquals("param2",params[1].getName());Assert.assertEquals("value 2",params[1].getValue());}

}

