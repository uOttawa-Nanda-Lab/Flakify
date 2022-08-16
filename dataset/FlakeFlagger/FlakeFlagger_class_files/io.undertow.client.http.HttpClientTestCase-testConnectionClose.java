/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.client.http;

import io.undertow.client.ClientCallback;
import io.undertow.client.ClientConnection;
import io.undertow.client.ClientExchange;
import io.undertow.client.ClientRequest;
import io.undertow.client.ClientResponse;
import io.undertow.client.UndertowClient;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.AjpIgnore;
import io.undertow.testutils.DefaultServer;
import io.undertow.util.AttachmentKey;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import io.undertow.util.StringReadChannelListener;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xnio.ByteBufferSlicePool;
import org.xnio.IoUtils;
import org.xnio.OptionMap;
import org.xnio.Options;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Emanuel Muckenhuber
 */
@RunWith(DefaultServer.class)
@AjpIgnore
public class HttpClientTestCase {

    private static final String message = "Hello World!";
    private static XnioWorker worker;

    private static final OptionMap DEFAULT_OPTIONS;
    private static final HttpHandler SIMPLE_MESSAGE_HANDLER;
    private static final URI ADDRESS;

    private static final AttachmentKey<String> RESPONSE_BODY = AttachmentKey.create(String.class);

