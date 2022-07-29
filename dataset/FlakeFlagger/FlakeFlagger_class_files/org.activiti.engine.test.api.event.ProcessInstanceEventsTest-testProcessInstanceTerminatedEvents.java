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

  @Deployment(resources="org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testProcessTerminate.bpmn") public void testProcessInstanceTerminatedEvents() throws Exception{ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");long executionEntities=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).count();assertEquals(3,executionEntities);Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateTask").singleResult();taskService.complete(task.getId());List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);assertEquals("There should be exactly one ActivitiEventType.PROCESS_CANCELLED event after the task complete.",1,processTerminatedEvents.size());ActivitiProcessCancelledEventImpl activitiEvent=(ActivitiProcessCancelledEventImpl)processTerminatedEvents.get(0);assertThat(activitiEvent.getProcessInstanceId(),is(pi.getProcessInstanceId()));List<ActivitiEvent> activityTerminatedEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);assertThat("There should be exactly two ActivitiEventType.ACTIVITY_CANCELLED event after the task complete.",activityTerminatedEvents.size(),is(2));for (ActivitiEvent event:activityTerminatedEvents){ActivitiActivityCancelledEventImpl activityEvent=(ActivitiActivityCancelledEventImpl)event;if (activityEvent.getActivityId().equals("preNormalTerminateTask")){assertThat("The user task must be terminated",activityEvent.getActivityId(),is("preNormalTerminateTask"));assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_2"));} else if (activityEvent.getActivityId().equals("EndEvent_2")){assertThat("The end event must be terminated",activityEvent.getActivityId(),is("EndEvent_2"));assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_2"));}}}

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
