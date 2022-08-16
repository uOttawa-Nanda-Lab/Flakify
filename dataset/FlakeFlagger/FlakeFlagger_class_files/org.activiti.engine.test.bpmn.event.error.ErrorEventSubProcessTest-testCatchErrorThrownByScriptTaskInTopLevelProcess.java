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
package org.activiti.engine.test.bpmn.event.error;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class ErrorEventSubProcessTest extends PluggableActivitiTestCase {

  @Deployment public void testCatchErrorThrownByScriptTaskInTopLevelProcess(){String procId=runtimeService.startProcessInstanceByKey("CatchErrorThrownByScriptTaskInTopLevelProcess").getId();assertThatErrorHasBeenCaught(procId);}

  private void assertThatErrorHasBeenCaught(String procId) {
    // The process will throw an error event,
    // which is caught and escalated by a User Task
    assertEquals("No tasks found in task list.", 1, taskService.createTaskQuery().count());
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("Escalated Task", task.getName());

    // Completing the Task will end the process instance
    taskService.complete(task.getId());
    assertProcessEnded(procId);
  }

}
