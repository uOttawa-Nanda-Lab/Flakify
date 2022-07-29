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

  @Deployment(resources = { "org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml" })
  public void testQueryNullVariable() throws Exception {
    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("nullVar", null);
    ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    vars = new HashMap<String, Object>();
    vars.put("nullVar", "notnull");
    ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    vars = new HashMap<String, Object>();
    vars.put("nullVarLong", "notnull");
    ProcessInstance processInstance3 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    vars = new HashMap<String, Object>();
    vars.put("nullVarDouble", "notnull");
    ProcessInstance processInstance4 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    vars = new HashMap<String, Object>();
    vars.put("nullVarByte", "testbytes".getBytes());
    ProcessInstance processInstance5 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    // Query on null value, should return one value
    ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().variableValueEquals("nullVar", null);
    List<ProcessInstance> processInstances = query.list();
    assertNotNull(processInstances);
    assertEquals(1, processInstances.size());
    assertEquals(processInstance1.getId(), processInstances.get(0).getId());

    // Test NOT_EQUALS null
    assertEquals(1, runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVar", null).count());
    assertEquals(1, runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarLong", null).count());
    assertEquals(1, runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarDouble", null).count());
    // When a byte-array reference is present, the variable is not considered
    // null
    assertEquals(1, runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarByte", null).count());

    // Test value-only
    assertEquals(1, runtimeService.createProcessInstanceQuery().variableValueEquals(null).count());

    // All other variable queries with null should throw exception
    try {
      runtimeService.createProcessInstanceQuery().variableValueGreaterThan("nullVar", null);
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertTextPresent("Booleans and null cannot be used in 'greater than' condition", ae.getMessage());
    }

    try {
      runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("nullVar", null);
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertTextPresent("Booleans and null cannot be used in 'greater than or equal' condition", ae.getMessage());
    }

    try {
      runtimeService.createProcessInstanceQuery().variableValueLessThan("nullVar", null);
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertTextPresent("Booleans and null cannot be used in 'less than' condition", ae.getMessage());
    }

    try {
      runtimeService.createProcessInstanceQuery().variableValueLessThanOrEqual("nullVar", null);
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertTextPresent("Booleans and null cannot be used in 'less than or equal' condition", ae.getMessage());
    }

    try {
      runtimeService.createProcessInstanceQuery().variableValueLike("nullVar", null);
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertTextPresent("Only string values can be used with 'like' condition", ae.getMessage());
    }

    runtimeService.deleteProcessInstance(processInstance1.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance2.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance3.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance4.getId(), "test");
    runtimeService.deleteProcessInstance(processInstance5.getId(), "test");

    // Test value-only, no more null-variables exist
    assertEquals(0, runtimeService.createProcessInstanceQuery().variableValueEquals(null).count());
  }
}
