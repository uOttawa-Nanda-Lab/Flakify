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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.delegate.event.ActivitiActivityCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiProcessStartedEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityCancelledEventImpl;
import org.activiti.engine.delegate.event.impl.ActivitiProcessCancelledEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to process instances.
 * 

 */
public class ProcessInstanceEventsTest extends PluggableActivitiTestCase {

  private TestInitializedEntityEventListener listener;

  @Deployment(resources={"org/activiti/engine/test/api/runtime/nestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testSubProcessInstanceEvents() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nestedSimpleSubProcess");assertNotNull(processInstance);String processDefinitionId=processInstance.getProcessDefinitionId();assertEquals(9,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiEntityEvent);ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertEquals(processInstance.getId(),((ProcessInstance)event.getEntity()).getId());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertEquals(processInstance.getId(),event.getExecutionId());assertEquals(processDefinitionId,event.getProcessDefinitionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);String processExecutionId=event.getExecutionId();assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertEquals(processInstance.getId(),processExecutionId);assertEquals(processDefinitionId,event.getProcessDefinitionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);processExecutionId=event.getExecutionId();assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertNotEquals(processInstance.getId(),processExecutionId);assertEquals(processDefinitionId,event.getProcessDefinitionId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertNotEquals(processInstance.getId(),((ExecutionEntity)event.getEntity()).getId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(4);assertEquals(ActivitiEventType.PROCESS_STARTED,event.getType());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());assertTrue(event instanceof ActivitiProcessStartedEvent);assertNull(((ActivitiProcessStartedEvent)event).getNestedProcessDefinitionId());assertNull(((ActivitiProcessStartedEvent)event).getNestedProcessInstanceId());event=(ActivitiEntityEvent)listener.getEventsReceived().get(5);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());ExecutionEntity subProcessEntity=(ExecutionEntity)event.getEntity();assertEquals(processExecutionId,subProcessEntity.getSuperExecutionId());String subProcessInstanceId=subProcessEntity.getProcessInstanceId();event=(ActivitiEntityEvent)listener.getEventsReceived().get(6);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());assertEquals(subProcessInstanceId,event.getProcessInstanceId());assertNotEquals(subProcessInstanceId,event.getExecutionId());String subProcessDefinitionId=((ExecutionEntity)event.getEntity()).getProcessDefinitionId();assertNotNull(subProcessDefinitionId);ProcessDefinition subProcessDefinition=repositoryService.getProcessDefinition(subProcessDefinitionId);assertEquals("simpleSubProcess",subProcessDefinition.getKey());event=(ActivitiEntityEvent)listener.getEventsReceived().get(7);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());assertEquals(subProcessInstanceId,event.getProcessInstanceId());assertNotEquals(subProcessInstanceId,event.getExecutionId());subProcessDefinitionId=((ExecutionEntity)event.getEntity()).getProcessDefinitionId();assertNotNull(subProcessDefinitionId);event=(ActivitiEntityEvent)listener.getEventsReceived().get(8);assertEquals(ActivitiEventType.PROCESS_STARTED,event.getType());assertEquals(subProcessInstanceId,event.getProcessInstanceId());assertEquals(subProcessDefinitionId,event.getProcessDefinitionId());assertTrue(event instanceof ActivitiProcessStartedEvent);assertEquals(processDefinitionId,((ActivitiProcessStartedEvent)event).getNestedProcessDefinitionId());assertEquals(processInstance.getId(),((ActivitiProcessStartedEvent)event).getNestedProcessInstanceId());listener.clearEventsReceived();}

  private class TestInitializedEntityEventListener implements ActivitiEventListener {

    private List<ActivitiEvent> eventsReceived;

    public TestInitializedEntityEventListener() {

      eventsReceived = new ArrayList<ActivitiEvent>();
    }

    public List<ActivitiEvent> getEventsReceived() {
      return eventsReceived;
    }

    public void clearEventsReceived() {
      eventsReceived.clear();
    }

    @Override
    public void onEvent(ActivitiEvent event) {
      if (event instanceof ActivitiEntityEvent && ProcessInstance.class.isAssignableFrom(((ActivitiEntityEvent) event).getEntity().getClass())) {
        // check whether entity in the event is initialized before
        // adding to the list.
        assertNotNull(((ExecutionEntity) ((ActivitiEntityEvent) event).getEntity()).getId());
        eventsReceived.add(event);
      } else if (ActivitiEventType.PROCESS_CANCELLED.equals(event.getType()) || ActivitiEventType.ACTIVITY_CANCELLED.equals(event.getType())) {
        eventsReceived.add(event);
      }
    }

    @Override
    public boolean isFailOnException() {
      return true;
    }

    public List<ActivitiEvent> filterEvents(ActivitiEventType eventType) {// count
                                                                          // timer
                                                                          // cancelled
                                                                          // events
      List<ActivitiEvent> filteredEvents = new ArrayList<ActivitiEvent>();
      List<ActivitiEvent> eventsReceived = listener.getEventsReceived();
      for (ActivitiEvent eventReceived : eventsReceived) {
        if (eventType.equals(eventReceived.getType())) {
          filteredEvents.add(eventReceived);
        }
      }
      return filteredEvents;
    }

  }
}
