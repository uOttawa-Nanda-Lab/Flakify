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

  @Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersForSubprocessStartEndEvent.bpmn20.xml"}) public void testExecutionListenersForSubprocessStartEndEvents(){RecorderExecutionListener.clear();ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess");List<RecordedEvent> recordedEvents=RecorderExecutionListener.getRecordedEvents();assertEquals(1,recordedEvents.size());assertEquals("Process Start",recordedEvents.get(0).getParameter());RecorderExecutionListener.clear();Task task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());assertProcessEnded(processInstance.getId());recordedEvents=RecorderExecutionListener.getRecordedEvents();assertEquals(3,recordedEvents.size());assertEquals("Subprocess Start",recordedEvents.get(0).getParameter());assertEquals("Subprocess End",recordedEvents.get(1).getParameter());assertEquals("Process End",recordedEvents.get(2).getParameter());}
}
