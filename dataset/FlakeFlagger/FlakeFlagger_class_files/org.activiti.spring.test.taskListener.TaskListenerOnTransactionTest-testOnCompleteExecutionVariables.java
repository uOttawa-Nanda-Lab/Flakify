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
package org.activiti.spring.test.taskListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

/**

 */
@ContextConfiguration("classpath:org/activiti/spring/test/executionListener/TransactionDependentListenerTest-context.xml")
public class TaskListenerOnTransactionTest extends SpringActivitiTestCase {

  @Deployment
  public void testOnCompleteExecutionVariables() {

    CurrentTaskTransactionDependentTaskListener.clear();

    runtimeService.startProcessInstanceByKey("taskListenersOnCompleteExecutionVariables");

    // task 1 has committed listener
    Task task = taskService.createTaskQuery().singleResult();
    taskService.complete(task.getId());

    // task 2 has committed listener
    task = taskService.createTaskQuery().singleResult();
    taskService.complete(task.getId());

    List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks = CurrentTaskTransactionDependentTaskListener.getCurrentTasks();
    assertEquals(2, currentTasks.size());

    assertEquals("usertask1", currentTasks.get(0).getTaskId());
    assertEquals("User Task 1", currentTasks.get(0).getTaskName());
    assertEquals(1, currentTasks.get(1).getExecutionVariables().size());
    assertEquals("test1", currentTasks.get(0).getExecutionVariables().get("injectedExecutionVariable"));

    assertEquals("usertask2", currentTasks.get(1).getTaskId());
    assertEquals("User Task 2", currentTasks.get(1).getTaskName());
    assertEquals(1, currentTasks.get(1).getExecutionVariables().size());
    assertEquals("test2", currentTasks.get(1).getExecutionVariables().get("injectedExecutionVariable"));
  }

}
