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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;


public class HistoricVariableInstanceTest extends PluggableActivitiTestCase {

  @Deployment(resources = { "org/activiti/examples/bpmn/callactivity/orderProcess.bpmn20.xml", "org/activiti/examples/bpmn/callactivity/checkCreditProcess.bpmn20.xml" })
  public void testOrderProcessWithCallActivity() {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
      // After the process has started, the 'verify credit history' task should be active
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("orderProcess");
      TaskQuery taskQuery = taskService.createTaskQuery();
      Task verifyCreditTask = taskQuery.singleResult();
      assertEquals("Verify credit history", verifyCreditTask.getName());

      // Verify with Query API
      ProcessInstance subProcessInstance = runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
      assertNotNull(subProcessInstance);
      assertEquals(pi.getId(), runtimeService.createProcessInstanceQuery().subProcessInstanceId(subProcessInstance.getId()).singleResult().getId());

      // Completing the task with approval, will end the subprocess and continue the original process
      taskService.complete(verifyCreditTask.getId(), CollectionUtil.singletonMap("creditApproved", true));
      Task prepareAndShipTask = taskQuery.singleResult();
      assertEquals("Prepare and Ship", prepareAndShipTask.getName());
    }
  }

  private HistoricVariableInstance getHistoricVariable(String variableName) {
    return historyService.createHistoricVariableInstanceQuery().variableName(variableName).singleResult();
  }
}
