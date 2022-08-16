/*
 * Copyright (c) 2013, 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.tyrus.test.standard_config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.server.Server;
import org.glassfish.tyrus.test.tools.TestContainer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class OnCloseTest extends TestContainer {

    @ServerEndpoint(value = "/close2")
    public static class OnCloseEndpoint {
        public static Session session;

        @OnMessage
        public String message(String message, Session session) throws IOException {

            // client side should receive close code 1000 and close reason "" (empty string), @see Session#close()
            if (message.equals("quit1")) {
                session.close();
                return null;
                // client side should receive close code 1000 and close reason "" (empty string)
            } else if (message.equals("quit2")) {
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, null));
                return null;
            }

            try {
                session.close();
                return null;
            } catch (IOException e) {
                // do nothing.
            }
            return "message";
        }
    }

    @Test
    public void testOnClose() throws DeploymentException {
        Server server = startServer(OnCloseEndpoint.class);

        final CountDownLatch messageLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new TestEndpointAdapter() {
                @Override
                public EndpointConfig getEndpointConfig() {
                    return cec;
                }

                @Override
                public void onOpen(Session session) {
                    session.addMessageHandler(new TestTextMessageHandler(this));
                    try {
                        session.getBasicRemote().sendText("message");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    if (closeReason != null
                            && closeReason.getCloseCode().getCode()
                            == CloseReason.CloseCodes.NORMAL_CLOSURE.getCode()) {
                        messageLatch.countDown();
                    }
                }

                @Override
                public void onMessage(String message) {
                    // do nothing
                }
            }, cec, getURI(OnCloseEndpoint.class));

            assertTrue(messageLatch.await(1, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    @Test
    public void testOnCloseWithoutCloseReason() throws DeploymentException {
        Server server = startServer(OnCloseEndpoint.class);

        final CountDownLatch closeLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new TestEndpointAdapter() {
                @Override
                public EndpointConfig getEndpointConfig() {
                    return cec;
                }

                @Override
                public void onOpen(Session session) {
                    session.addMessageHandler(new TestTextMessageHandler(this));
                    try {
                        session.getBasicRemote().sendText("quit1");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    if (closeReason != null
                            && closeReason.getCloseCode().getCode() == CloseReason.CloseCodes.NORMAL_CLOSURE.getCode()
                            && closeReason.getReasonPhrase().equals("")) {
                        closeLatch.countDown();
                    }
                }

                @Override
                public void onMessage(String message) {
                    // do nothing
                }
            }, cec, getURI(OnCloseEndpoint.class));

            assertTrue(closeLatch.await(1, TimeUnit.SECONDS));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    @Test
    public void testOnCloseWithCloseReasonWithoutReasonPhrase() throws DeploymentException {
        Server server = startServer(OnCloseEndpoint.class);

        final CountDownLatch closeLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new TestEndpointAdapter() {
                @Override
                public EndpointConfig getEndpointConfig() {
                    return cec;
                }

                @Override
                public void onOpen(Session session) {
                    session.addMessageHandler(new TestTextMessageHandler(this));
                    try {
                        session.getBasicRemote().sendText("quit2");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    if (closeReason != null
                            && closeReason.getCloseCode().getCode() == CloseReason.CloseCodes.NORMAL_CLOSURE.getCode()
                            && closeReason.getReasonPhrase().equals("")) {
                        closeLatch.countDown();
                    }
                }

                @Override
                public void onMessage(String message) {
                    // do nothing
                }
            }, cec, getURI(OnCloseEndpoint.class));

            assertTrue(closeLatch.await(1, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    static final String CUSTOM_REASON = "When nine hundred years old you reach, look as good, you will not, hmmm?";

    @ServerEndpoint(value = "/close3")
    public static class OnCloseWithCustomReasonEndpoint {
        public static Session session;
        public static volatile CloseReason closeReason;

        @OnMessage
        public String message(String message, Session session) {
            try {
                session.close(new CloseReason(new CloseReason.CloseCode() {
                    @Override
                    public int getCode() {
                        // custom close codes (4000-4999)
                        return 4000;
                    }
                }, CUSTOM_REASON));
                return null;
            } catch (IOException e) {
                // do nothing.
            }
            return "message";
        }

        @OnClose
        public void onClose(Session s, CloseReason c) {
            closeReason = c;
        }

        @OnError
        public void onError(Throwable t) {
            t.printStackTrace();
        }
    }

    @Test
    public void testOnCloseCustomCloseReasonServerInitiated() throws DeploymentException {
        Server server = startServer(OnCloseWithCustomReasonEndpoint.class);

        final CountDownLatch messageLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new TestEndpointAdapter() {
                @Override
                public EndpointConfig getEndpointConfig() {
                    return cec;
                }

                @Override
                public void onOpen(Session session) {
                    session.addMessageHandler(new TestTextMessageHandler(this));
                    try {
                        session.getBasicRemote().sendText("message");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    if (closeReason != null
                            && closeReason.getCloseCode().getCode() == 4000
                            && closeReason.getReasonPhrase().equals(CUSTOM_REASON)) {
                        messageLatch.countDown();
                    }
                }

                @Override
                public void onMessage(String message) {
                    // do nothing
                }
            }, cec, getURI(OnCloseWithCustomReasonEndpoint.class));

            messageLatch.await(5, TimeUnit.SECONDS);

            assertEquals(0L, messageLatch.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    @Test
    public void testOnCloseCustomCloseReasonClientInitiated() throws DeploymentException {
        Server server = startServer(OnCloseWithCustomReasonEndpoint.class, ServiceEndpoint.class);

        final CountDownLatch messageLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new TestEndpointAdapter() {
                @Override
                public EndpointConfig getEndpointConfig() {
                    return cec;
                }

                @Override
                public void onOpen(Session session) {
                    session.addMessageHandler(new TestTextMessageHandler(this));
                    try {
                        session.close(new CloseReason(new CloseReason.CloseCode() {
                            @Override
                            public int getCode() {
                                // custom close codes (4000-4999)
                                return 4000;
                            }
                        }, CUSTOM_REASON));
                    } catch (IOException e) {
                        // do nothing.
                    } finally {
                        messageLatch.countDown();
                    }
                }

                @Override
                public void onMessage(String message) {
                    // do nothing
                }
            }, cec, getURI(OnCloseWithCustomReasonEndpoint.class));

            messageLatch.await(5, TimeUnit.SECONDS);
            Thread.sleep(1000);

            testViaServiceEndpoint(client, ServiceEndpoint.class, POSITIVE, "OnCloseWithCustomReasonEndpoint");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    static int[] closeReasons = {
            1000, 1001, 1002, 1003, 1007, 1008, 1009, 1010, 1011, 1012, 1013
    };

    static int[] supportedClientCloseReasons = {
            1000, 1001, 1002, 1003, 1007, 1008, 1009, 1010, 1011
    };

    @ServerEndpoint(value = "/close5")
    public static class OnCloseAllSupportedReasonsEndpoint {

        @OnMessage
        public String message(final Integer message, Session session) {
            try {
                session.close(new CloseReason(new CloseReason.CloseCode() {
                    @Override
                    public int getCode() {
                        // custom close codes (4000-4999)
                        return message;
                    }
                }, null));
                return null;
            } catch (IOException e) {
                // do nothing.
            }
            return "message";
        }

        @OnError
        public void onError(Throwable t) {
            t.printStackTrace();
        }
    }

    @Test
    public void testOnCloseServerInitiatedAll() throws DeploymentException {
        for (int i : closeReasons) {
            testOnCloseServerInitiated(i);
        }
    }

    public void testOnCloseServerInitiated(int supportedCode) throws DeploymentException {
        Server server = startServer(OnCloseAllSupportedReasonsEndpoint.class);

        // close codes 1000 - 1013
        final CountDownLatch messageLatch = new CountDownLatch(1);

        final int closeCode = supportedCode;
        System.out.println("### Testing CloseCode " + supportedCode);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = createClient();
            client.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig endpointConfig) {
                    try {
                        session.getBasicRemote().sendObject(closeCode);
                    } catch (Exception e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    System.out.println("#### received: " + closeReason);
                    if (closeReason != null && closeReason.getCloseCode().getCode() == closeCode) {
                        messageLatch.countDown();
                    }
                }
            }, cec, getURI(OnCloseAllSupportedReasonsEndpoint.class));

            messageLatch.await(1, TimeUnit.SECONDS);

            assertEquals(0L, messageLatch.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    @ServerEndpoint(value = "/close4")
    public static class OnCloseAllSupportedReasonsClientInitEndpoint {

        public static volatile CloseReason closeReason;
        public static volatile CountDownLatch closeLatch;

        @OnOpen
        public void onOpen() {
            closeLatch = new CountDownLatch(1);
        }

        @OnMessage
        public String message(final Integer message, Session session) {
            return "message";
        }

        @OnClose
        public void onClose(CloseReason closeReason, Session session) {
            System.out.println("### Received closeReason: " + closeReason.getCloseCode().getCode());
            OnCloseAllSupportedReasonsClientInitEndpoint.closeReason = closeReason;
            closeLatch.countDown();
        }

        @OnError
        public void onError(Throwable t) {
            t.printStackTrace();
        }
    }

    @Test
    public void testOnCloseClientInitiated() throws DeploymentException {

        // close codes 1000 - 1013
        for (int i : closeReasons) {
            Server server = startServer(OnCloseAllSupportedReasonsClientInitEndpoint.class, ServiceEndpoint.class);

            final int closeCode = i;
            System.out.println("### Testing CloseCode " + i);

            try {
                final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

                final ClientManager client = createClient();
                final CountDownLatch onCloseLatch = new CountDownLatch(1);
                client.connectToServer(new Endpoint() {
                    @Override
                    public void onOpen(Session session, EndpointConfig endpointConfig) {
                        try {
                            session.close(new CloseReason(new CloseReason.CloseCode() {
                                @Override
                                public int getCode() {
                                    return closeCode;
                                }
                            }, null));
                        } catch (Exception e) {
                            // do nothing.
                        }
                    }

                    @Override
                    public void onClose(Session session, CloseReason closeReason) {
                        onCloseLatch.countDown();
                    }
                }, cec, getURI(OnCloseAllSupportedReasonsClientInitEndpoint.class));

                assertTrue(onCloseLatch.await(5, TimeUnit.SECONDS));

                testViaServiceEndpoint(client, ServiceEndpoint.class, POSITIVE, String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                stopServer(server);
            }
        }
    }

    @ServerEndpoint(value = "/serviceonclosetest")
    public static class ServiceEndpoint {

        @OnMessage
        public String message(String message, Session session) throws InterruptedException {
            if (message.equals("OnCloseWithCustomReasonEndpoint")) {
                if (OnCloseWithCustomReasonEndpoint.closeReason != null
                        && OnCloseWithCustomReasonEndpoint.closeReason.getCloseCode().getCode() == 4000
                        && OnCloseWithCustomReasonEndpoint.closeReason.getReasonPhrase().equals(CUSTOM_REASON)) {
                    return POSITIVE;
                }
            } else if (message.equals("DoubleCloseEndpoint")) {
                if (DoubleCloseEndpoint.exceptionThrown) {
                    return POSITIVE;
                }
            } else if (message.equals("SessionTestAllMethodsAfterCloseEndpoint")) {
                if (SessionTestAllMethodsAfterCloseEndpoint.exceptionsThrown) {
                    return POSITIVE;
                }
            } else { //testOnCloseClientInitiated, different test codes sent as a message
                int i = Integer.parseInt(message);

                /* There is a race, since the Session#close just sends a close frame asynchronously and does not wait for
                   the connection to be really closed, so in some rare cases the call to the service endpoint can overtake
                   the closing handshake completion. */
                OnCloseAllSupportedReasonsClientInitEndpoint.closeLatch.await(1, TimeUnit.SECONDS);

                if (OnCloseAllSupportedReasonsClientInitEndpoint.closeReason == null) {
                    System.out.println("!!! close reason still not set !!!");
                }

                if (i == 1012 || i == 1013) {
                    if (OnCloseAllSupportedReasonsClientInitEndpoint.closeReason.getCloseCode().getCode() == 1000) {
                        return POSITIVE;
                    }
                }

                if (OnCloseAllSupportedReasonsClientInitEndpoint.closeReason.getCloseCode().getCode() == i) {
                    return POSITIVE;
                }
            }

            return NEGATIVE;
        }
    }

    @ServerEndpoint(value = "/close1")
    public static class DoubleCloseEndpoint {
        public static boolean exceptionThrown = false;

        @OnMessage
        public String message(String message, Session session) {
            try {
                session.close();
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Normal closure."));
            } catch (IllegalStateException e) {
                exceptionThrown = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "message";
        }
    }

    @Test
    public void testDoubleClose() throws DeploymentException {
        Server server = startServer(DoubleCloseEndpoint.class, ServiceEndpoint.class);

        final CountDownLatch messageLatch = new CountDownLatch(1);
        ClientManager client = createClient();

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            client.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.getBasicRemote().sendText("message");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    messageLatch.countDown();
                }
            }, cec, getURI(DoubleCloseEndpoint.class));

            messageLatch.await(2, TimeUnit.SECONDS);
            assertEquals(0, messageLatch.getCount());

            testViaServiceEndpoint(client, ServiceEndpoint.class, POSITIVE, "DoubleCloseEndpoint");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    @ServerEndpoint(value = "/close6")
    public static class SessionTestAllMethodsAfterCloseEndpoint {
        public static boolean exceptionsThrown = false;

        @OnMessage
        public String message(String message, Session session) {
            boolean checker = true;
            boolean thrown = false;

            try {
                session.close();
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Normal closure."));
            } catch (IllegalStateException e) {
                exceptionsThrown = true;
                thrown = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getAsyncRemote();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getBasicRemote();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.addMessageHandler(null);
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getContainer();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getId();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getMaxBinaryMessageBufferSize();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getMaxIdleTimeout();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getNegotiatedExtensions();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getMessageHandlers();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getMaxTextMessageBufferSize();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getNegotiatedSubprotocol();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getOpenSessions();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getPathParameters();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getProtocolVersion();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getQueryString();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getRequestParameterMap();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getRequestURI();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getUserPrincipal();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.getUserProperties();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.isSecure();
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.removeMessageHandler(null);
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.setMaxBinaryMessageBufferSize(10);
            } catch (IllegalStateException e) {
                thrown = true;
            }

            checker &= thrown;
            thrown = false;
            try {
                session.setMaxIdleTimeout(1);
            } catch (IllegalStateException e) {
                thrown = true;
            }

            if (checker &= thrown) {
                exceptionsThrown = true;
            }

            return "message";
        }
    }

    @Test
    public void testSessionAllMethodsAfterClose() throws DeploymentException {
        Server server = startServer(SessionTestAllMethodsAfterCloseEndpoint.class, ServiceEndpoint.class);

        final CountDownLatch messageLatch = new CountDownLatch(1);

        try {
            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
            ClientManager client = createClient();

            client.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.getBasicRemote().sendText("message");
                    } catch (IOException e) {
                        // do nothing.
                    }
                }

                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    messageLatch.countDown();
                }
            }, cec, getURI(SessionTestAllMethodsAfterCloseEndpoint.class));

            messageLatch.await(2, TimeUnit.SECONDS);
            assertEquals(0L, messageLatch.getCount());

            testViaServiceEndpoint(client, ServiceEndpoint.class, POSITIVE, "SessionTestAllMethodsAfterCloseEndpoint");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }
}
