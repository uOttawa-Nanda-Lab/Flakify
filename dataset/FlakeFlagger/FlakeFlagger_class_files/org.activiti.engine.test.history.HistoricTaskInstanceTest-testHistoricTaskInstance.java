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

    @Deployment public void testHistoricTaskInstance() throws Exception{Map<String, Object> varMap=new HashMap<String, Object>();varMap.put("formKeyVar","expressionFormKey");String processInstanceId=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest",varMap).getId();SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");Task runtimeTask=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();runtimeTask.setPriority(1234);Date dueDate=sdf.parse("01/02/2003 04:05:06");runtimeTask.setDueDate(dueDate);taskService.saveTask(runtimeTask);String taskId=runtimeTask.getId();String taskDefinitionKey=runtimeTask.getTaskDefinitionKey();assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().singleResult();assertEquals(taskId,historicTaskInstance.getId());assertEquals(1234,historicTaskInstance.getPriority());assertEquals("Clean up",historicTaskInstance.getName());assertEquals("Schedule an engineering meeting for next week with the new hire.",historicTaskInstance.getDescription());assertEquals(dueDate,historicTaskInstance.getDueDate());assertEquals("kermit",historicTaskInstance.getAssignee());assertEquals(taskDefinitionKey,historicTaskInstance.getTaskDefinitionKey());assertEquals("expressionFormKey",historicTaskInstance.getFormKey());assertNull(historicTaskInstance.getEndTime());assertNull(historicTaskInstance.getDurationInMillis());assertNull(historicTaskInstance.getWorkTimeInMillis());runtimeService.setVariable(processInstanceId,"deadline","yesterday");taskService.claim(taskId,"kermit");assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());historicTaskInstance=historyService.createHistoricTaskInstanceQuery().singleResult();assertNotNull(historicTaskInstance.getClaimTime());assertNull(historicTaskInstance.getWorkTimeInMillis());assertEquals("expressionFormKey",historicTaskInstance.getFormKey());taskService.complete(taskId);assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());historicTaskInstance=historyService.createHistoricTaskInstanceQuery().singleResult();assertEquals(taskId,historicTaskInstance.getId());assertEquals(1234,historicTaskInstance.getPriority());assertEquals("Clean up",historicTaskInstance.getName());assertEquals("Schedule an engineering meeting for next week with the new hire.",historicTaskInstance.getDescription());assertEquals(dueDate,historicTaskInstance.getDueDate());assertEquals("kermit",historicTaskInstance.getAssignee());assertNull(historicTaskInstance.getDeleteReason());assertEquals(taskDefinitionKey,historicTaskInstance.getTaskDefinitionKey());assertEquals("expressionFormKey",historicTaskInstance.getFormKey());assertNotNull(historicTaskInstance.getEndTime());assertNotNull(historicTaskInstance.getDurationInMillis());assertNotNull(historicTaskInstance.getClaimTime());assertNotNull(historicTaskInstance.getWorkTimeInMillis());historyService.deleteHistoricTaskInstance(taskId);assertEquals(0,historyService.createHistoricTaskInstanceQuery().count());}
}
