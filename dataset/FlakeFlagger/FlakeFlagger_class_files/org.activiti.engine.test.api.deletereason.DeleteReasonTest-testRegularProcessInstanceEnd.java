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
package org.activiti.engine.test.api.deletereason;

import java.util.List;

import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class DeleteReasonTest extends PluggableActivitiTestCase {
  
  @Deployment public void testRegularProcessInstanceEnd(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonProcess");List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();while (!tasks.isEmpty()){for (Task task:tasks){taskService.complete(task.getId());}tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();}assertEquals(0L,runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).count());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){assertNull(historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).list();assertEquals(5,historicTaskInstances.size());for (HistoricTaskInstance historicTaskInstance:historicTaskInstances){assertNull(historicTaskInstance.getDeleteReason());}assertHistoricActivitiesDeleteReason(processInstance,null,"A","B","C","D","E");}}

}
