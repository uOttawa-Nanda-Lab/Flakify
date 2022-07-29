package io.undertow.server.handlers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;

import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import io.undertow.util.Headers;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class SenderTestCase {

    public static final int SENDS = 10000;
    public static final int TXS = 1000;
    public static final String HELLO_WORLD = "Hello World";

    @BeforeClass
    public static void setup() {
        HttpHandler lotsOfSendsHandler = new HttpHandler() {
            @Override
            public void handleRequest(final HttpServerExchange exchange) throws Exception {
                boolean blocking = exchange.getQueryParameters().get("blocking").getFirst().equals("true");
                if (blocking) {
                    if (exchange.isInIoThread()) {
                        exchange.startBlocking();
                        exchange.dispatch(this);
                        return;
                    }
                }
                final Sender sender = exchange.getResponseSender();
                class SendClass implements Runnable, IoCallback {

                    int sent = 0;

                    @Override
                    public void run() {
                        sent++;
                        sender.send("a", this);
                    }

                    @Override
                    public void onComplete(final HttpServerExchange exchange, final Sender sender) {
                        if (sent++ == SENDS) {
                            sender.close();
                            return;
                        }
                        sender.send("a", this);
                    }

                    @Override
                    public void onException(final HttpServerExchange exchange, final Sender sender, final IOException exception) {
                        exception.printStackTrace();
                        exchange.endExchange();
                    }
                }
                new SendClass().run();
            }
        };
        HttpHandler lotsOfTransferHandler = new HttpHandler() {
            @Override
            public void handleRequest(final HttpServerExchange exchange) throws Exception {

                boolean blocking = exchange.getQueryParameters().get("blocking").getFirst().equals("true");
                if (blocking) {
                    if (exchange.isInIoThread()) {
                        exchange.startBlocking();
                        exchange.dispatch(this);
                        return;
                    }
                }
                URI uri = SenderTestCase.class.getResource(SenderTestCase.class.getSimpleName() + ".class").toURI();
                File file = new File(uri);
                final FileChannel channel = new FileInputStream(file).getChannel();

                exchange.setResponseContentLength(channel.size() * TXS);

                final Sender sender = exchange.getResponseSender();
                class SendClass implements Runnable, IoCallback {

                    int sent = 0;

                    @Override
                    public void run() {
                        sent++;
                        try {
                            channel.position(0);
                        } catch (IOException e) {
                        }
                        sender.transferFrom(channel, this);
                    }

                    @Override
                    public void onComplete(final HttpServerExchange exchange, final Sender sender) {
                        if (sent++ == TXS) {
                            sender.close();
                            return;
                        }
                        try {
                            channel.position(0);
                        } catch (IOException e) {
                        }
                        sender.transferFrom(channel, this);
                    }

                    @Override
                    public void onException(final HttpServerExchange exchange, final Sender sender, final IOException exception) {
                        exception.printStackTrace();
                        exchange.endExchange();
                    }
                }
                new SendClass().run();
            }
        };

        final HttpHandler fixedLengthSender = new HttpHandler() {
            @Override
            public void handleRequest(final HttpServerExchange exchange) throws Exception {
                exchange.getResponseSender().send(HELLO_WORLD);
            }
        };

        PathHandler handler = new PathHandler().addPath("/lots", lotsOfSendsHandler)
                .addPath("/fixed", fixedLengthSender)
                .addPath("/transfer", lotsOfTransferHandler);
        DefaultServer.setRootHandler(handler);
    }


    @Test public void testSenderSetsContentLength() throws IOException{HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/fixed");TestHttpClient client=new TestHttpClient();try {HttpResponse result=client.execute(get);Assert.assertEquals(200,result.getStatusLine().getStatusCode());Assert.assertEquals(HELLO_WORLD,HttpClientUtils.readResponse(result));Header[] header=result.getHeaders(Headers.CONTENT_LENGTH_STRING);Assert.assertEquals(1,header.length);Assert.assertEquals("" + HELLO_WORLD.length(),header[0].getValue());}  finally {client.getConnectionManager().shutdown();}}
}
