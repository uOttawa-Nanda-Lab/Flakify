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
package org.activiti.engine.test.api.event;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.bpmn.helper.SignalThrowingEventListener;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEventListener}s that throws a signal BPMN event when an {@link ActivitiEvent} has been dispatched.
 * 

 */
public class SignalThrowingEventListenerTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignal.bpmn20.xml","org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignalExternalProcess.bpmn20.xml"}) public void testGlobalSignal() throws Exception{SignalThrowingEventListener listener=null;try {listener=new SignalThrowingEventListener();listener.setSignalName("Signal");listener.setProcessInstanceScope(false);processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.TASK_ASSIGNED);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("globalSignalProcess");assertNotNull(processInstance);ProcessInstance externalProcess=runtimeService.startProcessInstanceByKey("globalSignalProcessExternal");assertNotNull(processInstance);externalProcess=runtimeService.createProcessInstanceQuery().processInstanceId(externalProcess.getId()).singleResult();assertNotNull(externalProcess);Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.setAssignee(task.getId(),"kermit");externalProcess=runtimeService.createProcessInstanceQuery().processInstanceId(externalProcess.getId()).singleResult();assertNull(externalProcess);task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);assertEquals("kermit",task.getAssignee());}  finally {processEngineConfiguration.getEventDispatcher().removeEventListener(listener);}}
}
