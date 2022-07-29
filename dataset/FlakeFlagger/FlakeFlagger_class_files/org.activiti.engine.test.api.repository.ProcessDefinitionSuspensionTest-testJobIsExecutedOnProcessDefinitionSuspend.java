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

  @Deployment public void testJobIsExecutedOnProcessDefinitionSuspend(){Date now=new Date();processEngineConfiguration.getClock().setCurrentTime(now);ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();runtimeService.startProcessInstanceById(processDefinition.getId());repositoryService.suspendProcessDefinitionById(processDefinition.getId());assertEquals(1,managementService.createTimerJobQuery().count());processEngineConfiguration.getClock().setCurrentTime(new Date(now.getTime() + (60 * 60 * 1000)));waitForJobExecutorToProcessAllJobs(2000L,100L);assertEquals(0,managementService.createJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().count());}

}
