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

  @Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInParentProcess.bpmn","org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceTerminatedEvents_terminateInParentProcess() throws Exception{ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateParentProcess");Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();taskService.complete(task.getId());assertProcessEnded(pi.getId());List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);assertEquals("There should be exactly one ActivitiEventType.PROCESS_TERMINATED event after the task complete.",1,processTerminatedEvents.size());ActivitiProcessCancelledEventImpl processCancelledEvent=(ActivitiProcessCancelledEventImpl)processTerminatedEvents.get(0);assertThat(processCancelledEvent.getProcessInstanceId(),is(pi.getProcessInstanceId()));assertThat(processCancelledEvent.getProcessDefinitionId(),containsString("terminateParentProcess"));List<ActivitiEvent> activityTerminatedEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);assertThat("3 activities must be cancelled.",activityTerminatedEvents.size(),is(3));for (ActivitiEvent event:activityTerminatedEvents){ActivitiActivityCancelledEventImpl activityEvent=(ActivitiActivityCancelledEventImpl)event;if (activityEvent.getActivityId().equals("theTask")){assertThat("The user task must be terminated in the called sub process.",activityEvent.getActivityId(),is("theTask"));assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));} else if (activityEvent.getActivityId().equals("CallActivity_1")){assertThat("The call activity must be terminated",activityEvent.getActivityId(),is("CallActivity_1"));assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));} else if (activityEvent.getActivityId().equals("EndEvent_3")){assertThat("The end event must be terminated",activityEvent.getActivityId(),is("EndEvent_3"));assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));}}}

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
