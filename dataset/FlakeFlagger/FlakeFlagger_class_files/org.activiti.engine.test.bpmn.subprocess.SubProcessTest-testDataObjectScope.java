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

package org.activiti.engine.test.bpmn.subprocess;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**


 */
public class SubProcessTest extends PluggableActivitiTestCase {

  /**
 * @see  <a href="https://activiti.atlassian.net/browse/ACT-1847">https://activiti.atlassian.net/browse/ACT-1847</a>
 */@Deployment public void testDataObjectScope(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("dataObjectScope");Task currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertEquals("Complete Task A",currentTask.getName());Map<String, Object> variables=runtimeService.getVariables(pi.getId());assertEquals(2,variables.size());Iterator<String> varNameIt=variables.keySet().iterator();while (varNameIt.hasNext()){String varName=varNameIt.next();if ("StringTest123".equals(varName)){assertEquals("Testing123",variables.get(varName));} else if ("NoData123".equals(varName)){assertNull(variables.get(varName));} else {fail("Variable not expected " + varName);}}taskService.complete(currentTask.getId());currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertEquals("Complete SubTask",currentTask.getName());variables=runtimeService.getVariables(currentTask.getExecutionId());assertEquals(3,variables.size());varNameIt=variables.keySet().iterator();while (varNameIt.hasNext()){String varName=varNameIt.next();if ("StringTest123".equals(varName)){assertEquals("Testing123",variables.get(varName));} else if ("StringTest456".equals(varName)){assertEquals("Testing456",variables.get(varName));} else if ("NoData123".equals(varName)){assertNull(variables.get(varName));} else {fail("Variable not expected " + varName);}}taskService.complete(currentTask.getId());variables=runtimeService.getVariables(pi.getId());assertEquals(2,variables.size());varNameIt=variables.keySet().iterator();while (varNameIt.hasNext()){String varName=varNameIt.next();if ("StringTest123".equals(varName)){assertEquals("Testing123",variables.get(varName));} else if ("NoData123".equals(varName)){assertNull(variables.get(varName));} else {fail("Variable not expected " + varName);}}currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();variables=runtimeService.getVariablesLocal(currentTask.getExecutionId());assertEquals(0,variables.size());currentTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();assertEquals("Complete Task B",currentTask.getName());taskService.complete(currentTask.getId());assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());}
}
