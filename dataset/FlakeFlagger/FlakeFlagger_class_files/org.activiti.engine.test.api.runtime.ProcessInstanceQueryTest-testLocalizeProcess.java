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
package org.activiti.engine.test.api.runtime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**




 */
public class ProcessInstanceQueryTest extends PluggableActivitiTestCase {

  private static final int PROCESS_DEFINITION_KEY_DEPLOY_COUNT = 4;
  private static final int PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT = 1;
  private static final int PROCESS_DEPLOY_COUNT = PROCESS_DEFINITION_KEY_DEPLOY_COUNT + PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT;
  private static final String PROCESS_DEFINITION_KEY = "oneTaskProcess";
  private static final String PROCESS_DEFINITION_KEY_2 = "oneTaskProcess2";
  private static final String PROCESS_DEFINITION_NAME = "oneTaskProcessName";
  private static final String PROCESS_DEFINITION_NAME_2 = "oneTaskProcess2Name";
  private static final String PROCESS_DEFINITION_CATEGORY = "org.activiti.enginge.test.api.runtime.Category";
  private static final String PROCESS_DEFINITION_CATEGORY_2 = "org.activiti.enginge.test.api.runtime.2Category";
  

  private org.activiti.engine.repository.Deployment deployment;
  private List<String> processInstanceIds;

  /**
   * Setup starts 4 process instances of oneTaskProcess and 1 instance of oneTaskProcess2
   */
  protected void setUp() throws Exception {
    super.setUp();
    deployment = repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml")
        .addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml").deploy();

    processInstanceIds = new ArrayList<String>();
    for (int i = 0; i < PROCESS_DEFINITION_KEY_DEPLOY_COUNT; i++) {
      processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, i + "").getId());
    }
    processInstanceIds.add(runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY_2, "1").getId());
  }

  protected void tearDown() throws Exception {
    for (org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
      repositoryService.deleteDeployment(deployment.getId(), true);
    }
    super.tearDown();
  }

  public void testLocalizeProcess() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");List<ProcessInstance> processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).list();assertEquals(1,processes.size());assertNull(processes.get(0).getName());assertNull(processes.get(0).getDescription());ObjectNode infoNode=dynamicBpmnService.getProcessDefinitionInfo(processInstance.getProcessDefinitionId());dynamicBpmnService.changeLocalizationName("en-GB","oneTaskProcess","The One Task Process 'en-GB' localized name",infoNode);dynamicBpmnService.changeLocalizationDescription("en-GB","oneTaskProcess","The One Task Process 'en-GB' localized description",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);dynamicBpmnService.changeLocalizationName("en","oneTaskProcess","The One Task Process 'en' localized name",infoNode);dynamicBpmnService.changeLocalizationDescription("en","oneTaskProcess","The One Task Process 'en' localized description",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).list();assertEquals(1,processes.size());assertNull(processes.get(0).getName());assertNull(processes.get(0).getDescription());processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("es").list();assertEquals(1,processes.size());assertEquals("Nombre del proceso",processes.get(0).getName());assertEquals("Descripción del proceso",processes.get(0).getDescription());processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("en-GB").list();assertEquals(1,processes.size());assertEquals("The One Task Process 'en-GB' localized name",processes.get(0).getName());assertEquals("The One Task Process 'en-GB' localized description",processes.get(0).getDescription());processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).listPage(0,10);assertEquals(1,processes.size());assertNull(processes.get(0).getName());assertNull(processes.get(0).getDescription());processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("es").listPage(0,10);assertEquals(1,processes.size());assertEquals("Nombre del proceso",processes.get(0).getName());assertEquals("Descripción del proceso",processes.get(0).getDescription());processes=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("en-GB").listPage(0,10);assertEquals(1,processes.size());assertEquals("The One Task Process 'en-GB' localized name",processes.get(0).getName());assertEquals("The One Task Process 'en-GB' localized description",processes.get(0).getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();assertNull(processInstance.getName());assertNull(processInstance.getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("es").singleResult();assertEquals("Nombre del proceso",processInstance.getName());assertEquals("Descripción del proceso",processInstance.getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("en-GB").singleResult();assertEquals("The One Task Process 'en-GB' localized name",processInstance.getName());assertEquals("The One Task Process 'en-GB' localized description",processInstance.getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();assertNull(processInstance.getName());assertNull(processInstance.getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("en").singleResult();assertEquals("The One Task Process 'en' localized name",processInstance.getName());assertEquals("The One Task Process 'en' localized description",processInstance.getDescription());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).locale("en-AU").withLocalizationFallback().singleResult();assertEquals("The One Task Process 'en' localized name",processInstance.getName());assertEquals("The One Task Process 'en' localized description",processInstance.getDescription());}
}
