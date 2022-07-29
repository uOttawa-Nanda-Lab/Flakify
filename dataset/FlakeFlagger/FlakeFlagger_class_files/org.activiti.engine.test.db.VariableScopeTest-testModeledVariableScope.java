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

package org.activiti.engine.test.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class VariableScopeTest extends PluggableActivitiTestCase {

  @Deployment public void testModeledVariableScope(){Map<String, Object> varMap=new HashMap<String, Object>();ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedSubProcess",varMap);Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertEquals("Task in subprocess1",subProcessTask.getName());assertEquals("test2",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));assertEquals("test2",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));runtimeService.setVariableLocal(subProcessTask.getExecutionId(),"test","testX");assertEquals("testX",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));assertEquals("testX",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));assertEquals("test",runtimeService.getVariable(pi.getId(),"test"));assertEquals("test",runtimeService.getVariables(pi.getId()).get("test"));runtimeService.setVariable(pi.getId(),"test","testY");assertEquals("testX",runtimeService.getVariable(subProcessTask.getExecutionId(),"test"));assertEquals("testX",runtimeService.getVariables(subProcessTask.getExecutionId()).get("test"));assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));taskService.complete(subProcessTask.getId());List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();for (Task subProcTask:subProcessTasks){if (subProcTask.getName().equals("Task in subprocess2")){assertEquals("test3",runtimeService.getVariable(subProcTask.getExecutionId(),"test"));assertEquals("test3",runtimeService.getVariables(subProcTask.getExecutionId()).get("test"));assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));} else if (subProcTask.getName().equals("Task in subprocess3")){assertEquals("test4",runtimeService.getVariable(subProcTask.getExecutionId(),"test"));assertEquals("test4",runtimeService.getVariables(subProcTask.getExecutionId()).get("test"));assertEquals("testY",runtimeService.getVariable(pi.getId(),"test"));assertEquals("testY",runtimeService.getVariables(pi.getId()).get("test"));} else {fail("Unexpected subProcessTask: " + subProcTask);}}for (Task subProcTask:subProcessTasks){taskService.complete(subProcTask.getId());}}

  /**
   * A command to get the names of the variables
   * 


   */
  private class GetVariableNamesCommand implements Command<List<String>> {

    private String executionId;
    private boolean isLocal;

    public GetVariableNamesCommand(String executionId, boolean isLocal) {
      this.executionId = executionId;
      this.isLocal = isLocal;
    }

    public List<String> execute(CommandContext commandContext) {
      if (executionId == null) {
        throw new ActivitiIllegalArgumentException("executionId is null");
      }

      ExecutionEntity execution = commandContext.getExecutionEntityManager().findById(executionId);

      if (execution == null) {
        throw new ActivitiObjectNotFoundException("execution " + executionId + " doesn't exist", Execution.class);
      }

      List<String> executionVariables;
      if (isLocal) {
        executionVariables = new ArrayList<String>(execution.getVariableNamesLocal());
      } else {
        executionVariables = new ArrayList<String>(execution.getVariableNames());
      }

      return executionVariables;
    }

  }
}
