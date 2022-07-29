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

    @Test public void testEntityWithTransferEncoding() throws Exception{SessionInputBuffer inbuffer=new SessionInputBufferMock("0\r\n","US-ASCII");HttpMessage message=new DummyHttpMessage();message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);message.addHeader("Content-Type","unknown");message.addHeader("Transfer-Encoding","identity, chunked");message.addHeader("Content-Length","plain wrong");EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());HttpEntity entity=entitygen.deserialize(inbuffer,message);Assert.assertNotNull(entity);Assert.assertEquals(-1,entity.getContentLength());Assert.assertTrue(entity.isChunked());Assert.assertTrue(entity.getContent() instanceof ChunkedInputStream);message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,true);entity=entitygen.deserialize(inbuffer,message);Assert.assertNotNull(entity);Assert.assertEquals(-1,entity.getContentLength());Assert.assertTrue(entity.isChunked());Assert.assertTrue(entity.getContent() instanceof ChunkedInputStream);}

}

