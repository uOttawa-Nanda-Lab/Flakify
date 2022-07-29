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

import java.io.InputStream;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.ProtocolException;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestHttpService {

    @Test public void testInvalidInitialization() throws Exception{HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);ConnectionReuseStrategy connReuseStrategy=Mockito.mock(ConnectionReuseStrategy.class);HttpResponseFactory responseFactory=Mockito.mock(HttpResponseFactory.class);HttpRequestHandlerResolver handlerResolver=Mockito.mock(HttpRequestHandlerResolver.class);HttpParams params=new SyncBasicHttpParams();try {new HttpService(null,connReuseStrategy,responseFactory,handlerResolver,params);Assert.fail("IllegalArgumentException expected");} catch (IllegalArgumentException expected){}try {new HttpService(httprocessor,null,responseFactory,handlerResolver,params);Assert.fail("IllegalArgumentException expected");} catch (IllegalArgumentException expected){}try {new HttpService(httprocessor,connReuseStrategy,null,handlerResolver,params);Assert.fail("IllegalArgumentException expected");} catch (IllegalArgumentException expected){}try {new HttpService(httprocessor,connReuseStrategy,responseFactory,handlerResolver,null);Assert.fail("IllegalArgumentException expected");} catch (IllegalArgumentException expected){}}

}
