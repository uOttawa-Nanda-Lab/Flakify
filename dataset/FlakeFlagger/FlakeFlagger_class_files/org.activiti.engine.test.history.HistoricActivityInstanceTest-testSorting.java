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

  @Deployment public void testSorting(){runtimeService.startProcessInstanceByKey("process");int expectedActivityInstances;if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){expectedActivityInstances=2;} else {expectedActivityInstances=0;}assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().asc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().desc().list().size());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().asc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceId().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceDuration().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByExecutionId().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessDefinitionId().desc().count());assertEquals(expectedActivityInstances,historyService.createHistoricActivityInstanceQuery().orderByProcessInstanceId().desc().count());}

}
