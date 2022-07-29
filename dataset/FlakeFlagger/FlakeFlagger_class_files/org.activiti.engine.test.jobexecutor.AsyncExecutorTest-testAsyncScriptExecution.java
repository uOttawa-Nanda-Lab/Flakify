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
package org.activiti.engine.test.jobexecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.asyncexecutor.AsyncExecutor;
import org.activiti.engine.impl.asyncexecutor.DefaultAsyncJobExecutor;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.impl.test.JobTestHelper;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests specifically for the {@link AsyncExecutor}.
 * 

 */
public class AsyncExecutorTest {

  @Test public void testAsyncScriptExecution(){ProcessEngine processEngine=null;try {processEngine=createProcessEngine(true);setClockToCurrentTime(processEngine);deploy(processEngine,"AsyncExecutorTest.testAsyncScriptExecution.bpmn20.xml");ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceByKey("asyncScript");waitForAllJobsBeingExecuted(processEngine);Assert.assertEquals(0,processEngine.getManagementService().createJobQuery().count());Assert.assertEquals(0,processEngine.getManagementService().createTimerJobQuery().count());Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).count());Assert.assertEquals(1,processEngine.getTaskService().createTaskQuery().taskName("Task after script").count());Assert.assertEquals(1,getAsyncExecutorJobCount(processEngine));}  finally {cleanup(processEngine);}}

  

  // Helpers ////////////////////////////////////////////////////////

  private ProcessEngine createProcessEngine(boolean enableAsyncExecutor) {
    return createProcessEngine(enableAsyncExecutor, null);
  }

  private ProcessEngine createProcessEngine(boolean enableAsyncExecutor, Date time) {
    ProcessEngineConfigurationImpl processEngineConfiguration = new StandaloneInMemProcessEngineConfiguration();
    processEngineConfiguration.setJdbcUrl("jdbc:h2:mem:activiti-AsyncExecutorTest;DB_CLOSE_DELAY=1000");
    processEngineConfiguration.setDatabaseSchemaUpdate("true");

    if (enableAsyncExecutor) {
      processEngineConfiguration.setAsyncExecutorActivate(true);

      CountingAsyncExecutor countingAsyncExecutor = new CountingAsyncExecutor();
      countingAsyncExecutor.setDefaultAsyncJobAcquireWaitTimeInMillis(50); // To avoid waiting too long when a retry happens
      countingAsyncExecutor.setDefaultTimerJobAcquireWaitTimeInMillis(50);
      processEngineConfiguration.setAsyncExecutor(countingAsyncExecutor);
    }

    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

    if (time != null) {
      processEngine.getProcessEngineConfiguration().getClock().setCurrentTime(time);
    }

    return processEngine;
  }

  private Date setClockToCurrentTime(ProcessEngine processEngine) {
    Date date = new Date();
    processEngine.getProcessEngineConfiguration().getClock().setCurrentTime(date);
    return date;
  }

  private void addSecondsToCurrentTime(ProcessEngine processEngine, long nrOfSeconds) {
    Date currentTime = processEngine.getProcessEngineConfiguration().getClock().getCurrentTime();
    processEngine.getProcessEngineConfiguration().getClock().setCurrentTime(new Date(currentTime.getTime() + (nrOfSeconds * 1000L)));
  }

  private void cleanup(ProcessEngine processEngine) {
      if(processEngine != null) {
          for (org.activiti.engine.repository.Deployment deployment : processEngine.getRepositoryService().createDeploymentQuery().list()) {
              processEngine.getRepositoryService().deleteDeployment(deployment.getId(), true);
          }
          processEngine.close();
      }
  }

  private String deploy(ProcessEngine processEngine, String resource) {
    return processEngine.getRepositoryService().createDeployment().addClasspathResource("org/activiti/engine/test/jobexecutor/" + resource).deploy().getId();
  }

  private void waitForAllJobsBeingExecuted(ProcessEngine processEngine) {
    waitForAllJobsBeingExecuted(processEngine, 10000L);
  }

  private void waitForAllJobsBeingExecuted(ProcessEngine processEngine, long maxWaitTime) {
    JobTestHelper.waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(processEngine.getProcessEngineConfiguration(), processEngine.getManagementService(), maxWaitTime, 1000L, false);
  }

  private int getAsyncExecutorJobCount(ProcessEngine processEngine) {
    AsyncExecutor asyncExecutor = processEngine.getProcessEngineConfiguration().getAsyncExecutor();
    if (asyncExecutor instanceof CountingAsyncExecutor) {
      return ((CountingAsyncExecutor) asyncExecutor).getCounter().get();
    }
    return 0;
  }

  static class CountingAsyncExecutor extends DefaultAsyncJobExecutor {

    private static final Logger logger = LoggerFactory.getLogger(CountingAsyncExecutor.class);

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean executeAsyncJob(Job job) {
      logger.info("About to execute job " + job.getId());
      counter.incrementAndGet();
      boolean success = super.executeAsyncJob(job);
      logger.info("Handed off job " + job.getId() + " to async executor (retries=" + job.getRetries() + ")");
      return success;
    }

    public AtomicInteger getCounter() {
      return counter;
    }

    public void setCounter(AtomicInteger counter) {
      this.counter = counter;
    }

  }

}
