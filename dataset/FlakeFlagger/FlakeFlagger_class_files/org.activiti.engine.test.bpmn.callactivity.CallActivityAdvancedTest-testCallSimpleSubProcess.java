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

  @Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallSimpleSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testCallSimpleSubProcess(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callSimpleSubProcess");TaskQuery taskQuery=taskService.createTaskQuery();Task taskBeforeSubProcess=taskQuery.singleResult();assertEquals("Task before subprocess",taskBeforeSubProcess.getName());taskService.complete(taskBeforeSubProcess.getId());Task taskInSubProcess=taskQuery.singleResult();assertEquals("Task in subprocess",taskInSubProcess.getName());taskService.complete(taskInSubProcess.getId());Task taskAfterSubProcess=taskQuery.singleResult();assertEquals("Task after subprocess",taskAfterSubProcess.getName());taskService.complete(taskAfterSubProcess.getId());assertProcessEnded(processInstance.getId());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){HistoricProcessInstance historicProcess=historyService.createHistoricProcessInstanceQuery().processInstanceId(taskInSubProcess.getProcessInstanceId()).singleResult();assertNotNull(historicProcess);assertEquals("theStart",historicProcess.getStartActivityId());List<HistoricActivityInstance> historicInstances=historyService.createHistoricActivityInstanceQuery().processInstanceId(taskInSubProcess.getProcessInstanceId()).list();assertEquals(3L,historicInstances.size());Set<String> expectedActivities=new HashSet<String>(Arrays.asList(new String[]{"theStart","task","theEnd"}));for (HistoricActivityInstance act:historicInstances){expectedActivities.remove(act.getActivityId());}assertTrue("Not all expected activities were found in the history",expectedActivities.isEmpty());}}

}
