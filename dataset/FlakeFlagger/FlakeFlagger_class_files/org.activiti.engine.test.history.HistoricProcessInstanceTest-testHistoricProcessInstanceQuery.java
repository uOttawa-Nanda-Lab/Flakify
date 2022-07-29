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

  @Deployment(resources = { "org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml" })
  public void testHistoricProcessInstanceQuery() {
    Calendar startTime = Calendar.getInstance();

    processEngineConfiguration.getClock().setCurrentTime(startTime.getTime());
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess", "businessKey123");
    runtimeService.addUserIdentityLink(processInstance.getId(), "kermit", "someType");
    runtimeService.setProcessInstanceName(processInstance.getId(), "The name");
    Calendar hourAgo = Calendar.getInstance();
    hourAgo.add(Calendar.HOUR_OF_DAY, -1);
    Calendar hourFromNow = Calendar.getInstance();
    hourFromNow.add(Calendar.HOUR_OF_DAY, 1);

    // Name and name like
    assertEquals("The name", historyService.createHistoricProcessInstanceQuery().processInstanceName("The name").singleResult().getName());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceName("The name").count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processInstanceName("Other name").count());

    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("% name").count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("%nope").count());

    // Query after update name
    runtimeService.setProcessInstanceName(processInstance.getId(), "New name");
    assertEquals("New name", historyService.createHistoricProcessInstanceQuery().processInstanceName("New name").singleResult().getName());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceName("New name").count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processInstanceName("The name").count());

    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("New %").count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processInstanceNameLike("The %").count());

    // Start/end dates
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedBefore(hourAgo.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedBefore(hourFromNow.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedAfter(hourAgo.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedAfter(hourFromNow.getTime()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().startedBefore(hourFromNow.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().startedBefore(hourAgo.getTime()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().startedAfter(hourAgo.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().startedAfter(hourFromNow.getTime()).count());

    // General fields
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finished().count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionId(processInstance.getProcessDefinitionId()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKeyIn(Collections.singletonList("oneTaskProcess")).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKeyIn(Arrays.asList("undefined", "oneTaskProcess")).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKeyIn(Arrays.asList("undefined1", "undefined2")).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey("businessKey123").count());

    List<String> excludeIds = new ArrayList<String>();
    excludeIds.add("unexistingProcessDefinition");

    assertEquals(1, historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(excludeIds).count());

    excludeIds.add("oneTaskProcess");
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(excludeIds).count());

    // After finishing process
    taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult().getId());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().finished().count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedBefore(hourAgo.getTime()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().finishedBefore(hourFromNow.getTime()).count());
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().finishedAfter(hourAgo.getTime()).count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().finishedAfter(hourFromNow.getTime()).count());

    // Check identity links
    assertEquals(1, historyService.createHistoricProcessInstanceQuery().involvedUser("kermit").count());
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().involvedUser("gonzo").count());
  }

}
