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

  /**
 * Timer repetition
 */@Deployment public void testRepetitionJobEntityEvents() throws Exception{Clock previousClock=processEngineConfiguration.getClock();Clock testClock=new DefaultClockImpl();processEngineConfiguration.setClock(testClock);Date now=new Date();testClock.setCurrentTime(now);Calendar nowCalendar=new GregorianCalendar();ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testRepetitionJobEvents");Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(theJob);assertEquals(3,listener.getEventsReceived().size());ActivitiEvent event=listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());checkEventContext(event,theJob);event=listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());checkEventContext(event,theJob);event=listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.TIMER_SCHEDULED,event.getType());checkEventContext(event,theJob);listener.clearEventsReceived();waitForJobExecutorToProcessAllJobs(2000,200);assertEquals(0,listener.getEventsReceived().size());assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());Job firstTimerInstance=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();nowCalendar.add(Calendar.HOUR,1);nowCalendar.add(Calendar.MINUTE,5);testClock.setCurrentTime(nowCalendar.getTime());waitForJobExecutorToProcessAllJobs(2000,200);assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());Job secondTimerInstance=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();assertTrue(firstTimerInstance.getId() != secondTimerInstance.getId());checkEventCount(1,ActivitiEventType.TIMER_FIRED);checkEventContext(filterEvents(ActivitiEventType.TIMER_FIRED).get(0),firstTimerInstance);checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);checkEventContext(filterEvents(ActivitiEventType.TIMER_SCHEDULED).get(0),secondTimerInstance);listener.clearEventsReceived();nowCalendar.add(Calendar.HOUR,1);nowCalendar.add(Calendar.MINUTE,5);testClock.setCurrentTime(nowCalendar.getTime());waitForJobExecutorToProcessAllJobs(2000,200);assertEquals(0,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());nowCalendar.add(Calendar.HOUR,1);nowCalendar.add(Calendar.MINUTE,5);testClock.setCurrentTime(nowCalendar.getTime());waitForJobExecutorToProcessAllJobs(2000,200);assertEquals(0,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());checkEventCount(1,ActivitiEventType.TIMER_FIRED);checkEventContext(filterEvents(ActivitiEventType.TIMER_FIRED).get(0),secondTimerInstance);checkEventCount(0,ActivitiEventType.TIMER_SCHEDULED);listener.clearEventsReceived();processEngineConfiguration.setClock(previousClock);}

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
