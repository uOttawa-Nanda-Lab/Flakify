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
import org.apache.http.HttpMessage;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Header}.
 *
 */
public class TestAbstractMessage {

    static class TestHttpMessage extends AbstractHttpMessage {

        public TestHttpMessage() {
            super();
        }

        public ProtocolVersion getProtocolVersion() {
            return HttpProtocolParams.getVersion(this.getParams());
        }

    }

    @Test public void testBasicHeaderOps(){HttpMessage message=new TestHttpMessage();Assert.assertFalse(message.containsHeader("whatever"));message.addHeader("name","1");message.addHeader("name","2");Header[] headers=message.getAllHeaders();Assert.assertNotNull(headers);Assert.assertEquals(2,headers.length);Header h=message.getFirstHeader("name");Assert.assertNotNull(h);Assert.assertEquals("1",h.getValue());message.setHeader("name","3");h=message.getFirstHeader("name");Assert.assertNotNull(h);Assert.assertEquals("3",h.getValue());h=message.getLastHeader("name");Assert.assertNotNull(h);Assert.assertEquals("2",h.getValue());message.addHeader(null);message.setHeader(null);headers=message.getHeaders("name");Assert.assertNotNull(headers);Assert.assertEquals(2,headers.length);Assert.assertEquals("3",headers[0].getValue());Assert.assertEquals("2",headers[1].getValue());message.addHeader("name","4");headers[1]=new BasicHeader("name","5");message.setHeaders(headers);headers=message.getHeaders("name");Assert.assertNotNull(headers);Assert.assertEquals(2,headers.length);Assert.assertEquals("3",headers[0].getValue());Assert.assertEquals("5",headers[1].getValue());message.setHeader("whatever",null);message.removeHeaders("name");message.removeHeaders(null);headers=message.getAllHeaders();Assert.assertNotNull(headers);Assert.assertEquals(1,headers.length);Assert.assertEquals(null,headers[0].getValue());message.removeHeader(message.getFirstHeader("whatever"));headers=message.getAllHeaders();Assert.assertNotNull(headers);Assert.assertEquals(0,headers.length);}

}

