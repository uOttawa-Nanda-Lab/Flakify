/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.test.bpmn.async;

import java.util.Date;
import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

/**
 * 

 */
public class AsyncTaskTest extends PluggableActivitiTestCase {

  public static boolean INVOCATION;

  @Deployment public void testMultiInstanceAsyncTask(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncTask");assertEquals(3,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).list().get(0).getId());assertEquals(2,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).list().get(0).getId());assertEquals(1,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());managementService.executeJob(managementService.createJobQuery().processInstanceId(processInstance.getId()).singleResult().getId());assertEquals(0,managementService.createJobQuery().processInstanceId(processInstance.getId()).count());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();int startCount=0;int taskCount=0;int endCount=0;for (HistoricActivityInstance historicActivityInstance:historicActivities){if ("task".equals(historicActivityInstance.getActivityId())){taskCount++;} else if ("theStart".equals(historicActivityInstance.getActivityId())){startCount++;} else if ("theEnd".equals(historicActivityInstance.getActivityId())){endCount++;} else {Assert.fail("Unexpected activity found " + historicActivityInstance.getActivityId());}}assertEquals(1,startCount);assertEquals(3,taskCount);assertEquals(1,endCount);}}

}
