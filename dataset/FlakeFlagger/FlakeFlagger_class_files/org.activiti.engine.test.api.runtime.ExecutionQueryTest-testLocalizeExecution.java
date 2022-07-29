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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**


 */
public class ExecutionQueryTest extends PluggableActivitiTestCase {

  private static String CONCURRENT_PROCESS_KEY = "concurrent";
  private static String SEQUENTIAL_PROCESS_KEY = "oneTaskProcess";
  private static String CONCURRENT_PROCESS_NAME = "concurrentName";
  private static String SEQUENTIAL_PROCESS_NAME = "oneTaskProcessName";
  private static String CONCURRENT_PROCESS_CATEGORY = "org.activiti.enginge.test.api.runtime.concurrent.Category";
  private static String SEQUENTIAL_PROCESS_CATEGORY = "org.activiti.enginge.test.api.runtime.Category";
  

  private List<String> concurrentProcessInstanceIds;
  private List<String> sequentialProcessInstanceIds;

  protected void setUp() throws Exception {
    super.setUp();
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml")
        .addClasspathResource("org/activiti/engine/test/api/runtime/concurrentExecution.bpmn20.xml").deploy();

    concurrentProcessInstanceIds = new ArrayList<String>();
    sequentialProcessInstanceIds = new ArrayList<String>();

    for (int i = 0; i < 4; i++) {
      concurrentProcessInstanceIds.add(runtimeService.startProcessInstanceByKey(CONCURRENT_PROCESS_KEY, "BUSINESS-KEY-" + i).getId());
    }
    sequentialProcessInstanceIds.add(runtimeService.startProcessInstanceByKey(SEQUENTIAL_PROCESS_KEY).getId());
  }

  protected void tearDown() throws Exception {
    for (org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
      repositoryService.deleteDeployment(deployment.getId(), true);
    }
    super.tearDown();
  }

  @Deployment(resources={"org/activiti/engine/test/api/runtime/executionLocalization.bpmn20.xml"}) public void testLocalizeExecution() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionLocalization");List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertNull(execution.getName());assertNull(execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertNull(execution.getName());assertNull(execution.getDescription());}}ObjectNode infoNode=dynamicBpmnService.getProcessDefinitionInfo(processInstance.getProcessDefinitionId());dynamicBpmnService.changeLocalizationName("en-GB","executionLocalization","Process Name 'en-GB'",infoNode);dynamicBpmnService.changeLocalizationDescription("en-GB","executionLocalization","Process Description 'en-GB'",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);dynamicBpmnService.changeLocalizationName("en","executionLocalization","Process Name 'en'",infoNode);dynamicBpmnService.changeLocalizationDescription("en","executionLocalization","Process Description 'en'",infoNode);dynamicBpmnService.changeLocalizationName("en-GB","subProcess","SubProcess Name 'en-GB'",infoNode);dynamicBpmnService.changeLocalizationDescription("en-GB","subProcess","SubProcess Description 'en-GB'",infoNode);dynamicBpmnService.changeLocalizationName("en","subProcess","SubProcess Name 'en'",infoNode);dynamicBpmnService.changeLocalizationDescription("en","subProcess","SubProcess Description 'en'",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();String subProcessId=null;assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertNull(execution.getName());assertNull(execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertNull(execution.getName());assertNull(execution.getDescription());subProcessId=execution.getId();}}executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("es").list();assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertEquals("Nombre del proceso",execution.getName());assertEquals("Descripción del proceso",execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertEquals("Nombre Subproceso",execution.getName());assertEquals("Subproceso Descripción",execution.getDescription());}}executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("en-GB").list();assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertEquals("Process Name 'en-GB'",execution.getName());assertEquals("Process Description 'en-GB'",execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertEquals("SubProcess Name 'en-GB'",execution.getName());assertEquals("SubProcess Description 'en-GB'",execution.getDescription());}}executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).listPage(0,10);assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertNull(execution.getName());assertNull(execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertNull(execution.getName());assertNull(execution.getDescription());}}executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("es").listPage(0,10);assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertEquals("Nombre del proceso",execution.getName());assertEquals("Descripción del proceso",execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertEquals("Nombre Subproceso",execution.getName());assertEquals("Subproceso Descripción",execution.getDescription());}}executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).locale("en-GB").listPage(0,10);assertEquals(3,executions.size());for (Execution execution:executions){if (execution.getParentId() == null){assertEquals("Process Name 'en-GB'",execution.getName());assertEquals("Process Description 'en-GB'",execution.getDescription());} else if (execution.getParentId().equals(execution.getProcessInstanceId())){assertEquals("SubProcess Name 'en-GB'",execution.getName());assertEquals("SubProcess Description 'en-GB'",execution.getDescription());}}Execution execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).singleResult();assertNull(execution.getName());assertNull(execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(subProcessId).singleResult();assertNull(execution.getName());assertNull(execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("es").singleResult();assertEquals("Nombre del proceso",execution.getName());assertEquals("Descripción del proceso",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("es").singleResult();assertEquals("Nombre Subproceso",execution.getName());assertEquals("Subproceso Descripción",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-GB").singleResult();assertEquals("Process Name 'en-GB'",execution.getName());assertEquals("Process Description 'en-GB'",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-GB").singleResult();assertEquals("SubProcess Name 'en-GB'",execution.getName());assertEquals("SubProcess Description 'en-GB'",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-AU").withLocalizationFallback().singleResult();assertEquals("Process Name 'en'",execution.getName());assertEquals("Process Description 'en'",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-AU").withLocalizationFallback().singleResult();assertEquals("SubProcess Name 'en'",execution.getName());assertEquals("SubProcess Description 'en'",execution.getDescription());dynamicBpmnService.changeLocalizationName("en-US","executionLocalization","Process Name 'en-US'",infoNode);dynamicBpmnService.changeLocalizationDescription("en-US","executionLocalization","Process Description 'en-US'",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);dynamicBpmnService.changeLocalizationName("en-US","subProcess","SubProcess Name 'en-US'",infoNode);dynamicBpmnService.changeLocalizationDescription("en-US","subProcess","SubProcess Description 'en-US'",infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);execution=runtimeService.createExecutionQuery().executionId(processInstance.getId()).locale("en-US").singleResult();assertEquals("Process Name 'en-US'",execution.getName());assertEquals("Process Description 'en-US'",execution.getDescription());execution=runtimeService.createExecutionQuery().executionId(subProcessId).locale("en-US").singleResult();assertEquals("SubProcess Name 'en-US'",execution.getName());assertEquals("SubProcess Description 'en-US'",execution.getDescription());}
}
