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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryInvalidTypes() throws Exception{Map<String, Object> vars=new HashMap<String, Object>();vars.put("bytesVar","test".getBytes());vars.put("serializableVar",new DummySerializable());ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);try {runtimeService.createExecutionQuery().variableValueEquals("bytesVar","test".getBytes()).list();fail("Expected exception");} catch (ActivitiIllegalArgumentException ae){assertTextPresent("Variables of type ByteArray cannot be used to query",ae.getMessage());}try {runtimeService.createExecutionQuery().variableValueEquals("serializableVar",new DummySerializable()).list();fail("Expected exception");} catch (ActivitiIllegalArgumentException ae){assertTextPresent("Variables of type ByteArray cannot be used to query",ae.getMessage());}runtimeService.deleteProcessInstance(processInstance.getId(),"test");}
}
