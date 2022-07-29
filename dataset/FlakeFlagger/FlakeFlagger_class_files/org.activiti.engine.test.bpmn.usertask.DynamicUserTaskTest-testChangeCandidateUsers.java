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
  
  @Deployment(resources={"org/activiti/engine/test/bpmn/usertask/DynamicUserTaskTest.basictask.bpmn20.xml"}) public void testChangeCandidateUsers(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");String processDefinitionId=processInstance.getProcessDefinitionId();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();List<IdentityLink> taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());boolean candidateUserTestFound=false;for (IdentityLink identityLink:taskIdentityLinks){if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null){if ("test".equals(identityLink.getUserId())){candidateUserTestFound=true;}}}assertFalse(candidateUserTestFound);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());ObjectNode infoNode=dynamicBpmnService.changeUserTaskCandidateUser("task1","test",true);dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());candidateUserTestFound=false;for (IdentityLink identityLink:taskIdentityLinks){if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null){if ("test".equals(identityLink.getUserId())){candidateUserTestFound=true;}}}assertTrue(candidateUserTestFound);taskService.complete(task.getId());infoNode=dynamicBpmnService.getProcessDefinitionInfo(processDefinitionId);dynamicBpmnService.changeUserTaskCandidateUser("task1","test2",false,infoNode);dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();taskIdentityLinks=taskService.getIdentityLinksForTask(task.getId());candidateUserTestFound=false;boolean candidateUserTest2Found=false;for (IdentityLink identityLink:taskIdentityLinks){if (IdentityLinkType.CANDIDATE.equals(identityLink.getType()) && identityLink.getUserId() != null && identityLink.getGroupId() == null){if ("test".equals(identityLink.getUserId())){candidateUserTestFound=true;} else if ("test2".equals(identityLink.getUserId())){candidateUserTest2Found=true;}}}assertTrue(candidateUserTestFound);assertTrue(candidateUserTest2Found);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());}

}