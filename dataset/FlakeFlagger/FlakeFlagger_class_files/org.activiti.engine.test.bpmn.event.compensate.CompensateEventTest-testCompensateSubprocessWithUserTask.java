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

package org.activiti.engine.test.bpmn.event.compensate;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.EnableVerboseExecutionTreeLogging;
import org.activiti.engine.test.bpmn.event.compensate.helper.SetVariablesDelegate;

/**

 */
@EnableVerboseExecutionTreeLogging
public class CompensateEventTest extends PluggableActivitiTestCase {

  @Deployment public void testCompensateSubprocessWithUserTask(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertEquals("Manually undo book hotel",task.getName());taskService.complete(task.getId());Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();runtimeService.trigger(execution.getId());assertProcessEnded(processInstance.getId());}
  
}
