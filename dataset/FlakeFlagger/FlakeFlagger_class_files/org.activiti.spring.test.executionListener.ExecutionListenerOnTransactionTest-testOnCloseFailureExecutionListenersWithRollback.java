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
package org.activiti.spring.test.executionListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

/**

 */
@ContextConfiguration("classpath:org/activiti/spring/test/executionListener/TransactionDependentListenerTest-context.xml")
public class ExecutionListenerOnTransactionTest extends SpringActivitiTestCase {

  @Deployment
  public void testOnCloseFailureExecutionListenersWithRollback() {

    CurrentActivityTransactionDependentExecutionListener.clear();

    Map<String, Object> variables = new HashMap<>();
    variables.put("serviceTask1", false);
    variables.put("serviceTask2", false);
    variables.put("serviceTask3", true);

    processEngineConfiguration.setAsyncExecutorActivate(false);

    runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess", variables);

    // execute the only job that should be there 1 time
    try {
      managementService.executeJob(managementService.createJobQuery().singleResult().getId());
    } catch (Exception ex) {
      // expected; serviceTask3 throws exception
    }

    List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities = CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
    assertEquals(2, currentActivities.size());

    // the before commit listener
    assertEquals("serviceTask1", currentActivities.get(0).getActivityId());
    assertEquals("Service Task 1", currentActivities.get(0).getActivityName());

    // the before rolled-back listener
    assertEquals("serviceTask3", currentActivities.get(1).getActivityId());
    assertEquals("Service Task 3", currentActivities.get(1).getActivityName());
  }

}
