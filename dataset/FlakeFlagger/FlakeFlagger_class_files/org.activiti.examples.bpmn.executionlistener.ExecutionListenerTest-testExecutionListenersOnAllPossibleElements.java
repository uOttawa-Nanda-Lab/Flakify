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

  @Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersProcess.bpmn20.xml"}) public void testExecutionListenersOnAllPossibleElements(){RecorderExecutionListener.clear();ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess","businessKey123");String varSetInExecutionListener=(String)runtimeService.getVariable(processInstance.getId(),"variableSetInExecutionListener");assertNotNull(varSetInExecutionListener);assertEquals("firstValue",varSetInExecutionListener);String businessKey=(String)runtimeService.getVariable(processInstance.getId(),"businessKeyInExecution");assertNotNull(businessKey);assertEquals("businessKey123",businessKey);Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.complete(task.getId());varSetInExecutionListener=(String)runtimeService.getVariable(processInstance.getId(),"variableSetInExecutionListener");assertNotNull(varSetInExecutionListener);assertEquals("secondValue",varSetInExecutionListener);ExampleExecutionListenerPojo myPojo=new ExampleExecutionListenerPojo();runtimeService.setVariable(processInstance.getId(),"myPojo",myPojo);task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.complete(task.getId());ExampleExecutionListenerPojo pojoVariable=(ExampleExecutionListenerPojo)runtimeService.getVariable(processInstance.getId(),"myPojo");assertNotNull(pojoVariable.getReceivedEventName());assertEquals("end",pojoVariable.getReceivedEventName());task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.complete(task.getId());assertProcessEnded(processInstance.getId());List<RecordedEvent> events=RecorderExecutionListener.getRecordedEvents();assertEquals(1,events.size());RecordedEvent event=events.get(0);assertEquals("End Process Listener",event.getParameter());}
}
