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
  
  @Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateMultiInstanceEndEventTest.testTerminateMiCallactivity-parentProcess.bpmn20.xml","org/activiti/engine/test/bpmn/event/end/TerminateMultiInstanceEndEventTest.testTerminateMiCallactivity-calledProcess.bpmn20.xml"}) public void testTerminateMiCallactivity(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMiCallActivity");Task taskA=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("A",taskA.getName());taskService.complete(taskA.getId());List<Task> bTasks=taskService.createTaskQuery().taskName("B").list();assertEquals(4,bTasks.size());for (int i=0;i < 3;i++){taskService.complete(bTasks.get(i).getId());}List<Task> cTasks=taskService.createTaskQuery().taskName("C").list();assertEquals(3,cTasks.size());List<Task> dTasks=taskService.createTaskQuery().taskName("D").list();assertEquals(3,dTasks.size());taskService.complete(cTasks.get(0).getId());List<Task> afterMiTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();assertEquals(2,afterMiTasks.size());assertEquals("AfterMi",afterMiTasks.get(0).getName());assertEquals("Parallel task",afterMiTasks.get(1).getName());}
  
}
