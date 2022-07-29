package org.activiti.engine.test.db;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.TimerJobEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

/**
 * 

 */
public class ProcessInstanceSuspensionTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/db/oneJobProcess.bpmn20.xml"}) public void testJobsNotVisibleToAcquisitionIfDefinitionSuspended(){ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().singleResult();runtimeService.startProcessInstanceByKey(pd.getKey());Job job=managementService.createTimerJobQuery().singleResult();assertNotNull(job);makeSureJobDue(job);List<TimerJobEntity> acquiredJobs=executeAcquireJobsCommand();assertEquals(1,acquiredJobs.size());repositoryService.suspendProcessDefinitionById(pd.getId(),true,null);acquiredJobs=executeAcquireJobsCommand();assertEquals(0,acquiredJobs.size());}
  
  protected void makeSureJobDue(final Job job) {
    processEngineConfiguration.getCommandExecutor().execute(new Command<Void>() {
      public Void execute(CommandContext commandContext) {
        Date currentTime = processEngineConfiguration.getClock().getCurrentTime();
        commandContext.getTimerJobEntityManager().findById(job.getId()).setDuedate(new Date(currentTime.getTime() - 10000));
        return null;
      }

    });
  }

  protected List<TimerJobEntity> executeAcquireJobsCommand() {
    return processEngineConfiguration.getCommandExecutor().execute(new Command<List<TimerJobEntity>>() {
      public List<TimerJobEntity> execute(CommandContext commandContext) {
        return commandContext.getTimerJobEntityManager().findTimerJobsToExecute(new Page(0, 1));
      }
      
    });
  }

}
