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

  @Deployment public void testBoundaryMessageEventOnSubprocessAndInsideSubprocessMultiInstance(){runtimeService.startProcessInstanceByKey("process");assertEquals(18,runtimeService.createExecutionQuery().count());List<Task> userTasks=taskService.createTaskQuery().list();assertNotNull(userTasks);assertEquals(5,userTasks.size());List<Execution> executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").list();assertNotNull(executions);assertEquals(5,executions.size());executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName2").list();assertNotNull(executions);assertEquals(1,executions.size());Execution outerScopeExecution=executions.get(0);runtimeService.messageEventReceived("messageName2",outerScopeExecution.getId());executions=runtimeService.createExecutionQuery().messageEventSubscriptionName("messageName").list();assertEquals(0,executions.size());Task userTask=taskService.createTaskQuery().singleResult();assertNotNull(userTask);assertEquals("taskAfterOuterMessageBoundary",userTask.getTaskDefinitionKey());taskService.complete(userTask.getId());}

}
