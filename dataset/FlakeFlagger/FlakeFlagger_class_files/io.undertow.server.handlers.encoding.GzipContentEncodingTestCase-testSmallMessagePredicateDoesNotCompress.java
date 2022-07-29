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

    /**
     * This message should not be compressed as it is too small
     *
     * @throws java.io.IOException
     */
    @Test
    public void testSmallMessagePredicateDoesNotCompress() throws IOException {
        ContentEncodingHttpClient client = new ContentEncodingHttpClient();
        try {
            message = "Hi";
            HttpGet get = new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
            get.setHeader(Headers.ACCEPT_ENCODING_STRING, "gzip");
            HttpResponse result = client.execute(get);
            Assert.assertEquals(200, result.getStatusLine().getStatusCode());
            Header[] header = result.getHeaders(Headers.CONTENT_ENCODING_STRING);
            Assert.assertEquals(0, header.length);
            final String body = HttpClientUtils.readResponse(result);
            Assert.assertEquals("Hi", body);
        } finally {
            client.getConnectionManager().shutdown();
        }
    }
}
