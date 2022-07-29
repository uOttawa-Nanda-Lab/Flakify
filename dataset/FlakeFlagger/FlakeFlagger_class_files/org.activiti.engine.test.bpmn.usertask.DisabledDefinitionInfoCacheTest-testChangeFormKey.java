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

package org.activiti.engine.test.bpmn.usertask;



import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.test.AbstractActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;


/**

 */
public class DisabledDefinitionInfoCacheTest extends AbstractActivitiTestCase {

  protected static ProcessEngine cachedProcessEngine;
  
  protected void initializeProcessEngine() {
    if (cachedProcessEngine==null) {
      ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
          .createProcessEngineConfigurationFromResource("org/activiti/engine/test/bpmn/usertask/activiti.cfg.xml");
      
      cachedProcessEngine = processEngineConfiguration.buildProcessEngine();
    }
    processEngine = cachedProcessEngine;
  }

  @Deployment public void testChangeFormKey(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");String processDefinitionId=processInstance.getProcessDefinitionId();Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("test",task.getFormKey());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());ObjectNode infoNode=dynamicBpmnService.changeUserTaskFormKey("task1","test2");dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("test",task.getFormKey());taskService.complete(task.getId());assertProcessEnded(processInstance.getId());}
  
}