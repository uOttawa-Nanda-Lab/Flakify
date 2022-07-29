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
package org.activiti.standalone.escapeclause;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HistoricTaskQueryEscapeClauseTest extends AbstractEscapeClauseTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  private ProcessInstance processInstance1;

  private ProcessInstance processInstance2;
  
  private Task task1;
  
  private Task task2;
  
  private Task task3;
  
  private Task task4;
  
  @Test public void testQueryByTaskDeleteReasonLike(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){Task task5=taskService.newTask("task5");taskService.saveTask(task5);taskService.deleteTask(task5.getId(),"deleteReason%");Task task6=taskService.newTask("task6");taskService.saveTask(task6);taskService.deleteTask(task6.getId(),"deleteReason_");HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().taskDeleteReasonLike("%\\%%").singleResult();assertNotNull(historicTask);assertEquals(task5.getId(),historicTask.getId());historicTask=historyService.createHistoricTaskInstanceQuery().taskDeleteReasonLike("%\\_%").singleResult();assertNotNull(historicTask);assertEquals(task6.getId(),historicTask.getId());historicTask=historyService.createHistoricTaskInstanceQuery().or().taskDeleteReasonLike("%\\%%").processDefinitionId("undefined").singleResult();assertNotNull(historicTask);assertEquals(task5.getId(),historicTask.getId());historicTask=historyService.createHistoricTaskInstanceQuery().or().taskDeleteReasonLike("%\\_%").processDefinitionId("undefined").singleResult();assertNotNull(historicTask);assertEquals(task6.getId(),historicTask.getId());historyService.deleteHistoricTaskInstance(task5.getId());historyService.deleteHistoricTaskInstance(task6.getId());}}
}