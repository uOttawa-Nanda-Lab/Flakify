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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class BoundaryTimerEventTest extends PluggableActivitiTestCase {

  private static boolean listenerExecutedStartEvent;
  private static boolean listenerExecutedEndEvent;

  public static class MyExecutionListener implements ExecutionListener {
    private static final long serialVersionUID = 1L;

    public void notify(DelegateExecution execution) {
      if ("end".equals(execution.getEventName())) {
        listenerExecutedEndEvent = true;
      } else if ("start".equals(execution.getEventName())) {
        listenerExecutedStartEvent = true;
      }
    }
  }

  @Deployment public void testBoundaryTimerEvent2() throws Exception{SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyy.MM.dd hh:mm");Date currentTime=simpleDateFormat.parse("2015.10.01 11:01");processEngineConfiguration.getClock().setCurrentTime(currentTime);runtimeService.startProcessInstanceByKey("timerprocess");try {waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);} catch (Exception ex){}List<Task> tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());assertEquals("Start",tasks.get(0).getName());List<Job> jobList=managementService.createTimerJobQuery().list();assertEquals(1,jobList.size());long tenMinutes=2L * 60L * 1000L;currentTime=new Date(currentTime.getTime() + tenMinutes);processEngineConfiguration.getClock().setCurrentTime(currentTime);try {waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);} catch (Exception ex){ex.getCause();}tasks=taskService.createTaskQuery().list();assertEquals(0,tasks.size());jobList=managementService.createJobQuery().list();assertEquals(0,jobList.size());jobList=managementService.createTimerJobQuery().list();assertEquals(0,jobList.size());}

}
