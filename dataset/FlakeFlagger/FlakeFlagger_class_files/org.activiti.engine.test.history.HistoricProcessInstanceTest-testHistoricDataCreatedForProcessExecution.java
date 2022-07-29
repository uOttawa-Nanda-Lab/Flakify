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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class HistoricProcessInstanceTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricDataCreatedForProcessExecution(){Calendar calendar=new GregorianCalendar();calendar.set(Calendar.YEAR,2010);calendar.set(Calendar.MONTH,8);calendar.set(Calendar.DAY_OF_MONTH,30);calendar.set(Calendar.HOUR_OF_DAY,12);calendar.set(Calendar.MINUTE,0);calendar.set(Calendar.SECOND,0);calendar.set(Calendar.MILLISECOND,0);Date noon=calendar.getTime();processEngineConfiguration.getClock().setCurrentTime(noon);final ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess","myBusinessKey");assertEquals(1,historyService.createHistoricProcessInstanceQuery().unfinished().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().finished().count());HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(historicProcessInstance);assertEquals(processInstance.getId(),historicProcessInstance.getId());assertEquals(processInstance.getBusinessKey(),historicProcessInstance.getBusinessKey());assertEquals(processInstance.getProcessDefinitionId(),historicProcessInstance.getProcessDefinitionId());assertEquals(noon,historicProcessInstance.getStartTime());assertNull(historicProcessInstance.getEndTime());assertNull(historicProcessInstance.getDurationInMillis());List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();assertEquals(1,tasks.size());Date twentyFiveSecsAfterNoon=new Date(noon.getTime() + 25 * 1000);processEngineConfiguration.getClock().setCurrentTime(twentyFiveSecsAfterNoon);taskService.complete(tasks.get(0).getId());historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(historicProcessInstance);assertEquals(processInstance.getId(),historicProcessInstance.getId());assertEquals(processInstance.getProcessDefinitionId(),historicProcessInstance.getProcessDefinitionId());assertEquals(noon,historicProcessInstance.getStartTime());assertEquals(twentyFiveSecsAfterNoon,historicProcessInstance.getEndTime());assertEquals(new Long(25 * 1000),historicProcessInstance.getDurationInMillis());assertEquals(0,historyService.createHistoricProcessInstanceQuery().unfinished().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());}

  /*
   * @Deployment(resources = {"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceVariables() { Map<String,Object> vars = new
   * HashMap<String,Object>(); vars.put("foo", "bar"); vars.put("baz", "boo");
   * 
   * runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);
   * 
   * assertEquals(1, historyService.createHistoricProcessInstanceQuery().processVariableEquals ("foo", "bar").count()); assertEquals(1, historyService.createHistoricProcessInstanceQuery
   * ().processVariableEquals("baz", "boo").count()); assertEquals(1, historyService .createHistoricProcessInstanceQuery().processVariableEquals("foo", "bar").processVariableEquals("baz",
   * "boo").count()); }
   */

}
