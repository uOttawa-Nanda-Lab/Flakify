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

  /**
 * Test added to validate UUID variable type + querying (ACT-1665)
 */@Deployment public void testUUIDVariableAndQuery(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");assertNotNull(processInstance);Task task=taskService.createTaskQuery().singleResult();assertNotNull(task);UUID randomUUID=UUID.randomUUID();taskService.setVariableLocal(task.getId(),"conversationId",randomUUID);Task resultingTask=taskService.createTaskQuery().taskVariableValueEquals("conversationId",randomUUID).singleResult();assertNotNull(resultingTask);assertEquals(task.getId(),resultingTask.getId());randomUUID=UUID.randomUUID();runtimeService.setVariable(processInstance.getId(),"uuidVar",randomUUID);ProcessInstance result=runtimeService.createProcessInstanceQuery().variableValueEquals("uuidVar",randomUUID).singleResult();assertNotNull(result);assertEquals(processInstance.getId(),result.getId());}

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
