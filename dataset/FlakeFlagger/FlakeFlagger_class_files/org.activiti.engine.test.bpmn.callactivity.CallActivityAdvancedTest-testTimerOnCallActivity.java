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

package org.activiti.engine.test.bpmn.callactivity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**




 */
public class CallActivityAdvancedTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testTimerOnCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testTimerOnCallActivity(){Date startTime=processEngineConfiguration.getClock().getCurrentTime();ProcessInstance pi1=runtimeService.startProcessInstanceByKey("timerOnCallActivity");TaskQuery taskQuery=taskService.createTaskQuery();Task taskInSubProcess=taskQuery.singleResult();assertEquals("Task in subprocess",taskInSubProcess.getName());ProcessInstance pi2=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi1.getId()).singleResult();processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (6 * 60 * 1000)));waitForJobExecutorToProcessAllJobs(10000,5000L);Task escalatedTask=taskQuery.singleResult();assertEquals("Escalated Task",escalatedTask.getName());taskService.complete(escalatedTask.getId());assertEquals(0,runtimeService.createExecutionQuery().list().size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){assertTrue(historyService.createHistoricProcessInstanceQuery().processInstanceId(pi2.getId()).singleResult().getDeleteReason().startsWith(DeleteReason.BOUNDARY_EVENT_INTERRUPTING));assertHistoricTasksDeleteReason(pi2,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"Task in subprocess");assertHistoricActivitiesDeleteReason(pi1,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"callSubProcess");assertHistoricActivitiesDeleteReason(pi2,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"task");}}

}
