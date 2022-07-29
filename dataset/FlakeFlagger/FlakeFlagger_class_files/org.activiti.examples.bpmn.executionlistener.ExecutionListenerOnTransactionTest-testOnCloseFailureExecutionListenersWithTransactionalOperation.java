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

  @Deployment public void testOnCloseFailureExecutionListenersWithTransactionalOperation(){MyTransactionalOperationTransactionDependentExecutionListener.clear();ProcessInstance firstProcessInstance=runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");assertProcessEnded(firstProcessInstance.getId());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();assertEquals(1,historicProcessInstances.size());assertEquals("transactionDependentExecutionListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());}ProcessInstance secondProcessInstance=runtimeService.startProcessInstanceByKey("secondTransactionDependentExecutionListenerProcess");assertProcessEnded(secondProcessInstance.getId());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().list();assertEquals(1,historicProcessInstances.size());assertEquals("secondTransactionDependentExecutionListenerProcess",historicProcessInstances.get(0).getProcessDefinitionKey());}List<MyTransactionalOperationTransactionDependentExecutionListener.CurrentActivity> currentActivities=MyTransactionalOperationTransactionDependentExecutionListener.getCurrentActivities();assertEquals(1,currentActivities.size());assertEquals("serviceTask1",currentActivities.get(0).getActivityId());assertEquals("Service Task 1",currentActivities.get(0).getActivityName());}

}
