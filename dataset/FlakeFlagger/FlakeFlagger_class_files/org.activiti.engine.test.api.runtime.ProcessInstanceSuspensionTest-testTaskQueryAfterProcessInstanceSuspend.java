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
package org.activiti.engine.test.api.runtime;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;


public class ProcessInstanceSuspensionTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTaskQueryAfterProcessInstanceSuspend(){ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());Task task=taskService.createTaskQuery().singleResult();assertNotNull(task);task=taskService.createTaskQuery().active().singleResult();assertNotNull(task);runtimeService.suspendProcessInstanceById(processInstance.getId());assertEquals(1,taskService.createTaskQuery().count());assertEquals(1,taskService.createTaskQuery().suspended().count());assertEquals(0,taskService.createTaskQuery().active().count());runtimeService.activateProcessInstanceById(processInstance.getId());assertEquals(1,taskService.createTaskQuery().count());assertEquals(0,taskService.createTaskQuery().suspended().count());assertEquals(1,taskService.createTaskQuery().active().count());task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());assertEquals(0,runtimeService.createProcessInstanceQuery().count());}

}
