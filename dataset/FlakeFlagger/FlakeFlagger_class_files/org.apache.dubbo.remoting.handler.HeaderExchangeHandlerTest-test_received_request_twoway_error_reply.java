/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.remoting.handler;


import org.apache.dubbo.common.Constants;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.exchange.ExchangeChannel;
import org.apache.dubbo.remoting.exchange.ExchangeHandler;
import org.apache.dubbo.remoting.exchange.Request;
import org.apache.dubbo.remoting.exchange.Response;
import org.apache.dubbo.remoting.exchange.support.header.HeaderExchangeHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

//TODO response test
public class HeaderExchangeHandlerTest {

    @Test public void test_received_request_twoway_error_reply() throws RemotingException{final Person requestdata=new Person("charles");final Request request=new Request();request.setTwoWay(true);request.setData(requestdata);final AtomicInteger count=new AtomicInteger(0);final Channel mchannel=new MockedChannel(){@Override public void send(Object message) throws RemotingException{Response res=(Response)message;Assert.assertEquals(request.getId(),res.getId());Assert.assertEquals(request.getVersion(),res.getVersion());Assert.assertEquals(Response.SERVICE_ERROR,res.getStatus());Assert.assertNull(res.getResult());Assert.assertTrue(res.getErrorMessage().contains(BizException.class.getName()));count.incrementAndGet();}};ExchangeHandler exhandler=new MockedExchangeHandler(){@Override public CompletableFuture<Object> reply(ExchangeChannel channel,Object request) throws RemotingException{throw new BizException();}};HeaderExchangeHandler hexhandler=new HeaderExchangeHandler(exhandler);hexhandler.received(mchannel,request);Assert.assertEquals(1,count.get());}

    private class BizException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private class MockedExchangeHandler extends MockedChannelHandler implements ExchangeHandler {

        public String telnet(Channel channel, String message) throws RemotingException {
            throw new UnsupportedOperationException();
        }

        public CompletableFuture<Object> reply(ExchangeChannel channel, Object request) throws RemotingException {
            throw new UnsupportedOperationException();
        }
    }

    private class Person {
        private String name;

        public Person(String name) {
            super();
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + "]";
        }
    }
}
