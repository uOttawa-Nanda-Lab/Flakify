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

  @Deployment(resources={"org/activiti/engine/test/api/runtime/variableScope.bpmn20.xml"}) public void testHistoricVariableQueryByTaskIdsForScope(){Map<String, Object> processVars=new HashMap<String, Object>();processVars.put("processVar","processVar");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("variableScopeProcess",processVars);Set<String> executionIds=new HashSet<String>();List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();for (Execution execution:executions){if (!processInstance.getId().equals(execution.getId())){executionIds.add(execution.getId());runtimeService.setVariableLocal(execution.getId(),"executionVar","executionVar");}}List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();Set<String> taskIds=new HashSet<String>();for (Task task:tasks){taskService.setVariableLocal(task.getId(),"taskVar","taskVar");taskIds.add(task.getId());}List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();assertEquals(historicVariableInstances.size(),2);assertEquals(historicVariableInstances.get(0).getVariableName(),"taskVar");assertEquals(historicVariableInstances.get(0).getValue(),"taskVar");assertEquals(historicVariableInstances.get(1).getVariableName(),"taskVar");assertEquals(historicVariableInstances.get(1).getValue(),"taskVar");}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
