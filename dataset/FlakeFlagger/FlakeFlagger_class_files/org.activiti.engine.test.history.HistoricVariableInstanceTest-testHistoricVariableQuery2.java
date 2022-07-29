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

  public void testHistoricVariableQuery2(){deployTwoTasksTestProcess();Map<String, Object> startVars=new HashMap<String, Object>();startVars.put("startVar","hello");String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess",startVars).getId();List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstanceId).list();for (int i=0;i < tasks.size();i++){runtimeService.setVariableLocal(tasks.get(i).getExecutionId(),"executionVar" + i,i);taskService.setVariableLocal(tasks.get(i).getId(),"taskVar" + i,i);}List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).orderByVariableName().asc().list();assertEquals(5,historicVariableInstances.size());List<String> expectedVariableNames=Arrays.asList("executionVar0","executionVar1","startVar","taskVar0","taskVar1");for (int i=0;i < expectedVariableNames.size();i++){assertEquals(expectedVariableNames.get(i),historicVariableInstances.get(i).getVariableName());}historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionId(tasks.get(0).getExecutionId()).orderByVariableName().asc().list();assertEquals(2,historicVariableInstances.size());assertEquals("executionVar0",historicVariableInstances.get(0).getVariableName());assertEquals("taskVar0",historicVariableInstances.get(1).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionId(tasks.get(1).getExecutionId()).orderByVariableName().asc().list();assertEquals(2,historicVariableInstances.size());assertEquals("executionVar1",historicVariableInstances.get(0).getVariableName());assertEquals("taskVar1",historicVariableInstances.get(1).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).executionId(tasks.get(0).getExecutionId()).orderByVariableName().asc().list();assertEquals(2,historicVariableInstances.size());assertEquals("executionVar0",historicVariableInstances.get(0).getVariableName());assertEquals("taskVar0",historicVariableInstances.get(1).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).executionId(tasks.get(1).getExecutionId()).orderByVariableName().asc().list();assertEquals(2,historicVariableInstances.size());assertEquals("executionVar1",historicVariableInstances.get(0).getVariableName());assertEquals("taskVar1",historicVariableInstances.get(1).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskId(tasks.get(0).getId()).list();assertEquals(1,historicVariableInstances.size());assertEquals("taskVar0",historicVariableInstances.get(0).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskId(tasks.get(1).getId()).list();assertEquals(1,historicVariableInstances.size());assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).taskId(tasks.get(0).getId()).list();assertEquals(1,historicVariableInstances.size());assertEquals("taskVar0",historicVariableInstances.get(0).getVariableName());historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).taskId(tasks.get(1).getId()).list();assertEquals(1,historicVariableInstances.size());assertEquals("taskVar1",historicVariableInstances.get(0).getVariableName());}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
