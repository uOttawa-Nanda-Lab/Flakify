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
package org.activiti.examples.bpmn.tasklistener;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import java.util.List;

/**

 */
public class TaskListenerTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.testTaskListenersOnDelete.bpmn20.xml"}) public void testTaskListenersOnDeleteByComplete(){TaskDeleteListener.clear();runtimeService.startProcessInstanceByKey("executionListenersOnDelete");List<Task> tasks=taskService.createTaskQuery().list();assertNotNull(tasks);assertEquals(1,tasks.size());Task task=taskService.createTaskQuery().taskName("User Task 1").singleResult();assertNotNull(task);assertEquals(0,TaskDeleteListener.getCurrentMessages().size());assertEquals(0,TaskSimpleCompleteListener.getCurrentMessages().size());taskService.complete(task.getId());tasks=taskService.createTaskQuery().list();assertNotNull(tasks);assertEquals(0,tasks.size());assertEquals(1,TaskDeleteListener.getCurrentMessages().size());assertEquals("Delete Task Listener executed.",TaskDeleteListener.getCurrentMessages().get(0));assertEquals(1,TaskSimpleCompleteListener.getCurrentMessages().size());assertEquals("Complete Task Listener executed.",TaskSimpleCompleteListener.getCurrentMessages().get(0));}
}
