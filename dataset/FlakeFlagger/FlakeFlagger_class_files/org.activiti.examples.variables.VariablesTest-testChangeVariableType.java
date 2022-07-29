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

  @Deployment(resources={"org/activiti/examples/variables/VariablesTest.testBasicVariableOperations.bpmn20.xml"}) public void testChangeVariableType(){Date now=new Date();List<String> serializable=new ArrayList<String>();serializable.add("one");serializable.add("two");serializable.add("three");byte[] bytes="somebytes".getBytes();Map<String, Object> variables=new HashMap<String, Object>();variables.put("longVar",928374L);variables.put("shortVar",(short)123);variables.put("integerVar",1234);variables.put("stringVar","coca-cola");variables.put("dateVar",now);variables.put("nullVar",null);variables.put("serializableVar",serializable);variables.put("bytesVar",bytes);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess",variables);variables=runtimeService.getVariables(processInstance.getId());assertEquals(928374L,variables.get("longVar"));assertEquals((short)123,variables.get("shortVar"));assertEquals(1234,variables.get("integerVar"));assertEquals("coca-cola",variables.get("stringVar"));assertEquals(now,variables.get("dateVar"));assertEquals(null,variables.get("nullVar"));assertEquals(serializable,variables.get("serializableVar"));assertTrue(Arrays.equals(bytes,(byte[])variables.get("bytesVar")));assertEquals(8,variables.size());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){String oldSerializableVarId=getVariableInstanceId(processInstance.getId(),"serializableVar");String oldLongVar=getVariableInstanceId(processInstance.getId(),"longVar");Map<String, Object> newVariables=new HashMap<String, Object>();newVariables.put("serializableVar",(short)222);runtimeService.setVariables(processInstance.getId(),newVariables);variables=runtimeService.getVariables(processInstance.getId());assertEquals((short)222,variables.get("serializableVar"));String newSerializableVarId=getVariableInstanceId(processInstance.getId(),"serializableVar");assertEquals(oldSerializableVarId,newSerializableVarId);newVariables=new HashMap<String, Object>();newVariables.put("longVar",(short)123);runtimeService.setVariables(processInstance.getId(),newVariables);variables=runtimeService.getVariables(processInstance.getId());assertEquals((short)123,variables.get("longVar"));String newLongVar=getVariableInstanceId(processInstance.getId(),"longVar");assertEquals(oldLongVar,newLongVar);}}

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
