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

  @Deployment public void testGetJobExceptionStacktrace(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("exceptionInJobExecution");Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull("No job found for process instance",timerJob);try {managementService.moveTimerToExecutableJob(timerJob.getId());managementService.executeJob(timerJob.getId());fail("RuntimeException from within the script task expected");} catch (RuntimeException re){assertTextPresent("This is an exception thrown from scriptTask",re.getCause().getMessage());}timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(timerJob);assertNotNull(timerJob.getExceptionMessage());assertTextPresent("This is an exception thrown from scriptTask",timerJob.getExceptionMessage());String exceptionStack=managementService.getTimerJobExceptionStacktrace(timerJob.getId());assertNotNull(exceptionStack);assertTextPresent("This is an exception thrown from scriptTask",exceptionStack);}
}
