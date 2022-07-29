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

    @Test public void testInvalidCharArrayAppend() throws Exception{CharArrayBuffer buffer=new CharArrayBuffer(4);buffer.append((char[])null,0,0);char[] tmp=new char[]{'1','2','3','4'};try {buffer.append(tmp,-1,0);Assert.fail("IndexOutOfBoundsException should have been thrown");} catch (IndexOutOfBoundsException ex){}try {buffer.append(tmp,0,-1);Assert.fail("IndexOutOfBoundsException should have been thrown");} catch (IndexOutOfBoundsException ex){}try {buffer.append(tmp,0,8);Assert.fail("IndexOutOfBoundsException should have been thrown");} catch (IndexOutOfBoundsException ex){}try {buffer.append(tmp,10,Integer.MAX_VALUE);Assert.fail("IndexOutOfBoundsException should have been thrown");} catch (IndexOutOfBoundsException ex){}try {buffer.append(tmp,2,4);Assert.fail("IndexOutOfBoundsException should have been thrown");} catch (IndexOutOfBoundsException ex){}}

}
