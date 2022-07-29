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

  @Deployment public void testActivitySignalBoundaryEventsOnSubProcess() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalOnSubProcess");assertNotNull(processInstance);Execution executionWithSignal=runtimeService.createExecutionQuery().activityId("userTaskInsideProcess").singleResult();assertNotNull(executionWithSignal);runtimeService.signalEventReceived("signalName");assertEquals(3,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiSignalEventImpl);ActivitiSignalEventImpl signalEvent=(ActivitiSignalEventImpl)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_SIGNALED,signalEvent.getType());assertEquals("boundarySignalEventCatching",signalEvent.getActivityId());assertEquals(executionWithSignal.getProcessInstanceId(),signalEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),signalEvent.getProcessDefinitionId());assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiActivityCancelledEvent);ActivitiActivityCancelledEvent cancelEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,cancelEvent.getType());assertEquals("subProcess",cancelEvent.getActivityId());assertEquals(executionWithSignal.getProcessInstanceId(),cancelEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),cancelEvent.getProcessDefinitionId());assertNotNull(cancelEvent.getCause());assertTrue(cancelEvent.getCause() instanceof SignalEventSubscriptionEntity);SignalEventSubscriptionEntity cause=(SignalEventSubscriptionEntity)cancelEvent.getCause();assertEquals("signalName",cause.getEventName());assertTrue(listener.getEventsReceived().get(2) instanceof ActivitiActivityCancelledEvent);cancelEvent=(ActivitiActivityCancelledEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ACTIVITY_CANCELLED,cancelEvent.getType());assertEquals("userTaskInsideProcess",cancelEvent.getActivityId());assertEquals(executionWithSignal.getId(),cancelEvent.getExecutionId());assertEquals(executionWithSignal.getProcessInstanceId(),cancelEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),cancelEvent.getProcessDefinitionId());assertNotNull(cancelEvent.getCause());assertTrue(cancelEvent.getCause() instanceof SignalEventSubscriptionEntity);cause=(SignalEventSubscriptionEntity)cancelEvent.getCause();assertEquals("signalName",cause.getEventName());}

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
