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
package org.activiti.engine.test.bpmn.event.end;

import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TerminateMultiInstanceEndEventTest extends PluggableActivitiTestCase {
  
  @Deployment public void testTerminateNestedMiEmbeddedSubprocess(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateNestedMiEmbeddedSubprocess",CollectionUtil.singletonMap("var","notEnd"));List<Task> aTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("A").list();assertEquals(12,aTasks.size());List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();assertEquals(72,bTasks.size());int nrOfBTasksCompleted=3;for (int i=0;i < nrOfBTasksCompleted;i++){taskService.complete(bTasks.get(i).getId());}bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();assertEquals(72 - nrOfBTasksCompleted,bTasks.size());List<Job> timers=managementService.createTimerJobQuery().list();assertEquals(nrOfBTasksCompleted,timers.size());managementService.moveTimerToExecutableJob(timers.get(0).getId());managementService.executeJob(timers.get(0).getId());bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();assertEquals(66,bTasks.size());List<Task> afterInnerMiTasks=taskService.createTaskQuery().taskName("AfterInnerMi").list();assertEquals(1,afterInnerMiTasks.size());for (Task aTask:aTasks){taskService.complete(aTask.getId());}List<Task> nextTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();while (nextTasks != null && nextTasks.size() > 0){taskService.complete(nextTasks.get(0).getId());nextTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();}assertEquals(0,runtimeService.createExecutionQuery().count());}
  
}
