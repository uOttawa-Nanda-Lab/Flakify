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

package org.apache.http.impl.entity;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.ProtocolException;
import org.apache.http.impl.SessionInputBufferMock;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.params.CoreProtocolPNames;
import org.junit.Assert;
import org.junit.Test;

public class TestEntityDeserializer {

    @Test public void testEntityWithMultipleContentLength() throws Exception{SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{'0'});HttpMessage message=new DummyHttpMessage();message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);message.addHeader("Content-Type","unknown");message.addHeader("Content-Length","0");message.addHeader("Content-Length","0");message.addHeader("Content-Length","1");EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());HttpEntity entity=entitygen.deserialize(inbuffer,message);Assert.assertNotNull(entity);Assert.assertEquals(1,entity.getContentLength());Assert.assertFalse(entity.isChunked());InputStream instream=entity.getContent();Assert.assertNotNull(instream);Assert.assertTrue(instream instanceof ContentLengthInputStream);message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);try {entitygen.deserialize(inbuffer,message);Assert.fail("ProtocolException should have been thrown");} catch (ProtocolException ex){}}

}

