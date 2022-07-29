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
package org.activiti.spring.test.servicetask;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**

 */
@ContextConfiguration("classpath:org/activiti/spring/test/servicetask/servicetaskSpringTest-context.xml")
public class UseActivitiServiceInServiceTaskTest extends SpringActivitiTestCase {

  /**
 * This test will use the regular mechanism (delegateExecution.getProcessEngine().getRuntimeService()) to obtain the   {@link RuntimeService}  to start a new process.
 */@Deployment public void testUseRuntimeServiceNotInjectedInServiceTask(){runtimeService.startProcessInstanceByKey("startProcessFromDelegate");List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().list();assertEquals(2,processInstances.size());boolean startProcessFromDelegateFound=false;boolean oneTaskProcessFound=false;for (ProcessInstance processInstance:processInstances){ProcessDefinition processDefinition=repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());if (processDefinition.getKey().equals("startProcessFromDelegate")){startProcessFromDelegateFound=true;} else if (processDefinition.getKey().equals("oneTaskProcess")){oneTaskProcessFound=true;}}assertTrue(startProcessFromDelegateFound);assertTrue(oneTaskProcessFound);}

}
