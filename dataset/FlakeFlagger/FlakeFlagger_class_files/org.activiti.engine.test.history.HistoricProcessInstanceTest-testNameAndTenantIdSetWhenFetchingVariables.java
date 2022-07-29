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

  /**
 * Validation for https://jira.codehaus.org/browse/ACT-2182
 */public void testNameAndTenantIdSetWhenFetchingVariables(){String tenantId="testTenantId";String processInstanceName="myProcessInstance";String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml").tenantId(tenantId).deploy().getId();Map<String, Object> vars=new HashMap<String, Object>();vars.put("name","Kermit");vars.put("age",60);ProcessInstance processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("oneTaskProcess",vars,tenantId);runtimeService.setProcessInstanceName(processInstance.getId(),processInstanceName);List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().includeProcessVariables().list();assertEquals(1,processInstances.size());processInstance=processInstances.get(0);assertEquals(processInstanceName,processInstance.getName());assertEquals(tenantId,processInstance.getTenantId());Map<String, Object> processInstanceVars=processInstance.getProcessVariables();assertEquals(2,processInstanceVars.size());assertEquals("Kermit",processInstanceVars.get("name"));assertEquals(60,processInstanceVars.get("age"));List<HistoricProcessInstance> historicProcessInstances=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().list();assertEquals(1,historicProcessInstances.size());HistoricProcessInstance historicProcessInstance=historicProcessInstances.get(0);assertEquals(processInstanceName,historicProcessInstance.getName());assertEquals(tenantId,historicProcessInstance.getTenantId());Map<String, Object> historicProcessInstanceVars=historicProcessInstance.getProcessVariables();assertEquals(2,historicProcessInstanceVars.size());assertEquals("Kermit",historicProcessInstanceVars.get("name"));assertEquals(60,historicProcessInstanceVars.get("age"));repositoryService.deleteDeployment(deploymentId,true);}

}
