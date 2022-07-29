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
  public void testOnCompleteTransactionalOperation() {
    CurrentTaskTransactionDependentTaskListener.clear();

    ProcessInstance firstProcessInstance = runtimeService.startProcessInstanceByKey("transactionDependentTaskListenerProcess");
    assertProcessEnded(firstProcessInstance.getId());

    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
      List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
      assertEquals(1, historicProcessInstances.size());
      assertEquals("transactionDependentTaskListenerProcess", historicProcessInstances.get(0).getProcessDefinitionKey());
    }

    ProcessInstance secondProcessInstance = runtimeService.startProcessInstanceByKey("secondTransactionDependentTaskListenerProcess");

    Task task = taskService.createTaskQuery().singleResult();
    taskService.complete(task.getId());

    assertProcessEnded(secondProcessInstance.getId());

    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
      // first historic process instance was deleted by task listener
      List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
      assertEquals(1, historicProcessInstances.size());
      assertEquals("secondTransactionDependentTaskListenerProcess", historicProcessInstances.get(0).getProcessDefinitionKey());
    }

    List<MyTransactionalOperationTransactionDependentTaskListener.CurrentTask> currentTasks = MyTransactionalOperationTransactionDependentTaskListener.getCurrentTasks();
    assertEquals(1, currentTasks.size());

    assertEquals("usertask1", currentTasks.get(0).getTaskId());
    assertEquals("User Task 1", currentTasks.get(0).getTaskName());
  }

}
