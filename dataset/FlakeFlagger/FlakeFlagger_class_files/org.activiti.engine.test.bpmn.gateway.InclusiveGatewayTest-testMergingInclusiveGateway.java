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
package org.activiti.engine.test.bpmn.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**




 */
public class InclusiveGatewayTest extends PluggableActivitiTestCase {

  private static final String TASK1_NAME = "Task 1";
  private static final String TASK2_NAME = "Task 2";
  private static final String TASK3_NAME = "Task 3";

  private static final String BEAN_TASK1_NAME = "Basic service";
  private static final String BEAN_TASK2_NAME = "Standard service";
  private static final String BEAN_TASK3_NAME = "Gold Member service";

  @Deployment public void testMergingInclusiveGateway(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveGwMerging",CollectionUtil.singletonMap("input",2));assertEquals(1,taskService.createTaskQuery().count());runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");}

  protected List<Execution> getInactiveExecutionsInActivityId(String activityId) {
    List<Execution> result = new ArrayList<Execution>();
    List<Execution> executions = runtimeService.createExecutionQuery().list();
    Iterator<Execution> iterator = executions.iterator();
    while (iterator.hasNext()) {
      Execution execution = iterator.next();
      if (execution.getActivityId() != null 
          && execution.getActivityId().equals(activityId) 
          && !((ExecutionEntity) execution).isActive()) {
        result.add(execution);
      }
    }
    return result;
  }

  /*
   * @Deployment public void testAsyncBehavior() { for (int i = 0; i < 100; i++) { ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("async"); } assertEquals(200,
   * managementService.createJobQuery().count()); waitForJobExecutorToProcessAllJobs(120000, 5000); assertEquals(0, managementService.createJobQuery().count()); assertEquals(0,
   * runtimeService.createProcessInstanceQuery().count()); }
   */

  // /* This test case is related to ACT-1877 */
  //
  // @Deployment(resources={"org/activiti/engine/test/bpmn/gateway/InclusiveGatewayTest.testWithSignalBoundaryEvent.bpmn20.xml"})
  // public void testJoinAfterBoudarySignalEvent() {
  //
  //
  // ProcessInstance processInstanceId =
  // runtimeService.startProcessInstanceByKey("InclusiveGatewayAfterSignalBoundaryEvent");
  //
  // /// Gets the execution waiting for a message notification*/
  // String subcriptedExecutionId =
  // runtimeService.createExecutionQuery().processInstanceId(processInstanceId.getId()).messageEventSubscriptionName("MyMessage").singleResult().getId();
  //
  // /*Notify message received: this makes one execution to go on*/
  // runtimeService.messageEventReceived("MyMessage", subcriptedExecutionId);
  //
  // /*The other execution goes on*/
  // Task userTask =
  // taskService.createTaskQuery().processInstanceId(processInstanceId.getId()).singleResult();
  // assertEquals("There's still an active execution waiting in the first task",
  // "usertask1",userTask.getTaskDefinitionKey());
  //
  // taskService.complete( userTask.getId());
  //
  // /*The two executions become one because of Inclusive Gateway*/
  // /*The process ends*/
  // userTask =
  // taskService.createTaskQuery().processInstanceId(processInstanceId.getId()).singleResult();
  // assertEquals("Only when both executions reach the inclusive gateway, flow arrives to the last user task",
  // "usertask2",userTask.getTaskDefinitionKey());
  // taskService.complete(userTask.getId());
  //
  // long nExecutions =
  // runtimeService.createExecutionQuery().processInstanceId(processInstanceId.getId()).count();
  // assertEquals(0, nExecutions);
  //
  // }
}
