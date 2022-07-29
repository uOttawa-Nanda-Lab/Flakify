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

import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class HistoricActivityInstanceTest extends PluggableActivitiTestCase {

  @Deployment
  public void testLoop() {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("historic-activity-loops", CollectionUtil.singletonMap("input", 0));
    
    // completing 10 user tasks
    // 15 service tasks should have passed
    
    for (int i=0; i<10; i++) {
      Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
      Number inputNumber = (Number) taskService.getVariable(task.getId(), "input");
      int input = inputNumber.intValue();
      assertEquals(i, input);
      taskService.complete(task.getId(), CollectionUtil.singletonMap("input", input + 1));
      task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    }
    
    // Verify history
    List<HistoricActivityInstance> taskActivityInstances = historyService.createHistoricActivityInstanceQuery().activityType("userTask").list();
    assertEquals(10, taskActivityInstances.size());
    for (HistoricActivityInstance historicActivityInstance : taskActivityInstances) {
      assertNotNull(historicActivityInstance.getStartTime());
      assertNotNull(historicActivityInstance.getEndTime());
    }
    
    List<HistoricActivityInstance> serviceTaskInstances = historyService.createHistoricActivityInstanceQuery().activityType("serviceTask").list();
    assertEquals(15, serviceTaskInstances.size());
    for (HistoricActivityInstance historicActivityInstance : serviceTaskInstances) {
      assertNotNull(historicActivityInstance.getStartTime());
      assertNotNull(historicActivityInstance.getEndTime());
    }
  }

}
