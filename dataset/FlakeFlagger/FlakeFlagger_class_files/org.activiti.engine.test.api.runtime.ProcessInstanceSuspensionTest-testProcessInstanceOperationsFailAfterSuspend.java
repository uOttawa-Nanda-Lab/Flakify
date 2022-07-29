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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceOperationsFailAfterSuspend(){ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());runtimeService.suspendProcessInstanceById(processInstance.getId());try {runtimeService.messageEventReceived("someMessage",processInstance.getId());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.messageEventReceived("someMessage",processInstance.getId(),new HashMap<String, Object>());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.removeVariable(processInstance.getId(),"someVariable");fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.removeVariableLocal(processInstance.getId(),"someVariable");fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.removeVariables(processInstance.getId(),Arrays.asList("one","two","three"));fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.removeVariablesLocal(processInstance.getId(),Arrays.asList("one","two","three"));fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.setVariable(processInstance.getId(),"someVariable","someValue");fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.setVariableLocal(processInstance.getId(),"someVariable","someValue");fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.setVariables(processInstance.getId(),new HashMap<String, Object>());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.setVariablesLocal(processInstance.getId(),new HashMap<String, Object>());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.trigger(processInstance.getId());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.trigger(processInstance.getId(),new HashMap<String, Object>());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.signalEventReceived("someSignal",processInstance.getId());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}try {runtimeService.signalEventReceived("someSignal",processInstance.getId(),new HashMap<String, Object>());fail();} catch (ActivitiException e){e.getMessage().contains("is suspended");}}

}
