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

package org.apache.http.protocol;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.junit.Assert;
import org.junit.Test;

public class TestBasicHttpProcessor {

    static class TestHttpRequestInterceptorPlaceHolder implements HttpRequestInterceptor {

        public void process(
                HttpRequest request,
                HttpContext context) throws HttpException, IOException {
        }
    }

    @Test public void testAddTailRequestInterceptor(){HttpRequestInterceptor itcp1=new TestHttpRequestInterceptorPlaceHolder();HttpRequestInterceptor itcp2=new TestHttpRequestInterceptorPlaceHolder();BasicHttpProcessor instance=new BasicHttpProcessor();instance.addRequestInterceptor(itcp1);Assert.assertEquals(1,instance.getRequestInterceptorCount());Assert.assertSame(itcp1,instance.getRequestInterceptor(0));instance.addRequestInterceptor(itcp2,1);int itcpCount=instance.getRequestInterceptorCount();Assert.assertEquals(2,itcpCount);Assert.assertSame(itcp1,instance.getRequestInterceptor(0));Assert.assertSame(itcp2,instance.getRequestInterceptor(itcpCount - 1));}

}
