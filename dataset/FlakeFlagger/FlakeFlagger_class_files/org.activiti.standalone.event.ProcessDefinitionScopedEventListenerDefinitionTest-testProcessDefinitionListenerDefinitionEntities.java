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
 * Test to verify listeners defined in the BPMN xml are added to the process definition and are active, for all entity types
 */@Deployment public void testProcessDefinitionListenerDefinitionEntities() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testEventListeners");assertNotNull(processInstance);Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);TestActivitiEventListener theListener=(TestActivitiEventListener)processEngineConfiguration.getBeans().get("testAttachmentEventListener");assertNotNull(theListener);assertEquals(0,theListener.getEventsReceived().size());taskService.createAttachment("test",task.getId(),processInstance.getId(),"test","test","url");assertEquals(2,theListener.getEventsReceived().size());assertEquals(ActivitiEventType.ENTITY_CREATED,theListener.getEventsReceived().get(0).getType());assertEquals(ActivitiEventType.ENTITY_INITIALIZED,theListener.getEventsReceived().get(1).getType());}
}
