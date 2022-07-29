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

package org.activiti.engine.test.bpmn.subprocess;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**


 */
public class SubProcessTest extends PluggableActivitiTestCase {

  @Deployment public void testSimpleParallelSubProcessWithTimer(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleParallelSubProcessWithTimer");List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();Task taskA=subProcessTasks.get(0);Task taskB=subProcessTasks.get(1);assertEquals("Task A",taskA.getName());assertEquals("Task B",taskB.getName());Job job=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();managementService.moveTimerToExecutableJob(job.getId());managementService.executeJob(job.getId());Task taskAfterTimer=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("Task after timer",taskAfterTimer.getName());taskService.complete(taskAfterTimer.getId());assertProcessEnded(processInstance.getId());}
}
