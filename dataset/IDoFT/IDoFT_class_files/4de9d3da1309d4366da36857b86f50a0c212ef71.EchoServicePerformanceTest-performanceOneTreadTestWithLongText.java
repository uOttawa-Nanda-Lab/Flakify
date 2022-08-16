/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baidu.jprotobuf.pbrpc.proto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Test;

import com.baidu.jprotobuf.pbrpc.BasePerformaceTest;
import com.baidu.jprotobuf.pbrpc.client.ProtobufRpcProxy;
import com.baidu.jprotobuf.pbrpc.data.RpcDataPackage;
import com.baidu.jprotobuf.pbrpc.proto.EchoInfoClass.EchoInfo;
import com.baidu.jprotobuf.pbrpc.transport.RpcClient;
import com.baidu.jprotobuf.pbrpc.transport.RpcClientOptions;
import com.baidu.jprotobuf.pbrpc.transport.RpcServer;

import junit.framework.Assert;

/**
 * Test class for {@link EchoService}
 * 
 * @author xiemalin
 * @since 1.0
 */
public class EchoServicePerformanceTest extends BasePerformaceTest {

    RpcServer rpcServer;
    RpcClient rpcClient;
    EchoInfo echoInfo;
    EchoService echoService;

    RpcDataPackage in;
    RpcDataPackage out;

    int totalRequestSize = 1000;
    
    /**
	 * set totalRequestSize value to totalRequestSize
	 * @param totalRequestSize the totalRequestSize to set
	 */
	public void setTotalRequestSize(int totalRequestSize) {
		this.totalRequestSize = totalRequestSize;
	}

    Runnable runnable = new Runnable() {

        public void run() {
            echoService.echo(echoInfo);

        }
    };

    public void setUp(int threadSize, String requestData, String responseData) {
        rpcServer = new RpcServer();
        EchoServiceImpl echoServiceImpl = new EchoServiceImpl();
        rpcServer.registerService(echoServiceImpl);
        rpcServer.start(PORT);

        RpcClientOptions options = new RpcClientOptions();
        options.setThreadPoolSize(threadSize);
        options.setMaxIdleSize(threadSize);
        options.setMaxWait(1000);

        rpcClient = new RpcClient(options);
        ProtobufRpcProxy<EchoService> pbrpcProxy = new ProtobufRpcProxy<EchoService>(rpcClient, EchoService.class);
        pbrpcProxy.setPort(PORT);
        echoService = pbrpcProxy.proxy();

        echoInfo = EchoInfo.newBuilder().setMessage(requestData).build();
        echoService.echo(echoInfo);
        in = buildPackage(requestData.getBytes(), null, null, "echoService", "echo");
        out = buildPackage(responseData.getBytes(), null, null, "echoService", "echo");
    }

    @After
    public void tearDown() {
        rpcClient.stop();

        rpcServer.shutdown();
    }

    @Test
    public void performanceOneTreadTest() {
        oneThreadExecute("world", "hello world");

    }

    @Test
    public void performanceOneTreadTest2() {
        oneThreadExecute("world", "hello world");

    }

    @Test
    public void performanceOneTreadTestWithLongText() {

        String requestString = "";
        String responseString = "";
        for (int i = 0; i < 100; i++) {
            requestString += "world world";
            responseString += "hello world";
        }

        oneThreadExecute(requestString, responseString);

    }

    /**
     * 
     */
    private void oneThreadExecute(String requestString, String responseString) {
        setUp(1, requestString, responseString);

        long time = System.currentTimeMillis();
        for (int i = 0; i < totalRequestSize; i++) {
            echoService.echo(echoInfo);
        }
        long timetook = System.currentTimeMillis() - time;

        printResult(in, out, totalRequestSize, timetook, 1);
    }

    @Test
    public void performanceTwoTreadsTest() throws Exception {
        int thread = 2;
        long timetook = multiExecute(totalRequestSize, thread, "world", "hello world");

        printResult(in, out, totalRequestSize, timetook, thread);
    }

    @Test
    public void performanceFourTreadsTest() throws Exception {
        int thread = 4;
        long timetook = multiExecute(totalRequestSize, thread, "world", "hello world");

        printResult(in, out, totalRequestSize, timetook, thread);
    }

    @Test
    public void performance20TreadsTest() throws Exception {
        int thread = 20;
        long timetook = multiExecute(totalRequestSize, thread, "world", "hello world");

        printResult(in, out, totalRequestSize, timetook, thread);
    }

    @Test
    public void performance20TreadsTestWithLongText() throws Exception {

        String requestString = "";
        String responseString = "";
        for (int i = 0; i < 100; i++) {
            requestString += "world world";
            responseString += "hello world";
        }

        int thread = 20;
        long timetook = multiExecute(totalRequestSize, thread, requestString, responseString);

        printResult(in, out, totalRequestSize, timetook, thread);

    }

    @Test
    public void performance40TreadsTestWithLongText() throws Exception {

        String requestString = "";
        String responseString = "";
        for (int i = 0; i < 100; i++) {
            requestString += "world world";
            responseString += "hello world";
        }

        int thread = 40;
        long timetook = multiExecute(totalRequestSize, thread, requestString, responseString);

        printResult(in, out, totalRequestSize, timetook, thread);

    }

    @Test
    public void multiExecuteValidTest() throws Exception {

        setUp(100, "hello", "world");
        ExecutorService pool = Executors.newFixedThreadPool(100);

        List<Future<?>> futures = new ArrayList<Future<?>>(10000);
        for (int i = 0; i < 10000; i++) {
            final EchoInfo echoInfo = EchoInfo.newBuilder().setMessage(i + "").build();
            final int order = i;
            Runnable runnable = new Runnable() {

                public void run() {
                    try {

                        EchoInfo echo = echoService.echo(echoInfo);
                        Assert.assertEquals("hello:" + order, echo.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            Future<?> submit = pool.submit(runnable);
            futures.add(submit);
        }
        for (Future<?> future : futures) {
            future.get();
        }

        pool.shutdown();
    }

    /**
     * @param totalRequestSize
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private long multiExecute(int totalRequestSize, int multiSize, String requestData, String responseData)
            throws InterruptedException, ExecutionException {
        setUp(multiSize, requestData, responseData);

        ExecutorService pool = Executors.newFixedThreadPool(multiSize);

        long time = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<Future<?>>(multiSize);
        for (int i = 0; i < totalRequestSize; i++) {
            Future<?> submit = pool.submit(runnable);
            futures.add(submit);
        }
        for (Future<?> future : futures) {
            future.get();
        }

        long timetook = System.currentTimeMillis() - time;
        pool.shutdown();
        return timetook;
    }
}
