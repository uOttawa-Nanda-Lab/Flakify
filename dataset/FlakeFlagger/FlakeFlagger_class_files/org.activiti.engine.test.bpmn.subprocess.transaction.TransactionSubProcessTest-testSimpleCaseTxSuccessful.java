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

  @Deployment(resources={"org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testSimpleCase.bpmn20.xml"}) public void testSimpleCaseTxSuccessful(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").count());assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").count());Task task=taskService.createTaskQuery().singleResult();assertNotNull(task);taskService.setVariable(task.getId(),"confirmed",true);taskService.complete(task.getId());List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());assertTrue(activeActivityIds.contains("afterSuccess"));EventSubscriptionEntity eventSubscriptionEntity=createEventSubscriptionQuery().eventType("compensate").activityId("tx").executionId(processInstance.getId()).singleResult();assertNotNull(eventSubscriptionEntity.getConfiguration());Execution eventScopeExecution=runtimeService.createExecutionQuery().executionId(eventSubscriptionEntity.getConfiguration()).singleResult();assertNotNull(eventScopeExecution);assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookHotel").executionId(eventScopeExecution.getId()).count());assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoBookFlight").executionId(eventScopeExecution.getId()).count());assertEquals(1,createEventSubscriptionQuery().eventType("compensate").activityId("undoChargeCard").executionId(eventScopeExecution.getId()).count());assertNull(runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));assertNull(runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));assertNull(runtimeService.getVariable(processInstance.getId(),"undoChargeCard"));Execution receiveExecution=runtimeService.createExecutionQuery().activityId("afterSuccess").singleResult();runtimeService.trigger(receiveExecution.getId());assertProcessEnded(processInstance.getId());assertEquals(0,runtimeService.createExecutionQuery().count());}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }
}
