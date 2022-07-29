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

  @Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceSorting(){ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess");assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().asc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().desc().list().size());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().asc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().desc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().desc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().desc().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().desc().count());ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess");for (Task task:taskService.createTaskQuery().processInstanceId(processInstance2.getId()).list()){taskService.complete(task.getId());}for (Task task:taskService.createTaskQuery().processInstanceId(processInstance1.getId()).list()){taskService.complete(task.getId());}assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().asc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().desc().list().size());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().asc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceId().desc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceDuration().desc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().desc().count());assertEquals(2,historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceBusinessKey().desc().count());List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().list();List<String> processInstanceIds=new ArrayList<String>(2);processInstanceIds.add(historicProcessInstances.get(0).getId());processInstanceIds.add(historicProcessInstances.get(1).getId());assertTrue(processInstanceIds.contains(processInstance1.getId()));assertTrue(processInstanceIds.contains(processInstance2.getId()));historicProcessInstances=historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceEndTime().desc().includeProcessVariables().list();processInstanceIds=new ArrayList<String>(2);processInstanceIds.add(historicProcessInstances.get(0).getId());processInstanceIds.add(historicProcessInstances.get(1).getId());assertTrue(processInstanceIds.contains(processInstance1.getId()));assertTrue(processInstanceIds.contains(processInstance2.getId()));}

}