    static {
        final OptionMap.Builder builder = OptionMap.builder()
                .set(Options.WORKER_IO_THREADS, 8)
                .set(Options.TCP_NODELAY, true)
                .set(Options.KEEP_ALIVE, true)
                .set(Options.WORKER_NAME, "Client");

        DEFAULT_OPTIONS = builder.getMap();

        SIMPLE_MESSAGE_HANDLER = new HttpHandler() {
            @Override
            public void handleRequest(HttpServerExchange exchange) throws Exception {
                sendMessage(exchange);
            }
        };
        try {
            ADDRESS = new URI(DefaultServer.getDefaultServerURL());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static void sendMessage(final HttpServerExchange exchange) {
        exchange.setResponseCode(200);
        exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, message.length() + "");
        final Sender sender = exchange.getResponseSender();
        sender.send(message);
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        // Create xnio worker
        final Xnio xnio = Xnio.getInstance();
        final XnioWorker xnioWorker = xnio.createWorker(null, DEFAULT_OPTIONS);
        worker = xnioWorker;
    }

    static UndertowClient createClient() {
        return createClient(OptionMap.EMPTY);
    }

    static UndertowClient createClient(final OptionMap options) {
        return UndertowClient.getInstance();
    }

    @Test public void testConnectionClose() throws Exception{DefaultServer.setRootHandler(SIMPLE_MESSAGE_HANDLER);final UndertowClient client=createClient();final CountDownLatch latch=new CountDownLatch(1);final ClientConnection connection=client.connect(ADDRESS,worker,new ByteBufferSlicePool(1024,1024),OptionMap.EMPTY).get();try {ClientRequest request=new ClientRequest().setPath("/1324").setMethod(Methods.GET);final List<ClientResponse> responses=new CopyOnWriteArrayList<ClientResponse>();request.getRequestHeaders().add(Headers.CONNECTION,Headers.CLOSE.toString());connection.sendRequest(request,createClientCallback(responses,latch));latch.await();final ClientResponse response=responses.iterator().next();Assert.assertEquals(message,response.getAttachment(RESPONSE_BODY));try {request=new ClientRequest().setPath("/1324").setMethod(Methods.GET);connection.sendRequest(request,createClientCallback(responses,latch));Assert.fail();} catch (IllegalStateException e){}}  finally {IoUtils.safeClose(connection);}}

    /*
    @Test
    public void testSimpleHttpContinue() throws Exception {
        //
        final HttpContinueAcceptingHandler handler = new HttpContinueAcceptingHandler();
        DefaultServer.setRootHandler(handler);
        final UndertowClient client = createClient();
        try {
            {
                final ClientConnection connection = client.connect(ADDRESS, worker, new ByteBufferSlicePool(1024, 1024), OptionMap.EMPTY).get();
                try {
                    final UndertowClientRequest request = connection.createRequest(Methods.POST_STRING, new URI("/"));
                    request.getRequestHeaders().add(Headers.EXPECT, "100-continue");
                    final StreamSinkChannel channel = request.writeRequestBody(message.length());

                    final StringWriteChannelListener listener = new StringWriteChannelListener(message);
                    listener.setup(channel);

                    final UndertowClientResponse response = request.getResponse().get();
                    Assert.assertEquals(404, response.getResponseCode());

                } finally {
                    IoUtils.safeClose(connection);
                }
            }finally{
                IoUtils.safeClose(client);
            }
        }
    }

    @Test
    public void testRejectHttpContinue() throws Exception {
        //
        final HttpContinueAcceptingHandler handler = new HttpContinueAcceptingHandler() {
            @Override
            protected boolean acceptRequest(HttpServerExchange exchange) {
                return false;
            }
        };
        DefaultServer.setRootHandler(handler);
        final UndertowClient client = createClient();
        try {
            {
                final ClientConnection connection = client.connect(ADDRESS, worker, new ByteBufferSlicePool(1024, 1024), OptionMap.EMPTY).get();
                try {
                    final UndertowClientRequest request = connection.createRequest(Methods.POST_STRING, new URI("/"));
                    request.getRequestHeaders().add(Headers.EXPECT, "100-continue");
                    final StreamSinkChannel channel = request.writeRequestBody(message.length());

                    final StringWriteChannelListener listener = new StringWriteChannelListener(message);
                    listener.setup(channel);

                    final UndertowClientResponse response = request.getResponse().get();
                    Assert.assertEquals(417, response.getResponseCode());
                    Assert.assertTrue(listener.hasRemaining());

                } finally {
                    IoUtils.safeClose(connection);
                }
            }finally{
                IoUtils.safeClose(client);
            }
        }
    }
 */

    private ClientCallback<ClientExchange> createClientCallback(final List<ClientResponse> responses, final CountDownLatch latch) {
        return new ClientCallback<ClientExchange>() {
            @Override
            public void completed(ClientExchange result) {
                result.setResponseListener(new ClientCallback<ClientExchange>() {
                    @Override
                    public void completed(final ClientExchange result) {
                        responses.add(result.getResponse());
                        new StringReadChannelListener(result.getConnection().getBufferPool()) {

                            @Override
                            protected void stringDone(String string) {
                                result.getResponse().putAttachment(RESPONSE_BODY, string);
                                latch.countDown();
                            }

                            @Override
                            protected void error(IOException e) {
                                e.printStackTrace();

                                latch.countDown();
                            }
                        }.setup(result.getResponseChannel());
                    }

                    @Override
                    public void failed(IOException e) {
                        e.printStackTrace();

                        latch.countDown();
                    }
                });
            }

            @Override
            public void failed(IOException e) {
                e.printStackTrace();
                latch.countDown();
            }
        };
    }

    /*
    @Test
    public void testHttpPipeline() throws Exception {
        final OptionMap options = OptionMap.create(UndertowClientOptions.HTTP_PIPELINING, true);
        //
        DefaultServer.setRootHandler(SIMPLE_MESSAGE_HANDLER);
        final UndertowClient client = createClient();
        try {
            final ClientConnection connection = client.connect(ADDRESS, options).get();
            try {
                final List<IoFuture<UndertowClientResponse>> responses = new ArrayList<IoFuture<UndertowClientResponse>>();
                for(int i = 0; i < 10; i++) {
                    final UndertowClientRequest request = connection.createRequest(Methods.GET, new URI("/"));
                    responses.add(request.writeRequest());
                }
                Assert.assertEquals(10, responses.size());
                for(final IoFuture<UndertowClientResponse> future : responses) {
                    UndertowClientResponse response = future.get();
                    final StreamSourceChannel channel = response.readReplyBody();
                    try {
                        final InputStream is = new ChannelInputStream(channel);
                        Assert.assertEquals(message, UndertowClientUtils.readResponse(is));
                    } finally {
                        IoUtils.safeClose(channel);
                    }
                }
            } finally {
                IoUtils.safeClose(connection);
            }
        } finally {
            IoUtils.safeClose(client);
        }
    }
    */

}
