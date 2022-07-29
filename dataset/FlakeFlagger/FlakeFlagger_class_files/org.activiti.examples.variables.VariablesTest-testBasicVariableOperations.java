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

  @Deployment public void testBasicVariableOperations(){processEngineConfiguration.getVariableTypes().addType(CustomVariableType.instance);Date now=new Date();List<String> serializable=new ArrayList<String>();serializable.add("one");serializable.add("two");serializable.add("three");byte[] bytes1="somebytes1".getBytes();byte[] bytes2="somebytes2".getBytes();StringBuilder long2000StringBuilder=new StringBuilder();for (int i=0;i < 2000;i++){long2000StringBuilder.append("z");}StringBuilder long2001StringBuilder=new StringBuilder();for (int i=0;i < 2000;i++){long2001StringBuilder.append("a");}long2001StringBuilder.append("a");StringBuilder long4001StringBuilder=new StringBuilder();for (int i=0;i < 4000;i++){long4001StringBuilder.append("a");}long4001StringBuilder.append("a");Map<String, Object> variables=new HashMap<String, Object>();variables.put("longVar",928374L);variables.put("shortVar",(short)123);variables.put("integerVar",1234);variables.put("longString2000chars",long2000StringBuilder.toString());variables.put("stringVar","coca-cola");variables.put("dateVar",now);variables.put("nullVar",null);variables.put("serializableVar",serializable);variables.put("bytesVar",bytes1);variables.put("customVar1",new CustomType(bytes2));variables.put("customVar2",null);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess",variables);variables=runtimeService.getVariables(processInstance.getId());assertEquals(928374L,variables.get("longVar"));assertEquals((short)123,variables.get("shortVar"));assertEquals(1234,variables.get("integerVar"));assertEquals("coca-cola",variables.get("stringVar"));assertEquals(long2000StringBuilder.toString(),variables.get("longString2000chars"));assertEquals(now,variables.get("dateVar"));assertEquals(null,variables.get("nullVar"));assertEquals(serializable,variables.get("serializableVar"));assertTrue(Arrays.equals(bytes1,(byte[])variables.get("bytesVar")));assertEquals(new CustomType(bytes2),variables.get("customVar1"));assertEquals(null,variables.get("customVar2"));assertEquals(11,variables.size());runtimeService.setVariable(processInstance.getId(),"longVar",null);runtimeService.setVariable(processInstance.getId(),"shortVar",null);runtimeService.setVariable(processInstance.getId(),"integerVar",null);runtimeService.setVariable(processInstance.getId(),"stringVar",null);runtimeService.setVariable(processInstance.getId(),"longString2000chars",null);runtimeService.setVariable(processInstance.getId(),"longString4000chars",null);runtimeService.setVariable(processInstance.getId(),"dateVar",null);runtimeService.setVariable(processInstance.getId(),"nullVar",null);runtimeService.setVariable(processInstance.getId(),"serializableVar",null);runtimeService.setVariable(processInstance.getId(),"bytesVar",null);runtimeService.setVariable(processInstance.getId(),"customVar1",null);runtimeService.setVariable(processInstance.getId(),"customVar2",null);variables=runtimeService.getVariables(processInstance.getId());assertEquals(null,variables.get("longVar"));assertEquals(null,variables.get("shortVar"));assertEquals(null,variables.get("integerVar"));assertEquals(null,variables.get("stringVar"));assertEquals(null,variables.get("longString2000chars"));assertEquals(null,variables.get("longString4000chars"));assertEquals(null,variables.get("dateVar"));assertEquals(null,variables.get("nullVar"));assertEquals(null,variables.get("serializableVar"));assertEquals(null,variables.get("bytesVar"));assertEquals(null,variables.get("customVar1"));assertEquals(null,variables.get("customVar2"));assertEquals(12,variables.size());runtimeService.setVariable(processInstance.getId(),"new var","hi");runtimeService.setVariable(processInstance.getId(),"longVar",9987L);runtimeService.setVariable(processInstance.getId(),"shortVar",(short)456);runtimeService.setVariable(processInstance.getId(),"integerVar",4567);runtimeService.setVariable(processInstance.getId(),"stringVar","colgate");runtimeService.setVariable(processInstance.getId(),"longString2000chars",long2001StringBuilder.toString());runtimeService.setVariable(processInstance.getId(),"longString4000chars",long4001StringBuilder.toString());runtimeService.setVariable(processInstance.getId(),"dateVar",now);runtimeService.setVariable(processInstance.getId(),"serializableVar",serializable);runtimeService.setVariable(processInstance.getId(),"bytesVar",bytes1);runtimeService.setVariable(processInstance.getId(),"customVar1",new CustomType(bytes2));runtimeService.setVariable(processInstance.getId(),"customVar2",new CustomType(bytes1));variables=runtimeService.getVariables(processInstance.getId());assertEquals("hi",variables.get("new var"));assertEquals(9987L,variables.get("longVar"));assertEquals((short)456,variables.get("shortVar"));assertEquals(4567,variables.get("integerVar"));assertEquals("colgate",variables.get("stringVar"));assertEquals(long2001StringBuilder.toString(),variables.get("longString2000chars"));assertEquals(long4001StringBuilder.toString(),variables.get("longString4000chars"));assertEquals(now,variables.get("dateVar"));assertEquals(null,variables.get("nullVar"));assertEquals(serializable,variables.get("serializableVar"));assertTrue(Arrays.equals(bytes1,(byte[])variables.get("bytesVar")));assertEquals(new CustomType(bytes2),variables.get("customVar1"));assertEquals(new CustomType(bytes1),variables.get("customVar2"));assertEquals(13,variables.size());Collection<String> varFilter=new ArrayList<String>(2);varFilter.add("stringVar");varFilter.add("integerVar");Map<String, Object> filteredVariables=runtimeService.getVariables(processInstance.getId(),varFilter);assertEquals(2,filteredVariables.size());assertTrue(filteredVariables.containsKey("stringVar"));assertTrue(filteredVariables.containsKey("integerVar"));runtimeService.setVariable(processInstance.getId(),"nullVar","a value");Object newValue=runtimeService.getVariable(processInstance.getId(),"nullVar");assertNotNull(newValue);assertEquals("a value",newValue);Task task=taskService.createTaskQuery().singleResult();taskService.complete(task.getId());}

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
