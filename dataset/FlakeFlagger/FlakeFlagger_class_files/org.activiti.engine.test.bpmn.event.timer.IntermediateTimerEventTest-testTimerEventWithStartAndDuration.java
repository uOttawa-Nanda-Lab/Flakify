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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.TimerJobQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class IntermediateTimerEventTest extends PluggableActivitiTestCase {

  @Deployment public void testTimerEventWithStartAndDuration() throws Exception{Calendar testStartCal=new GregorianCalendar(2016,0,1,10,0,0);Date testStartTime=testStartCal.getTime();processEngineConfiguration.getClock().setCurrentTime(testStartTime);ProcessInstance pi=runtimeService.startProcessInstanceByKey("timerEventWithStartAndDuration");List<Task> tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());Task task=tasks.get(0);assertEquals("Task A",task.getName());TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());assertEquals(0,jobQuery.count());Date startDate=new Date();runtimeService.setVariable(pi.getId(),"StartDate",startDate);taskService.complete(task.getId());jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());assertEquals(1,jobQuery.count());processEngineConfiguration.getClock().setCurrentTime(new Date(startDate.getTime() + 7000L));jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());assertEquals(1,jobQuery.count());jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId()).executable();assertEquals(0,jobQuery.count());processEngineConfiguration.getClock().setCurrentTime(new Date(startDate.getTime() + 11000L));waitForJobExecutorToProcessAllJobs(15000L,25L);jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());assertEquals(0,jobQuery.count());tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());task=tasks.get(0);assertEquals("Task B",task.getName());taskService.complete(task.getId());assertProcessEnded(pi.getProcessInstanceId());processEngineConfiguration.getClock().reset();}

}
