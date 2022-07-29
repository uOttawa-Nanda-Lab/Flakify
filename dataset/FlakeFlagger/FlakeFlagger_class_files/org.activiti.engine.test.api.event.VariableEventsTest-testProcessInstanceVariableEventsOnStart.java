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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiVariableEvent;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * Test case for all {@link ActivitiEvent}s related to variables.
 */
public class VariableEventsTest extends PluggableActivitiTestCase {

    private TestVariableEventListener listener;

    @Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceVariableEventsOnStart() throws Exception{HashMap<String, Object> vars=new HashMap<String, Object>();vars.put("testVariable","The value");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);assertNotNull(processInstance);assertEquals(1,listener.getEventsReceived().size());ActivitiVariableEvent event=(ActivitiVariableEvent)listener.getEventsReceived().get(0);assertEquals(ActivitiEventType.VARIABLE_CREATED,event.getType());assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());assertEquals(processInstance.getId(),event.getExecutionId());assertEquals(processInstance.getId(),event.getProcessInstanceId());assertNull(event.getTaskId());assertEquals("testVariable",event.getVariableName());assertEquals("The value",event.getVariableValue());listener.clearEventsReceived();}
}
