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

  @Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceOrQuery(){Calendar startTime=Calendar.getInstance();processEngineConfiguration.getClock().setCurrentTime(startTime.getTime());ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess","businessKey123");runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","someType");runtimeService.setProcessInstanceName(processInstance.getId(),"The name");Calendar hourAgo=Calendar.getInstance();hourAgo.add(Calendar.HOUR_OF_DAY,-1);Calendar hourFromNow=Calendar.getInstance();hourFromNow.add(Calendar.HOUR_OF_DAY,1);assertEquals("The name",historyService.createHistoricProcessInstanceQuery().or().processInstanceName("The name").processDefinitionId("undefined").endOr().singleResult().getName());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("The name").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("Other name").processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("% name").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("%nope").processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("The name").processDefinitionId("undefined").endOr().or().processInstanceNameLike("% name").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("The name").processDefinitionId("undefined").endOr().or().processInstanceNameLike("undefined").processDefinitionId("undefined").endOr().count());runtimeService.setProcessInstanceName(processInstance.getId(),"New name");assertEquals("New name",historyService.createHistoricProcessInstanceQuery().or().processInstanceName("New name").processDefinitionId("undefined").endOr().singleResult().getName());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("New name").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processInstanceName("The name").processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("New %").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLike("The %").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedBefore(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedBefore(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedAfter(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedAfter(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().startedBefore(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().startedBefore(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().startedAfter(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().startedAfter(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finished().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceId(processInstance.getId()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionId(processInstance.getProcessDefinitionId()).processDefinitionKey("undefined").count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionId("undefined").processDefinitionKeyIn(Arrays.asList("undefined","oneTaskProcess")).endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processDefinitionId("undefined").processDefinitionKeyIn(Arrays.asList("undefined1","undefined2")).endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionKey("oneTaskProcess").processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processInstanceBusinessKey("businessKey123").processDefinitionId("undefined").endOr().count());List<String> excludeIds=new ArrayList<String>();excludeIds.add("unexistingProcessDefinition");assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(excludeIds).processDefinitionId("undefined").endOr().count());excludeIds.add("oneTaskProcess");assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(excludeIds).processDefinitionId("undefined").endOr().count());taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult().getId());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().finished().processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedBefore(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().finishedBefore(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().finishedAfter(hourAgo.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().finishedAfter(hourFromNow.getTime()).processDefinitionId("undefined").endOr().count());assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().involvedUser("kermit").processDefinitionId("undefined").endOr().count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().or().involvedUser("gonzo").processDefinitionId("undefined").endOr().count());}

}
