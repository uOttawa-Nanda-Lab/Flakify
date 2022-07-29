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

  public void testHistoricVariableQueryByExecutionIds(){deployTwoTasksTestProcess();Set<String> processInstanceIds=new HashSet<String>();Set<String> testProcessInstanceIds=new HashSet<String>();for (int i=0;i < 3;i++){Map<String, Object> startVars=new HashMap<String, Object>();if (i == 1){startVars.put("startVar2","hello2");} else {startVars.put("startVar","hello");}String processInstanceId=runtimeService.startProcessInstanceByKey("twoTasksProcess",startVars).getId();processInstanceIds.add(processInstanceId);if (i != 1){testProcessInstanceIds.add(processInstanceId);}}assertThat(historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).list().size()).isEqualTo((int)historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).count()).isEqualTo(2);List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(testProcessInstanceIds).list();assertThat(historicVariableInstances).hasSize(2).extracting("name","value").containsExactly(tuple("startVar","hello"),tuple("startVar","hello"));historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(processInstanceIds).list();assertThat(historicVariableInstances).hasSize(3).extracting("name","value").containsExactlyInAnyOrder(tuple("startVar","hello"),tuple("startVar","hello"),tuple("startVar2","hello2"));}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
