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
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for header value parsing.
 *
 * @version $Id$
 */
public class TestBasicHeaderValueParser {

    @Test public void testNVParseAllWithCursor(){HeaderValueParser parser=BasicHeaderValueParser.DEFAULT;String s="test; test1 =  stuff   ; test2 =  \"stuff; stuff\"; test3=\"stuff";CharArrayBuffer buffer=new CharArrayBuffer(16);buffer.append(s);ParserCursor cursor=new ParserCursor(0,s.length());NameValuePair[] params=parser.parseParameters(buffer,cursor);Assert.assertEquals("test",params[0].getName());Assert.assertEquals(null,params[0].getValue());Assert.assertEquals("test1",params[1].getName());Assert.assertEquals("stuff",params[1].getValue());Assert.assertEquals("test2",params[2].getName());Assert.assertEquals("stuff; stuff",params[2].getValue());Assert.assertEquals("test3",params[3].getName());Assert.assertEquals("\"stuff",params[3].getValue());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="test; test1 =  stuff   ; test2 =  \"stuff; stuff\"; test3=\"stuff\",123";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());params=parser.parseParameters(buffer,cursor);Assert.assertEquals("test",params[0].getName());Assert.assertEquals(null,params[0].getValue());Assert.assertEquals("test1",params[1].getName());Assert.assertEquals("stuff",params[1].getValue());Assert.assertEquals("test2",params[2].getName());Assert.assertEquals("stuff; stuff",params[2].getValue());Assert.assertEquals("test3",params[3].getName());Assert.assertEquals("stuff",params[3].getValue());Assert.assertEquals(s.length() - 3,cursor.getPos());Assert.assertFalse(cursor.atEnd());s="  ";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());params=parser.parseParameters(buffer,cursor);Assert.assertEquals(0,params.length);}

}
