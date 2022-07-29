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

package org.activiti.engine.test.bpmn.event.compensate;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.EnableVerboseExecutionTreeLogging;
import org.activiti.engine.test.bpmn.event.compensate.helper.SetVariablesDelegate;

/**

 */
@EnableVerboseExecutionTreeLogging
public class CompensateEventTest extends PluggableActivitiTestCase {

  @Deployment public void testCompensateWithSubprocess(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).activityId("bookHotel").singleResult();assertNotNull(historicActivityInstance.getEndTime());}Task afterBookHotelTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("afterBookHotel").singleResult();taskService.complete(afterBookHotelTask.getId());Task compensationTask1=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask1").singleResult();assertNotNull(compensationTask1);Task compensationTask2=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask2").singleResult();assertNotNull(compensationTask2);taskService.complete(compensationTask1.getId());taskService.complete(compensationTask2.getId());Task compensationTask3=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("compensateTask3").singleResult();assertNotNull(compensationTask3);taskService.complete(compensationTask3.getId());assertProcessEnded(processInstance.getId());}
  
}
