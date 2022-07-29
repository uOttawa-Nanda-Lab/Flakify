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

package org.activiti.engine.test.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;


/**

 */
public class DynamicUserTaskTest extends PluggableActivitiTestCase {
  
  @Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.assignment.bpmn20.xml"}) public void testChangeOwner(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");String processDefinitionId=processInstance.getProcessDefinitionId();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("ownerTest",task.getOwner());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());ObjectNode infoNode=dynamicBpmnService.changeUserTaskOwner("task1","ownerTest2");dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("ownerTest2",task.getOwner());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());}

}