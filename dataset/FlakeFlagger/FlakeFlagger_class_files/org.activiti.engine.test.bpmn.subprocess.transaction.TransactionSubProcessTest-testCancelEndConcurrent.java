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

package org.activiti.engine.test.bpmn.subprocess.transaction;

import java.util.List;

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TransactionSubProcessTest extends PluggableActivitiTestCase {

  @Deployment public void testCancelEndConcurrent(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").count());assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());Task task=taskService.createTaskQuery().singleResult();assertNotNull(task);assertEquals("askCustomer",task.getTaskDefinitionKey());taskService.setVariable(task.getId(),"confirmed",false);taskService.complete(task.getId());List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());assertTrue(activeActivityIds.contains("afterCancellation"));assertEquals(0,createEventSubscriptionQuery().eventType("compensate").count());assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));assertEquals(1,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookHotel").count());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("undoBookFlight").count());}Execution receiveExecution=runtimeService.createExecutionQuery().activityId("afterCancellation").singleResult();runtimeService.trigger(receiveExecution.getId());assertProcessEnded(processInstance.getId());assertEquals(0,runtimeService.createExecutionQuery().count());}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }
}
