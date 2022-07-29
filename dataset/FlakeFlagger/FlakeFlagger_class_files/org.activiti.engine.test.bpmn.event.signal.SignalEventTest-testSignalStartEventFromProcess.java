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

package org.activiti.engine.test.bpmn.event.signal;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.validation.validator.Problems;

/**

 */
public class SignalEventTest extends PluggableActivitiTestCase {

  private EventSubscriptionQueryImpl createEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }

  public void testSignalStartEventFromProcess(){repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEvent.bpmn20.xml").deploy();runtimeService.startProcessInstanceByKey("processWithSignalThrow");assertEquals(3,runtimeService.createProcessInstanceQuery().count());assertEquals(3,taskService.createTaskQuery().count());List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();List<String> names=Arrays.asList("A","B","C");for (int i=0;i < tasks.size();i++){assertEquals("Task in process " + names.get(i),tasks.get(i).getName());}runtimeService.startProcessInstanceByKey("processWithSignalCatch");assertEquals(4,runtimeService.createProcessInstanceQuery().count());assertEquals(4,taskService.createTaskQuery().count());assertEquals(1,taskService.createTaskQuery().taskName("Task in process D").count());runtimeService.startProcessInstanceByKey("processWithSignalThrow");assertEquals(7,runtimeService.createProcessInstanceQuery().count());assertEquals(7,taskService.createTaskQuery().count());assertEquals(1,taskService.createTaskQuery().taskName("Task after signal").count());for (org.activiti.engine.repository.Deployment deployment:repositoryService.createDeploymentQuery().list()){repositoryService.deleteDeployment(deployment.getId(),true);}}

  private void validateTaskCounts(long taskACount, long taskBCount, long taskCCount) {
    assertEquals(taskACount, taskService.createTaskQuery().taskName("Task A").count());
    assertEquals(taskBCount, taskService.createTaskQuery().taskName("Task B").count());
    assertEquals(taskCCount, taskService.createTaskQuery().taskName("Task C").count());
  }

}
