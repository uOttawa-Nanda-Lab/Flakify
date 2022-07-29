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
 * Unit tests for {@link ByteArrayBuffer}.
 *
 */
public class TestByteArrayBuffer {

    @Test public void testIndexOf() throws Exception{final byte COLON=(byte)':';final byte COMMA=(byte)',';byte[] bytes="name1: value1; name2: value2".getBytes("US-ASCII");int index1=5;int index2=20;ByteArrayBuffer buffer=new ByteArrayBuffer(16);buffer.append(bytes,0,bytes.length);Assert.assertEquals(index1,buffer.indexOf(COLON));Assert.assertEquals(-1,buffer.indexOf(COMMA));Assert.assertEquals(index1,buffer.indexOf(COLON,-1,11));Assert.assertEquals(index1,buffer.indexOf(COLON,0,1000));Assert.assertEquals(-1,buffer.indexOf(COLON,2,1));Assert.assertEquals(index2,buffer.indexOf(COLON,index1 + 1,buffer.length()));}

}
