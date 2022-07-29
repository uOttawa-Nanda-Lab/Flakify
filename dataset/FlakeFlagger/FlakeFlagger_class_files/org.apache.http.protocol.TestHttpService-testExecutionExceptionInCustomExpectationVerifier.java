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

    @Test public void testExecutionExceptionInCustomExpectationVerifier() throws Exception{HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);ConnectionReuseStrategy connReuseStrategy=Mockito.mock(ConnectionReuseStrategy.class);HttpResponseFactory responseFactory=Mockito.mock(HttpResponseFactory.class);HttpExpectationVerifier expectationVerifier=Mockito.mock(HttpExpectationVerifier.class);HttpRequestHandlerResolver handlerResolver=Mockito.mock(HttpRequestHandlerResolver.class);HttpParams params=new SyncBasicHttpParams();HttpService httpservice=new HttpService(httprocessor,connReuseStrategy,responseFactory,handlerResolver,expectationVerifier,params);HttpContext context=new BasicHttpContext();HttpServerConnection conn=Mockito.mock(HttpServerConnection.class);HttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("POST","/");request.addHeader(HTTP.EXPECT_DIRECTIVE,HTTP.EXPECT_CONTINUE);InputStream instream=Mockito.mock(InputStream.class);InputStreamEntity entity=new InputStreamEntity(instream,-1);request.setEntity(entity);Mockito.when(conn.receiveRequestHeader()).thenReturn(request);HttpResponse resp100=new BasicHttpResponse(HttpVersion.HTTP_1_1,100,"Continue");Mockito.when(responseFactory.newHttpResponse(HttpVersion.HTTP_1_1,100,context)).thenReturn(resp100);HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,500,"Oppsie");Mockito.when(responseFactory.newHttpResponse(HttpVersion.HTTP_1_0,500,context)).thenReturn(response);Mockito.doThrow(new HttpException("Oopsie")).when(expectationVerifier).verify(request,resp100,context);Mockito.when(connReuseStrategy.keepAlive(response,context)).thenReturn(false);httpservice.handleRequest(conn,context);Assert.assertSame(conn,context.getAttribute(ExecutionContext.HTTP_CONNECTION));Assert.assertSame(request,context.getAttribute(ExecutionContext.HTTP_REQUEST));Assert.assertSame(response,context.getAttribute(ExecutionContext.HTTP_RESPONSE));Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,response.getStatusLine().getStatusCode());Mockito.verify(conn).sendResponseHeader(response);Mockito.verify(conn,Mockito.never()).receiveRequestEntity(request);Mockito.verify(httprocessor).process(response,context);Mockito.verify(conn).sendResponseHeader(response);Mockito.verify(conn).sendResponseEntity(response);Mockito.verify(conn).flush();Mockito.verify(conn).close();}

}
