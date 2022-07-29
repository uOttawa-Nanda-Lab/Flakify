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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to tasks.
 * 

 */
public class TaskEventsTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  /**
   * Check all events for tasks not related to a process-instance
   */
  public void testStandaloneTaskEvents() throws Exception {

    Task task = null;
    try {
      task = taskService.newTask();
      task.setCategory("123");
      task.setDescription("Description");
      taskService.saveTask(task);

      assertEquals(3, listener.getEventsReceived().size());

      ActivitiEntityEvent event = (ActivitiEntityEvent) listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_CREATED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      Task taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());

      event = (ActivitiEntityEvent) listener.getEventsReceived().get(1);
      assertEquals(ActivitiEventType.ENTITY_INITIALIZED, event.getType());

      event = (ActivitiEntityEvent) listener.getEventsReceived().get(2);
      assertEquals(ActivitiEventType.TASK_CREATED, event.getType());
      listener.clearEventsReceived();

      // Update task
      taskService.setOwner(task.getId(), "owner");
      assertEquals(1, listener.getEventsReceived().size());
      event = (ActivitiEntityEvent) listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.ENTITY_UPDATED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertEquals("owner", taskFromEvent.getOwner());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      listener.clearEventsReceived();

      // Assign task
      taskService.setAssignee(task.getId(), "kermit");
      assertEquals(2, listener.getEventsReceived().size());
      event = (ActivitiEntityEvent) listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.TASK_ASSIGNED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertEquals("kermit", taskFromEvent.getAssignee());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      event = (ActivitiEntityEvent) listener.getEventsReceived().get(1);
      assertEquals(ActivitiEventType.ENTITY_UPDATED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());
      listener.clearEventsReceived();

      // Complete task
      taskService.complete(task.getId());
      assertEquals(2, listener.getEventsReceived().size());
      event = (ActivitiEntityEvent) listener.getEventsReceived().get(0);
      assertEquals(ActivitiEventType.TASK_COMPLETED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());

      event = (ActivitiEntityEvent) listener.getEventsReceived().get(1);
      assertEquals(ActivitiEventType.ENTITY_DELETED, event.getType());
      assertTrue(event.getEntity() instanceof Task);
      taskFromEvent = (Task) event.getEntity();
      assertEquals(task.getId(), taskFromEvent.getId());
      assertNull(event.getProcessDefinitionId());
      assertNull(event.getProcessInstanceId());
      assertNull(event.getExecutionId());

    } finally {
      if (task != null) {
        String taskId = task.getId();
        task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task != null) {
          // If task still exists, delete it to have a clean DB after
          // test
          taskService.deleteTask(taskId);
        }
        historyService.deleteHistoricTaskInstance(taskId);
      }
    }
  }

  protected void assertExecutionDetails(ActivitiEvent event, ProcessInstance processInstance) {
    assertEquals(processInstance.getId(), event.getProcessInstanceId());
    assertNotNull(event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(), event.getProcessDefinitionId());
  }
}
