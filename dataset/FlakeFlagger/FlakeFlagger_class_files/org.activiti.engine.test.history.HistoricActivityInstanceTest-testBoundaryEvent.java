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
 * Test to validate fix for ACT-1399: Boundary-event and event-based auditing
 */@Deployment public void testBoundaryEvent(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("boundaryEventProcess");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.complete(task.getId());assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("boundary").processInstanceId(processInstance.getId()).singleResult();assertNull(historicActivityInstance);processInstance=runtimeService.startProcessInstanceByKey("boundaryEventProcess");task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();Execution signalExecution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();runtimeService.signalEventReceived("alert",signalExecution.getId());assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("boundary").processInstanceId(processInstance.getId()).singleResult();assertNotNull(historicActivityInstance);assertNotNull(historicActivityInstance.getStartTime());assertNotNull(historicActivityInstance.getEndTime());}

}
