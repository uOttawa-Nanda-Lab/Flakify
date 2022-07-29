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
 * Test starting and completed events for activity. Since these events are dispatched in the core of the PVM, not all individual activity-type is tested. Rather, we test the main types (tasks, gateways, events, subprocesses).
 */@Deployment public void testActivityEvents() throws Exception{listener.setIgnoreRawActivityEvents(false);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("activityProcess");assertNotNull(processInstance);assertEquals(3,listener.getEventsReceived().size());ActivitiActivityEvent activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("theStart",activityEvent.getActivityId());assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("theStart",activityEvent.getActivityId());assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("shipOrder",activityEvent.getActivityId());assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());listener.clearEventsReceived();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.complete(task.getId());Execution execution=runtimeService.createExecutionQuery().parentId(processInstance.getId()).singleResult();assertNotNull(execution);assertEquals(5,listener.getEventsReceived().size());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("shipOrder",activityEvent.getActivityId());assertTrue(!processInstance.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("subProcess",activityEvent.getActivityId());assertEquals(execution.getId(),activityEvent.getExecutionId());assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("subProcessStart",activityEvent.getActivityId());assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("subProcessStart",activityEvent.getActivityId());assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(4);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("subTask",activityEvent.getActivityId());assertTrue(!execution.getId().equals(activityEvent.getExecutionId()));assertEquals(processInstance.getProcessInstanceId(),activityEvent.getProcessInstanceId());assertEquals(processInstance.getProcessDefinitionId(),activityEvent.getProcessDefinitionId());listener.clearEventsReceived();Task subTask=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();assertNotNull(subTask);taskService.complete(subTask.getId());assertEquals(10,listener.getEventsReceived().size());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("subTask",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(1);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("gateway",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(2);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("gateway",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(3);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("throwMessageEvent",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(4);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("throwMessageEvent",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(5);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("endSubProcess",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(6);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("endSubProcess",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(7);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("subProcess",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(8);assertEquals(ActivitiEventType.ACTIVITY_STARTED,activityEvent.getType());assertEquals("theEnd",activityEvent.getActivityId());activityEvent=(ActivitiActivityEvent)listener.getEventsReceived().get(9);assertEquals(ActivitiEventType.ACTIVITY_COMPLETED,activityEvent.getType());assertEquals("theEnd",activityEvent.getActivityId());}

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
