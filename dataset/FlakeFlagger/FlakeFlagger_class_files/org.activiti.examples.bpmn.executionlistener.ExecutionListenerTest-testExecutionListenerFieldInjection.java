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

  @Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersFieldInjectionProcess.bpmn20.xml"}) public void testExecutionListenerFieldInjection(){Map<String, Object> variables=new HashMap<String, Object>();variables.put("myVar","listening!");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess",variables);Object varSetByListener=runtimeService.getVariable(processInstance.getId(),"var");assertNotNull(varSetByListener);assertTrue(varSetByListener instanceof String);assertEquals("Yes, I am listening!",varSetByListener);}
}
