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

    /**
	 * Test to validate fix for ACT-1939: HistoryService loads invalid task local variables for completed task
	 */@Deployment public void testVariableUpdateOrderHistoricTaskInstance() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historicTask");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);for (int i=0;i < 10;i++){taskService.setVariableLocal(task.getId(),"taskVar",i);runtimeService.setVariable(task.getExecutionId(),"procVar",i);}taskService.complete(task.getId());HistoricTaskInstance taskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeProcessVariables().singleResult();Object varValue=taskInstance.getProcessVariables().get("procVar");assertEquals(9,varValue);taskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeTaskLocalVariables().singleResult();varValue=taskInstance.getTaskLocalVariables().get("taskVar");assertEquals(9,varValue);}
}
