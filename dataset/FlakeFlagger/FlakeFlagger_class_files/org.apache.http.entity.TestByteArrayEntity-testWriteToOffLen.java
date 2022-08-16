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

package org.apache.http.entity;

import java.io.ByteArrayOutputStream;

import org.apache.http.protocol.HTTP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ByteArrayEntity}.
 *
 */
public class TestByteArrayEntity {

    @Test public void testWriteToOffLen() throws Exception{byte[] bytes="Message content".getBytes(HTTP.US_ASCII);int off=8;int len=7;ByteArrayEntity httpentity=new ByteArrayEntity(bytes,off,len);ByteArrayOutputStream out=new ByteArrayOutputStream();httpentity.writeTo(out);byte[] bytes2=out.toByteArray();Assert.assertNotNull(bytes2);Assert.assertEquals(len,bytes2.length);for (int i=0;i < len;i++){Assert.assertEquals(bytes[i + off],bytes2[i]);}out=new ByteArrayOutputStream();httpentity.writeTo(out);bytes2=out.toByteArray();Assert.assertNotNull(bytes2);Assert.assertEquals(len,bytes2.length);for (int i=0;i < len;i++){Assert.assertEquals(bytes[i + off],bytes2[i]);}try {httpentity.writeTo(null);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}}

}
