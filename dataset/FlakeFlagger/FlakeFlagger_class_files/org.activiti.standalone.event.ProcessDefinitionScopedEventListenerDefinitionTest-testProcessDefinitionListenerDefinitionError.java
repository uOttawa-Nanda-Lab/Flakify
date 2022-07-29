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
package org.activiti.standalone.event;

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.api.event.StaticTestActivitiEventListener;
import org.activiti.engine.test.api.event.TestActivitiEventListener;

/**
 * Test for event-listeners that are registered on a process-definition scope, rather than on the global engine-wide scope, declared in the BPMN XML.
 * 

 */
public class ProcessDefinitionScopedEventListenerDefinitionTest extends ResourceActivitiTestCase {

  protected TestActivitiEventListener testListenerBean;

  /**
 * Test to verify listeners defined in the BPMN xml with invalid class/delegateExpression values cause an exception when process is started.
 */
public void testProcessDefinitionListenerDefinitionError() throws Exception {
	org.activiti.engine.repository.Deployment deployment = repositoryService.createDeployment()
			.addClasspathResource("org/activiti/standalone/event/invalidEventListenerExpression.bpmn20.xml").deploy();
	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testInvalidEventExpression");
	assertNotNull(processInstance);
	repositoryService.deleteDeployment(deployment.getId(), true);
	deployment = repositoryService.createDeployment()
			.addClasspathResource("org/activiti/standalone/event/invalidEventListenerClass.bpmn20.xml").deploy();
	processInstance = runtimeService.startProcessInstanceByKey("testInvalidEventClass");
	repositoryService.deleteDeployment(deployment.getId(), true);
}
}
