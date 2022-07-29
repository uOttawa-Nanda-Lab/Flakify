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

package org.activiti.engine.test.api.mgmt;

import java.util.Date;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.JobNotFoundException;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cmd.AcquireTimerJobsCmd;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.management.TableMetaData;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

/**




 */
public class ManagementServiceTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/mgmt/timerOnTask.bpmn20.xml"}) public void testDeleteJobThatWasAlreadyAcquired(){processEngineConfiguration.getClock().setCurrentTime(new Date());ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnTask");Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + 7200000L));ProcessEngineImpl processEngineImpl=(ProcessEngineImpl)processEngine;AcquireTimerJobsCmd acquireJobsCmd=new AcquireTimerJobsCmd(processEngine.getProcessEngineConfiguration().getAsyncExecutor());CommandExecutor commandExecutor=processEngineImpl.getProcessEngineConfiguration().getCommandExecutor();commandExecutor.execute(acquireJobsCmd);try {managementService.deleteJob(timerJob.getId());fail();} catch (ActivitiException e){}managementService.moveTimerToExecutableJob(timerJob.getId());managementService.executeJob(timerJob.getId());}
}
