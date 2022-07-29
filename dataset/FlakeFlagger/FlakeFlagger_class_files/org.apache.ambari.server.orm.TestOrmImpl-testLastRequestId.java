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

package org.apache.ambari.server.orm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import org.apache.ambari.server.Role;
import org.apache.ambari.server.actionmanager.HostRoleStatus;
import org.apache.ambari.server.orm.dao.*;
import org.apache.ambari.server.orm.entities.*;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestOrmImpl extends Assert {
  private static final Logger log = LoggerFactory.getLogger(TestOrmImpl.class);

  private static Injector injector;

  @Before
  public void setup() {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    injector.getInstance(OrmTestHelper.class).createDefaultData();
  }

  private void createService(Date currentTime, String serviceName, String clusterName) {
    ClusterServiceDAO clusterServiceDAO = injector.getInstance(ClusterServiceDAO.class);
    ClusterDAO clusterDAO = injector.getInstance(ClusterDAO.class);
    ClusterEntity cluster = clusterDAO.findByName(clusterName);

    ClusterServiceEntity clusterServiceEntity = new ClusterServiceEntity();
    clusterServiceEntity.setClusterEntity(cluster);
    clusterServiceEntity.setServiceName(serviceName);

    cluster.getClusterServiceEntities().add(clusterServiceEntity);

    clusterServiceDAO.create(clusterServiceEntity);
    clusterDAO.merge(cluster);

    clusterServiceEntity = clusterServiceDAO.findByClusterAndServiceNames(clusterName, serviceName);
    assertNotNull(clusterServiceEntity);

    clusterServiceDAO.merge(clusterServiceEntity);
  }

  @Test public void testLastRequestId(){injector.getInstance(OrmTestHelper.class).createStageCommands();ClusterDAO clusterDAO=injector.getInstance(ClusterDAO.class);StageDAO stageDAO=injector.getInstance(StageDAO.class);StageEntity stageEntity=new StageEntity();stageEntity.setCluster(clusterDAO.findByName("test_cluster1"));stageEntity.setRequestId(0L);stageEntity.setStageId(1L);stageDAO.create(stageEntity);StageEntity stageEntity2=new StageEntity();stageEntity2.setCluster(clusterDAO.findByName("test_cluster1"));stageEntity2.setRequestId(0L);stageEntity2.setStageId(2L);stageDAO.create(stageEntity2);assertEquals(0L,stageDAO.getLastRequestId());}

}
