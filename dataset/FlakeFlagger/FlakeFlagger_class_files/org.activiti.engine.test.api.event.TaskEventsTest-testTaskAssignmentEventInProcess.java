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

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testTaskAssignmentEventInProcess() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");assertNotNull(processInstance);listener.clearEventsReceived();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.setAssignee(task.getId(),"kermit");assertEquals(2,listener.getEventsReceived().size());ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());assertTrue(event.getEntity() instanceof Task);Task taskFromEvent=(Task)event.getEntity();assertEquals(task.getId(),taskFromEvent.getId());assertEquals("kermit",taskFromEvent.getAssignee());assertExecutionDetails(event,processInstance);event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());assertTrue(event.getEntity() instanceof Task);assertExecutionDetails(event,processInstance);listener.clearEventsReceived();task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();task.setAssignee("newAssignee");taskService.saveTask(task);assertEquals(2,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());assertTrue(event.getEntity() instanceof Task);taskFromEvent=(Task)event.getEntity();assertEquals(task.getId(),taskFromEvent.getId());assertEquals("newAssignee",taskFromEvent.getAssignee());assertExecutionDetails(event,processInstance);event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());assertTrue(event.getEntity() instanceof Task);assertExecutionDetails(event,processInstance);listener.clearEventsReceived();taskService.unclaim(task.getId());assertEquals(2,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.TASK_ASSIGNED,event.getType());assertTrue(event.getEntity() instanceof Task);taskFromEvent=(Task)event.getEntity();assertEquals(task.getId(),taskFromEvent.getId());assertEquals(null,taskFromEvent.getAssignee());assertExecutionDetails(event,processInstance);event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());assertTrue(event.getEntity() instanceof Task);assertExecutionDetails(event,processInstance);listener.clearEventsReceived();}

  protected void assertExecutionDetails(ActivitiEvent event, ProcessInstance processInstance) {
    assertEquals(processInstance.getId(), event.getProcessInstanceId());
    assertNotNull(event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(), event.getProcessDefinitionId());
  }
}
