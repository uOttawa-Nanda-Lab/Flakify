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

  /**
 * From https://forums.activiti.org/content/boundary-signal-causes-already-taking-transition
 */@Deployment public void testSignalThrowAndCatchInSameTransaction(){String fileExistsVar="fileexists";FileExistsMock.getInstance().removeFile();ProcessInstance firstProcessInstance=runtimeService.startProcessInstanceByKey("signalBoundaryProcess");assertNotNull(firstProcessInstance);Task firstTask=taskService.createTaskQuery().singleResult();assertEquals("Add a file",firstTask.getName());Map<String, Object> vars=runtimeService.getVariables(firstTask.getExecutionId());assertEquals(false,vars.get(fileExistsVar));ProcessInstance secondProcessInstance=runtimeService.startProcessInstanceByKey("signalBoundaryProcess");assertNotNull(secondProcessInstance);List<Task> tasks=taskService.createTaskQuery().list();assertEquals(2,tasks.size());Task secondTask=taskService.createTaskQuery().processInstanceId(secondProcessInstance.getProcessInstanceId()).singleResult();assertEquals("Add a file",secondTask.getName());vars=runtimeService.getVariables(secondTask.getExecutionId());assertEquals(false,vars.get(fileExistsVar));taskService.claim(firstTask.getId(),"user");FileExistsMock.getInstance().touchFile();taskService.complete(firstTask.getId());List<Task> usingTask=taskService.createTaskQuery().taskName("Use the file").list();assertEquals(1,usingTask.size());}
  
  private void validateTaskCounts(long taskACount, long taskBCount, long taskCCount) {
    assertEquals(taskACount, taskService.createTaskQuery().taskName("Task A").count());
    assertEquals(taskBCount, taskService.createTaskQuery().taskName("Task B").count());
    assertEquals(taskCCount, taskService.createTaskQuery().taskName("Task C").count());
  }

}
