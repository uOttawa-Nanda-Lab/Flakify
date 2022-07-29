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

import java.io.ByteArrayInputStream;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to attachments.
 * 

 */
public class AttachmentEventsTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  public void testAttachmentEntityEventsOnHistoricTaskDelete() throws Exception {
	if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
		Task task = null;
		try {
			task = taskService.newTask();
			taskService.saveTask(task);
			assertNotNull(task);
			Attachment attachment = taskService.createAttachment("test", task.getId(), null, "attachment name",
					"description", "http://activiti.org");
			listener.clearEventsReceived();
			taskService.deleteTask(task.getId());
			historyService.deleteHistoricTaskInstance(task.getId());
			assertEquals(1, listener.getEventsReceived().size());
			ActivitiEntityEvent event = (ActivitiEntityEvent) listener.getEventsReceived().get(0);
			assertEquals(ActivitiEventType.ENTITY_DELETED, event.getType());
			assertNull(event.getProcessInstanceId());
			assertNull(event.getExecutionId());
			assertNull(event.getProcessDefinitionId());
			Attachment attachmentFromEvent = (Attachment) event.getEntity();
			assertEquals(attachment.getId(), attachmentFromEvent.getId());
		} finally {
			if (task != null && task.getId() != null) {
				taskService.deleteTask(task.getId());
				historyService.deleteHistoricTaskInstance(task.getId());
			}
		}
	}
}
}
