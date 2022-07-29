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
package org.activiti.standalone.event;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.api.event.StaticTestActivitiEventListener;
import org.activiti.engine.test.api.event.TestActivitiEventListener;

/**
 * Test for event-listeners that are registered on a process-definition scope, rather than on the global engine-wide scope, declared in the BPMN XML.
 * 

 */
public class ProcessDefinitionScopedEventListenerDefinitionTest extends ResourceActivitiTestCase {

  protected TestActivitiEventListener testListenerBean;

  /**
 * Test to verify listeners defined in the BPMN xml are added to the process definition and are active.
 */@Deployment public void testProcessDefinitionListenerDefinition() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testEventListeners");assertNotNull(testListenerBean);Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();taskService.complete(task.getId());assertFalse(testListenerBean.getEventsReceived().isEmpty());for (ActivitiEvent event:testListenerBean.getEventsReceived()){assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());}assertTrue(testListenerBean.getEventsReceived().get(0) instanceof ActivitiEntityEvent);ActivitiEntityEvent event=(ActivitiEntityEvent)testListenerBean.getEventsReceived().get(0);assertTrue(event.getEntity() instanceof ProcessInstance);assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());List<ActivitiEvent> events=StaticTestActivitiEventListener.getEventsReceived();assertFalse(events.isEmpty());boolean insertFound=false;boolean deleteFound=false;for (ActivitiEvent e:events){if (ActivitiEventType.ENTITY_CREATED == e.getType()){insertFound=true;} else if (ActivitiEventType.ENTITY_DELETED == e.getType()){deleteFound=true;}}assertTrue(insertFound);assertTrue(deleteFound);}
}
