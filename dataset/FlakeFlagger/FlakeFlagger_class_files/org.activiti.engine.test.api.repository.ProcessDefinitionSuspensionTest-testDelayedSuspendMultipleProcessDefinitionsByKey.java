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

  public void testDelayedSuspendMultipleProcessDefinitionsByKey(){Date startTime=new Date();processEngineConfiguration.getClock().setCurrentTime(startTime);final long hourInMs=60 * 60 * 1000;int nrOfProcessDefinitions=5;for (int i=0;i < nrOfProcessDefinitions;i++){repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml").deploy();}assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());runtimeService.startProcessInstanceByKey("oneTaskProcess");repositoryService.suspendProcessDefinitionByKey("oneTaskProcess",true,new Date(startTime.getTime() + (2 * hourInMs)));assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());assertEquals(1,runtimeService.createProcessInstanceQuery().active().count());assertEquals(nrOfProcessDefinitions,managementService.createTimerJobQuery().count());for (ProcessDefinition processDefinition:repositoryService.createProcessDefinitionQuery().list()){assertEquals(1,managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count());}processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (3 * hourInMs)));waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(30000L,100L);assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().suspended().count());assertEquals(1,runtimeService.createProcessInstanceQuery().suspended().count());repositoryService.activateProcessDefinitionByKey("oneTaskProcess",true,new Date(startTime.getTime() + (5 * hourInMs)));assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().suspended().count());assertEquals(1,runtimeService.createProcessInstanceQuery().suspended().count());processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (6 * hourInMs)));waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(60000L,100L);assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().active().count());assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());assertEquals(1,runtimeService.createProcessInstanceQuery().active().count());for (org.activiti.engine.repository.Deployment deployment:repositoryService.createDeploymentQuery().list()){repositoryService.deleteDeployment(deployment.getId(),true);}}

}
