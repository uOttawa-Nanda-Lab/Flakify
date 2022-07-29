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

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class MessageEventSubprocessTest extends PluggableActivitiTestCase {

  private void testInterruptingUnderProcessDefinition(int expectedNumberOfEventSubscriptions, int numberOfExecutions) {
	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process");
	Execution execution = runtimeService.createExecutionQuery().messageEventSubscriptionName("newMessage")
			.singleResult();
	assertNotNull(execution);
	assertEquals(expectedNumberOfEventSubscriptions, createEventSubscriptionQuery().count());
	assertEquals(numberOfExecutions, runtimeService.createExecutionQuery().count());
	Task task = taskService.createTaskQuery().singleResult();
	assertEquals("task", task.getTaskDefinitionKey());
	taskService.complete(task.getId());
	assertEquals(0, createEventSubscriptionQuery().count());
	assertEquals(0, runtimeService.createExecutionQuery().count());
	assertProcessEnded(processInstance.getId());
	processInstance = runtimeService.startProcessInstanceByKey("process");
	execution = runtimeService.createExecutionQuery().messageEventSubscriptionName("newMessage").singleResult();
	assertNotNull(execution);
	runtimeService.messageEventReceived("newMessage", execution.getId());
	task = taskService.createTaskQuery().singleResult();
	assertEquals("eventSubProcessTask", task.getTaskDefinitionKey());
	taskService.complete(task.getId());
	assertProcessEnded(processInstance.getId());
	assertEquals(0, createEventSubscriptionQuery().count());
	assertEquals(0, runtimeService.createExecutionQuery().count());
}

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }

}
