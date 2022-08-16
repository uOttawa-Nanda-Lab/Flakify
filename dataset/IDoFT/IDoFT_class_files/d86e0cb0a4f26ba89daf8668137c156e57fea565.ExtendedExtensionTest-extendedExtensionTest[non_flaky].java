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

package org.glassfish.tyrus.test.e2e.non_deployable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.core.extension.ExtendedExtension;
import org.glassfish.tyrus.core.frame.Frame;
import org.glassfish.tyrus.server.Server;
import org.glassfish.tyrus.test.tools.TestContainer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class ExtendedExtensionTest extends TestContainer {

    @Test
    public void extendedExtensionTest() throws DeploymentException {
        EXTENDED_EXTENSION = new MyExtendedExtension();

        Server server = startServer(ExtendedExtensionApplicationConfig.class);
        final CountDownLatch messageLatch = new CountDownLatch(1);

        try {
            ArrayList<Extension> extensions = new ArrayList<Extension>();
            extensions.add(EXTENDED_EXTENSION);

            final ClientEndpointConfig clientConfiguration =
                    ClientEndpointConfig.Builder.create()
                                                .extensions(extensions)
                                                .configurator(new LoggingClientEndpointConfigurator()).build();

            ClientManager client = createClient();
            final Session session = client.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            System.out.println("client onMessage: " + message);
                            messageLatch.countDown();
                        }
                    });

                    try {
                        session.getBasicRemote().sendText("Always pass on what you have learned.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, clientConfiguration, getURI("/extendedExtensionEndpoint"));

            assertEquals(1, session.getNegotiatedExtensions().size());
            final Extension extension = session.getNegotiatedExtensions().get(0);
            assertEquals(EXTENDED_EXTENSION, extension);

            assertTrue(messageLatch.await(1, TimeUnit.SECONDS));

            // once for client side, once for server side
            assertEquals(2, EXTENDED_EXTENSION.incomingCounter.get());
            // dtto
            assertEquals(2, EXTENDED_EXTENSION.outgoingCounter.get());

            assertNotNull(EXTENDED_EXTENSION.onExtensionNegotiation);
            assertNotNull(EXTENDED_EXTENSION.onHandshakeResponse);

            assertEquals(1, EXTENDED_EXTENSION.onHandshakeResponse.size());
            assertEquals("param1", EXTENDED_EXTENSION.onHandshakeResponse.get(0).getName());
            assertEquals("value1", EXTENDED_EXTENSION.onHandshakeResponse.get(0).getValue());

            assertEquals(EXTENDED_EXTENSION.getParameters().size(), EXTENDED_EXTENSION.onExtensionNegotiation.size());
            assertEquals(EXTENDED_EXTENSION.getParameters().get(0).getName(), EXTENDED_EXTENSION
                    .onExtensionNegotiation.get(0).getName());
            assertEquals(EXTENDED_EXTENSION.getParameters().get(0).getValue(), EXTENDED_EXTENSION
                    .onExtensionNegotiation.get(0).getValue());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            stopServer(server);
        }
    }

    private static MyExtendedExtension EXTENDED_EXTENSION;

    public static class ExtendedExtensionEndpoint extends Endpoint {

        @Override
        public void onOpen(final Session session, EndpointConfig config) {
            print("onOpen " + session);
            session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    try {
                        print("onMessage");
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onClose(Session session, CloseReason closeReason) {
            print("onClose " + session);
        }

        @Override
        public void onError(Session session, Throwable thr) {
            print("onError " + session);
            thr.printStackTrace();
        }

        private void print(String s) {
            System.out.println(this.getClass().getName() + " " + s);
        }
    }

    public static class ExtendedExtensionApplicationConfig implements ServerApplicationConfig {

        @Override
        public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
            Set<ServerEndpointConfig> endpointConfigs = new HashSet<ServerEndpointConfig>();
            endpointConfigs.add(
                    ServerEndpointConfig.Builder
                            .create(ExtendedExtensionEndpoint.class, "/extendedExtensionEndpoint")
                            .extensions(Arrays.<Extension>asList(EXTENDED_EXTENSION))
                            .build());
            return endpointConfigs;
        }

        @Override
        public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
            return Collections.<Class<?>>emptySet();
        }
    }

    public static class MyExtendedExtension implements ExtendedExtension {

        private static final String NAME = "MyExtendedExtension";
        public final AtomicInteger incomingCounter = new AtomicInteger(0);
        public final AtomicInteger outgoingCounter = new AtomicInteger(0);

        public volatile List<Parameter> onExtensionNegotiation = null;
        public volatile List<Parameter> onHandshakeResponse = null;

        @Override
        public Frame processIncoming(ExtendedExtension.ExtensionContext context, Frame frame) {
            print("processIncoming :: " + incomingCounter.incrementAndGet() + " :: " + frame);
            return frame;
        }

        @Override
        public Frame processOutgoing(ExtendedExtension.ExtensionContext context, Frame frame) {
            print("processOutgoing :: " + outgoingCounter.incrementAndGet() + " :: " + frame);
            return frame;
        }

        @Override
        public List<Extension.Parameter> onExtensionNegotiation(ExtendedExtension.ExtensionContext context,
                                                                List<Extension.Parameter> requestedParameters) {
            print("onExtensionNegotiation :: " + context + " :: " + requestedParameters);
            onExtensionNegotiation = requestedParameters;

            List<Extension.Parameter> paramList = new ArrayList<Extension.Parameter>();
            paramList.add(new Parameter() {
                @Override
                public String getName() {
                    return "param1";
                }

                @Override
                public String getValue() {
                    return "value1";
                }

                @Override
                public String toString() {
                    return "[param1=value1]";
                }
            });

            print(paramList.toString());
            print("");

            return paramList;
        }

        @Override
        public void onHandshakeResponse(ExtensionContext context, List<Parameter> responseParameters) {
            onHandshakeResponse = responseParameters;
            print("onHandshakeResponse :: " + context + " :: " + responseParameters);
        }

        @Override
        public String getName() {
            print("getName");
            return NAME;
        }

        @Override
        public List<Parameter> getParameters() {
            print("getParameters");
            List<Parameter> paramList = new ArrayList<Parameter>();
            paramList.add(new Parameter() {
                @Override
                public String getName() {
                    return "basicParam1";
                }

                @Override
                public String getValue() {
                    return "basicValue1";
                }
            });

            return paramList;
        }

        @Override
        public void destroy(ExtensionContext context) {
            print("destroy :: " + context);
        }

        private void print(String s) {
            System.out.println("##### " + NAME + " " + s);
        }
    }

    public static class LoggingClientEndpointConfigurator extends ClientEndpointConfig.Configurator {
        @Override
        public void beforeRequest(Map<String, List<String>> headers) {
            System.out.println("##### beforeRequest");
            logHeaders(headers);
            System.out.println();
        }

        @Override
        public void afterResponse(HandshakeResponse hr) {
            System.out.println("##### afterResponse");
            logHeaders(hr.getHeaders());
            System.out.println();
        }

        private void logHeaders(Map<String, List<String>> headers) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                System.out.print("# " + entry.getKey() + ": ");
                boolean first = true;
                for (String value : entry.getValue()) {
                    System.out.print((first ? "" : ", ") + value);
                    if (first) {
                        first = false;
                    }
                }
                System.out.println();
            }
        }
    }
}
