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

package org.activiti.engine.test.bpmn.callactivity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**




 */
public class CallActivityAdvancedTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallParallelSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleParallelSubProcess.bpmn20.xml"}) public void testCallParallelSubProcess(){runtimeService.startProcessInstanceByKey("callParallelSubProcess");TaskQuery taskQuery=taskService.createTaskQuery().orderByTaskName().asc();List<Task> tasks=taskQuery.list();assertEquals(2,tasks.size());Task taskA=tasks.get(0);Task taskB=tasks.get(1);assertEquals("Task A",taskA.getName());assertEquals("Task B",taskB.getName());taskService.complete(taskA.getId());assertEquals(1,taskQuery.list().size());taskService.complete(taskB.getId());assertEquals(0,runtimeService.createExecutionQuery().count());}

}
