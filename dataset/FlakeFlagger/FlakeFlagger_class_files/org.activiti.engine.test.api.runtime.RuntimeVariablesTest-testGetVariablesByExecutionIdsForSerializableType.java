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
package org.activiti.engine.test.api.runtime;

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
public class RuntimeVariablesTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/runtime/RuntimeVariablesTest.testGetVariablesByExecutionIds.bpmn20.xml"}) public void testGetVariablesByExecutionIdsForSerializableType(){ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess");Task task1=taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult();StringBuilder sb=new StringBuilder("a");for (int i=0;i < 4001;i++){sb.append("a");}String serializableTypeVar=sb.toString();taskService.setVariable(task1.getId(),"executionVar1",serializableTypeVar);Set<String> executionIds=new HashSet<String>();executionIds.add(processInstance1.getId());List<VariableInstance> variables=runtimeService.getVariableInstancesByExecutionIds(executionIds);assertEquals(serializableTypeVar,variables.get(0).getValue());}
  
  private void checkVariable(String executionId, String name, String value, List<VariableInstance> variables){
    for (VariableInstance variable : variables){
      if (executionId.equals(variable.getExecutionId())){
        assertEquals(name, variable.getName());
        assertEquals(value, variable.getValue());
        return;
      }
    }
    fail();
  }
}
