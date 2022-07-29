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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.cmd.CancelJobsCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.JobEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.test.Deployment;

/**


 */
public class JobQueryTest extends PluggableActivitiTestCase {

  private String deploymentId;
  private String messageId;
  private CommandExecutor commandExecutor;
  private JobEntity jobEntity;

  private Date testStartTime;
  private Date timerOneFireTime;
  private Date timerTwoFireTime;
  private Date timerThreeFireTime;

  private String processInstanceIdOne;
  private String processInstanceIdTwo;
  private String processInstanceIdThree;

  private static final long ONE_HOUR = 60L * 60L * 1000L;
  private static final long ONE_SECOND = 1000L;
  private static final String EXCEPTION_MESSAGE = "problem evaluating script: javax.script.ScriptException: java.lang.RuntimeException: This is an exception thrown from scriptTask";

  /**
   * Setup will create - 3 process instances, each with one timer, each firing at t1/t2/t3 + 1 hour (see process) - 1 message
   */
  protected void setUp() throws Exception {
    super.setUp();

    this.commandExecutor = processEngineConfiguration.getCommandExecutor();

    deploymentId = repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/mgmt/timerOnTask.bpmn20.xml").deploy().getId();

    // Create proc inst that has timer that will fire on t1 + 1 hour
    Calendar startTime = Calendar.getInstance();
    startTime.set(Calendar.MILLISECOND, 0);

    Date t1 = startTime.getTime();
    processEngineConfiguration.getClock().setCurrentTime(t1);

    processInstanceIdOne = runtimeService.startProcessInstanceByKey("timerOnTask").getId();
    testStartTime = t1;
    timerOneFireTime = new Date(t1.getTime() + ONE_HOUR);

    // Create process instance that has timer that will fire on t2 + 1 hour
    startTime.add(Calendar.HOUR_OF_DAY, 1);
    Date t2 = startTime.getTime(); // t2 = t1 + 1 hour
    processEngineConfiguration.getClock().setCurrentTime(t2);
    processInstanceIdTwo = runtimeService.startProcessInstanceByKey("timerOnTask").getId();
    timerTwoFireTime = new Date(t2.getTime() + ONE_HOUR);

    // Create process instance that has timer that will fire on t3 + 1 hour
    startTime.add(Calendar.HOUR_OF_DAY, 1);
    Date t3 = startTime.getTime(); // t3 = t2 + 1 hour
    processEngineConfiguration.getClock().setCurrentTime(t3);
    processInstanceIdThree = runtimeService.startProcessInstanceByKey("timerOnTask").getId();
    timerThreeFireTime = new Date(t3.getTime() + ONE_HOUR);

    // Create one message
    messageId = commandExecutor.execute(new Command<String>() {
      public String execute(CommandContext commandContext) {
        JobEntity message = commandContext.getJobEntityManager().create();
        message.setJobType(Job.JOB_TYPE_MESSAGE);
        message.setRetries(3);
        commandContext.getJobManager().scheduleAsyncJob(message);
        return message.getId();
      }
    });
  }

  public void testQueryByExceptionMessageNull() {
	try {
		managementService.createJobQuery().exceptionMessage(null);
		fail("ActivitiException expected");
	} catch (ActivitiIllegalArgumentException e) {
		assertEquals("Provided exception message is null", e.getMessage());
	}
}

  

  // sorting //////////////////////////////////////////

  

  // helper ////////////////////////////////////////////////////////////

  private void setRetries(final String processInstanceId, final int retries) {
    final Job job = managementService.createTimerJobQuery().processInstanceId(processInstanceId).singleResult();
    managementService.setTimerJobRetries(job.getId(), retries);
  }

