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

package org.activiti.engine.test.history;

import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**


 */
public class HistoricActivityInstanceTest extends PluggableActivitiTestCase {

  @Deployment public void testHistoricActivityInstanceForEventsQuery(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("eventProcess");assertEquals(1,taskService.createTaskQuery().count());runtimeService.signalEventReceived("signal");assertProcessEnded(pi.getId());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("noop").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("userTask").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("intermediate-event").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("start").list().size());assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("end").list().size());HistoricActivityInstance intermediateEvent=historyService.createHistoricActivityInstanceQuery().activityId("intermediate-event").singleResult();assertNotNull(intermediateEvent.getStartTime());assertNotNull(intermediateEvent.getEndTime());HistoricActivityInstance startEvent=historyService.createHistoricActivityInstanceQuery().activityId("start").singleResult();assertNotNull(startEvent.getStartTime());assertNotNull(startEvent.getEndTime());HistoricActivityInstance endEvent=historyService.createHistoricActivityInstanceQuery().activityId("end").singleResult();assertNotNull(endEvent.getStartTime());assertNotNull(endEvent.getEndTime());}

}
