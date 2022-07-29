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

package org.apache.http.nio.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpCoreNIOTestBase;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.nio.entity.NByteArrayEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.reactor.IOReactorStatus;
import org.apache.http.nio.reactor.ListenerEndpoint;
import org.apache.http.nio.reactor.SessionRequest;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpExpectationVerifier;
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
import org.apache.http.util.EncodingUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * HttpCore NIO integration tests using throttling versions of the
 * protocol handlers.
 */
@Deprecated
public class TestThrottlingNHttpHandlers extends HttpCoreNIOTestBase {

    private ExecutorService execService;

    @Before
    public void initExecService() throws Exception {
        this.execService = Executors.newCachedThreadPool();
    }

    private void executeStandardTest(
            final HttpRequestHandler requestHandler,
            final HttpRequestExecutionHandler requestExecutionHandler) throws Exception {
        int connNo = 3;
        int reqNo = 20;
        Job[] jobs = new Job[connNo * reqNo];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = new Job();
        }
        Queue<Job> queue = new ConcurrentLinkedQueue<Job>();
        for (int i = 0; i < jobs.length; i++) {
            queue.add(jobs[i]);
        }

        HttpProcessor serverHttpProc = new ImmutableHttpProcessor(new HttpResponseInterceptor[] {
                new ResponseDate(),
                new ResponseServer(),
                new ResponseContent(),
                new ResponseConnControl()
        });

        ThrottlingHttpServiceHandler serviceHandler = new ThrottlingHttpServiceHandler(
                serverHttpProc,
                new DefaultHttpResponseFactory(),
                new DefaultConnectionReuseStrategy(),
                this.execService,
                this.server.getParams());

        serviceHandler.setHandlerResolver(
                new SimpleHttpRequestHandlerResolver(requestHandler));
        serviceHandler.setEventListener(
                new SimpleEventListener());

        HttpProcessor clientHttpProc = new ImmutableHttpProcessor(new HttpRequestInterceptor[] {
                new RequestContent(),
                new RequestTargetHost(),
                new RequestConnControl(),
                new RequestUserAgent(),
                new RequestExpectContinue()});

        ThrottlingHttpClientHandler clientHandler = new ThrottlingHttpClientHandler(
                clientHttpProc,
                requestExecutionHandler,
                new DefaultConnectionReuseStrategy(),
                this.execService,
                this.client.getParams());

        clientHandler.setEventListener(
                new SimpleEventListener());

        this.server.start(serviceHandler);
        this.client.start(clientHandler);

        ListenerEndpoint endpoint = this.server.getListenerEndpoint();
        endpoint.waitFor();
        InetSocketAddress serverAddress = (InetSocketAddress) endpoint.getAddress();

        Assert.assertEquals("Test server status", IOReactorStatus.ACTIVE, this.server.getStatus());

        Queue<SessionRequest> connRequests = new LinkedList<SessionRequest>();
        for (int i = 0; i < connNo; i++) {
            SessionRequest sessionRequest = this.client.openConnection(
                    new InetSocketAddress("localhost", serverAddress.getPort()),
                    queue);
            connRequests.add(sessionRequest);
        }

        while (!connRequests.isEmpty()) {
            SessionRequest sessionRequest = connRequests.remove();
            sessionRequest.waitFor();
            if (sessionRequest.getException() != null) {
                throw sessionRequest.getException();
            }
            Assert.assertNotNull(sessionRequest.getSession());
        }

        Assert.assertEquals("Test client status", IOReactorStatus.ACTIVE, this.client.getStatus());

