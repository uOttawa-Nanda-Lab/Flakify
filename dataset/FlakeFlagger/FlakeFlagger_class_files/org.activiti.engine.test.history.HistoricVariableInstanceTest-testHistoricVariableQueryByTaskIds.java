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

package org.activiti.engine.test.history;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;


public class HistoricVariableInstanceTest extends PluggableActivitiTestCase {

  public void testHistoricVariableQueryByTaskIds(){deployTwoTasksTestProcess();String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess").getId();List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstanceId).list();taskService.setVariableLocal(tasks.get(0).getId(),"taskVar1","hello1");taskService.setVariableLocal(tasks.get(1).getId(),"taskVar2","hello2");Set<String> taskIds=new HashSet<String>();taskIds.add(tasks.get(0).getId());taskIds.add(tasks.get(1).getId());List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();assertEquals(2,historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).count());assertEquals(2,historicVariableInstances.size());assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());assertEquals("hello1",historicVariableInstances.get(0).getValue());assertEquals("taskVar2",historicVariableInstances.get(1).getVariableName());assertEquals("hello2",historicVariableInstances.get(1).getValue());taskIds=new HashSet<String>();taskIds.add(tasks.get(0).getId());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();assertEquals(1,historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).count());assertEquals(1,historicVariableInstances.size());assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());assertEquals("hello1",historicVariableInstances.get(0).getValue());}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
