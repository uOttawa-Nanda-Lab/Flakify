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

package org.activiti.engine.test.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**

 */
public class UserTaskTest extends PluggableActivitiTestCase {

  public void testTaskFormKeyWhenUsingIncludeVariables(){deployOneTaskTestProcess();runtimeService.startProcessInstanceByKey("oneTaskProcess");Task task=taskService.createTaskQuery().singleResult();assertNotNull(task);Map<String, Object> vars=new HashMap<String, Object>();for (int i=0;i < 20;i++){vars.put("var" + i,i * 2);}taskService.setVariables(task.getId(),vars);task=taskService.createTaskQuery().singleResult();task.setFormKey("test123");taskService.saveTask(task);task=taskService.createTaskQuery().includeProcessVariables().singleResult();assertEquals(vars.size(),task.getProcessVariables().size());assertEquals("test123",task.getFormKey());}

}
