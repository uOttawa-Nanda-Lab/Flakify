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

  @Deployment(resources={"org/activiti/standalone/history/FullHistoryTest.testVariableUpdatesAreLinkedToActivity.bpmn20.xml"}) public void testVariableUpdatesLinkedToActivity() throws Exception{if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)){ProcessInstance pi=runtimeService.startProcessInstanceByKey("ProcessWithSubProcess");Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();Map<String, Object> variables=new HashMap<String, Object>();variables.put("test","1");taskService.complete(task.getId(),variables);task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();variables.clear();variables.put("test","2");taskService.complete(task.getId(),variables);assertProcessEnded(pi.getId());List<HistoricDetail> updates=historyService.createHistoricDetailQuery().variableUpdates().list();assertEquals(2,updates.size());Map<String, HistoricVariableUpdate> updatesMap=new HashMap<String, HistoricVariableUpdate>();HistoricVariableUpdate update=(HistoricVariableUpdate)updates.get(0);updatesMap.put((String)update.getValue(),update);update=(HistoricVariableUpdate)updates.get(1);updatesMap.put((String)update.getValue(),update);HistoricVariableUpdate update1=updatesMap.get("1");HistoricVariableUpdate update2=updatesMap.get("2");assertNotNull(update1.getActivityInstanceId());assertNotNull(update1.getExecutionId());HistoricActivityInstance historicActivityInstance1=historyService.createHistoricActivityInstanceQuery().activityInstanceId(update1.getActivityInstanceId()).singleResult();assertEquals("usertask1",historicActivityInstance1.getActivityId());assertNotNull(update2.getActivityInstanceId());HistoricActivityInstance historicActivityInstance2=historyService.createHistoricActivityInstanceQuery().activityInstanceId(update2.getActivityInstanceId()).singleResult();assertEquals("usertask2",historicActivityInstance2.getActivityId());assertFalse(historicActivityInstance2.getExecutionId().equals(update2.getExecutionId()));}}

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
