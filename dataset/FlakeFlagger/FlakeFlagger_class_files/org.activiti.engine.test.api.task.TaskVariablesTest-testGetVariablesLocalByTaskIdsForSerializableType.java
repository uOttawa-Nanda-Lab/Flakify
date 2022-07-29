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
  
  @Deployment(resources={"org/activiti/engine/test/api/task/TaskVariablesTest.testTaskExecutionVariables.bpmn20.xml"}) public void testGetVariablesLocalByTaskIdsForSerializableType(){runtimeService.startProcessInstanceByKey("oneTaskProcess").getId();String taskId=taskService.createTaskQuery().singleResult().getId();StringBuilder sb=new StringBuilder("a");for (int i=0;i < 4001;i++){sb.append("a");}String serializableTypeVar=sb.toString();taskService.setVariableLocal(taskId,"taskVar1",serializableTypeVar);Set<String> taskIds=new HashSet<String>();taskIds.add(taskId);List<VariableInstance> variables=taskService.getVariableInstancesLocalByTaskIds(taskIds);assertEquals(serializableTypeVar,variables.get(0).getValue());}
  
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
