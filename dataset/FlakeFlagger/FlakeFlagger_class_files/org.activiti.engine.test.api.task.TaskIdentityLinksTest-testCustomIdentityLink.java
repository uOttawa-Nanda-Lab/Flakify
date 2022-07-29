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

  @Deployment public void testCustomIdentityLink(){runtimeService.startProcessInstanceByKey("customIdentityLink");List<Task> tasks=taskService.createTaskQuery().taskInvolvedUser("kermit").list();assertEquals(1,tasks.size());List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(tasks.get(0).getId());assertEquals(2,identityLinks.size());for (IdentityLink idLink:identityLinks){assertEquals("businessAdministrator",idLink.getType());String userId=idLink.getUserId();if (userId == null){assertEquals("management",idLink.getGroupId());} else {assertEquals("kermit",userId);}}}
}
