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

  /**
 * Test events related to signalling
 */@Deployment public void testActivitySignalEvents() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalProcess");assertNotNull(processInstance);Execution executionWithSignal=runtimeService.createExecutionQuery().activityId("receivePayment").singleResult();assertNotNull(executionWithSignal);runtimeService.trigger(executionWithSignal.getId());assertEquals(1,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEvent);ActivitiSignalEvent signalEvent=(ActivitiSignalEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());assertEquals("receivePayment",signalEvent.getActivityId());assertEquals(executionWithSignal.getId(),signalEvent.getExecutionId());assertEquals(executionWithSignal.getProcessInstanceId(),signalEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());assertNull(signalEvent.getSignalName());assertNull(signalEvent.getSignalData());listener.clearEventsReceived();Execution executionWithSignalEvent=runtimeService.createExecutionQuery().activityId("shipOrder").singleResult();runtimeService.signalEventReceived("alert",executionWithSignalEvent.getId(),Collections.singletonMap("test",(Object)"test"));assertEquals(1,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEvent);signalEvent=(ActivitiSignalEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());assertEquals("shipOrder",signalEvent.getActivityId());assertEquals(executionWithSignalEvent.getId(),signalEvent.getExecutionId());assertEquals(executionWithSignalEvent.getProcessInstanceId(),signalEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());assertEquals("alert",signalEvent.getSignalName());assertNotNull(signalEvent.getSignalData());listener.clearEventsReceived();assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_SIGNALED);}

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
