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

package org.activiti.engine.test.cache;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.test.AbstractTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * Test cases for testing functionality when the process engine is rebooted.
 */
public class ProcessDefinitionCacheTest extends AbstractTestCase {

    public void testDeployRevisedProcessAfterDeleteOnOtherProcessEngine() {
		ProcessEngine processEngine1 = new StandaloneProcessEngineConfiguration()
				.setProcessEngineName("reboot-test-schema")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
				.setJdbcUrl("jdbc:h2:mem:activiti-process-cache-test;DB_CLOSE_DELAY=1000")
				.setAsyncExecutorActivate(false).buildProcessEngine();
		RepositoryService repositoryService1 = processEngine1.getRepositoryService();
		ProcessEngine processEngine2 = new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test")
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				.setJdbcUrl("jdbc:h2:mem:activiti-process-cache-test;DB_CLOSE_DELAY=1000")
				.setAsyncExecutorActivate(false).buildProcessEngine();
		RepositoryService repositoryService2 = processEngine2.getRepositoryService();
		RuntimeService runtimeService2 = processEngine2.getRuntimeService();
		TaskService taskService2 = processEngine2.getTaskService();
		String deploymentId = repositoryService1.createDeployment()
				.addClasspathResource("org/activiti/engine/test/cache/originalProcess.bpmn20.xml").deploy().getId();
		String processDefinitionId = repositoryService2.createProcessDefinitionQuery().singleResult().getId();
		runtimeService2.startProcessInstanceById(processDefinitionId);
		Task task = taskService2.createTaskQuery().singleResult();
		assertEquals("original task", task.getName());
		repositoryService2.deleteDeployment(deploymentId, true);
		assertEquals(0, repositoryService2.createDeploymentQuery().count());
		assertEquals(0, runtimeService2.createProcessInstanceQuery().count());
		deploymentId = repositoryService1.createDeployment()
				.addClasspathResource("org/activiti/engine/test/cache/revisedProcess.bpmn20.xml").deploy().getId();
		repositoryService2.createProcessDefinitionQuery().singleResult().getId();
		runtimeService2.startProcessInstanceByKey("oneTaskProcess");
		task = taskService2.createTaskQuery().singleResult();
		assertEquals("revised task", task.getName());
		repositoryService1.deleteDeployment(deploymentId, true);
		processEngine1.close();
		processEngine2.close();
	}
}
