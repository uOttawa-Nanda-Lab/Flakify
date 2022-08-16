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

package org.apache.http.impl.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpCoreNIOTestBase;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.OoopsieRuntimeException;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.nio.NHttpConnection;
import org.apache.http.nio.protocol.BufferingHttpClientHandler;
import org.apache.http.nio.protocol.BufferingHttpServiceHandler;
import org.apache.http.nio.protocol.EventListener;
import org.apache.http.nio.protocol.HttpRequestExecutionHandler;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.nio.reactor.IOReactorExceptionHandler;
import org.apache.http.nio.reactor.IOReactorStatus;
import org.apache.http.nio.reactor.ListenerEndpoint;
import org.apache.http.nio.reactor.SessionRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;
import org.apache.http.testserver.SimpleEventListener;
import org.apache.http.testserver.SimpleHttpRequestHandlerResolver;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for basic I/O functionality.
 */
public class TestDefaultIOReactors extends HttpCoreNIOTestBase {

    @Test public void testHandledRuntimeException() throws Exception{final CountDownLatch requestConns=new CountDownLatch(1);HttpRequestHandler requestHandler=new HttpRequestHandler(){public void handle(final HttpRequest request,final HttpResponse response,final HttpContext context) throws HttpException,IOException{throw new OoopsieRuntimeException();}};HttpRequestExecutionHandler requestExecutionHandler=new HttpRequestExecutionHandler(){public void initalizeContext(final HttpContext context,final Object attachment){}public void finalizeContext(final HttpContext context){}public HttpRequest submitRequest(final HttpContext context){Boolean b=((Boolean)context.getAttribute("done"));if (b == null){BasicHttpRequest get=new BasicHttpRequest("GET","/");context.setAttribute("done",Boolean.TRUE);return get;} else {return null;}}public void handleResponse(final HttpResponse response,final HttpContext context){}};IOReactorExceptionHandler exceptionHandler=new IOReactorExceptionHandler(){public boolean handle(final IOException ex){return false;}public boolean handle(final RuntimeException ex){requestConns.countDown();return true;}};HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());serviceHandler.setHandlerResolver(new SimpleHttpRequestHandlerResolver(requestHandler));serviceHandler.setEventListener(new SimpleEventListener());HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});BufferingHttpClientHandler clientHandler=new BufferingHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.client.getParams());this.server.setExceptionHandler(exceptionHandler);this.server.start(serviceHandler);this.client.start(clientHandler);ListenerEndpoint endpoint=this.server.getListenerEndpoint();endpoint.waitFor();InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),null);requestConns.await();Assert.assertEquals(0,requestConns.getCount());this.server.join(1000);Assert.assertEquals(IOReactorStatus.ACTIVE,this.server.getStatus());Assert.assertNull(this.server.getException());this.client.shutdown();this.server.shutdown();}

}
