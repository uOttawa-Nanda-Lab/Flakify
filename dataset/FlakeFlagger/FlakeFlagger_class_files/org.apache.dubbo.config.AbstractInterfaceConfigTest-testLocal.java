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
package org.apache.dubbo.config;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.common.utils.IOUtils;
import org.apache.dubbo.config.api.Greeting;
import org.apache.dubbo.config.mock.GreetingLocal1;
import org.apache.dubbo.config.mock.GreetingLocal2;
import org.apache.dubbo.config.mock.GreetingLocal3;
import org.apache.dubbo.config.mock.GreetingMock1;
import org.apache.dubbo.config.mock.GreetingMock2;
import org.apache.dubbo.monitor.MonitorService;
import org.apache.dubbo.registry.RegistryService;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class AbstractInterfaceConfigTest {
    @ClassRule
    public static TemporaryFolder tempDir = new TemporaryFolder();
    private static File dubboProperties;

    @BeforeClass
    public static void setUp() throws Exception {
        dubboProperties = tempDir.newFile(Constants.DUBBO_PROPERTIES_KEY);
        System.setProperty(Constants.DUBBO_PROPERTIES_KEY, dubboProperties.getAbsolutePath());
    }

    @Test public void testLocal() throws Exception{InterfaceConfig interfaceConfig=new InterfaceConfig();interfaceConfig.setLocal((Boolean)null);TestCase.assertNull(interfaceConfig.getLocal());interfaceConfig.setLocal(true);TestCase.assertEquals("true",interfaceConfig.getLocal());interfaceConfig.setLocal("GreetingMock");TestCase.assertEquals("GreetingMock",interfaceConfig.getLocal());}

    private void writeDubboProperties(String key, String value) {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(dubboProperties));
            Properties properties = new Properties();
            properties.put(key, value);
            properties.store(os, "");
            os.close();
        } catch (IOException e) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ioe) {
                    // ignore
                }
            }
        }
    }

    private static class InterfaceConfig extends AbstractInterfaceConfig {

    }
}
