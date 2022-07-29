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

    public void testStartProcessInstanceByIdAfterReboot(){ProcessEngines.destroy();ProcessEngineConfigurationImpl processEngineConfiguration=new StandaloneInMemProcessEngineConfiguration();processEngineConfiguration.setProcessEngineName("reboot-test-schema");processEngineConfiguration.setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000");ProcessEngine schemaProcessEngine=processEngineConfiguration.buildProcessEngine();ProcessEngine processEngine=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();processEngine.getRepositoryService().createDeployment().addClasspathResource("org/activiti/engine/test/cache/originalProcess.bpmn20.xml").deploy();List<ProcessDefinition> processDefinitions=processEngine.getRepositoryService().createProcessDefinitionQuery().list();assertEquals(1,processDefinitions.size());ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceById(processDefinitions.get(0).getId());String processInstanceId=processInstance.getId();assertNotNull(processInstance);processEngine.close();assertNotNull(processEngine.getRuntimeService());processEngine=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();assertNotNull(processInstance);TaskService taskService=processEngine.getTaskService();Task task=taskService.createTaskQuery().list().get(0);taskService.complete(task.getId());processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();assertNull(processInstance);processInstance=processEngine.getRuntimeService().startProcessInstanceById(processDefinitions.get(0).getId());assertNotNull(processInstance);processEngine.close();schemaProcessEngine.close();}
}
