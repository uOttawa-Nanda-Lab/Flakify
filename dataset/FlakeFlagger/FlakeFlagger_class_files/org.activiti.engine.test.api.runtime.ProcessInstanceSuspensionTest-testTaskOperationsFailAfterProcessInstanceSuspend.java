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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTaskOperationsFailAfterProcessInstanceSuspend(){ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());final Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);runtimeService.suspendProcessInstanceById(processInstance.getId());try {taskService.complete(task.getId());fail("It is not allowed to complete a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.claim(task.getId(),"jos");fail("It is not allowed to claim a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.setVariable(task.getId(),"someVar","someValue");fail("It is not allowed to set a variable on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.setVariableLocal(task.getId(),"someVar","someValue");fail("It is not allowed to set a variable on a task of a suspended process instance");} catch (ActivitiException e){}try {HashMap<String, String> variables=new HashMap<String, String>();variables.put("varOne","one");variables.put("varTwo","two");taskService.setVariables(task.getId(),variables);fail("It is not allowed to set variables on a task of a suspended process instance");} catch (ActivitiException e){}try {HashMap<String, String> variables=new HashMap<String, String>();variables.put("varOne","one");variables.put("varTwo","two");taskService.setVariablesLocal(task.getId(),variables);fail("It is not allowed to set variables on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.removeVariable(task.getId(),"someVar");fail("It is not allowed to remove a variable on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.removeVariableLocal(task.getId(),"someVar");fail("It is not allowed to remove a variable on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.removeVariables(task.getId(),Arrays.asList("one","two"));fail("It is not allowed to remove variables on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.removeVariablesLocal(task.getId(),Arrays.asList("one","two"));fail("It is not allowed to remove variables on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.addCandidateGroup(task.getId(),"blahGroup");fail("It is not allowed to add a candidate group on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.addCandidateUser(task.getId(),"blahUser");fail("It is not allowed to add a candidate user on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.addGroupIdentityLink(task.getId(),"blahGroup",IdentityLinkType.CANDIDATE);fail("It is not allowed to add a candidate user on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.addUserIdentityLink(task.getId(),"blahUser",IdentityLinkType.OWNER);fail("It is not allowed to add an identityLink on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.addComment(task.getId(),processInstance.getId(),"test comment");fail("It is not allowed to add a comment on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.createAttachment("text",task.getId(),processInstance.getId(),"testName","testDescription","http://test.com");fail("It is not allowed to add an attachment on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.setAssignee(task.getId(),"mispiggy");fail("It is not allowed to set an assignee on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.setOwner(task.getId(),"kermit");fail("It is not allowed to set an owner on a task of a suspended process instance");} catch (ActivitiException e){}try {taskService.setPriority(task.getId(),99);fail("It is not allowed to set a priority on a task of a suspended process instance");} catch (ActivitiException e){}}

}
