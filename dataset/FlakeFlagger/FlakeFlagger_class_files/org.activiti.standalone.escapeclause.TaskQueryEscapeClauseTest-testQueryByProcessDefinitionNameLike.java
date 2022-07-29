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
import java.util.List;

import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TaskQueryEscapeClauseTest extends AbstractEscapeClauseTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  private ProcessInstance processInstance1;

  private ProcessInstance processInstance2;
  
  private Task task1;
  
  private Task task2;
  
  @Test
  public void testQueryByProcessDefinitionNameLike(){
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
        // processDefinitionNameLike
        List<Task> list = taskService.createTaskQuery().processDefinitionNameLike("%\\%%").orderByTaskCreateTime().asc().list();
        assertEquals(2, list.size());
        List<String> taskIds = new ArrayList<String>(2);
        taskIds.add(list.get(0).getId());
        taskIds.add(list.get(1).getId());
        assertTrue(taskIds.contains(task1.getId()));
        assertTrue(taskIds.contains(task2.getId()));
        
        // orQuery
        list = taskService.createTaskQuery().or().processDefinitionNameLike("%\\%%").processDefinitionId("undefined").orderByTaskCreateTime().asc().list();
        assertEquals(2, list.size());
        taskIds = new ArrayList<String>(2);
        taskIds.add(list.get(0).getId());
        taskIds.add(list.get(1).getId());
        assertTrue(taskIds.contains(task1.getId()));
        assertTrue(taskIds.contains(task2.getId()));
    }
  }
}