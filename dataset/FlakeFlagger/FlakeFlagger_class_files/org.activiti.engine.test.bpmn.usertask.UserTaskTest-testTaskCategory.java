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

package org.activiti.engine.test.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class UserTaskTest extends PluggableActivitiTestCase {

  @Deployment public void testTaskCategory(){runtimeService.startProcessInstanceByKey("testTaskCategory");Task task=taskService.createTaskQuery().singleResult();String testCategory="My Category";assertEquals(testCategory,task.getCategory());assertEquals("Task with category",taskService.createTaskQuery().taskCategory(testCategory).singleResult().getName());assertTrue(taskService.createTaskQuery().taskCategory("Does not exist").count() == 0);if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();assertEquals(testCategory,historicTaskInstance.getCategory());assertEquals("Task with category",historyService.createHistoricTaskInstanceQuery().taskCategory(testCategory).singleResult().getName());assertTrue(historyService.createHistoricTaskInstanceQuery().taskCategory("Does not exist").count() == 0);String newCategory="New Test Category";task.setCategory(newCategory);taskService.saveTask(task);task=taskService.createTaskQuery().singleResult();assertEquals(newCategory,task.getCategory());assertEquals("Task with category",taskService.createTaskQuery().taskCategory(newCategory).singleResult().getName());assertTrue(taskService.createTaskQuery().taskCategory(testCategory).count() == 0);taskService.complete(task.getId());historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();assertEquals(newCategory,historicTaskInstance.getCategory());assertEquals("Task with category",historyService.createHistoricTaskInstanceQuery().taskCategory(newCategory).singleResult().getName());assertTrue(historyService.createHistoricTaskInstanceQuery().taskCategory(testCategory).count() == 0);}}

}
