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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;

/**

 */
public class HistoricProcessInstanceAndVariablesQueryTest extends PluggableActivitiTestCase {

  private static String PROCESS_DEFINITION_KEY = "oneTaskProcess";
  private static String PROCESS_DEFINITION_KEY_2 = "oneTaskProcess2";
  private static String PROCESS_DEFINITION_NAME_2 = "oneTaskProcess2Name";
  private static String PROCESS_DEFINITION_CATEGORY_2 = "org.activiti.enginge.test.api.runtime.2Category";
  private static String PROCESS_DEFINITION_KEY_3 = "oneTaskProcess3";
  
  private List<String> processInstanceIds;

  /**
   * Setup starts 4 process instances of oneTaskProcess and 1 instance of oneTaskProcess2
   */
  protected void setUp() throws Exception {
    super.setUp();
    repositoryService.createDeployment()
      .addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml")
      .addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml")
      .addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess3.bpmn20.xml")
      .deploy();
    
    Map<String, Object> startMap = new HashMap<String, Object>();
    startMap.put("test", "test");
    startMap.put("test2", "test2");
    processInstanceIds = new ArrayList<String>();
    for (int i = 0; i < 4; i++) {
      processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, i + "", startMap).getId());
      if (i == 0) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceIds.get(0)).singleResult();
        taskService.complete(task.getId());
      }
    }
    startMap.clear();
    startMap.put("anothertest", 123);
    processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY_2, "1", startMap).getId());
    
    startMap.clear();
    startMap.put("casetest", "MyTest");
    processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY_3, "1", startMap).getId());
  }

  protected void tearDown() throws Exception {
    for (org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
      repositoryService.deleteDeployment(deployment.getId(), true);
    }
    super.tearDown();
  }

  public void testOrQueryByprocessDefinition(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){String deploymentId=repositoryService.createDeploymentQuery().list().get(0).getId();HistoricProcessInstanceQuery historicprocessInstanceQuery=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").deploymentId(deploymentId).endOr();assertEquals(6,historicprocessInstanceQuery.list().size());assertEquals(6,historicprocessInstanceQuery.count());Map<String, Object> variableMap=historicprocessInstanceQuery.list().get(4).getProcessVariables();assertEquals(1,variableMap.size());assertEquals(123,variableMap.get("anothertest"));for (HistoricProcessInstance processInstance:historicprocessInstanceQuery.list()){assertEquals(deploymentId,processInstance.getDeploymentId());}HistoricProcessInstance processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").deploymentId("invalid").endOr().singleResult();assertNull(processInstance);processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionName(PROCESS_DEFINITION_NAME_2).endOr().singleResult();variableMap=processInstance.getProcessVariables();assertEquals(1,variableMap.size());assertEquals(123,variableMap.get("anothertest"));assertEquals(PROCESS_DEFINITION_NAME_2,processInstance.getProcessDefinitionName());processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionName("invalid").endOr().singleResult();assertNull(processInstance);processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).endOr().singleResult();variableMap=processInstance.getProcessVariables();assertEquals(1,variableMap.size());assertEquals(123,variableMap.get("anothertest"));processInstance=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest","invalid").processDefinitionCategory("invalid").endOr().singleResult();assertNull(processInstance);}}
}
