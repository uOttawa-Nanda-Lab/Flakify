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
package org.apache.dubbo.remoting.exchange.support.header;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.exchange.support.DefaultFuture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.dubbo.remoting.Channel;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.exchange.Request;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class HeaderExchangeChannelTest {

    private HeaderExchangeChannel header;
    private MockChannel channel;
    private URL url = URL.valueOf("dubbo://localhost:20880");
    private static final String CHANNEL_KEY = HeaderExchangeChannel.class.getName() + ".CHANNEL";

    @Before
    public void setup() {
        channel = new MockChannel() {
            @Override
            public URL getUrl() {
                return url;
            }
        };

        header = new HeaderExchangeChannel(channel);
    }

    @Test public void sendTest01() throws RemotingException{boolean sent=true;String message="this is a test message";header.send(message,sent);List<Object> objects=channel.getSentObjects();Assert.assertEquals(objects.get(0),"this is a test message");}
}

