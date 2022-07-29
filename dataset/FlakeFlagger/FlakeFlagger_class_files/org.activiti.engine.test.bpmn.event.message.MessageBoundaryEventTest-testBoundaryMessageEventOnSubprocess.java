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

  @Deployment public void testBoundaryMessageEventOnSubprocess(){runtimeService.startProcessInstanceByKey("process");assertEquals(5,runtimeService.createExecutionQuery().count());Task userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);Execution executionMessageOne=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_one").singleResult();assertNotNull(executionMessageOne);runtimeService.messageEventReceived("messageName_one",executionMessageOne.getId());userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterMessage_one",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());runtimeService.startProcessInstanceByKey("process");Execution executionMessageTwo=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_two").singleResult();assertNotNull(executionMessageTwo);runtimeService.messageEventReceived("messageName_two",executionMessageTwo.getId());userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterMessage_two",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());runtimeService.startProcessInstanceByKey("process");userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);taskService.complete(userTask.getId());executionMessageOne=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_one").singleResult();assertNull(executionMessageOne);executionMessageTwo=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_two").singleResult();assertNull(executionMessageTwo);userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterSubProcess",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());}

}
