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

  @Deployment(resources="org/activiti/engine/test/bpmn/event/timer/BoundaryTimerNonInterruptingEventTest.testTimerOnConcurrentSubprocess.bpmn20.xml") public void testTimerOnConcurrentSubprocess2(){String procId=runtimeService.startProcessInstanceByKey("testTimerOnConcurrentSubprocess").getId();assertEquals(4,taskService.createTaskQuery().count());Job timer=managementService.createTimerJobQuery().singleResult();managementService.moveTimerToExecutableJob(timer.getId());managementService.executeJob(timer.getId());assertEquals(5,taskService.createTaskQuery().count());Task task=taskService.createTaskQuery().taskDefinitionKey("sub1task1").singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().taskDefinitionKey("sub1task2").singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().taskDefinitionKey("timerFiredTask").singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().taskDefinitionKey("sub2task1").singleResult();taskService.complete(task.getId());task=taskService.createTaskQuery().taskDefinitionKey("sub2task2").singleResult();taskService.complete(task.getId());assertEquals(0,taskService.createTaskQuery().count());assertProcessEnded(procId);}

  private void moveByMinutes(int minutes) throws Exception {
    processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + ((minutes * 60 * 1000))));
  }

}
