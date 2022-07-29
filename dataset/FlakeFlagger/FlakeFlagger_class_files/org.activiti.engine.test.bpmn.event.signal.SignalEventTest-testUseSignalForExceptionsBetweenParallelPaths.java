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

  @Deployment public void testUseSignalForExceptionsBetweenParallelPaths(){runtimeService.startProcessInstanceByKey("processWithSignal");Task task=taskService.createTaskQuery().singleResult();assertEquals("Enter developers",task.getName());taskService.complete(task.getId(),CollectionUtil.singletonMap("developers",Arrays.asList("developerOne","developerTwo","developerThree")));assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerOne").singleResult().getName());assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerTwo").singleResult().getName());assertEquals("Develop specifications",taskService.createTaskQuery().taskAssignee("developerThree").singleResult().getName());task=taskService.createTaskQuery().taskAssignee("kermit").singleResult();assertEquals("Negotiate with client",task.getName());taskService.complete(task.getId(),CollectionUtil.singletonMap("negotationFailed",true));assertEquals(0,taskService.createTaskQuery().count());assertEquals(0,runtimeService.createExecutionQuery().count());}

  private void validateTaskCounts(long taskACount, long taskBCount, long taskCCount) {
    assertEquals(taskACount, taskService.createTaskQuery().taskName("Task A").count());
    assertEquals(taskBCount, taskService.createTaskQuery().taskName("Task B").count());
    assertEquals(taskCCount, taskService.createTaskQuery().taskName("Task C").count());
  }

}
