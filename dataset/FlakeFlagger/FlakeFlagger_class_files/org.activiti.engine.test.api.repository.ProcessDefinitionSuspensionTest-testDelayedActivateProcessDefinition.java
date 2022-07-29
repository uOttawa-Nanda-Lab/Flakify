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
package org.activiti.engine.test.api.repository;

import java.util.Date;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class ProcessDefinitionSuspensionTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testDelayedActivateProcessDefinition(){Date startTime=new Date();processEngineConfiguration.getClock().setCurrentTime(startTime);ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();repositoryService.suspendProcessDefinitionById(processDefinition.getId());try {runtimeService.startProcessInstanceById(processDefinition.getId());fail();} catch (ActivitiException e){assertTextPresentIgnoreCase("suspended",e.getMessage());}assertEquals(0,runtimeService.createProcessInstanceQuery().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(1,repositoryService.createProcessDefinitionQuery().suspended().count());long oneDayFromStart=startTime.getTime() + (24 * 60 * 60 * 1000);repositoryService.activateProcessDefinitionById(processDefinition.getId(),false,new Date(oneDayFromStart));long twoDaysFromStart=startTime.getTime() + (2 * 24 * 60 * 60 * 1000);processEngineConfiguration.getClock().setCurrentTime(new Date(twoDaysFromStart));waitForJobExecutorToProcessAllJobs(5000L,50L);runtimeService.startProcessInstanceById(processDefinition.getId());assertEquals(1,runtimeService.createProcessInstanceQuery().count());assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());}

}
