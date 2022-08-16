package io.undertow.server.handlers;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class GracefulShutdownTestCase {

    static final AtomicReference<CountDownLatch> latch1 = new AtomicReference<CountDownLatch>();
    static final AtomicReference<CountDownLatch> latch2 = new AtomicReference<CountDownLatch>();

    private static GracefulShutdownHandler shutdown;

    @BeforeClass
    public static void setup() {

        shutdown = Handlers.gracefulShutdown(new HttpHandler() {
            @Override
            public void handleRequest(HttpServerExchange exchange) throws Exception {
                final CountDownLatch countDownLatch = latch2.get();
                final CountDownLatch latch = latch1.get();
                if (latch != null) {
                    latch.countDown();
                }
                if (countDownLatch != null) {
                    countDownLatch.await();
                }
            }
        });
        DefaultServer.setRootHandler(shutdown);
    }

    @Test public void simpleGracefulShutdownTestCase() throws IOException,InterruptedException{HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");TestHttpClient client=new TestHttpClient();try {HttpResponse result=client.execute(get);Assert.assertEquals(200,result.getStatusLine().getStatusCode());HttpClientUtils.readResponse(result);shutdown.shutdown();result=client.execute(get);Assert.assertEquals(503,result.getStatusLine().getStatusCode());HttpClientUtils.readResponse(result);shutdown.start();result=client.execute(get);Assert.assertEquals(200,result.getStatusLine().getStatusCode());HttpClientUtils.readResponse(result);CountDownLatch latch=new CountDownLatch(1);latch2.set(latch);latch1.set(new CountDownLatch(1));Thread t=new Thread(new RequestTask());t.start();latch1.get().await();shutdown.shutdown();Assert.assertFalse(shutdown.awaitShutdown(10));latch.countDown();Assert.assertTrue(shutdown.awaitShutdown(10000));}  finally {client.getConnectionManager().shutdown();}}



    private class ShutdownListener implements GracefulShutdownHandler.ShutdownListener {

        private volatile boolean invoked = false;

        @Override
        public synchronized void shutdown(boolean sucessful) {
            invoked = true;
        }
    }

    private final class RequestTask implements Runnable {

        @Override
        public void run() {
            HttpGet get = new HttpGet(DefaultServer.getDefaultServerURL() + "/path");
            TestHttpClient client = new TestHttpClient();
            try {
                HttpResponse result = client.execute(get);
                Assert.assertEquals(200, result.getStatusLine().getStatusCode());
                HttpClientUtils.readResponse(result);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.getConnectionManager().shutdown();
            }

        }
    }

}
