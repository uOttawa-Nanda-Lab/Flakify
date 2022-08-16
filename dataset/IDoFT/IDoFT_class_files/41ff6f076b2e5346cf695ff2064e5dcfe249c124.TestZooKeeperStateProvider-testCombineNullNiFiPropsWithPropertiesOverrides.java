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
package org.apache.nifi.controller.state.providers.zookeeper;

import org.apache.curator.test.TestingServer;
import org.apache.nifi.parameter.ParameterLookup;
import org.apache.nifi.attribute.expression.language.StandardPropertyValue;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.PropertyValue;
import org.apache.nifi.components.state.StateProvider;
import org.apache.nifi.components.state.StateProviderInitializationContext;
import org.apache.nifi.components.state.exception.StateTooLargeException;
import org.apache.nifi.controller.state.providers.AbstractTestStateProvider;
import org.apache.nifi.logging.ComponentLog;
import org.apache.nifi.util.NiFiProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestZooKeeperStateProvider extends AbstractTestStateProvider {

    private volatile StateProvider provider;
    private volatile TestingServer zkServer;

    private static final Map<PropertyDescriptor, String> defaultProperties = new HashMap<>();
    private static NiFiProperties nifiProperties;
    private static final String KEYSTORE = "/a/keyStore.jks";
    private static final String KEYSTORE_PASSWORD = "aKeystorePassword";
    private static final String KEYSTORE_TYPE = "JKS";
    private static final String TRUSTSTORE = "/a/trustStore.jks";
    private static final String TRUSTSTORE_PASSWORD = "aTruststorePassword";
    private static final String TRUSTSTORE_TYPE = "JKS";

    static {
        defaultProperties.put(ZooKeeperStateProvider.SESSION_TIMEOUT, "15 secs");
        defaultProperties.put(ZooKeeperStateProvider.ROOT_NODE, "/nifi/team1/testing");
        defaultProperties.put(ZooKeeperStateProvider.ACCESS_CONTROL, ZooKeeperStateProvider.OPEN_TO_WORLD.getValue());
    }

    @Before
    public void setup() throws Exception {
        zkServer = new TestingServer(true);
        zkServer.start();

        final Map<PropertyDescriptor, String> properties = new HashMap<>(defaultProperties);
        properties.put(ZooKeeperStateProvider.CONNECTION_STRING, zkServer.getConnectString());
        this.provider = createProvider(properties);
    }

    private void initializeProvider(final ZooKeeperStateProvider provider, final Map<PropertyDescriptor, String> properties) throws IOException {
        provider.initialize(new StateProviderInitializationContext() {
            @Override
            public String getIdentifier() {
                return "Unit Test Provider Initialization Context";
            }

            @Override
            public Map<PropertyDescriptor, PropertyValue> getProperties() {
                final Map<PropertyDescriptor, PropertyValue> propValueMap = new HashMap<>();
                for (final Map.Entry<PropertyDescriptor, String> entry : properties.entrySet()) {
                    propValueMap.put(entry.getKey(), new StandardPropertyValue(entry.getValue(), null, ParameterLookup.EMPTY));
                }
                return propValueMap;
            }

            @Override
            public Map<String,String> getAllProperties() {
                final Map<String,String> propValueMap = new LinkedHashMap<>();
                for (final Map.Entry<PropertyDescriptor, PropertyValue> entry : getProperties().entrySet()) {
                    propValueMap.put(entry.getKey().getName(), entry.getValue().getValue());
                }
                return propValueMap;
            }

            @Override
            public PropertyValue getProperty(final PropertyDescriptor property) {
                final String prop = properties.get(property);
                return new StandardPropertyValue(prop, null, ParameterLookup.EMPTY);
            }

            @Override
            public SSLContext getSSLContext() {
                return null;
            }

            @Override
            public ComponentLog getLogger() {
                return null;
            }
        });
    }

    private ZooKeeperStateProvider createProvider(final Map<PropertyDescriptor, String> properties) throws Exception {
        final ZooKeeperStateProvider provider = new ZooKeeperStateProvider();
        nifiProperties = createTestNiFiProperties();
        provider.setNiFiProperties(nifiProperties);
        initializeProvider(provider, properties);
        provider.enable();
        return provider;
    }

    private NiFiProperties createTestNiFiProperties() {
        Properties keystoreProps = new Properties();
        keystoreProps.setProperty(NiFiProperties.SECURITY_KEYSTORE, KEYSTORE);
        keystoreProps.setProperty(NiFiProperties.SECURITY_KEYSTORE_PASSWD, KEYSTORE_PASSWORD);
        keystoreProps.setProperty(NiFiProperties.SECURITY_KEYSTORE_TYPE, KEYSTORE_TYPE);

        return NiFiProperties.createBasicNiFiProperties(null, keystoreProps);
    }

    @After
    public void clear() throws IOException {
        try {
            if (provider != null) {
                provider.onComponentRemoved(componentId);
                provider.disable();
                provider.shutdown();
            }
        } finally {
            if (zkServer != null) {
                zkServer.stop();
                zkServer.close();
            }
        }
    }

    @Override
    protected StateProvider getProvider() {
        return provider;
    }

    @Test(timeout = 30000)
    public void testStateTooLargeExceptionThrownOnSetState() throws InterruptedException {
        final Map<String, String> state = new HashMap<>();
        final StringBuilder sb = new StringBuilder();

        // Build a string that is a little less than 64 KB, because that's
        // the largest value available for DataOutputStream.writeUTF
        for (int i = 0; i < 6500; i++) {
            sb.append("0123456789");
        }

        for (int i = 0; i < 20; i++) {
            state.put("numbers." + i, sb.toString());
        }

        while (true) {
            try {
                getProvider().setState(state, componentId);
                Assert.fail("Expected StateTooLargeException");
            } catch (final StateTooLargeException stle) {
                // expected behavior.
                break;
            } catch (final IOException ioe) {
                // If we attempt to interact with the server too quickly, we will get a
                // ZooKeeper ConnectionLoss Exception, which the provider wraps in an IOException.
                // We will wait 1 second in this case and try again. The test will timeout if this
                // does not succeeed within 30 seconds.
                Thread.sleep(1000L);
            } catch (final Exception e) {
                Assert.fail("Expected StateTooLargeException but " + e.getClass() + " was thrown", e);
            }
        }
    }

    @Test(timeout = 30000)
    public void testStateTooLargeExceptionThrownOnReplace() throws InterruptedException {
        final Map<String, String> state = new HashMap<>();
        final StringBuilder sb = new StringBuilder();

        // Build a string that is a little less than 64 KB, because that's
        // the largest value available for DataOutputStream.writeUTF
        for (int i = 0; i < 6500; i++) {
            sb.append("0123456789");
        }

        for (int i = 0; i < 20; i++) {
            state.put("numbers." + i, sb.toString());
        }

        final Map<String, String> smallState = new HashMap<>();
        smallState.put("abc", "xyz");

        while (true) {
            try {
                getProvider().setState(smallState, componentId);
                break;
            } catch (final IOException ioe) {
                // If we attempt to interact with the server too quickly, we will get a
                // ZooKeeper ConnectionLoss Exception, which the provider wraps in an IOException.
                // We will wait 1 second in this case and try again. The test will timeout if this
                // does not succeeed within 30 seconds.
                Thread.sleep(1000L);
            }
        }

        assertThrows(StateTooLargeException.class, () -> getProvider().replace(getProvider().getState(componentId), state, componentId));
    }

    @Test(timeout = 5000)
    public void testStateTooLargeExceptionThrownOnReplaceSmallJuteMaxbuffer() throws Exception {
        final Map<String, String> initialState = new HashMap<>();
        final String stateValue = UUID.randomUUID().toString();
        final int maxBufferSize = 100;
        initialState.put("1", stateValue);

        final Map<PropertyDescriptor, String> properties = new HashMap<>(defaultProperties);
        properties.put(ZooKeeperStateProvider.CONNECTION_STRING, zkServer.getConnectString());

        final ZooKeeperStateProvider stateProvider = new ZooKeeperStateProvider();
        final Properties applicationProperties = new Properties();
        applicationProperties.setProperty(NiFiProperties.ZOOKEEPER_JUTE_MAXBUFFER, Integer.toString(maxBufferSize));
        final NiFiProperties providerProperties = NiFiProperties.createBasicNiFiProperties(null, applicationProperties);
        stateProvider.setNiFiProperties(providerProperties);
        initializeProvider(stateProvider, properties);

        try {
            stateProvider.enable();

            while (true) {
                try {
                    stateProvider.setState(initialState, componentId);
                    break;
                } catch (final IOException ioe) {
                    // Retry initial connection
                    Thread.sleep(1000L);
                }
            }

            final Map<String, String> state = new HashMap<>();
            state.put("1", stateValue);
            state.put("2", stateValue);
            state.put("3", stateValue);
            state.put("4", stateValue);
            assertThrows(StateTooLargeException.class, () -> stateProvider.replace(getProvider().getState(componentId), state, componentId));
        } finally {
            stateProvider.shutdown();
        }
    }

    @Test
    public void testCombineProperties() {
        Properties truststoreProps = new Properties();
        truststoreProps.setProperty(NiFiProperties.SECURITY_TRUSTSTORE, TRUSTSTORE);
        truststoreProps.setProperty(NiFiProperties.SECURITY_TRUSTSTORE_PASSWD, TRUSTSTORE_PASSWORD);
        truststoreProps.setProperty(NiFiProperties.SECURITY_TRUSTSTORE_TYPE, TRUSTSTORE_TYPE);

        NiFiProperties combinedProperties = ZooKeeperStateProvider.combineProperties(nifiProperties, truststoreProps);
        assertEquals(KEYSTORE, combinedProperties.getProperty(NiFiProperties.SECURITY_KEYSTORE));
        assertEquals(TRUSTSTORE, combinedProperties.getProperty(NiFiProperties.SECURITY_TRUSTSTORE));
    }

    @Test
    public void testCombinePropertiesOverridesWithAdditionalProperties() {
        final String OVERRIDE_KEYSTORE = "/override/keystore.jks";
        final String OVERRIDE_KEYSTORE_PASSWORD = "overridePassword";

        Properties overrideProps = new Properties();
        overrideProps.setProperty(NiFiProperties.SECURITY_KEYSTORE, OVERRIDE_KEYSTORE);
        overrideProps.setProperty(NiFiProperties.SECURITY_KEYSTORE_PASSWD, OVERRIDE_KEYSTORE_PASSWORD);

        NiFiProperties combinedProperties = ZooKeeperStateProvider.combineProperties(nifiProperties, overrideProps);
        assertEquals(OVERRIDE_KEYSTORE, combinedProperties.getProperty(NiFiProperties.SECURITY_KEYSTORE));
        assertEquals(OVERRIDE_KEYSTORE_PASSWORD, combinedProperties.getProperty(NiFiProperties.SECURITY_KEYSTORE_PASSWD));
    }

    @Test
    public void testCombineNullNiFiPropsWithPropertiesOverrides() {
        final String OVERRIDE_KEYSTORE = "/override/keystore.jks";
        final String OVERRIDE_KEYSTORE_PASSWORD = "overridePassword";

        Properties overrideProps = new Properties();
        overrideProps.setProperty(NiFiProperties.SECURITY_KEYSTORE, OVERRIDE_KEYSTORE);
        overrideProps.setProperty(NiFiProperties.SECURITY_KEYSTORE_PASSWD, OVERRIDE_KEYSTORE_PASSWORD);

        NiFiProperties combinedProperties = ZooKeeperStateProvider.combineProperties(null, overrideProps);
        assertEquals(OVERRIDE_KEYSTORE, combinedProperties.getProperty(NiFiProperties.SECURITY_KEYSTORE));
        assertEquals(OVERRIDE_KEYSTORE_PASSWORD, combinedProperties.getProperty(NiFiProperties.SECURITY_KEYSTORE_PASSWD));
    }
}
