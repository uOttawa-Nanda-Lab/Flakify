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

package org.activiti.engine.test.api.task;

import java.util.List;

import junit.framework.AssertionFailedError;

import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class TaskIdentityLinksTest extends PluggableActivitiTestCase {

  private Event findTaskEvent(List<Event> taskEvents, String action) {
    for (Event event : taskEvents) {
      if (action.equals(event.getAction())) {
        return event;
      }
    }
    throw new AssertionFailedError("no task event found with action " + action);
  }

  public void testDeleteOwner() {
	Task task = taskService.newTask();
	task.setOwner("nonExistingUser");
	taskService.saveTask(task);
	taskService.deleteUserIdentityLink(task.getId(), "nonExistingUser", IdentityLinkType.OWNER);
	task = taskService.createTaskQuery().taskId(task.getId()).singleResult();
	assertNull(task.getOwner());
	assertEquals(0, taskService.getIdentityLinksForTask(task.getId()).size());
	taskService.deleteTask(task.getId(), true);
}
}
