package io.undertow.server.handlers.accesslog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.util.CompletionLatchHandler;
import io.undertow.util.FileUtils;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests writing the access log to a file
 *
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class AccessLogFileTestCase {

    private static final File logDirectory = new File(System.getProperty("java.io.tmpdir") + "/logs");

    private static final int NUM_THREADS = 10;
    private static final int NUM_REQUESTS = 12;

    @Before
    public void before() {
        logDirectory.mkdirs();
    }

    private static final HttpHandler HELLO_HANDLER = new HttpHandler() {
        @Override
        public void handleRequest(final HttpServerExchange exchange) throws Exception {
            exchange.getResponseSender().send("Hello");
        }
    };

    @Test public void testLogLotsOfThreads() throws IOException,InterruptedException,ExecutionException{File directory=logDirectory;File logFileName=new File(directory,"server2.log");DefaultAccessLogReceiver logReceiver=new DefaultAccessLogReceiver(DefaultServer.getWorker(),directory,"server2");CompletionLatchHandler latchHandler;DefaultServer.setRootHandler(latchHandler=new CompletionLatchHandler(NUM_REQUESTS * NUM_THREADS,new AccessLogHandler(HELLO_HANDLER,logReceiver,"REQ %{i,test-header}",AccessLogFileTestCase.class.getClassLoader())));ExecutorService executor=Executors.newFixedThreadPool(NUM_THREADS);try {final List<Future<?>> futures=new ArrayList<Future<?>>();for (int i=0;i < NUM_THREADS;++i){final int threadNo=i;futures.add(executor.submit(new Runnable(){@Override public void run(){TestHttpClient client=new TestHttpClient();try {for (int i=0;i < NUM_REQUESTS;++i){HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");get.addHeader("test-header","thread-" + threadNo + "-request-" + i);HttpResponse result=client.execute(get);Assert.assertEquals(200,result.getStatusLine().getStatusCode());final String response=HttpClientUtils.readResponse(result);Assert.assertEquals("Hello",response);}} catch (IOException e){throw new RuntimeException(e);} finally {client.getConnectionManager().shutdown();}}}));}for (Future<?> future:futures){future.get();}}  finally {executor.shutdown();}latchHandler.await();logReceiver.awaitWrittenForTest();String completeLog=FileUtils.readFile(logFileName);for (int i=0;i < NUM_THREADS;++i){for (int j=0;j < NUM_REQUESTS;++j){Assert.assertTrue(completeLog.contains("REQ thread-" + i + "-request-" + j));}}}

}
