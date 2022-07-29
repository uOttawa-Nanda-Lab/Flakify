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


  public void testHistoricVariableOkOnUpdate(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){Task task=taskService.newTask();task.setName("test execution");task.setOwner("josOwner");task.setAssignee("JosAssignee");taskService.saveTask(task);Map<String, Object> taskVariables=new HashMap<String, Object>();taskVariables.put("finishedAmount",0);taskService.setVariables(task.getId(),taskVariables);Map<String, Object> finishVariables=new HashMap<String, Object>();finishVariables.put("finishedAmount",40);taskService.complete(task.getId(),finishVariables);List<HistoricVariableInstance> hisVarList=historyService.createHistoricVariableInstanceQuery().taskId(task.getId()).list();assertEquals(1,hisVarList.size());assertEquals(40,hisVarList.get(0).getValue());historyService.deleteHistoricTaskInstance(task.getId());}}

}