        for (int i = 0; i < jobs.length; i++) {
            Job testjob = jobs[i];
            testjob.waitFor();
            if (testjob.isSuccessful()) {
                Assert.assertEquals(HttpStatus.SC_OK, testjob.getStatusCode());
                Assert.assertEquals(testjob.getExpected(), testjob.getResult());
            } else {
                Assert.fail(testjob.getFailureMessage());
            }
        }
    }

    @Test public void testInputThrottling() throws Exception{HttpRequestExecutionHandler requestExecutionHandler=new HttpRequestExecutionHandler(){public void initalizeContext(final HttpContext context,final Object attachment){context.setAttribute("queue",attachment);}public HttpRequest submitRequest(final HttpContext context){@SuppressWarnings("unchecked") Queue<Job> queue=(Queue<Job>)context.getAttribute("queue");if (queue == null){throw new IllegalStateException("Queue is null");}Job testjob=queue.poll();context.setAttribute("job",testjob);if (testjob != null){String s=testjob.getPattern() + "x" + testjob.getCount();HttpEntityEnclosingRequest r=new BasicHttpEntityEnclosingRequest("POST",s);StringEntity entity=null;try {entity=new StringEntity(testjob.getExpected(),"US-ASCII");entity.setChunked(testjob.getCount() % 2 == 0);} catch (UnsupportedEncodingException ignore){}r.setEntity(entity);return r;} else {return null;}}public void handleResponse(final HttpResponse response,final HttpContext context){Job testjob=(Job)context.removeAttribute("job");if (testjob == null){throw new IllegalStateException("TestJob is null");}int statusCode=response.getStatusLine().getStatusCode();String content=null;HttpEntity entity=response.getEntity();if (entity != null){try {ByteArrayOutputStream outstream=new ByteArrayOutputStream();InputStream instream=entity.getContent();byte[] tmp=new byte[2048];int l;while ((l=instream.read(tmp)) != -1){Thread.sleep(1);outstream.write(tmp,0,l);}ContentType contentType=ContentType.getOrDefault(entity);String charset=contentType.getCharset();if (charset == null){charset=HTTP.DEFAULT_CONTENT_CHARSET;}content=new String(outstream.toByteArray(),charset);} catch (InterruptedException ex){content="Interrupted: " + ex.getMessage();}catch (IOException ex){content="I/O exception: " + ex.getMessage();}}testjob.setResult(statusCode,content);}public void finalizeContext(final HttpContext context){Job testjob=(Job)context.removeAttribute("job");if (testjob != null){testjob.fail("Request failed");}}};int connNo=3;int reqNo=20;Job[] jobs=new Job[connNo * reqNo];for (int i=0;i < jobs.length;i++){jobs[i]=new Job(10000);}Queue<Job> queue=new ConcurrentLinkedQueue<Job>();for (int i=0;i < jobs.length;i++){queue.add(jobs[i]);}HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});ThrottlingHttpServiceHandler serviceHandler=new ThrottlingHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.execService,this.server.getParams());serviceHandler.setHandlerResolver(new SimpleHttpRequestHandlerResolver(new RequestHandler()));serviceHandler.setEventListener(new SimpleEventListener());HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});ThrottlingHttpClientHandler clientHandler=new ThrottlingHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.execService,this.client.getParams());clientHandler.setEventListener(new SimpleEventListener());this.server.start(serviceHandler);this.client.start(clientHandler);ListenerEndpoint endpoint=this.server.getListenerEndpoint();endpoint.waitFor();InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();Assert.assertEquals("Test server status",IOReactorStatus.ACTIVE,this.server.getStatus());Queue<SessionRequest> connRequests=new LinkedList<SessionRequest>();for (int i=0;i < connNo;i++){SessionRequest sessionRequest=this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),queue);connRequests.add(sessionRequest);}while (!connRequests.isEmpty()){SessionRequest sessionRequest=connRequests.remove();sessionRequest.waitFor();if (sessionRequest.getException() != null){throw sessionRequest.getException();}Assert.assertNotNull(sessionRequest.getSession());}Assert.assertEquals("Test client status",IOReactorStatus.ACTIVE,this.client.getStatus());for (int i=0;i < jobs.length;i++){Job testjob=jobs[i];testjob.waitFor();if (testjob.isSuccessful()){Assert.assertEquals(HttpStatus.SC_OK,testjob.getStatusCode());Assert.assertEquals(testjob.getExpected(),testjob.getResult());} else {Assert.fail(testjob.getFailureMessage());}}}

}
