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
package org.activiti.engine.test.api.runtime;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;


public class ProcessInstanceSuspensionTest extends PluggableActivitiTestCase {

  @Deployment(resources="org/activiti/engine/test/api/runtime/ProcessInstanceSuspensionTest.testSignalEventReceivedAfterProcessInstanceSuspended.bpmn20.xml") public void testSignalEventReceivedAfterMultipleProcessInstancesSuspended(){final String signal="Some Signal";ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");runtimeService.signalEventReceived(signal);assertEquals(0,runtimeService.createProcessInstanceQuery().count());processInstance=runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");runtimeService.suspendProcessInstanceById(processInstance.getId());processInstance=runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");runtimeService.suspendProcessInstanceById(processInstance.getId());runtimeService.signalEventReceived(signal);assertEquals(2,runtimeService.createProcessInstanceQuery().count());runtimeService.signalEventReceived(signal,new HashMap<String, Object>());assertEquals(2,runtimeService.createProcessInstanceQuery().count());runtimeService.activateProcessInstanceById(processInstance.getId());runtimeService.signalEventReceived(signal);assertEquals(1,runtimeService.createProcessInstanceQuery().count());}

}
