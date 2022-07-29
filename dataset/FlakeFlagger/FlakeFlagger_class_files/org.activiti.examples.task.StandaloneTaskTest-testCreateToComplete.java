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
package org.activiti.examples.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiOptimisticLockingException;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;

/**

 */
public class StandaloneTaskTest extends PluggableActivitiTestCase {


  public void testCreateToComplete(){Task task=taskService.newTask();task.setName("testTask");taskService.saveTask(task);String taskId=task.getId();taskService.addCandidateUser(taskId,"kermit");taskService.addCandidateUser(taskId,"gonzo");List<Task> tasks=taskService.createTaskQuery().taskCandidateUser("kermit").list();assertEquals(1,tasks.size());assertEquals("testTask",tasks.get(0).getName());tasks=taskService.createTaskQuery().taskCandidateUser("gonzo").list();assertEquals(1,tasks.size());assertEquals("testTask",tasks.get(0).getName());task.setName("Update name");taskService.saveTask(task);tasks=taskService.createTaskQuery().taskCandidateUser("kermit").list();assertEquals(1,tasks.size());assertEquals("Update name",tasks.get(0).getName());taskService.claim(taskId,"kermit");assertTrue(taskService.createTaskQuery().taskCandidateUser("kermit").list().isEmpty());assertTrue(taskService.createTaskQuery().taskCandidateUser("gonzo").list().isEmpty());taskService.deleteTask(taskId,true);assertNull(taskService.createTaskQuery().taskId(taskId).singleResult());}

}
