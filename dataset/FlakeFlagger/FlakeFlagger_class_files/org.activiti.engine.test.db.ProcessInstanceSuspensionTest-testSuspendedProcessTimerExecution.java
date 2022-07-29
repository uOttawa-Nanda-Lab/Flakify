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

  @Deployment public void testSuspendedProcessTimerExecution() throws Exception{ProcessInstance procInst=runtimeService.startProcessInstanceByKey("suspendProcess");assertNotNull(procInst);assertEquals(1,managementService.createTimerJobQuery().processInstanceId(procInst.getId()).count());Calendar tomorrow=Calendar.getInstance();tomorrow.add(Calendar.DAY_OF_YEAR,1);processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();List<TimerJobEntity> jobs=commandExecutor.execute(new Command<List<TimerJobEntity>>(){@Override public List<TimerJobEntity> execute(CommandContext commandContext){return processEngineConfiguration.getTimerJobEntityManager().findTimerJobsToExecute(new Page(0,1));}});assertEquals(1,jobs.size());runtimeService.suspendProcessInstanceById(procInst.getId());jobs=commandExecutor.execute(new Command<List<TimerJobEntity>>(){@Override public List<TimerJobEntity> execute(CommandContext commandContext){return processEngineConfiguration.getTimerJobEntityManager().findTimerJobsToExecute(new Page(0,1));}});assertEquals(0,jobs.size());}

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
