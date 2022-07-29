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

package org.activiti.engine.test.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**


 */
public class JsonTest extends PluggableActivitiTestCase {

  public static final String MY_JSON_OBJ = "myJsonObj";
  public static final String BIG_JSON_OBJ = "bigJsonObj";

  protected ObjectMapper objectMapper = new ObjectMapper();
  
  @Deployment public void testJsonObjectAvailable(){Map<String, Object> vars=new HashMap<String, Object>();ObjectNode varNode=objectMapper.createObjectNode();varNode.put("var","myValue");vars.put(MY_JSON_OBJ,varNode);ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJsonAvailableProcess",vars);ObjectNode value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);assertNotNull(value);assertEquals("myValue",value.get("var").asText());ObjectNode var2Node=objectMapper.createObjectNode();var2Node.put("var","myValue");var2Node.put("var2","myOtherValue");runtimeService.setVariable(processInstance.getId(),MY_JSON_OBJ,var2Node);value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);assertNotNull(value);assertEquals("myValue",value.get("var").asText());assertEquals("myOtherValue",value.get("var2").asText());Task task=taskService.createTaskQuery().active().singleResult();assertNotNull(task);ObjectNode var3Node=objectMapper.createObjectNode();var3Node.put("var","myValue");var3Node.put("var2","myOtherValue");var3Node.put("var3","myThirdValue");vars=new HashMap<String, Object>();vars.put(MY_JSON_OBJ,var3Node);vars.put(BIG_JSON_OBJ,createBigJsonObject());taskService.complete(task.getId(),vars);value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);assertNotNull(value);assertEquals("myValue",value.get("var").asText());assertEquals("myOtherValue",value.get("var2").asText());assertEquals("myThirdValue",value.get("var3").asText());value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),BIG_JSON_OBJ);assertNotNull(value);assertEquals(createBigJsonObject().toString(),value.toString());task=taskService.createTaskQuery().active().singleResult();assertNotNull(task);assertEquals("userTaskSuccess",task.getTaskDefinitionKey());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).orderByVariableName().asc().list();assertEquals(2,historicVariableInstances.size());assertEquals(BIG_JSON_OBJ,historicVariableInstances.get(0).getVariableName());value=(ObjectNode)historicVariableInstances.get(0).getValue();assertNotNull(value);assertEquals(createBigJsonObject().toString(),value.toString());assertEquals(MY_JSON_OBJ,historicVariableInstances.get(1).getVariableName());value=(ObjectNode)historicVariableInstances.get(1).getValue();assertNotNull(value);assertEquals("myValue",value.get("var").asText());assertEquals("myOtherValue",value.get("var2").asText());assertEquals("myThirdValue",value.get("var3").asText());}runtimeService.removeVariable(processInstance.getId(),MY_JSON_OBJ);assertNull(runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ));runtimeService.removeVariable(processInstance.getId(),BIG_JSON_OBJ);assertNull(runtimeService.getVariable(processInstance.getId(),BIG_JSON_OBJ));}
  
  protected ObjectNode createBigJsonObject() {
    ObjectNode valueNode = objectMapper.createObjectNode();
    for (int i = 0; i < 1000; i++) {
      ObjectNode childNode = objectMapper.createObjectNode();
      childNode.put("test", "this is a simple test text");
      childNode.put("test2", "this is a simple test2 text");
      childNode.put("test3", "this is a simple test3 text");
      childNode.put("test4", "this is a simple test4 text");
      valueNode.put("var" + i, childNode);
    }
    return valueNode;
  }

}