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

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to executions.
 * 

 */
public class ExecutionEventsTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testExecutionEvents() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");assertNotNull(processInstance);assertEquals(5,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());listener.clearEventsReceived();runtimeService.suspendProcessInstanceById(processInstance.getId());runtimeService.activateProcessInstanceById(processInstance.getId());assertEquals(4,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());listener.clearEventsReceived();repositoryService.suspendProcessDefinitionById(processInstance.getProcessDefinitionId(),true,null);repositoryService.activateProcessDefinitionById(processInstance.getProcessDefinitionId(),true,null);assertEquals(4,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());assertEquals(ActivitiEventType.ENTITY_SUSPENDED,event.getType());event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ENTITY_ACTIVATED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());listener.clearEventsReceived();runtimeService.updateBusinessKey(processInstance.getId(),"thekey");assertEquals(1,listener.getEventsReceived().size());event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(processInstance.getId(),((Execution)event.getEntity()).getId());assertEquals(ActivitiEventType.ENTITY_UPDATED,event.getType());listener.clearEventsReceived();runtimeService.deleteProcessInstance(processInstance.getId(),"Testing events");event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());assertEquals(processInstance.getId(),((Execution)event.getEntity()).getProcessInstanceId());listener.clearEventsReceived();}
}
