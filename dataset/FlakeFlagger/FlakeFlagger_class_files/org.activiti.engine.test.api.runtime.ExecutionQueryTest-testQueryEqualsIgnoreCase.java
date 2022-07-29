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
  public void testQueryEqualsIgnoreCase() {
    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("mixed", "AbCdEfG");
    vars.put("lower", "ABCDEFG");
    vars.put("upper", "abcdefg");
    ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("oneTaskProcess", vars);

    Execution execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("mixed", "abcdefg").singleResult();
    assertNotNull(execution);
    assertEquals(processInstance1.getId(), execution.getId());

    execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("lower", "abcdefg").singleResult();
    assertNotNull(execution);
    assertEquals(processInstance1.getId(), execution.getId());

    execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper", "abcdefg").singleResult();
    assertNotNull(execution);
    assertEquals(processInstance1.getId(), execution.getId());

    // Pass in non-lower-case string
    execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper", "ABCdefg").singleResult();
    assertNotNull(execution);
    assertEquals(processInstance1.getId(), execution.getId());

    // Pass in null-value, should cause exception
    try {
      execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper", null).singleResult();
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertEquals("value is null", ae.getMessage());
    }

    // Pass in null name, should cause exception
    try {
      execution = runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase(null, "abcdefg").singleResult();
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertEquals("name is null", ae.getMessage());
    }

    // Test NOT equals
    execution = runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper", "UIOP").singleResult();
    assertNotNull(execution);

    // Should return result when using "ABCdefg" case-insensitive while
    // normal not-equals won't
    execution = runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper", "ABCdefg").singleResult();
    assertNull(execution);
    execution = runtimeService.createExecutionQuery().variableValueNotEquals("upper", "ABCdefg").singleResult();
    assertNotNull(execution);

    // Pass in null-value, should cause exception
    try {
      execution = runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper", null).singleResult();
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertEquals("value is null", ae.getMessage());
    }

    // Pass in null name, should cause exception
    try {
      execution = runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase(null, "abcdefg").singleResult();
      fail("Exception expected");
    } catch (ActivitiIllegalArgumentException ae) {
      assertEquals("name is null", ae.getMessage());
    }
  }
}
