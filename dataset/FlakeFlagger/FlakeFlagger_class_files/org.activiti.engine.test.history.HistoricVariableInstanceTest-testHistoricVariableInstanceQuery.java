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

  @Deployment(resources={"org/activiti/engine/test/history/HistoricVariableInstanceTest.testCallSimpleSubProcess.bpmn20.xml","org/activiti/engine/test/history/simpleSubProcess.bpmn20.xml"}) public void testHistoricVariableInstanceQuery(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callSimpleSubProcess");assertProcessEnded(processInstance.getId());assertEquals(4,historyService.createHistoricVariableInstanceQuery().count());assertEquals(4,historyService.createHistoricVariableInstanceQuery().list().size());assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByProcessInstanceId().asc().count());assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByProcessInstanceId().asc().list().size());assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().count());assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().list().size());assertEquals(2,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(2,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).list().size());assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableName("myVar").count());assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableName("myVar").list().size());assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableNameLike("myVar1").count());assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableNameLike("myVar1").list().size());List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().list();assertEquals(4,variables.size());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test123").count());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test123").list().size());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test456").count());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test456").list().size());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test666").count());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test666").list().size());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test666").count());assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test666").list().size());assertEquals(8,historyService.createHistoricActivityInstanceQuery().count());assertEquals(5,historyService.createHistoricDetailQuery().count());}}
  
  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
