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

    public void testMultipleStartEvents(){Date startTime=new Date(1462906201000L);processEngineConfiguration.getClock().setCurrentTime(startTime);String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testMultipleStartEvents.bpmn20.xml").deploy().getId();assertEquals(4,managementService.createTimerJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().executable().count());Date newDate=new Date(startTime.getTime() + (7 * 1000));processEngineConfiguration.getClock().setCurrentTime(newDate);List<Job> executableTimers=managementService.createTimerJobQuery().executable().list();assertEquals(1,executableTimers.size());executeJobs(executableTimers);validateTaskCounts(0,1,0,0);assertEquals(4,managementService.createTimerJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().executable().count());newDate=new Date(newDate.getTime() + (4 * 1000));processEngineConfiguration.getClock().setCurrentTime(newDate);executableTimers=managementService.createTimerJobQuery().executable().list();assertEquals(2,executableTimers.size());executeJobs(executableTimers);validateTaskCounts(1,2,0,0);assertEquals(4,managementService.createTimerJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().executable().count());newDate=new Date(newDate.getTime() + (6 * 1000));processEngineConfiguration.getClock().setCurrentTime(newDate);executableTimers=managementService.createTimerJobQuery().executable().list();assertEquals(2,executableTimers.size());executeJobs(executableTimers);validateTaskCounts(1,3,1,0);assertEquals(2,managementService.createTimerJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().executable().count());newDate=new Date(newDate.getTime() + (6 * 1000));processEngineConfiguration.getClock().setCurrentTime(newDate);executableTimers=managementService.createTimerJobQuery().executable().list();assertEquals(2,executableTimers.size());executeJobs(executableTimers);validateTaskCounts(2,3,1,1);assertEquals(1,managementService.createTimerJobQuery().count());assertEquals(0,managementService.createTimerJobQuery().executable().count());repositoryService.deleteDeployment(deploymentId,true);}

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
