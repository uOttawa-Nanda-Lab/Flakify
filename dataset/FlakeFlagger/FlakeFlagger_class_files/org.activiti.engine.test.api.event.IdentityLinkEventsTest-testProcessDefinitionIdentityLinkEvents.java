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

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to process definitions.
 * 

 */
public class IdentityLinkEventsTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessDefinitionIdentityLinkEvents() throws Exception{ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").singleResult();assertNotNull(processDefinition);repositoryService.addCandidateStarterUser(processDefinition.getId(),"kermit");repositoryService.addCandidateStarterGroup(processDefinition.getId(),"sales");assertEquals(4,listener.getEventsReceived().size());ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertTrue(event.getEntity() instanceof IdentityLink);assertEquals(processDefinition.getId(),event.getProcessDefinitionId());assertNull(event.getProcessInstanceId());assertNull(event.getExecutionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertTrue(event.getEntity() instanceof IdentityLink);assertEquals(processDefinition.getId(),event.getProcessDefinitionId());assertNull(event.getProcessInstanceId());assertNull(event.getExecutionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());listener.clearEventsReceived();repositoryService.deleteCandidateStarterUser(processDefinition.getId(),"kermit");repositoryService.deleteCandidateStarterGroup(processDefinition.getId(),"sales");assertEquals(2,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());assertTrue(event.getEntity() instanceof IdentityLink);assertEquals(processDefinition.getId(),event.getProcessDefinitionId());assertNull(event.getProcessInstanceId());assertNull(event.getExecutionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());assertTrue(event.getEntity() instanceof IdentityLink);assertEquals(processDefinition.getId(),event.getProcessDefinitionId());assertNull(event.getProcessInstanceId());assertNull(event.getExecutionId());}
}
