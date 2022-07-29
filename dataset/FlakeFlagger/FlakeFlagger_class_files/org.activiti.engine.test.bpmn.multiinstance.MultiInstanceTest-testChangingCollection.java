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

package org.activiti.engine.test.bpmn.multiinstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.assertj.core.api.Assertions;

/**


 */
public class MultiInstanceTest extends PluggableActivitiTestCase {

  public static final String NR_OF_INSTANCES_KEY = "nrOfInstances";
  public static final String NR_OF_ACTIVE_INSTANCES_KEY = "nrOfActiveInstances";
  public static final String NR_OF_COMPLETED_INSTANCES_KEY = "nrOfCompletedInstances";
  public static final String NR_OF_LOOPS_KEY = "nrOfLoops";
  public static final String LOOP_COUNTER_KEY = "loopCounter";

  private void checkSequentialUserTasks(String processDefinitionKey, String elementIndexVariable) {
    int nrOfLoops = 3;
    String procId = runtimeService.startProcessInstanceByKey(processDefinitionKey, CollectionUtil.singletonMap(NR_OF_LOOPS_KEY, nrOfLoops)).getId();

    Execution outerInstance = retrieveOuterExecution(procId);

    checkAndCompleteTask("kermit_0", 0, nrOfLoops, elementIndexVariable, outerInstance);
    checkAndCompleteTask("kermit_1", 1, nrOfLoops, elementIndexVariable, outerInstance);
    checkAndCompleteTask("kermit_2", 2, nrOfLoops, elementIndexVariable, outerInstance);
    assertNull(taskService.createTaskQuery().singleResult());
    assertProcessEnded(procId);
  }

  private Execution retrieveOuterExecution(String procId) {
    List<Execution> executions = runtimeService.createExecutionQuery().parentId(procId).list();
    Assertions.assertThat(executions).hasSize(1);
    Execution outerInstance = executions.get(0);
    Assertions.assertThat(outerInstance.getActivityId()).isEqualTo("miTasks");
    return outerInstance;
  }

  private void checkAndCompleteTask(String expectedAssignee, int expectedLoopCounter, int nrOfLoops, String elementIndexVariable, Execution outerInstance) {
    Task task = taskService.createTaskQuery().singleResult();
    Assertions.assertThat(task.getName()).isEqualTo("My Task");
    Assertions.assertThat(task.getAssignee()).isEqualTo(expectedAssignee);

    checkInnerInstanceVariables(task, expectedLoopCounter, elementIndexVariable);
    checkOuterInstanceVariables(outerInstance, expectedLoopCounter, nrOfLoops, elementIndexVariable);

    taskService.complete(task.getId());
  }

  private void checkOuterInstanceVariables(Execution outerInstance, int loopCounter, int nrOfLoops, String elementIndexVariable) {
    Map<String, Object> localVariables = runtimeService.getVariablesLocal(outerInstance.getId());
    // this variable should be available only in the inner instance: see BPMN specification table 10.30, page 194
    Assertions.assertThat(localVariables).doesNotContainKey(elementIndexVariable);

    Assertions.assertThat(localVariables).containsKeys(NR_OF_INSTANCES_KEY, NR_OF_ACTIVE_INSTANCES_KEY, NR_OF_COMPLETED_INSTANCES_KEY);
    Assertions.assertThat(localVariables.get(NR_OF_INSTANCES_KEY)).isEqualTo(nrOfLoops);
    Assertions.assertThat(localVariables.get(NR_OF_ACTIVE_INSTANCES_KEY)).isEqualTo(1);
    Assertions.assertThat(localVariables.get(NR_OF_COMPLETED_INSTANCES_KEY)).isEqualTo(loopCounter);
  }

  private void checkInnerInstanceVariables(Task task, int loopCounter, String elementIndexVariable) {
    Map<String, Object> localVariables = runtimeService.getVariablesLocal(task.getExecutionId());
    // these variables should be available only in the outer instance: see BPMN specification table 10.30, page 194
    Assertions.assertThat(localVariables).doesNotContainKeys(NR_OF_INSTANCES_KEY, NR_OF_ACTIVE_INSTANCES_KEY, NR_OF_COMPLETED_INSTANCES_KEY);

    Assertions.assertThat(localVariables).containsKey(elementIndexVariable);
    Assertions.assertThat(localVariables.get(elementIndexVariable)).isEqualTo(loopCounter);
  }