  private ProcessInstance startProcessInstanceWithFailingJob() {
    // start a process with a failing job
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("exceptionInJobExecution");

    // The execution is waiting in the first usertask. This contains a boundary
    // timer event which we will execute manual for testing purposes.
    Job timerJob = managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();

    assertNotNull("No job found for process instance", timerJob);

    try {
      managementService.moveTimerToExecutableJob(timerJob.getId());
      managementService.executeJob(timerJob.getId());
      fail("RuntimeException from within the script task expected");
      
    } catch (RuntimeException re) {
      assertTextPresent(EXCEPTION_MESSAGE, re.getMessage());
    }
    
    return processInstance;
  }

  private void verifyFailedJob(TimerJobQuery query, ProcessInstance processInstance) {
    verifyQueryResults(query, 1);

    Job failedJob = query.singleResult();
    assertNotNull(failedJob);
    assertEquals(processInstance.getId(), failedJob.getProcessInstanceId());
    assertNotNull(failedJob.getExceptionMessage());
    assertTextPresent(EXCEPTION_MESSAGE, failedJob.getExceptionMessage());
  }
  
  private void verifyQueryResults(JobQuery query, int countExpected) {
    assertEquals(countExpected, query.list().size());
    assertEquals(countExpected, query.count());

    if (countExpected == 1) {
      assertNotNull(query.singleResult());
    } else if (countExpected > 1) {
      verifySingleResultFails(query);
    } else if (countExpected == 0) {
      assertNull(query.singleResult());
    }
  }

  private void verifySingleResultFails(JobQuery query) {
    try {
      query.singleResult();
      fail();
    } catch (ActivitiException e) {
    }
  }

  private void verifyQueryResults(TimerJobQuery query, int countExpected) {
    assertEquals(countExpected, query.list().size());
    assertEquals(countExpected, query.count());

    if (countExpected == 1) {
      assertNotNull(query.singleResult());
    } else if (countExpected > 1) {
      verifySingleResultFails(query);
    } else if (countExpected == 0) {
      assertNull(query.singleResult());
    }
  }

  private void verifySingleResultFails(TimerJobQuery query) {
    try {
      query.singleResult();
      fail();
    } catch (ActivitiException e) {
    }
  }

  private void createJobWithoutExceptionMsg() {
    CommandExecutor commandExecutor = processEngineConfiguration.getCommandExecutor();
    commandExecutor.execute(new Command<Void>() {
      public Void execute(CommandContext commandContext) {
        jobEntity = commandContext.getJobEntityManager().create();
        jobEntity.setJobType(Job.JOB_TYPE_MESSAGE);
        jobEntity.setLockOwner(UUID.randomUUID().toString());
        jobEntity.setRetries(0);
        
        StringWriter stringWriter = new StringWriter();
        NullPointerException exception = new NullPointerException();
        exception.printStackTrace(new PrintWriter(stringWriter));
        jobEntity.setExceptionStacktrace(stringWriter.toString());

        commandContext.getJobEntityManager().insert(jobEntity);

        assertNotNull(jobEntity.getId());

        return null;

      }
    });

  }

  private void createJobWithoutExceptionStacktrace() {
    CommandExecutor commandExecutor = processEngineConfiguration.getCommandExecutor();
    commandExecutor.execute(new Command<Void>() {
      public Void execute(CommandContext commandContext) {
        jobEntity = commandContext.getJobEntityManager().create();
        jobEntity.setJobType(Job.JOB_TYPE_MESSAGE);
        jobEntity.setLockOwner(UUID.randomUUID().toString());
        jobEntity.setRetries(0);
        
        jobEntity.setExceptionMessage("I'm supposed to fail");
        
        commandContext.getJobEntityManager().insert(jobEntity);

        assertNotNull(jobEntity.getId());

        return null;

      }
    });

  }

  private void deleteJobInDatabase() {
    CommandExecutor commandExecutor = processEngineConfiguration.getCommandExecutor();
    commandExecutor.execute(new Command<Void>() {
      public Void execute(CommandContext commandContext) {

        commandContext.getJobEntityManager().delete(jobEntity.getId());
        return null;
      }
    });
  }

}
