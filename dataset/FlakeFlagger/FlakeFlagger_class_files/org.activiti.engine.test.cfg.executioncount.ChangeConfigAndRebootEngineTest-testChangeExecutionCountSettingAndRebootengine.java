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
package org.activiti.engine.test.cfg.executioncount;

import java.util.List;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cmd.ValidateExecutionRelatedEntityCountCfgCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.CountingExecutionEntity;
import org.activiti.engine.impl.persistence.entity.PropertyEntity;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 */
public class ChangeConfigAndRebootEngineTest extends ResourceActivitiTestCase {
  
  private static final Logger logger = LoggerFactory.getLogger(ChangeConfigAndRebootEngineTest.class);
  
  protected boolean newExecutionRelationshipCountValue;
  
  protected void rebootEngine(boolean newExecutionRelationshipCountValue) {
    logger.info("Rebooting engine");
    this.newExecutionRelationshipCountValue = newExecutionRelationshipCountValue;
    closeDownProcessEngine();
    initializeProcessEngine();
    initializeServices();
  }
  
  @Deployment public void testChangeExecutionCountSettingAndRebootengine(){rebootEngine(true);assertConfigProperty(true);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");assertExecutions(processInstance,true);rebootEngine(true);assertConfigProperty(true);assertExecutions(processInstance,true);rebootEngine(false);assertConfigProperty(false);assertExecutions(processInstance,false);finishProcessInstance(processInstance);rebootEngine(false);assertConfigProperty(false);processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");assertExecutions(processInstance,false);rebootEngine(true);assertConfigProperty(true);assertExecutions(processInstance,false);finishProcessInstance(processInstance);processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");assertExecutions(processInstance,true);finishProcessInstance(processInstance);}

  protected void assertConfigProperty(boolean expectedValue) {
    PropertyEntity propertyEntity = managementService.executeCommand(new Command<PropertyEntity>() {
      @Override
      public PropertyEntity execute(CommandContext commandContext) {
        return commandContext.getPropertyEntityManager().findById(
            ValidateExecutionRelatedEntityCountCfgCmd.PROPERTY_EXECUTION_RELATED_ENTITY_COUNT);
      }
    });
    assertEquals(expectedValue, Boolean.parseBoolean(propertyEntity.getValue()));
  }
  
  protected void assertExecutions(ProcessInstance processInstance, boolean expectedCountIsEnabledFlag) {
    List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(2, executions.size());
    for (Execution execution : executions) {
      CountingExecutionEntity countingExecutionEntity = (CountingExecutionEntity) execution;
      assertEquals(expectedCountIsEnabledFlag, countingExecutionEntity.isCountEnabled());
      
      if (expectedCountIsEnabledFlag && execution.getParentId() != null) {
        assertEquals(1, countingExecutionEntity.getTaskCount());
      }
    }
  }
  
  protected void finishProcessInstance(ProcessInstance processInstance) {
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
    task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
    assertProcessEnded(processInstance.getId());
  }

}