  private void checkBuiltInOuterVariables(Execution outerExecution, int expetedActiveNumber, int expectedCompletedNumber) {
    Map<String, Object> variables = runtimeService.getVariablesLocal(outerExecution.getId());
    Assertions.assertThat(variables).containsEntry(NR_OF_INSTANCES_KEY, 3);
    Assertions.assertThat(variables).containsEntry(NR_OF_ACTIVE_INSTANCES_KEY, expetedActiveNumber);
    Assertions.assertThat(variables).containsEntry(NR_OF_COMPLETED_INSTANCES_KEY, expectedCompletedNumber);
    Assertions.assertThat(variables).doesNotContainKey(LOOP_COUNTER_KEY);
  }

  private void checkParallelUserTasksCustomExtensions(String processDefinitionKey) {
    Map<String, Object> vars = new HashMap<String, Object>();
    List<String> assigneeList = Arrays.asList("kermit", "gonzo", "fozzie");
    vars.put("assigneeList", assigneeList);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, vars);

    List<Task> tasks = taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals(3, tasks.size());
    assertEquals("My Task 0", tasks.get(0).getName());
    assertEquals("My Task 1", tasks.get(1).getName());
    assertEquals("My Task 2", tasks.get(2).getName());

    tasks = taskService.createTaskQuery().orderByTaskAssignee().asc().list();
    assertEquals("fozzie", tasks.get(0).getAssignee());
    assertEquals("gonzo", tasks.get(1).getAssignee());
    assertEquals("kermit", tasks.get(2).getAssignee());

    // Completing 3 tasks will trigger completion condition
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
    taskService.complete(tasks.get(2).getId());
    assertEquals(0, taskService.createTaskQuery().count());
    assertProcessEnded(processInstance.getProcessInstanceId());
  }

  @Deployment public void testChangingCollection(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("multi_users",Arrays.asList("testuser"));ProcessInstance instance=runtimeService.startProcessInstanceByKey("test_multi",vars);assertNotNull(instance);Task task=taskService.createTaskQuery().singleResult();assertEquals("multi",task.getTaskDefinitionKey());vars.put("multi_users",new ArrayList<String>());taskService.complete(task.getId(),vars);List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().list();assertEquals(0,instances.size());}
  
  protected void resetTestCounts() {
  	TestStartExecutionListener.countWithLoopCounter.set(0);
  	TestStartExecutionListener.countWithoutLoopCounter.set(0);
  	TestEndExecutionListener.countWithLoopCounter.set(0);
  	TestEndExecutionListener.countWithoutLoopCounter.set(0);
  	TestTaskCompletionListener.count.set(0);
  }
  
  public static class TestStartExecutionListener implements ExecutionListener {
  	
  	public static AtomicInteger countWithLoopCounter = new AtomicInteger(0);
  	public static AtomicInteger countWithoutLoopCounter = new AtomicInteger(0);

  	@Override
  	public void notify(DelegateExecution execution) {
  		Integer loopCounter = (Integer) execution.getVariable(LOOP_COUNTER_KEY);
  		if (loopCounter != null) {
  			countWithLoopCounter.incrementAndGet();
  		} else {
  			countWithoutLoopCounter.incrementAndGet();
  		}
  	}

  }
  
  public static class TestEndExecutionListener implements ExecutionListener {
  	
  	public static AtomicInteger countWithLoopCounter = new AtomicInteger(0);
  	public static AtomicInteger countWithoutLoopCounter = new AtomicInteger(0);

  	@Override
  	public void notify(DelegateExecution execution) {
  		Integer loopCounter = (Integer) execution.getVariable(LOOP_COUNTER_KEY);
  		if (loopCounter != null) {
  			countWithLoopCounter.incrementAndGet();
  		} else{
  			countWithoutLoopCounter.incrementAndGet();
  		}
  	}

  }
  
  public static class TestTaskCompletionListener implements TaskListener {
  	
  	public static AtomicInteger count = new AtomicInteger(0);
  	
  	@Override
  	public void notify(DelegateTask delegateTask) {
  		count.incrementAndGet();
  	}
  	
  }


}
