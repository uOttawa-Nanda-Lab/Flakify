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
  
  @Test public void testQueryByTaskDescriptionLike(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().taskDescriptionLike("%\\%%").orderByHistoricTaskInstanceStartTime().asc().list();assertEquals(2,list.size());List<String> taskIds=new ArrayList<String>(2);taskIds.add(list.get(0).getId());taskIds.add(list.get(1).getId());assertTrue(taskIds.contains(task1.getId()));assertTrue(taskIds.contains(task3.getId()));list=historyService.createHistoricTaskInstanceQuery().taskDescriptionLike("%\\_%").orderByHistoricTaskInstanceStartTime().asc().list();assertEquals(2,list.size());taskIds=new ArrayList<String>(2);taskIds.add(list.get(0).getId());taskIds.add(list.get(1).getId());assertTrue(taskIds.contains(task2.getId()));assertTrue(taskIds.contains(task4.getId()));list=historyService.createHistoricTaskInstanceQuery().or().taskDescriptionLike("%\\%%").processDefinitionId("undefined").orderByHistoricTaskInstanceStartTime().asc().list();assertEquals(2,list.size());taskIds=new ArrayList<String>(2);taskIds.add(list.get(0).getId());taskIds.add(list.get(1).getId());assertTrue(taskIds.contains(task1.getId()));assertTrue(taskIds.contains(task3.getId()));list=historyService.createHistoricTaskInstanceQuery().or().taskDescriptionLike("%\\_%").processDefinitionId("undefined").orderByHistoricTaskInstanceStartTime().asc().list();assertEquals(2,list.size());taskIds=new ArrayList<String>(2);taskIds.add(list.get(0).getId());taskIds.add(list.get(1).getId());assertTrue(taskIds.contains(task2.getId()));assertTrue(taskIds.contains(task4.getId()));}}
}