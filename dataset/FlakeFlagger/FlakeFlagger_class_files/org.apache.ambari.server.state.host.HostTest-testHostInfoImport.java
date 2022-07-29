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

package org.apache.ambari.server.state.host;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.actionmanager.ActionManager;
import org.apache.ambari.server.agent.ActionQueue;
import org.apache.ambari.server.agent.DiskInfo;
import org.apache.ambari.server.agent.HeartBeatHandler;
import org.apache.ambari.server.agent.HostInfo;
import org.apache.ambari.server.agent.TestHeartbeatHandler;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.orm.dao.HostDAO;
import org.apache.ambari.server.orm.entities.HostEntity;
import org.apache.ambari.server.state.AgentVersion;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.Host;
import org.apache.ambari.server.state.HostHealthStatus;
import org.apache.ambari.server.state.HostHealthStatus.HealthStatus;
import org.apache.ambari.server.state.HostState;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public class HostTest {

  private Injector injector;
  private Clusters clusters;
  private HostDAO hostDAO;
  private static Log LOG = LogFactory.getLog(HostTest.class);

  @Before
   public void setup() throws AmbariException{
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    hostDAO = injector.getInstance(HostDAO.class);
  }

  @Test public void testHostInfoImport() throws AmbariException{HostInfo info=new HostInfo();info.setMemorySize(100);info.setPhysicalProcessorCount(10);List<DiskInfo> mounts=new ArrayList<DiskInfo>();mounts.add(new DiskInfo("/dev/sda","/mnt/disk1","5000000","4000000","10%","size","fstype"));info.setMounts(mounts);info.setHostName("foo");info.setInterfaces("fip_4");info.setArchitecture("os_arch");info.setOS("os_type");info.setMemoryTotal(10);clusters.addHost("foo");Host host=clusters.getHost("foo");host.importHostInfo(info);Assert.assertEquals(info.getHostName(),host.getHostName());Assert.assertEquals(info.getFreeMemory(),host.getAvailableMemBytes());Assert.assertEquals(info.getMemoryTotal(),host.getTotalMemBytes());Assert.assertEquals(info.getPhysicalProcessorCount(),host.getCpuCount());Assert.assertEquals(info.getMounts().size(),host.getDisksInfo().size());Assert.assertEquals(info.getArchitecture(),host.getOsArch());Assert.assertEquals(info.getOS(),host.getOsType());}

  private void registerHost(Host host) throws Exception {
    registerHost(host, true);
  }
  
  private void registerHost(Host host, boolean firstReg) throws Exception {
    HostInfo info = new HostInfo();
    info.setMemorySize(100);
    info.setProcessorCount(10);
    List<DiskInfo> mounts = new ArrayList<DiskInfo>();
    mounts.add(new DiskInfo("/dev/sda", "/mnt/disk1",
        "5000000", "4000000", "10%", "size", "fstype"));
    info.setMounts(mounts);

    info.setHostName("foo");
    info.setInterfaces("fip_4");
    info.setArchitecture("os_arch");
    info.setOS("os_type");
    info.setMemoryTotal(10);

    AgentVersion agentVersion = null;
    long currentTime = System.currentTimeMillis();

    HostRegistrationRequestEvent e =
        new HostRegistrationRequestEvent("foo", agentVersion, currentTime,
            info);
    if (!firstReg) {
      Assert.assertTrue(host.isPersisted());
    }
    host.handleEvent(e);
    Assert.assertEquals(currentTime, host.getLastRegistrationTime());

    HostEntity entity = hostDAO.findByName(host.getHostName());
    Assert.assertEquals(currentTime,
        entity.getLastRegistrationTime().longValue());
    Assert.assertEquals("os_arch", entity.getOsArch());
    Assert.assertEquals("os_type", entity.getOsType());
    Assert.assertEquals(10, entity.getTotalMem().longValue());
  }

  private void ensureHostUpdatesReceived(Host host) throws Exception {
    HostStatusUpdatesReceivedEvent e =
        new HostStatusUpdatesReceivedEvent(host.getHostName(), 1);
    host.handleEvent(e);
  }

  private void verifyHostState(Host host, HostState state) {
    Assert.assertEquals(state, host.getState());
  }

  private void sendHealthyHeartbeat(Host host, long counter)
      throws Exception {
    HostHealthyHeartbeatEvent e = new HostHealthyHeartbeatEvent(
        host.getHostName(), counter);
    host.handleEvent(e);
  }

  private void sendUnhealthyHeartbeat(Host host, long counter)
      throws Exception {
    HostHealthStatus healthStatus = new HostHealthStatus(HealthStatus.UNHEALTHY,
        "Unhealthy server");
    HostUnhealthyHeartbeatEvent e = new HostUnhealthyHeartbeatEvent(
        host.getHostName(), counter, healthStatus);
    host.handleEvent(e);
  }

  private void timeoutHost(Host host) throws Exception {
    HostHeartbeatLostEvent e = new HostHeartbeatLostEvent(
        host.getHostName());
    host.handleEvent(e);
  }
}
