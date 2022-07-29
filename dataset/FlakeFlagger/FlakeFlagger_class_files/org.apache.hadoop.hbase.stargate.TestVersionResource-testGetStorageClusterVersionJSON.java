/*
 * Copyright 2010 The Apache Software Foundation
 *
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

package org.apache.hadoop.hbase.stargate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.stargate.client.Cluster;
import org.apache.hadoop.hbase.stargate.client.Response;
import org.apache.hadoop.hbase.stargate.model.StorageClusterVersionModel;
import org.apache.hadoop.hbase.stargate.model.VersionModel;
import org.apache.hadoop.hbase.util.Bytes;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class TestVersionResource extends MiniClusterTestBase {
  private static final Log LOG =
    LogFactory.getLog(TestVersionResource.class);

  private Client client;
  private JAXBContext context;

  private static void validate(VersionModel model) {
    assertNotNull(model);
    assertNotNull(model.getStargateVersion());
    assertEquals(model.getStargateVersion(), RESTServlet.VERSION_STRING);
    String osVersion = model.getOsVersion(); 
    assertNotNull(osVersion);
    assertTrue(osVersion.contains(System.getProperty("os.name")));
    assertTrue(osVersion.contains(System.getProperty("os.version")));
    assertTrue(osVersion.contains(System.getProperty("os.arch")));
    String jvmVersion = model.getJvmVersion();
    assertNotNull(jvmVersion);
    assertTrue(jvmVersion.contains(System.getProperty("java.vm.vendor")));
    assertTrue(jvmVersion.contains(System.getProperty("java.version")));
    assertTrue(jvmVersion.contains(System.getProperty("java.vm.version")));
    assertNotNull(model.getServerVersion());
    String jerseyVersion = model.getJerseyVersion();
    assertNotNull(jerseyVersion);
    assertEquals(jerseyVersion, ServletContainer.class.getPackage()
      .getImplementationVersion());
  }

  public void testGetStorageClusterVersionJSON() throws IOException {
	Response response = client.get("/version/cluster", MIMETYPE_JSON);
	assertTrue(response.getCode() == 200);
}

}
