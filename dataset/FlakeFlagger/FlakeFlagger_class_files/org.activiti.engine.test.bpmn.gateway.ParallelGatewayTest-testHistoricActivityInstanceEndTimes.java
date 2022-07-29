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

package org.activiti.engine.test.bpmn.gateway;

import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

/**

 */
public class ParallelGatewayTest extends PluggableActivitiTestCase {

  

  /*
   * @Deployment public void testAsyncBehavior() { for (int i = 0; i < 100; i++) { ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("async"); } assertEquals(200,
   * managementService.createJobQuery().count()); waitForJobExecutorToProcessAllJobs(120000, 5000); assertEquals(0, managementService.createJobQuery().count()); assertEquals(0,
   * runtimeService.createProcessInstanceQuery().count()); }
   */
  
  @Deployment public void testHistoricActivityInstanceEndTimes(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){runtimeService.startProcessInstanceByKey("nestedForkJoin");List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().list();assertEquals(21,historicActivityInstances.size());for (HistoricActivityInstance historicActivityInstance:historicActivityInstances){Assert.assertTrue(historicActivityInstance.getStartTime() != null);Assert.assertTrue(historicActivityInstance.getEndTime() != null);}}}

}
