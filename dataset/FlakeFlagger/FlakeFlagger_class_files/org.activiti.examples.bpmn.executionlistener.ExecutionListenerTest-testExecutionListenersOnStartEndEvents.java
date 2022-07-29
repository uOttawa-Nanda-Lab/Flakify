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

package org.activiti.examples.bpmn.executionlistener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.examples.bpmn.executionlistener.CurrentActivityExecutionListener.CurrentActivity;
import org.activiti.examples.bpmn.executionlistener.RecorderExecutionListener.RecordedEvent;

/**

 */
public class ExecutionListenerTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersStartEndEvent.bpmn20.xml"}) public void testExecutionListenersOnStartEndEvents(){RecorderExecutionListener.clear();ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess");assertProcessEnded(processInstance.getId());List<RecordedEvent> recordedEvents=RecorderExecutionListener.getRecordedEvents();assertEquals(4,recordedEvents.size());assertEquals("theStart",recordedEvents.get(0).getActivityId());assertEquals("Start Event",recordedEvents.get(0).getActivityName());assertEquals("Start Event Listener",recordedEvents.get(0).getParameter());assertEquals("end",recordedEvents.get(0).getEventName());assertEquals("noneEvent",recordedEvents.get(1).getActivityId());assertEquals("None Event",recordedEvents.get(1).getActivityName());assertEquals("Intermediate Catch Event Listener",recordedEvents.get(1).getParameter());assertEquals("end",recordedEvents.get(1).getEventName());assertEquals("signalEvent",recordedEvents.get(2).getActivityId());assertEquals("Signal Event",recordedEvents.get(2).getActivityName());assertEquals("Intermediate Throw Event Listener",recordedEvents.get(2).getParameter());assertEquals("start",recordedEvents.get(2).getEventName());assertEquals("theEnd",recordedEvents.get(3).getActivityId());assertEquals("End Event",recordedEvents.get(3).getActivityName());assertEquals("End Event Listener",recordedEvents.get(3).getParameter());assertEquals("start",recordedEvents.get(3).getEventName());}
}
