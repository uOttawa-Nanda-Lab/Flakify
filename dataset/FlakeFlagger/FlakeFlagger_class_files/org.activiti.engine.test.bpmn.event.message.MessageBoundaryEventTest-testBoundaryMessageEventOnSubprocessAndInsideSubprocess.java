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

package org.activiti.engine.test.bpmn.event.message;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class MessageBoundaryEventTest extends PluggableActivitiTestCase {

  @Deployment public void testBoundaryMessageEventOnSubprocessAndInsideSubprocess(){runtimeService.startProcessInstanceByKey("process");assertEquals(5,runtimeService.createExecutionQuery().count());Task userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);Execution execution1=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();assertNotNull(execution1);Execution execution2=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNotNull(execution2);assertNotSame(execution1.getId(),execution2.getId());taskService.complete(userTask.getId());userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterTask",userTask.getTaskDefinitionKey());Execution execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();assertNull(execution);execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNotNull(execution);taskService.complete(userTask.getId());execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNull(execution);userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterSubprocess",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());runtimeService.startProcessInstanceByKey("process");execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();runtimeService.messageEventReceived("messageName",execution.getId());userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterMessage",userTask.getTaskDefinitionKey());execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();assertNull(execution);execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNotNull(execution);taskService.complete(userTask.getId());execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNull(execution);userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterSubprocess",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());runtimeService.startProcessInstanceByKey("process");execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();runtimeService.messageEventReceived("messageName2",execution.getId());userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterOuterMessageBoundary",userTask.getTaskDefinitionKey());execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").singleResult();assertNull(execution);execution=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").singleResult();assertNull(execution);taskService.complete(userTask.getId());}

}
