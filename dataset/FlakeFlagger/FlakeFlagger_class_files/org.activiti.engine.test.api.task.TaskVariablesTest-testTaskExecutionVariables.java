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

package org.activiti.engine.test.api.task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TaskVariablesTest extends PluggableActivitiTestCase {

  @Deployment public void testTaskExecutionVariables(){String processInstanceId=runtimeService.startProcessInstanceByKey("oneTaskProcess").getId();String taskId=taskService.createTaskQuery().singleResult().getId();Map<String, Object> expectedVariables=new HashMap<String, Object>();assertEquals(expectedVariables,runtimeService.getVariables(processInstanceId));assertEquals(expectedVariables,taskService.getVariables(taskId));assertEquals(expectedVariables,runtimeService.getVariablesLocal(processInstanceId));assertEquals(expectedVariables,taskService.getVariablesLocal(taskId));runtimeService.setVariable(processInstanceId,"instrument","trumpet");expectedVariables=new HashMap<String, Object>();assertEquals(expectedVariables,taskService.getVariablesLocal(taskId));expectedVariables.put("instrument","trumpet");assertEquals(expectedVariables,runtimeService.getVariables(processInstanceId));assertEquals(expectedVariables,taskService.getVariables(taskId));assertEquals(expectedVariables,runtimeService.getVariablesLocal(processInstanceId));taskService.setVariable(taskId,"player","gonzo");assertTrue(taskService.hasVariable(taskId,"player"));assertFalse(taskService.hasVariableLocal(taskId,"budget"));expectedVariables=new HashMap<String, Object>();assertEquals(expectedVariables,taskService.getVariablesLocal(taskId));expectedVariables.put("player","gonzo");expectedVariables.put("instrument","trumpet");assertEquals(expectedVariables,runtimeService.getVariables(processInstanceId));assertEquals(expectedVariables,taskService.getVariables(taskId));assertEquals(expectedVariables,runtimeService.getVariablesLocal(processInstanceId));taskService.setVariableLocal(taskId,"budget","unlimited");assertTrue(taskService.hasVariableLocal(taskId,"budget"));assertTrue(taskService.hasVariable(taskId,"budget"));expectedVariables=new HashMap<String, Object>();expectedVariables.put("budget","unlimited");assertEquals(expectedVariables,taskService.getVariablesLocal(taskId));expectedVariables.put("player","gonzo");expectedVariables.put("instrument","trumpet");assertEquals(expectedVariables,taskService.getVariables(taskId));expectedVariables=new HashMap<String, Object>();expectedVariables.put("player","gonzo");expectedVariables.put("instrument","trumpet");assertEquals(expectedVariables,runtimeService.getVariables(processInstanceId));assertEquals(expectedVariables,runtimeService.getVariablesLocal(processInstanceId));}

  private void checkVariable(String taskId, String name, String value, List<VariableInstance> variables){
    for (VariableInstance variable : variables){
      if (taskId.equals(variable.getTaskId())){
        assertEquals(name, variable.getName());
        assertEquals(value, variable.getValue());
        return;
      }
    }
    fail();
  }
  
  public static class MyVariable implements Serializable {

    private String value;

    public MyVariable(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

  }

}
