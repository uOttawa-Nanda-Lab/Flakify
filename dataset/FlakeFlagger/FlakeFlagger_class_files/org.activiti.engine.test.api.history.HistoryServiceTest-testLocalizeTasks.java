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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testLocalizeTasks() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");List<HistoricTaskInstance> tasks=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list();assertEquals(1,tasks.size());assertEquals("my task",tasks.get(0).getName());assertNull(tasks.get(0).getDescription());ObjectNode infoNode=dynamicBpmnService.changeLocalizationName("en-GB","theTask","My localized name");dynamicBpmnService.changeLocalizationDescription("en-GB".toString(),"theTask","My localized description",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);tasks=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list();assertEquals(1,tasks.size());assertEquals("my task",tasks.get(0).getName());assertNull(tasks.get(0).getDescription());tasks=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).locale("en-GB").list();assertEquals(1,tasks.size());assertEquals("My localized name",tasks.get(0).getName());assertEquals("My localized description",tasks.get(0).getDescription());tasks=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).listPage(0,10);assertEquals(1,tasks.size());assertEquals("my task",tasks.get(0).getName());assertNull(tasks.get(0).getDescription());tasks=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).locale("en-GB").listPage(0,10);assertEquals(1,tasks.size());assertEquals("My localized name",tasks.get(0).getName());assertEquals("My localized description",tasks.get(0).getDescription());HistoricTaskInstance task=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();assertEquals("my task",task.getName());assertNull(task.getDescription());task=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).locale("en-GB").singleResult();assertEquals("My localized name",task.getName());assertEquals("My localized description",task.getDescription());task=historyService.createHistoricTaskInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();assertEquals("my task",task.getName());assertNull(task.getDescription());}

}
