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
 * Test events related to message events, called from the API.
 */@Deployment public void testActivityMessageEvents() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("messageProcess");assertNotNull(processInstance);Execution executionWithMessage=runtimeService.createExecutionQuery().activityId("shipOrder").singleResult();assertNotNull(executionWithMessage);runtimeService.messageEventReceived("messageName",executionWithMessage.getId());assertEquals(2,listener.getEventsReceived().size());assertTrue(listener.getEventsReceived().get(0) instanceof ActivitiMessageEvent);ActivitiMessageEvent messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_WAITING,messageEvent.getType());assertEquals("shipOrder",messageEvent.getActivityId());assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());assertEquals("messageName",messageEvent.getMessageName());assertNull(messageEvent.getMessageData());assertTrue(listener.getEventsReceived().get(1) instanceof ActivitiMessageEvent);messageEvent=(ActivitiMessageEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED,messageEvent.getType());assertEquals("shipOrder",messageEvent.getActivityId());assertEquals(executionWithMessage.getId(),messageEvent.getExecutionId());assertEquals(executionWithMessage.getProcessInstanceId(),messageEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),messageEvent.getProcessDefinitionId());assertEquals("messageName",messageEvent.getMessageName());assertNull(messageEvent.getMessageData());assertDatabaseEventPresent(ActivitiEventType.ACTIVITY_MESSAGE_RECEIVED);}

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
