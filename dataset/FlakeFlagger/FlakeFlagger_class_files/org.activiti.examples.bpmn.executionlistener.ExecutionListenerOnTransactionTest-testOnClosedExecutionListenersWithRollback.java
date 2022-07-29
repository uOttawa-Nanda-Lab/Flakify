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
package org.activiti.examples.bpmn.executionlistener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

/**

 */
public class ExecutionListenerOnTransactionTest extends PluggableActivitiTestCase {

  @Deployment public void testOnClosedExecutionListenersWithRollback(){CurrentActivityTransactionDependentExecutionListener.clear();Map<String, Object> variables=new HashMap<>();variables.put("serviceTask1",false);variables.put("serviceTask2",false);variables.put("serviceTask3",true);processEngineConfiguration.setAsyncExecutorActivate(false);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess",variables);try {managementService.executeJob(managementService.createJobQuery().singleResult().getId());} catch (Exception ex){}List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities=CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();assertEquals(1,currentActivities.size());assertEquals("serviceTask1",currentActivities.get(0).getActivityId());assertEquals("Service Task 1",currentActivities.get(0).getActivityName());assertEquals(processInstance.getId(),currentActivities.get(0).getProcessInstanceId());assertNotNull(currentActivities.get(0).getProcessInstanceId());assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());assertEquals(1,activeActivityIds.size());assertEquals("serviceTask2",activeActivityIds.get(0));}

}
