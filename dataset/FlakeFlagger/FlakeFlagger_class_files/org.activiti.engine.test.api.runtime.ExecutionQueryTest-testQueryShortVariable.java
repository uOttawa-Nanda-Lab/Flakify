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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryShortVariable(){Map<String, Object> vars=new HashMap<String, Object>();short shortVar=1234;vars.put("shortVar",shortVar);ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);short shortVar2=6789;vars=new HashMap<String, Object>();vars.put("shortVar",shortVar);vars.put("shortVar2",shortVar2);ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);vars=new HashMap<String, Object>();vars.put("shortVar",(short)5555);ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);ExecutionQuery query=runtimeService.createExecutionQuery().variableValueEquals("shortVar",shortVar);List<Execution> executions=query.list();assertNotNull(executions);assertEquals(2,executions.size());query=runtimeService.createExecutionQuery().variableValueEquals("shortVar",shortVar).variableValueEquals("shortVar2",shortVar2);Execution execution=query.singleResult();assertNotNull(execution);assertEquals(processInstance2.getId(),execution.getId());short unexistingValue=(short)9999;execution=runtimeService.createExecutionQuery().variableValueEquals("shortVar",unexistingValue).singleResult();assertNull(execution);execution=runtimeService.createExecutionQuery().variableValueNotEquals("shortVar",(short)1234).singleResult();assertNotNull(execution);assertEquals(processInstance3.getId(),execution.getId());execution=runtimeService.createExecutionQuery().variableValueGreaterThan("shortVar",(short)4444).singleResult();assertNotNull(execution);assertEquals(processInstance3.getId(),execution.getId());assertEquals(0,runtimeService.createExecutionQuery().variableValueGreaterThan("shortVar",(short)5555).count());assertEquals(3,runtimeService.createExecutionQuery().variableValueGreaterThan("shortVar",(short)1).count());execution=runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("shortVar",(short)4444).singleResult();assertNotNull(execution);assertEquals(processInstance3.getId(),execution.getId());execution=runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("shortVar",(short)5555).singleResult();assertNotNull(execution);assertEquals(processInstance3.getId(),execution.getId());assertEquals(3,runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("shortVar",(short)1).count());executions=runtimeService.createExecutionQuery().variableValueLessThan("shortVar",(short)5555).list();assertEquals(2,executions.size());List<String> expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());List<String> ids=new ArrayList<String>(Arrays.asList(executions.get(0).getId(),executions.get(1).getId()));ids.removeAll(expectedIds);assertTrue(ids.isEmpty());assertEquals(0,runtimeService.createExecutionQuery().variableValueLessThan("shortVar",(short)1234).count());assertEquals(3,runtimeService.createExecutionQuery().variableValueLessThan("shortVar",(short)6666).count());executions=runtimeService.createExecutionQuery().variableValueLessThanOrEqual("shortVar",(short)5555).list();assertEquals(3,executions.size());assertEquals(0,runtimeService.createExecutionQuery().variableValueLessThanOrEqual("shortVar",(short)1233).count());execution=runtimeService.createExecutionQuery().variableValueEquals((short)5555).singleResult();assertNotNull(execution);assertEquals(processInstance3.getId(),execution.getId());executions=runtimeService.createExecutionQuery().variableValueEquals((short)1234).list();assertEquals(2,executions.size());expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());ids=new ArrayList<String>(Arrays.asList(executions.get(0).getId(),executions.get(1).getId()));ids.removeAll(expectedIds);assertTrue(ids.isEmpty());execution=runtimeService.createExecutionQuery().variableValueEquals((short)999).singleResult();assertNull(execution);runtimeService.deleteProcessInstance(processInstance1.getId(),"test");runtimeService.deleteProcessInstance(processInstance2.getId(),"test");runtimeService.deleteProcessInstance(processInstance3.getId(),"test");}
}
