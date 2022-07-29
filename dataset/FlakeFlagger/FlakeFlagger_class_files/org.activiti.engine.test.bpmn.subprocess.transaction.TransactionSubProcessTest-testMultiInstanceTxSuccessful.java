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

  @Deployment(resources={"org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testMultiInstanceTx.bpmn20.xml"}) public void testMultiInstanceTxSuccessful(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");List<EventSubscriptionEntity> EventSubscriptionEntitys=createEventSubscriptionQuery().eventType("compensate").list();assertEquals(10,EventSubscriptionEntitys.size());List<Task> tasks=taskService.createTaskQuery().list();for (Task task:tasks){taskService.setVariable(task.getId(),"confirmed",true);taskService.complete(task.getId());}List<Execution> executions=runtimeService.createExecutionQuery().activityId("receive").list();for (Execution execution:executions){runtimeService.trigger(execution.getId());}runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterSuccess").singleResult().getId());assertEquals(0,createEventSubscriptionQuery().count());assertProcessEnded(processInstance.getId());}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }
}
