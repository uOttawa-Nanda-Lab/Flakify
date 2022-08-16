/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.api.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ambari.server.state.ServiceInfo;
import org.apache.ambari.server.state.StackInfo;
import org.apache.ambari.server.utils.StageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AmbariMetaServiceTest extends JerseyTest {
  static String PACKAGE_NAME = "org.apache.ambari.server.api.services";
  private static Log LOG = LogFactory.getLog(AmbariMetaService.class);
  Injector injector;
  protected Client client;

  public class MockModule extends AbstractModule {
    File stackRoot = new File("src/test/resources/stacks");
    AmbariMetaInfo ambariMetaInfo;
    
    public MockModule() throws Exception {
      this.ambariMetaInfo = new AmbariMetaInfo(stackRoot);
    }

    @Override
    protected void configure() {
      bind(AmbariMetaInfo.class).toInstance(ambariMetaInfo);
      requestStaticInjection(AmbariMetaService.class);     
    }
  }

  @Test public void testStacks() throws UniformInterfaceException,JSONException,IOException{ClientConfig clientConfig=new DefaultClientConfig();clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);client=Client.create(clientConfig);WebResource webResource=client.resource("http://localhost:9998/stacks");String output=webResource.get(String.class);LOG.info("All Stack Info \n" + output);ObjectMapper mapper=new ObjectMapper();mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);List<StackInfo> stackInfos=mapper.readValue(output,new TypeReference<List<StackInfo>>(){});StackInfo stackInfo=stackInfos.get(0);Assert.assertEquals("HDP",stackInfo.getName());webResource=client.resource("http://localhost:9998/stacks/" + "HDP/version/0.1/services/HDFS");output=webResource.get(String.class);ServiceInfo info=mapper.readValue(output,ServiceInfo.class);Assert.assertEquals("HDFS",info.getName());}
}