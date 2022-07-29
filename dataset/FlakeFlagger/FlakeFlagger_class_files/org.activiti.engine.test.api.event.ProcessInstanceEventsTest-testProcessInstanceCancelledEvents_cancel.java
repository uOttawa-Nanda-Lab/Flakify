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

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceCancelledEvents_cancel() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");assertNotNull(processInstance);listener.clearEventsReceived();runtimeService.deleteProcessInstance(processInstance.getId(),"delete_test");List<ActivitiEvent> processCancelledEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);assertEquals("ActivitiEventType.PROCESS_CANCELLED was expected 1 time.",1,processCancelledEvents.size());ActivitiCancelledEvent processCancelledEvent=(ActivitiCancelledEvent)processCancelledEvents.get(0);assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiCancelledEvent.class.isAssignableFrom(processCancelledEvent.getClass()));assertEquals("The process instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getProcessInstanceId());assertEquals("The execution instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getExecutionId());assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",processCancelledEvent.getCause());List<ActivitiEvent> taskCancelledEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);assertEquals("ActivitiEventType.ACTIVITY_CANCELLED was expected 1 time.",1,taskCancelledEvents.size());ActivitiActivityCancelledEvent activityCancelledEvent=(ActivitiActivityCancelledEvent)taskCancelledEvents.get(0);assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiActivityCancelledEvent.class.isAssignableFrom(activityCancelledEvent.getClass()));assertEquals("The process instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),activityCancelledEvent.getProcessInstanceId());assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",activityCancelledEvent.getCause());listener.clearEventsReceived();}

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
