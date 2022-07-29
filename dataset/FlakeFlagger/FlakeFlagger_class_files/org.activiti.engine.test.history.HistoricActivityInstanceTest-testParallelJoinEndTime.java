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

  /**
 * Test to validate fix for ACT-1549: endTime of joining parallel gateway is not set
 */@Deployment public void testParallelJoinEndTime(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("forkJoin");List<Task> tasksToComplete=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();assertEquals(2,tasksToComplete.size());taskService.complete(tasksToComplete.get(0).getId());taskService.complete(tasksToComplete.get(1).getId());List<HistoricActivityInstance> historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("join").processInstanceId(processInstance.getId()).list();assertNotNull(historicActivityInstance);assertEquals(2,historicActivityInstance.size());assertNotNull(historicActivityInstance.get(0).getEndTime());assertNotNull(historicActivityInstance.get(1).getEndTime());}

}
