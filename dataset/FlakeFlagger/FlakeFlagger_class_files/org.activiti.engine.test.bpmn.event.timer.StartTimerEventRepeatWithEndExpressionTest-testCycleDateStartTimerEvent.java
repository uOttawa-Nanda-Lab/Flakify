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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.DefaultClockImpl;
import org.activiti.engine.runtime.Clock;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.api.event.TestActivitiEntityEventListener;

/**

 */
public class StartTimerEventRepeatWithEndExpressionTest extends PluggableActivitiTestCase {

  private TestActivitiEntityEventListener listener;

  /**
 * Timer repetition
 */public void testCycleDateStartTimerEvent() throws Exception{Clock previousClock=processEngineConfiguration.getClock();Clock testClock=new DefaultClockImpl();processEngineConfiguration.setClock(testClock);Calendar calendar=Calendar.getInstance();calendar.set(2025,Calendar.DECEMBER,10,0,0,0);testClock.setCurrentTime(calendar.getTime());repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventRepeatWithEndExpressionTest.testCycleDateStartTimerEvent.bpmn20.xml").deploy();assertEquals(1,repositoryService.createProcessDefinitionQuery().count());List<Job> jobs=managementService.createTimerJobQuery().list();assertEquals(1,jobs.size());Calendar dueDateCalendar=Calendar.getInstance();dueDateCalendar.set(2025,Calendar.DECEMBER,11,0,0,0);assertEquals(true,Math.abs(dueDateCalendar.getTime().getTime() - jobs.get(0).getDuedate().getTime()) < 2000);List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().list();assertEquals(0,processInstances.size());List<Task> tasks=taskService.createTaskQuery().list();assertEquals(0,tasks.size());moveByMinutes(60 * 24);waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000L,200);assertEquals(1,managementService.createTimerJobQuery().count());processInstances=runtimeService.createProcessInstanceQuery().list();assertEquals(1,processInstances.size());tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());jobs=managementService.createTimerJobQuery().list();assertEquals(1,jobs.size());dueDateCalendar=Calendar.getInstance();dueDateCalendar.set(2025,Calendar.DECEMBER,12,0,0,0);assertEquals(true,Math.abs(dueDateCalendar.getTime().getTime() - jobs.get(0).getDuedate().getTime()) < 2000);moveByMinutes(60 * 24);waitForJobExecutorToProcessAllJobsAndExecutableTimerJobs(2000,200);processInstances=runtimeService.createProcessInstanceQuery().list();assertEquals(2,processInstances.size());jobs=managementService.createTimerJobQuery().list();assertEquals(0,jobs.size());jobs=managementService.createJobQuery().list();assertEquals(0,jobs.size());tasks=taskService.createTaskQuery().list();assertEquals(2,tasks.size());int timerFiredCount=0;List<ActivitiEvent> eventsReceived=listener.getEventsReceived();for (ActivitiEvent eventReceived:eventsReceived){if (ActivitiEventType.TIMER_FIRED.equals(eventReceived.getType())){timerFiredCount++;}}int eventCreatedCount=0;for (ActivitiEvent eventReceived:eventsReceived){if (ActivitiEventType.ENTITY_CREATED.equals(eventReceived.getType())){eventCreatedCount++;}}int eventDeletedCount=0;for (ActivitiEvent eventReceived:eventsReceived){if (ActivitiEventType.ENTITY_DELETED.equals(eventReceived.getType())){eventDeletedCount++;}}assertEquals(2,timerFiredCount);assertEquals(4,eventCreatedCount);assertEquals(4,eventDeletedCount);for (ProcessInstance processInstance:processInstances){tasks=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();Task task=tasks.get(0);assertEquals("Task A",task.getName());assertEquals(1,tasks.size());taskService.complete(task.getId());}processInstances=runtimeService.createProcessInstanceQuery().list();assertEquals(0,processInstances.size());jobs=managementService.createTimerJobQuery().list();assertEquals(0,jobs.size());jobs=managementService.createJobQuery().list();assertEquals(0,jobs.size());tasks=taskService.createTaskQuery().list();assertEquals(0,tasks.size());listener.clearEventsReceived();processEngineConfiguration.setClock(previousClock);repositoryService.deleteDeployment(repositoryService.createDeploymentQuery().singleResult().getId(),true);}

  private void moveByMinutes(int minutes) throws Exception {
    processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + ((minutes * 60 * 1000))));
  }

}
