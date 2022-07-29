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

  @Deployment public void testSimpleParallelSubProcess(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleParallelSubProcess");List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc().list();Task taskA=subProcessTasks.get(0);Task taskB=subProcessTasks.get(1);assertEquals("Task A",taskA.getName());assertEquals("Task B",taskB.getName());taskService.complete(taskA.getId());taskService.complete(taskB.getId());Task taskAfterSubProcess=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertEquals("Task after sub process",taskAfterSubProcess.getName());}
}
