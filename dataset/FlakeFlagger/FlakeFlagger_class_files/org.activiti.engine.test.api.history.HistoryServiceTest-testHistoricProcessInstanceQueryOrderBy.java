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

package org.activiti.engine.test.api.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.api.runtime.ProcessInstanceQueryTest;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**


 */
public class HistoryServiceTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQueryOrderBy(){assertTrue(historyService.createHistoricProcessInstanceQuery().count() == 0);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();assertEquals(1,tasks.size());taskService.complete(tasks.get(0).getId());historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().asc().list();historyService.createHistoricTaskInstanceQuery().orderByExecutionId().asc().list();historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc().list();historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceDuration().asc().list();historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceEndTime().asc().list();historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().asc().list();historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskId().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskName().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskOwner().asc().list();historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().asc().list();}

}
