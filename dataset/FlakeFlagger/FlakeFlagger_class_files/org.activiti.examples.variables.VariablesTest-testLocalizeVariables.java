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
package org.activiti.examples.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.variable.ValueFields;
import org.activiti.engine.impl.variable.VariableType;
import org.activiti.engine.runtime.DataObject;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class VariablesTest extends PluggableActivitiTestCase {

  @Deployment public void testLocalizeVariables(){Map<String, Object> variables=new HashMap<String, Object>();variables.put("stringVar","coca-cola");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("localizeVariables",variables);Map<String, VariableInstance> variableInstances=runtimeService.getVariableInstances(processInstance.getId());assertEquals(1,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("coca-cola",variableInstances.get("stringVar").getValue());List<String> variableNames=new ArrayList<String>();variableNames.add("stringVar");variableInstances=runtimeService.getVariableInstances(processInstance.getId(),variableNames);assertEquals(1,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("coca-cola",variableInstances.get("stringVar").getValue());variableInstances=runtimeService.getVariableInstancesLocal(processInstance.getId(),variableNames);assertEquals(1,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("coca-cola",variableInstances.get("stringVar").getValue());VariableInstance variableInstance=runtimeService.getVariableInstance(processInstance.getId(),"stringVar");assertNotNull(variableInstance);assertEquals("stringVar",variableInstance.getName());assertEquals("coca-cola",variableInstance.getValue());variableInstance=runtimeService.getVariableInstanceLocal(processInstance.getId(),"stringVar");assertNotNull(variableInstance);assertEquals("stringVar",variableInstance.getName());assertEquals("coca-cola",variableInstance.getValue());Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();variableInstances=taskService.getVariableInstances(task.getId());assertEquals(2,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("coca-cola",variableInstances.get("stringVar").getValue());assertEquals("intVar",variableInstances.get("intVar").getName());assertEquals(null,variableInstances.get("intVar").getValue());variableNames=new ArrayList<String>();variableNames.add("stringVar");variableInstances=taskService.getVariableInstances(task.getId(),variableNames);assertEquals(1,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("coca-cola",variableInstances.get("stringVar").getValue());taskService.setVariableLocal(task.getId(),"stringVar","pepsi-cola");variableInstances=taskService.getVariableInstancesLocal(task.getId(),variableNames);assertEquals(1,variableInstances.size());assertEquals("stringVar",variableInstances.get("stringVar").getName());assertEquals("pepsi-cola",variableInstances.get("stringVar").getValue());variableInstance=taskService.getVariableInstance(task.getId(),"stringVar");assertNotNull(variableInstance);assertEquals("stringVar",variableInstance.getName());assertEquals("pepsi-cola",variableInstance.getValue());variableInstance=taskService.getVariableInstanceLocal(task.getId(),"stringVar");assertNotNull(variableInstance);assertEquals("stringVar",variableInstance.getName());assertEquals("pepsi-cola",variableInstance.getValue());}

}

class CustomType {
  private byte[] value;

}

/**
 * A custom variable type for testing byte array value handling.
 *
 */
class CustomVariableType implements VariableType {
  public static final CustomVariableType instance = new CustomVariableType();

}
