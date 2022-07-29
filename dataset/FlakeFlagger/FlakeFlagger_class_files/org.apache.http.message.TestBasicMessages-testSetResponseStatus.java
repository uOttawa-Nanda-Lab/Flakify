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

package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.params.CoreProtocolPNames;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Header}.
 *
 */
public class TestBasicMessages {

    @Test public void testSetResponseStatus(){HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");Assert.assertNotNull(response.getProtocolVersion());Assert.assertNotNull(response.getStatusLine());Assert.assertEquals(200,response.getStatusLine().getStatusCode());response=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_BAD_REQUEST,"Bad Request");Assert.assertNotNull(response.getProtocolVersion());Assert.assertEquals(HttpVersion.HTTP_1_0,response.getProtocolVersion());Assert.assertEquals(HttpStatus.SC_BAD_REQUEST,response.getStatusLine().getStatusCode());response=new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_INTERNAL_SERVER_ERROR,"whatever"));Assert.assertNotNull(response.getProtocolVersion());Assert.assertEquals(HttpVersion.HTTP_1_1,response.getProtocolVersion());Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,response.getStatusLine().getStatusCode());Assert.assertEquals("whatever",response.getStatusLine().getReasonPhrase());response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");try {response.setStatusCode(-23);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");try {response.setStatusLine(null,200);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");try {response.setStatusLine(null);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}}

}

