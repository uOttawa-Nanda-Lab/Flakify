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
import java.util.Collections;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiActivityCancelledEvent;
import org.activiti.engine.delegate.event.ActivitiActivityEvent;
import org.activiti.engine.delegate.event.ActivitiErrorEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiMessageEvent;
import org.activiti.engine.delegate.event.ActivitiSignalEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.activiti.engine.delegate.event.impl.ActivitiSignalEventImpl;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.impl.event.logger.EventLogger;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.persistence.entity.MessageEventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.SignalEventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to activities.
 * 


 */
public class ActivityEventsTest extends PluggableActivitiTestCase {

  private TestActivitiActivityEventListener listener;

  protected EventLogger databaseEventLogger;

  @Deployment(resources="org/activiti/engine/test/bpmn/event/timer/BoundaryTimerEventTest.testTimerOnNestingOfSubprocesses.bpmn20.xml") public void testActivityTimeOutEventInSubProcess(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnNestedSubprocesses");Job theJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(theJob);Calendar timeToFire=Calendar.getInstance();timeToFire.add(Calendar.HOUR,2);timeToFire.add(Calendar.SECOND,5);processEngineConfiguration.getClock().setCurrentTime(timeToFire.getTime());waitForJobExecutorToProcessAllJobs(2000,200);assertEquals(4,listener.getEventsReceived().size());List<String> eventIdList=new ArrayList<String>();for (ActivitiEvent event:listener.getEventsReceived()){assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,event.getType());assertTrue("TIMER is the cause of the cancellation",((ActivitiActivityCancelledEvent)event).getCause() instanceof JobEntity);eventIdList.add(((ActivitiActivityEventImpl)event).getActivityId());}assertTrue(eventIdList.indexOf("innerTask1") >= 0);assertTrue(eventIdList.indexOf("innerTask2") >= 0);assertTrue(eventIdList.indexOf("subprocess") >= 0);assertTrue(eventIdList.indexOf("innerSubprocess") >= 0);}

  protected void assertDatabaseEventPresent(ActivitiEventType eventType) {
    String eventTypeString = eventType.name();
    List<EventLogEntry> eventLogEntries = managementService.getEventLogEntries(0L, 100000L);
    boolean found = false;
    for (EventLogEntry entry : eventLogEntries) {
      if (entry.getType().equals(eventTypeString)) {
        found = true;
      }
    }
    assertTrue(found);
  }

}
