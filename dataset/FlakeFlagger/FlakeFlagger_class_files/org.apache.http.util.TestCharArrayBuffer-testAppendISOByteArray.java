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

package org.apache.http.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CharArrayBuffer}.
 *
 */
public class TestCharArrayBuffer {

    @Test public void testAppendISOByteArray() throws Exception{byte[] b=new byte[]{0x00,0x20,0x7F,-0x80,-0x01};CharArrayBuffer buffer=new CharArrayBuffer(8);buffer.append(b,0,b.length);char[] ch=buffer.toCharArray();Assert.assertNotNull(ch);Assert.assertEquals(5,ch.length);Assert.assertEquals(0x00,ch[0]);Assert.assertEquals(0x20,ch[1]);Assert.assertEquals(0x7F,ch[2]);Assert.assertEquals(0x80,ch[3]);Assert.assertEquals(0xFF,ch[4]);}

}
