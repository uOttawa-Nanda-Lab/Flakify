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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.apache.ambari.server.state.ComponentInfo;
import org.apache.ambari.server.state.RepositoryInfo;
import org.apache.ambari.server.state.ServiceInfo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmbariMetaInfoTest {

  private static String STACK_NAME_HDP = "HDP";
  private static String STACK_VERSION_HDP = "0.1";
  private static String SERVICE_NAME_HDFS = "HDFS";
  private static String SERVICE_COMPONENT_NAME = "NAMENODE";

  private AmbariMetaInfo metaInfo = null;
  private final static Logger LOG =
      LoggerFactory.getLogger(AmbariMetaInfoTest.class);

  @Before
  public void before() throws Exception {
    File stackRoot = new File("src/test/resources/stacks");
   LOG.info("Stacks file " + stackRoot.getAbsolutePath());
    metaInfo = new AmbariMetaInfo(stackRoot);
    try {
      metaInfo.init();
    } catch(Exception e) {
      LOG.info("Error in initializing ", e);
    }
  }

  /**
 * Method: Map<String, ServiceInfo> getServices(String stackName, String version, String serviceName)
 */@Test public void getServices(){Map<String, ServiceInfo> services=metaInfo.getServices(STACK_NAME_HDP,STACK_VERSION_HDP);LOG.info("Getting all the services ");for (Map.Entry<String, ServiceInfo> entry:services.entrySet()){LOG.info("Service Name " + entry.getKey() + " values " + entry.getValue());}assertTrue(services.containsKey("HDFS"));assertTrue(services.containsKey("MAPREDUCE"));assertNotNull(services);assertNotSame(services.keySet().size(),0);}

}
