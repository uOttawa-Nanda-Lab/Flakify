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

package org.activiti.engine.test.bpmn.gateway;

import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;

/**

 */
public class ParallelGatewayTest extends PluggableActivitiTestCase {

  /**
 * https://activiti.atlassian.net/browse/ACT-1222
 */@Deployment public void testReceyclingExecutionWithCallActivity(){runtimeService.startProcessInstanceByKey("parent-process");TaskQuery query=taskService.createTaskQuery().orderByTaskName().asc();List<Task> tasks=query.list();assertEquals(2,tasks.size());assertEquals("Another task",tasks.get(0).getName());assertEquals("Some Task",tasks.get(1).getName());taskService.complete(tasks.get(1).getId());tasks=query.list();assertEquals(1,tasks.size());assertEquals("Another task",tasks.get(0).getName());taskService.complete(tasks.get(0).getId());assertEquals(0,taskService.createTaskQuery().count());}

  /*
   * @Deployment public void testAsyncBehavior() { for (int i = 0; i < 100; i++) { ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("async"); } assertEquals(200,
   * managementService.createJobQuery().count()); waitForJobExecutorToProcessAllJobs(120000, 5000); assertEquals(0, managementService.createJobQuery().count()); assertEquals(0,
   * runtimeService.createProcessInstanceQuery().count()); }
   */

}
