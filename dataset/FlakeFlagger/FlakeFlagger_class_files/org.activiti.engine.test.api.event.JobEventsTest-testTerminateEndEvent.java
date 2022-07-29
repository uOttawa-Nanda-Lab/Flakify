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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiActivityEvent;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.DefaultClockImpl;
import org.activiti.engine.runtime.Clock;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to jobs.
 * 

 */
public class JobEventsTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  private void checkEventCount(int expectedCount, ActivitiEventType eventType) {// count
                                                                                // timer
                                                                                // cancelled
                                                                                // events
    int timerCancelledCount = 0;
    List<ActivitiEvent> eventsReceived = listener.getEventsReceived();
    for (ActivitiEvent eventReceived : eventsReceived) {
      if (eventType.equals(eventReceived.getType())) {
        timerCancelledCount++;
      }
    }
    assertEquals(eventType.name() + " event was expected " + expectedCount + " times.", expectedCount, timerCancelledCount);
  }

  private List<ActivitiEvent> filterEvents(ActivitiEventType eventType) {
    List<ActivitiEvent> eventsReceived = listener.getEventsReceived();
    List<ActivitiEvent> filteredEvents = new ArrayList<>();
    for (ActivitiEvent eventReceived : eventsReceived) {
      if (eventType.equals(eventReceived.getType())) {
        filteredEvents.add(eventReceived);
      }
    }
    return filteredEvents;
  }
  @Deployment public void testTerminateEndEvent() throws Exception{Clock previousClock=processEngineConfiguration.getClock();TestActivitiEventListener activitiEventListener=new TestActivitiEventListener();processEngineConfiguration.getEventDispatcher().addEventListener(activitiEventListener);Clock testClock=new DefaultClockImpl();processEngineConfiguration.setClock(testClock);testClock.setCurrentTime(new Date());ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testTerminateEndEvent");listener.clearEventsReceived();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("Inside Task",task.getName());Calendar later=Calendar.getInstance();later.add(Calendar.YEAR,1);processEngineConfiguration.getClock().setCurrentTime(later.getTime());waitForJobExecutorToProcessAllJobs(2000,100);List<ActivitiEvent> eventsReceived=activitiEventListener.getEventsReceived();for (ActivitiEvent eventReceived:eventsReceived){if (ActivitiEventType.PROCESS_CANCELLED.equals(eventReceived.getType())){fail("Should not have received PROCESS_CANCELLED event");}}for (ActivitiEvent eventReceived:eventsReceived){if (ActivitiEventType.ACTIVITY_CANCELLED.equals(eventReceived.getType())){ActivitiActivityEvent event=(ActivitiActivityEvent)eventReceived;String activityType=event.getActivityType();if (!"userTask".equals(activityType) && (!"subProcess".equals(activityType)) && (!"endEvent".equals(activityType))){fail("Unexpected activity type: " + activityType);}}}task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("Outside Task",task.getName());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());processEngineConfiguration.setClock(previousClock);}

  protected void checkEventContext(ActivitiEvent event, Job entity) {
    assertEquals(entity.getProcessInstanceId(), event.getProcessInstanceId());
    assertEquals(entity.getProcessDefinitionId(), event.getProcessDefinitionId());
    assertNotNull(event.getExecutionId());

    assertTrue(event instanceof ActivitiEntityEvent);
    ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
    assertTrue(entityEvent.getEntity() instanceof Job);
    assertEquals(entity.getId(), ((Job) entityEvent.getEntity()).getId());
  }
}
