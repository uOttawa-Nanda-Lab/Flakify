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

package org.activiti.examples.bpmn.gateway;

import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**

 */
public class ParallelGatewayTest extends PluggableActivitiTestCase {

  @Deployment public void testForkJoin(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("forkJoin");TaskQuery query=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();List<Task> tasks=query.list();assertEquals(2,tasks.size());Task task1=tasks.get(0);assertEquals("Receive Payment",task1.getName());Task task2=tasks.get(1);assertEquals("Ship Order",task2.getName());taskService.complete(tasks.get(0).getId());taskService.complete(tasks.get(1).getId());tasks=query.list();assertEquals(1,tasks.size());assertEquals("Archive Order",tasks.get(0).getName());}

}
