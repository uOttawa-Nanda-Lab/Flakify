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

package org.activiti.engine.test.bpmn.callactivity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;

/**




 */
public class CallActivityAdvancedTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testSubProcessDataInputOutput.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testSubProcessWithDataInputOutput(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("superVariable","Hello from the super process.");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("subProcessDataInputOutput",vars);TaskQuery taskQuery=taskService.createTaskQuery();Task taskBeforeSubProcess=taskQuery.singleResult();assertEquals("Task in subprocess",taskBeforeSubProcess.getName());assertEquals("Hello from the super process.",runtimeService.getVariable(taskBeforeSubProcess.getProcessInstanceId(),"subVariable"));assertEquals("Hello from the super process.",taskService.getVariable(taskBeforeSubProcess.getId(),"subVariable"));runtimeService.setVariable(taskBeforeSubProcess.getProcessInstanceId(),"subVariable","Hello from sub process.");assertEquals("Hello from the super process.",runtimeService.getVariable(processInstance.getId(),"superVariable"));taskService.complete(taskBeforeSubProcess.getId());Task taskAfterSubProcess=taskQuery.singleResult();assertEquals("Task in super process",taskAfterSubProcess.getName());assertEquals("Hello from sub process.",runtimeService.getVariable(processInstance.getId(),"superVariable"));assertEquals("Hello from sub process.",taskService.getVariable(taskAfterSubProcess.getId(),"superVariable"));vars.clear();vars.put("x",5l);taskService.complete(taskAfterSubProcess.getId(),vars);Task taskInSecondSubProcess=taskQuery.singleResult();assertEquals("Task in subprocess",taskInSecondSubProcess.getName());assertEquals(10l,runtimeService.getVariable(taskInSecondSubProcess.getProcessInstanceId(),"y"));assertEquals(10l,taskService.getVariable(taskInSecondSubProcess.getId(),"y"));taskService.complete(taskInSecondSubProcess.getId());Task taskAfterSecondSubProcess=taskQuery.singleResult();assertEquals("Task in super process",taskAfterSecondSubProcess.getName());assertEquals(15l,runtimeService.getVariable(taskAfterSecondSubProcess.getProcessInstanceId(),"z"));assertEquals(15l,taskService.getVariable(taskAfterSecondSubProcess.getId(),"z"));taskService.complete(taskAfterSecondSubProcess.getId());assertProcessEnded(processInstance.getId());assertEquals(0,runtimeService.createExecutionQuery().list().size());}

}
