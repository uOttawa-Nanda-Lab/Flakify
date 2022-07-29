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

import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class HistoricActivityInstanceTest extends PluggableActivitiTestCase {

  @Deployment public void testHistoricActivityInstanceQuery(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("noopProcess");assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityId("nonExistingActivityId").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("noop").list().size());assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityType("nonExistingActivityType").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityType("serviceTask").list().size());assertEquals(0,historyService.createHistoricActivityInstanceQuery().activityName("nonExistingActivityName").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityName("No operation").list().size());assertEquals(0,historyService.createHistoricActivityInstanceQuery().taskAssignee("nonExistingAssignee").list().size());assertEquals(0,historyService.createHistoricActivityInstanceQuery().executionId("nonExistingExecutionId").list().size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());} else {assertEquals(0,historyService.createHistoricActivityInstanceQuery().executionId(processInstance.getId()).list().size());}assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId("nonExistingProcessInstanceId").list().size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());} else {assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list().size());}assertEquals(0,historyService.createHistoricActivityInstanceQuery().processDefinitionId("nonExistingProcessDefinitionId").list().size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){assertEquals(3,historyService.createHistoricActivityInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list().size());} else {assertEquals(0,historyService.createHistoricActivityInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list().size());}assertEquals(0,historyService.createHistoricActivityInstanceQuery().unfinished().list().size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){assertEquals(3,historyService.createHistoricActivityInstanceQuery().finished().list().size());} else {assertEquals(0,historyService.createHistoricActivityInstanceQuery().finished().list().size());}if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().list().get(0);assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityInstanceId(historicActivityInstance.getId()).list().size());}}

}
