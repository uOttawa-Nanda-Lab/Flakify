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
package org.activiti.engine.test.api.variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class TransientVariablesTest extends PluggableActivitiTestCase {
  
  @Deployment public void testUseTransientVariableInExclusiveGateway(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("responseOk",task.getTaskDefinitionKey());assertNull(runtimeService.getVariable(processInstance.getId(),"response"));}
  
  
  
  
  
  /* Service task class for previous tests */
  
  
  /**
   * Mimics a service task that fetches data from a server and stored the whole thing
   * in a transient variable.  
   */
  public static class FetchDataServiceTask implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      execution.setTransientVariable("response", "author=kermit;version=3;message=Hello World");
      execution.setTransientVariable("status", new Integer(200));
    }
  }
  
  /**
   * Processes the transient variable and puts the relevant bits in real variables
   */
  public static class ServiceTask02 implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      String response = (String) execution.getTransientVariable("response");
      for (String s : response.split(";")) {
        String[] data = s.split("=");
        if (data[0].equals("message")) {
          execution.setVariable("message", data[1] + "!");
        }
      }
    }
  }
  
  public static class CombineVariablesExecutionListener implements ExecutionListener {
    public void notify(DelegateExecution execution) {
      String persistentVar1 = (String) execution.getVariable("persistentVar1");
      
      Object unusedTransientVar = execution.getVariable("unusedTransientVar");
      if (unusedTransientVar != null) {
        throw new RuntimeException("Unused transient var should have been deleted");
      }
      
      String secondTransientVar = (String) execution.getVariable("secondTransientVar");
      Number thirdTransientVar = (Number) execution.getTransientVariable("thirdTransientVar");
      
      String combinedVar = persistentVar1 + secondTransientVar + thirdTransientVar.intValue();
      execution.setVariable("combinedVar", combinedVar);
    }
  }
  
  public static class GetDataDelegate implements JavaDelegate {
    private Expression variableName; 
    public void execute(DelegateExecution execution) {
      String var = (String) variableName.getValue(execution);
      execution.setTransientVariable(var, "author=kermit;version=3;message=" + var);
    }
  }
  
  public static class ProcessDataDelegate implements JavaDelegate {
    private Expression dataVariableName;
    private Expression resultVariableName;
    public void execute(DelegateExecution execution) {
      String varName = (String) dataVariableName.getValue(execution);
      String resultVar = (String) resultVariableName.getValue(execution);
      
      // Sets the name of the variable as 'resultVar'
      for (String s : ((String)execution.getVariable(varName)).split(";")) {
        String[] data = s.split("=");
        if (data[0].equals("message")) {
          execution.setTransientVariable(resultVar, data[1]);
        }
      }
    }
  }
  
  public static class MergeTransientVariablesTaskListener implements TaskListener {
    public void notify(DelegateTask delegateTask) {
      Map<String, Object> transientVariables = delegateTask.getTransientVariables();
      List<String> variableNames = new ArrayList(transientVariables.keySet());
      Collections.sort(variableNames);
      
      StringBuilder strb = new StringBuilder();
      for (String variableName : variableNames) {
        if (variableName.startsWith("transientResult")) {
          String result = (String) transientVariables.get(variableName);
         strb.append(result); 
        }
      }
      
      delegateTask.setVariable("mergedResult", strb.toString());
    }
  }
  
  public static class MergeVariableValues implements JavaDelegate {
    public void execute(DelegateExecution execution) {
      Map<String, Object> vars = execution.getVariables();
      List<String> varNames = new ArrayList<String>(vars.keySet());
      Collections.sort(varNames);
      
      StringBuilder strb = new StringBuilder();
      for (String varName : varNames) {
        strb.append(vars.get(varName));
      }
      
      execution.setVariable("result", strb.toString());
    }
  }
  
}
