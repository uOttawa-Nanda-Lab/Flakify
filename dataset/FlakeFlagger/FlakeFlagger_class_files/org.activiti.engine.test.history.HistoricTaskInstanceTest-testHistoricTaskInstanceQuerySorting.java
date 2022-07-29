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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class HistoricTaskInstanceTest extends PluggableActivitiTestCase {

    @Deployment public void testHistoricTaskInstanceQuerySorting(){ProcessInstance instance=runtimeService.startProcessInstanceByKey("HistoricTaskQueryTest");String taskId=taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult().getId();taskService.complete(taskId);assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByExecutionId().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskName().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskId().asc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByExecutionId().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskName().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().desc().count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskId().desc().count());}
}
