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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.impl.util.JvmUtil;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class BoundaryErrorEventTest extends PluggableActivitiTestCase {

  private void assertErrorCaughtInConcurrentEmbeddedSubprocesses(String processDefinitionKey) {
    // Completing task A will lead to task D
    String procId = runtimeService.startProcessInstanceByKey(processDefinitionKey).getId();
    List<Task> tasks = taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals(2, tasks.size());
    assertEquals("task A", tasks.get(0).getName());
    assertEquals("task B", tasks.get(1).getName());
    taskService.complete(tasks.get(0).getId());
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("task D", task.getName());
    taskService.complete(task.getId());
    assertProcessEnded(procId);

    // Completing task B will lead to task C
    runtimeService.startProcessInstanceByKey(processDefinitionKey).getId();
    tasks = taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals(2, tasks.size());
    assertEquals("task A", tasks.get(0).getName());
    assertEquals("task B", tasks.get(1).getName());
    taskService.complete(tasks.get(1).getId());

    tasks = taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals(2, tasks.size());
    assertEquals("task A", tasks.get(0).getName());
    assertEquals("task C", tasks.get(1).getName());
    taskService.complete(tasks.get(1).getId());
    task = taskService.createTaskQuery().singleResult();
    assertEquals("task A", task.getName());

    taskService.complete(task.getId());
    task = taskService.createTaskQuery().singleResult();
    assertEquals("task D", task.getName());
  }

  @Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorOnGroovyScriptTask.bpmn20.xml"}) public void testCatchErrorOnGroovyScriptTask(){String procId=runtimeService.startProcessInstanceByKey("catchErrorOnScriptTask").getId();assertProcessEnded(procId);}

  private void assertThatErrorHasBeenCaught(String procId) {
    // The service task will throw an error event,
    // which is caught on the service task boundary
    assertEquals("No tasks found in task list.", 1, taskService.createTaskQuery().count());
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("Escalated Task", task.getName());

    // Completing the task will end the process instance
    taskService.complete(task.getId());
    assertProcessEnded(procId);
  }

}
