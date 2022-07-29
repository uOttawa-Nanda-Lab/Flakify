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

  @Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testChangeType(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");TaskQuery taskQuery=taskService.createTaskQuery();Task task=taskQuery.singleResult();assertEquals("my task",task.getName());runtimeService.setVariable(processInstance.getId(),"firstVar","123");assertEquals("123",getHistoricVariable("firstVar").getValue());runtimeService.setVariable(processInstance.getId(),"firstVar","456");assertEquals("456",getHistoricVariable("firstVar").getValue());runtimeService.setVariable(processInstance.getId(),"firstVar","789");assertEquals("789",getHistoricVariable("firstVar").getValue());runtimeService.setVariable(processInstance.getId(),"secondVar","123");assertEquals("123",getHistoricVariable("secondVar").getValue());runtimeService.setVariable(processInstance.getId(),"secondVar",456);assertEquals(456,getHistoricVariable("secondVar").getValue());runtimeService.setVariable(processInstance.getId(),"secondVar","789");assertEquals("789",getHistoricVariable("secondVar").getValue());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());}}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
