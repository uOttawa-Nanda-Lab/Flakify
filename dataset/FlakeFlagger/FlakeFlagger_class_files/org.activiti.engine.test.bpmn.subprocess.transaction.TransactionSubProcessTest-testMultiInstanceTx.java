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

  @Deployment public void testMultiInstanceTx(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");List<EventSubscriptionEntity> eventSubscriptionEntities=createEventSubscriptionQuery().eventType("compensate").list();assertEquals(10,eventSubscriptionEntities.size());Task task=taskService.createTaskQuery().listPage(0,1).get(0);taskService.setVariable(task.getId(),"confirmed",false);taskService.complete(task.getId());assertEquals(0,createEventSubscriptionQuery().count());assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterCancellation").singleResult().getId());assertProcessEnded(processInstance.getId());}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }
}
