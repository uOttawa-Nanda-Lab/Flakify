package io.undertow.server.handlers.encoding;

import io.undertow.io.IoCallback;
import io.undertow.predicate.Predicates;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.util.Headers;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Random;

/**
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class GzipContentEncodingTestCase {

    private static volatile String message;

    @BeforeClass
    public static void setup() {
        final EncodingHandler handler = new EncodingHandler(new ContentEncodingRepository()
                .addEncodingHandler("gzip", new GzipEncodingProvider(), 50, Predicates.maxContentSize(5)))
                .setNext(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, message.length() + "");
                        exchange.getResponseSender().send(message, IoCallback.END_EXCHANGE);
                    }
                });

        DefaultServer.setRootHandler(handler);
    }

    @Test public void testGzipEncodingRandomSizeResponse() throws IOException{int seed=new Random().nextInt();System.out.println("Using seed " + seed);try {final Random random=new Random(seed);int size=random.nextInt(691963);final StringBuilder messageBuilder=new StringBuilder(size);for (int i=0;i < size;++i){messageBuilder.append('*' + random.nextInt(10));}runTest(messageBuilder.toString());} catch (Exception e){throw new RuntimeException("Test failed with seed " + seed,e);}}
}
