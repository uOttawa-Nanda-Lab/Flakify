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

  @Deployment(resources={"org/activiti/engine/test/api/event/TaskEventsTest.testEventFiring.bpmn20.xml"}) public void testEventFiringOrdering(){TestActivitiEntityEventTaskListener tlistener=new TestActivitiEntityEventTaskListener(Task.class);processEngineConfiguration.getEventDispatcher().addEventListener(tlistener);try {runtimeService.startProcessInstanceByKey("testTaskLocalVars");Task task=taskService.createTaskQuery().singleResult();Map<String, Object> taskParams=new HashMap<String, Object>();taskService.complete(task.getId(),taskParams,true);ActivitiEntityEvent event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertTrue(event.getEntity() instanceof Task);event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertTrue(event.getEntity() instanceof Task);event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(2);assertEquals(ActivitiEventType.TASK_CREATED,event.getType());assertTrue(event.getEntity() instanceof Task);Task taskFromEvent=tlistener.getTasks().get(2);assertEquals(task.getId(),taskFromEvent.getId());assertEquals("The ScriptTaskListener must set this value before the dispatchEvent fires.","scriptedAssignee",taskFromEvent.getAssignee());assertEquals("The ScriptTaskListener must set this value before the dispatchEvent fires.",877,taskFromEvent.getPriority());taskService.createTaskQuery().singleResult();}  finally {processEngineConfiguration.getEventDispatcher().removeEventListener(tlistener);}}

  protected void assertExecutionDetails(ActivitiEvent event, ProcessInstance processInstance) {
    assertEquals(processInstance.getId(), event.getProcessInstanceId());
    assertNotNull(event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(), event.getProcessDefinitionId());
  }
}
