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
package org.apache.dubbo.remoting.transport.netty;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.utils.DubboAppender;
import org.apache.dubbo.common.utils.LogUtil;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.Client;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.Server;
import org.apache.dubbo.remoting.exchange.Exchangers;
import org.apache.dubbo.remoting.exchange.support.ExchangeHandlerAdapter;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Client reconnect test
 */
public class ClientReconnectTest {
    @Before
    public void clear() {
        DubboAppender.clear();
    }

    /**
	 * Reconnect log check, when the time is not enough for shutdown time, there is no error log, but there must be a warn log
	 */@Test public void testReconnectWarnLog() throws RemotingException,InterruptedException{int port=NetUtils.getAvailablePort();DubboAppender.doStart();String url="exchange://127.0.0.1:" + port + "/client.reconnect.test?check=false&client=netty3&" + Constants.RECONNECT_KEY + "=" + 1;try {Exchangers.connect(url);} catch (Exception e){}Thread.sleep(1500);Assert.assertEquals("no error message ",0,LogUtil.findMessage(Level.ERROR,"client reconnect to "));Assert.assertEquals("must have one warn message ",1,LogUtil.findMessage(Level.WARN,"client reconnect to "));DubboAppender.doStop();}

    static class HandlerAdapter extends ExchangeHandlerAdapter {
        @Override
        public void connected(Channel channel) throws RemotingException {
        }

        @Override
        public void disconnected(Channel channel) throws RemotingException {
        }

        @Override
        public void caught(Channel channel, Throwable exception) throws RemotingException {
        }
    }
}
