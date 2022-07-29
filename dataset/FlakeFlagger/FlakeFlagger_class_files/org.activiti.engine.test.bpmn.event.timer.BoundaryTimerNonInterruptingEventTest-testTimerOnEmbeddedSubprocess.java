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

  @Deployment public void testTimerOnEmbeddedSubprocess(){String id=runtimeService.startProcessInstanceByKey("nonInterruptingTimerOnEmbeddedSubprocess").getId();TaskQuery tq=taskService.createTaskQuery().taskAssignee("kermit");assertEquals(1,tq.count());Job timer=managementService.createTimerJobQuery().singleResult();managementService.moveTimerToExecutableJob(timer.getId());managementService.executeJob(timer.getId());tq=taskService.createTaskQuery().taskAssignee("kermit");assertEquals(2,tq.count());List<Task> tasks=tq.list();taskService.complete(tasks.get(0).getId());taskService.complete(tasks.get(1).getId());assertProcessEnded(id);}

  private void moveByMinutes(int minutes) throws Exception {
    processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + ((minutes * 60 * 1000))));
  }

}
