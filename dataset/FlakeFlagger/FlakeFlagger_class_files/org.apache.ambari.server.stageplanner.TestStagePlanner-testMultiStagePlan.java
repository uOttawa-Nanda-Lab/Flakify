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
package org.apache.ambari.server.stageplanner;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.apache.ambari.server.Role;
import org.apache.ambari.server.RoleCommand;
import org.apache.ambari.server.actionmanager.Stage;
import org.apache.ambari.server.metadata.RoleCommandOrder;
import org.apache.ambari.server.state.svccomphost.ServiceComponentHostStartEvent;
import org.apache.ambari.server.utils.StageUtils;
import org.junit.Test;

public class TestStagePlanner {

  @Test public void testMultiStagePlan(){RoleCommandOrder.initialize();RoleCommandOrder rco=new RoleCommandOrder();RoleGraph rg=new RoleGraph(rco);long now=System.currentTimeMillis();Stage stage=StageUtils.getATestStage(1,1,"host1");stage.addHostRoleExecutionCommand("host2",Role.HBASE_MASTER,RoleCommand.START,new ServiceComponentHostStartEvent("HBASE_MASTER","host2",now,new HashMap<String, String>()),"cluster1","HBASE");stage.addHostRoleExecutionCommand("host3",Role.ZOOKEEPER_SERVER,RoleCommand.START,new ServiceComponentHostStartEvent("ZOOKEEPER_SERVER","host3",now,new HashMap<String, String>()),"cluster1","ZOOKEEPER");System.out.println(stage.toString());rg.build(stage);System.out.println(rg.stringifyGraph());List<Stage> outStages=rg.getStages();for (Stage s:outStages){System.out.println(s.toString());}assertEquals(3,outStages.size());}
}
