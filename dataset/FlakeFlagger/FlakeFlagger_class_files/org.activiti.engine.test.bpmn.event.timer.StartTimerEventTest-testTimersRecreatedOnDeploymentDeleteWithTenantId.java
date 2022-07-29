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
package org.activiti.engine.test.bpmn.event.timer;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.activiti.engine.impl.cmd.CancelJobsCmd;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.test.Deployment;

public class StartTimerEventTest extends PluggableActivitiTestCase {

    private void moveByMinutes(int minutes) throws Exception {
        processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + ((minutes * 60 * 1000) + 5000)));
    }

    public void testTimersRecreatedOnDeploymentDeleteWithTenantId(){for (int i=1;i <= 4;i++){repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v" + i + ".bpmn20.xml").deploy();}String testTenant="Activiti-tenant";String deployment1=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v1.bpmn20.xml").tenantId(testTenant).deploy().getId();assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());String deployment2=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v2.bpmn20.xml").tenantId(testTenant).deploy().getId();assertEquals(2,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());String deployment3=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v3.bpmn20.xml").tenantId(testTenant).deploy().getId();assertEquals(3,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(3,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());String deployment4=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v4.bpmn20.xml").tenantId(testTenant).deploy().getId();assertEquals(4,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(4,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());repositoryService.deleteDeployment(deployment4,true);assertEquals(3,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(3,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());repositoryService.deleteDeployment(deployment2,true);assertEquals(2,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());repositoryService.deleteDeployment(deployment3,true);assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());for (ProcessDefinition processDefinition:repositoryService.createProcessDefinitionQuery().processDefinitionKey("timer").orderByProcessDefinitionVersion().desc().list()){repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);}assertEquals(0,managementService.createTimerJobQuery().count());}

    private void validateTaskCounts(long taskACount,
                                    long taskBCount,
                                    long taskCCount,
                                    long taskDCount) {
        assertEquals("task A counts are incorrect",
                     taskACount,
                     taskService.createTaskQuery().taskName("Task A").count());
        assertEquals("task B counts are incorrect",
                     taskBCount,
                     taskService.createTaskQuery().taskName("Task B").count());
        assertEquals("task C counts are incorrect",
                     taskCCount,
                     taskService.createTaskQuery().taskName("Task C").count());
        assertEquals("task D counts are incorrect",
                     taskDCount,
                     taskService.createTaskQuery().taskName("Task D").count());
    }

    private void executeJobs(List<Job> jobs) {
        for (Job job : jobs) {
            managementService.moveTimerToExecutableJob(job.getId());
            managementService.executeJob(job.getId());
        }
    }

    private void cleanDB() {
        String jobId = managementService.createTimerJobQuery().singleResult().getId();
        CommandExecutor commandExecutor = processEngineConfiguration.getCommandExecutor();
        commandExecutor.execute(new CancelJobsCmd(jobId));
    }
}
