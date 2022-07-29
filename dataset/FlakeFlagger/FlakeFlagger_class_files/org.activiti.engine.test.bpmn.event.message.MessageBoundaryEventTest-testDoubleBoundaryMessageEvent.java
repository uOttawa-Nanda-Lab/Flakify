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

  @Deployment public void testDoubleBoundaryMessageEvent(){runtimeService.startProcessInstanceByKey("process");assertEquals(4,runtimeService.createExecutionQuery().count());Task userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);Execution execution1=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_1").singleResult();assertNotNull(execution1);Execution execution2=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_2").singleResult();assertNotNull(execution2);assertFalse(execution1.getId().equals(execution2.getId()));runtimeService.messageEventReceived("messageName_1",execution1.getId());try {runtimeService.messageEventReceived("messageName_2",execution2.getId());fail();} catch (Exception e){}userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterMessage_1",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());runtimeService.startProcessInstanceByKey("process");userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);taskService.complete(userTask.getId());execution1=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_1").singleResult();assertNull(execution1);execution2=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName_2").singleResult();assertNull(execution2);userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterTask",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());}

}
