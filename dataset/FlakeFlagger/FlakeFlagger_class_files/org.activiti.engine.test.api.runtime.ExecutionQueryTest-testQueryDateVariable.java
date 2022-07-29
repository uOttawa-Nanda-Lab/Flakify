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

  @Deployment(resources = { "org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml" })
  public void testQueryDateVariable() throws Exception {
    Map<String, Object> vars = new HashMap<String, Object>();
    Date date1 = Calendar.getInstance().getTime();
    vars.put("dateVar", date1);

    ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    Calendar cal2 = Calendar.getInstance();
    cal2.add(Calendar.SECOND, 1);

    Date date2 = cal2.getTime();
    vars = new HashMap<String, Object>();
    vars.put("dateVar", date1);
    vars.put("dateVar2", date2);
    ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    Calendar nextYear = Calendar.getInstance();
    nextYear.add(Calendar.YEAR, 1);
    vars = new HashMap<String, Object>();
    vars.put("dateVar", nextYear.getTime());
    ProcessInstance processInstance3 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    Calendar nextMonth = Calendar.getInstance();
    nextMonth.add(Calendar.MONTH, 1);

    Calendar twoYearsLater = Calendar.getInstance();
    twoYearsLater.add(Calendar.YEAR, 2);

    Calendar oneYearAgo = Calendar.getInstance();
    oneYearAgo.add(Calendar.YEAR, -1);

    // Query on single short variable, should result in 2 matches
    ExecutionQuery query = runtimeService.createExecutionQuery().variableValueEquals("dateVar", date1);
    List<Execution> executions = query.list();
    assertNotNull(executions);
    assertEquals(2, executions.size());

    // Query on two short variables, should result in single value
    query = runtimeService.createExecutionQuery().variableValueEquals("dateVar", date1).variableValueEquals("dateVar2", date2);
    Execution execution = query.singleResult();
    assertNotNull(execution);
    assertEquals(processInstance2.getId(), execution.getId());

    // Query with unexisting variable value
    Date unexistingDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("01/01/1989 12:00:00");
    execution = runtimeService.createExecutionQuery().variableValueEquals("dateVar", unexistingDate).singleResult();
    assertNull(execution);

    // Test NOT_EQUALS
    execution = runtimeService.createExecutionQuery().variableValueNotEquals("dateVar", date1).singleResult();
    assertNotNull(execution);
    assertEquals(processInstance3.getId(), execution.getId());

    // Test GREATER_THAN
    execution = runtimeService.createExecutionQuery().variableValueGreaterThan("dateVar", nextMonth.getTime()).singleResult();
    assertNotNull(execution);
    assertEquals(processInstance3.getId(), execution.getId());

    assertEquals(0, runtimeService.createExecutionQuery().variableValueGreaterThan("dateVar", nextYear.getTime()).count());
    assertEquals(3, runtimeService.createExecutionQuery().variableValueGreaterThan("dateVar", oneYearAgo.getTime()).count());

    // Test GREATER_THAN_OR_EQUAL
    execution = runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("dateVar", nextMonth.getTime()).singleResult();
    assertNotNull(execution);
    assertEquals(processInstance3.getId(), execution.getId());

    execution = runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("dateVar", nextYear.getTime()).singleResult();
    assertNotNull(execution);
    assertEquals(processInstance3.getId(), execution.getId());

    assertEquals(3, runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("dateVar", oneYearAgo.getTime()).count());

    // Test LESS_THAN
    executions = runtimeService.createExecutionQuery().variableValueLessThan("dateVar", nextYear.getTime()).list();
    assertEquals(2, executions.size());

    List<String> expectedIds = Arrays.asList(processInstance1.getId(), processInstance2.getId());
    List<String> ids = new ArrayList<String>(Arrays.asList(executions.get(0).getId(), executions.get(1).getId()));
    ids.removeAll(expectedIds);
    assertTrue(ids.isEmpty());

    assertEquals(0, runtimeService.createExecutionQuery().variableValueLessThan("dateVar", date1).count());
    assertEquals(3, runtimeService.createExecutionQuery().variableValueLessThan("dateVar", twoYearsLater.getTime()).count());

    // Test LESS_THAN_OR_EQUAL
    executions = runtimeService.createExecutionQuery().variableValueLessThanOrEqual("dateVar", nextYear.getTime()).list();
    assertEquals(3, executions.size());

    assertEquals(0, runtimeService.createExecutionQuery().variableValueLessThanOrEqual("dateVar", oneYearAgo.getTime()).count());

    // Test value-only matching
    execution = runtimeService.createExecutionQuery().variableValueEquals(nextYear.getTime()).singleResult();
    assertNotNull(execution);
    assertEquals(processInstance3.getId(), execution.getId());

    executions = runtimeService.createExecutionQuery().variableValueEquals(date1).list();
    assertEquals(2, executions.size());
    expectedIds = Arrays.asList(processInstance1.getId(), processInstance2.getId());
    ids = new ArrayList<String>(Arrays.asList(executions.get(0).getId(), executions.get(1).getId()));
    ids.removeAll(expectedIds);
    assertTrue(ids.isEmpty());

    execution = runtimeService.createExecutionQuery().variableValueEquals(twoYearsLater.getTime()).singleResult();
    assertNull(execution);

    runtimeService.deleteProcessInstance(processInstance1.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance2.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance3.getId(), "test");
  }
}
