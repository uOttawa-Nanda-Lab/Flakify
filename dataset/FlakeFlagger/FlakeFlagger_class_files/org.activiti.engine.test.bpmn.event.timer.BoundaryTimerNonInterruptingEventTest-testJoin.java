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

package org.activiti.engine.test.bpmn.event.timer;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**

 */
public class BoundaryTimerNonInterruptingEventTest extends PluggableActivitiTestCase {

  @Deployment public void testJoin(){Date startTime=new Date();ProcessInstance pi=runtimeService.startProcessInstanceByKey("testJoin");Task task1=taskService.createTaskQuery().singleResult();assertEquals("Main Task",task1.getName());TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());List<Job> jobs=jobQuery.list();assertEquals(1,jobs.size());processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((60 * 60 * 1000) + 5000)));waitForJobExecutorToProcessAllJobs(5000L,25L);assertEquals(0L,jobQuery.count());assertEquals(2L,taskService.createTaskQuery().count());taskService.complete(task1.getId());assertEquals(1L,taskService.createTaskQuery().count());Task task2=taskService.createTaskQuery().singleResult();assertEquals("Escalation Task",task2.getName());taskService.complete(task2.getId());assertProcessEnded(pi.getId());}

  private void moveByMinutes(int minutes) throws Exception {
    processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + ((minutes * 60 * 1000))));
  }

}
