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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TaskListenerOnTransactionTest extends PluggableActivitiTestCase {

  @Deployment public void testOnCompleteCommitted(){CurrentTaskTransactionDependentTaskListener.clear();Map<String, Object> variables=new HashMap<>();variables.put("serviceTask1",false);variables.put("serviceTask2",false);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenersOnCompleteCommitted",variables);Task task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks=CurrentTaskTransactionDependentTaskListener.getCurrentTasks();assertEquals(1,currentTasks.size());assertEquals("usertask1",currentTasks.get(0).getTaskId());assertEquals("User Task 1",currentTasks.get(0).getTaskName());assertEquals(processInstance.getId(),currentTasks.get(0).getProcessInstanceId());assertNotNull(currentTasks.get(0).getProcessInstanceId());}

}
