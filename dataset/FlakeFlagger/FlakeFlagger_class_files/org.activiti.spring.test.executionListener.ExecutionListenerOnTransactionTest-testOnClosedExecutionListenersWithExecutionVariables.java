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
  public void testOnClosedExecutionListenersWithExecutionVariables() {

    CurrentActivityTransactionDependentExecutionListener.clear();

    runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");

    List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities = CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
    assertEquals(3, currentActivities.size());

    assertEquals("serviceTask1", currentActivities.get(0).getActivityId());
    assertEquals("Service Task 1", currentActivities.get(0).getActivityName());
    assertEquals(0, currentActivities.get(0).getExecutionVariables().size());

    assertEquals("serviceTask2", currentActivities.get(1).getActivityId());
    assertEquals("Service Task 2", currentActivities.get(1).getActivityName());
    assertEquals(1, currentActivities.get(1).getExecutionVariables().size());
    assertEquals("test1", currentActivities.get(1).getExecutionVariables().get("injectedExecutionVariable"));

    assertEquals("serviceTask3", currentActivities.get(2).getActivityId());
    assertEquals("Service Task 3", currentActivities.get(2).getActivityName());
    assertEquals(1, currentActivities.get(2).getExecutionVariables().size());
    assertEquals("test2", currentActivities.get(2).getExecutionVariables().get("injectedExecutionVariable"));
  }

}
