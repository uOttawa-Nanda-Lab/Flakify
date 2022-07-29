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

package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDefaultConnectionReuseStrategy {

    /** HTTP context. */
    private HttpContext context;

    /** The reuse strategy to be tested. */
    private ConnectionReuseStrategy reuseStrategy;

    @Before
    public void setUp() {
        reuseStrategy = new DefaultConnectionReuseStrategy();
        context = new BasicHttpContext(null);
    }

    @Test public void testConnectionTokens4() throws Exception{HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");response.addHeader("Transfer-Encoding","chunked");response.addHeader("Connection","yadda, close, dumdy");response.addHeader("Proxy-Connection","keep-alive");Assert.assertFalse(reuseStrategy.keepAlive(response,context));}

}

