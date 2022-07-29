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

    @Test public void testNVParseUsingCursor(){HeaderValueParser parser=BasicHeaderValueParser.DEFAULT;String s="test";CharArrayBuffer buffer=new CharArrayBuffer(16);buffer.append(s);ParserCursor cursor=new ParserCursor(0,s.length());NameValuePair param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals(null,param.getValue());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="test;";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals(null,param.getValue());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="test  ,12";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals(null,param.getValue());Assert.assertEquals(s.length() - 2,cursor.getPos());Assert.assertFalse(cursor.atEnd());s="test=stuff";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals("stuff",param.getValue());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="   test  =   stuff ";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals("stuff",param.getValue());Assert.assertEquals(s.length(),cursor.getPos());Assert.assertTrue(cursor.atEnd());s="   test  =   stuff ;1234";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals("stuff",param.getValue());Assert.assertEquals(s.length() - 4,cursor.getPos());Assert.assertFalse(cursor.atEnd());s="test  = \"stuff\"";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals("stuff",param.getValue());s="test  = \"  stuff\\\"\"";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals("  stuff\\\"",param.getValue());s="  test";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("test",param.getName());Assert.assertEquals(null,param.getValue());s="  ";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("",param.getName());Assert.assertEquals(null,param.getValue());s=" = stuff ";buffer=new CharArrayBuffer(16);buffer.append(s);cursor=new ParserCursor(0,s.length());param=parser.parseNameValuePair(buffer,cursor);Assert.assertEquals("",param.getName());Assert.assertEquals("stuff",param.getValue());}

}
