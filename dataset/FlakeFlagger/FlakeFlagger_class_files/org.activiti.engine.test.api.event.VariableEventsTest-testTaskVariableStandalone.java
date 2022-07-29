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
package org.activiti.engine.test.api.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiVariableEvent;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to variables.
 */
public class VariableEventsTest extends PluggableActivitiTestCase {

    private TestVariableEventListener listener;

    /**
	 * Test to check create, update an delete behavior for variables on a task not related to a process.
	 */
	public void testTaskVariableStandalone() throws Exception {
		Task newTask = taskService.newTask();
		try {
			taskService.saveTask(newTask);
			taskService.setVariable(newTask.getId(), "testVariable", 123);
			taskService.setVariable(newTask.getId(), "testVariable", 456);
			taskService.removeVariable(newTask.getId(), "testVariable");
			assertEquals(3, listener.getEventsReceived().size());
			ActivitiVariableEvent event = (ActivitiVariableEvent) listener.getEventsReceived().get(0);
			assertEquals(ActivitiEventType.VARIABLE_CREATED, event.getType());
			assertNull(event.getProcessDefinitionId());
			assertNull(event.getExecutionId());
			assertNull(event.getProcessInstanceId());
			assertEquals(newTask.getId(), event.getTaskId());
			assertEquals("testVariable", event.getVariableName());
			assertEquals(123, event.getVariableValue());
			event = (ActivitiVariableEvent) listener.getEventsReceived().get(1);
			assertEquals(ActivitiEventType.VARIABLE_UPDATED, event.getType());
			assertNull(event.getProcessDefinitionId());
			assertNull(event.getExecutionId());
			assertNull(event.getProcessInstanceId());
			assertEquals(newTask.getId(), event.getTaskId());
			assertEquals("testVariable", event.getVariableName());
			assertEquals(456, event.getVariableValue());
			event = (ActivitiVariableEvent) listener.getEventsReceived().get(2);
			assertEquals(ActivitiEventType.VARIABLE_DELETED, event.getType());
			assertNull(event.getProcessDefinitionId());
			assertNull(event.getExecutionId());
			assertNull(event.getProcessInstanceId());
			assertEquals(newTask.getId(), event.getTaskId());
			assertEquals("testVariable", event.getVariableName());
			assertEquals(null, event.getVariableValue());
		} finally {
			if (newTask.getId() != null) {
				taskService.deleteTask(newTask.getId());
				if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
					historyService.deleteHistoricTaskInstance(newTask.getId());
				}
			}
		}
	}
}
