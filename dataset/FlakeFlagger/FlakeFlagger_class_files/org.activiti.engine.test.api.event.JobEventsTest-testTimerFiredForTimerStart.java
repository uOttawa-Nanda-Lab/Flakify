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
  /**
 * /** Test TIMER_FIRED event for timer start bpmn event.
 */@Deployment public void testTimerFiredForTimerStart() throws Exception{Calendar tomorrow=Calendar.getInstance();tomorrow.add(Calendar.DAY_OF_YEAR,1);processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());waitForJobExecutorToProcessAllJobs(2000,100);assertEquals(6,listener.getEventsReceived().size());assertEquals(ActivitiEventType.ENTITY_CREATED,listener.getEventsReceived().get(0).getType());assertEquals(ActivitiEventType.ENTITY_INITIALIZED,listener.getEventsReceived().get(1).getType());assertEquals(ActivitiEventType.ENTITY_DELETED,listener.getEventsReceived().get(2).getType());assertEquals(ActivitiEventType.TIMER_FIRED,listener.getEventsReceived().get(3).getType());assertEquals(ActivitiEventType.JOB_EXECUTION_SUCCESS,listener.getEventsReceived().get(5).getType());checkEventCount(0,ActivitiEventType.JOB_CANCELED);}

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
