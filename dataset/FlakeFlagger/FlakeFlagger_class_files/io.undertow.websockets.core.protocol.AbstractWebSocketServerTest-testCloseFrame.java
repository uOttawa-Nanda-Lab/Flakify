/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
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
package io.undertow.websockets.core.protocol;

import io.undertow.testutils.AjpIgnore;
import io.undertow.testutils.DefaultServer;
import io.undertow.util.NetworkUtils;
import io.undertow.util.StringReadChannelListener;
import io.undertow.util.StringWriteChannelListener;
import io.undertow.websockets.core.StreamSinkFrameChannel;
import io.undertow.websockets.core.StreamSourceFrameChannel;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSocketFrameType;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.WebSocketProtocolHandshakeHandler;
import io.undertow.websockets.spi.WebSocketHttpExchange;
import io.undertow.websockets.utils.FrameChecker;
import io.undertow.websockets.utils.WebSocketTestClient;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.jboss.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.xnio.ChannelListener;
import org.xnio.ChannelListeners;
import org.xnio.FutureResult;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@RunWith(DefaultServer.class)
@AjpIgnore(apacheOnly = true)
public class AbstractWebSocketServerTest {

    @Test public void testCloseFrame() throws Exception{if (getVersion() == WebSocketVersion.V00){return;}final CountDownLatch latch=new CountDownLatch(1);final AtomicBoolean connected=new AtomicBoolean(false);DefaultServer.setRootHandler(new WebSocketProtocolHandshakeHandler(new WebSocketConnectionCallback(){@Override public void onConnect(final WebSocketHttpExchange exchange,final WebSocketChannel channel){connected.set(true);channel.getReceiveSetter().set(new ChannelListener<WebSocketChannel>(){@Override public void handleEvent(final WebSocketChannel channel){try {final StreamSourceFrameChannel ws=channel.receive();if (ws == null){return;}Assert.assertEquals(WebSocketFrameType.CLOSE,ws.getType());channel.close();channel.getReceiveSetter().set(null);latch.countDown();} catch (IOException e){throw new RuntimeException(e);}}});channel.resumeReceives();}}));final AtomicBoolean receivedResponse=new AtomicBoolean(false);WebSocketTestClient client=new WebSocketTestClient(getVersion(),new URI("ws://" + NetworkUtils.formatPossibleIpv6Address(DefaultServer.getHostAddress("default")) + ":" + DefaultServer.getHostPort("default") + "/"));client.connect();client.send(new CloseWebSocketFrame(),new WebSocketTestClient.FrameListener(){@Override public void onFrame(WebSocketFrame frame){receivedResponse.set(true);}@Override public void onError(Throwable t){t.printStackTrace();}});latch.await();Assert.assertFalse(receivedResponse.get());client.destroy();}

    protected WebSocketVersion getVersion() {
        return WebSocketVersion.V00;
    }
}
