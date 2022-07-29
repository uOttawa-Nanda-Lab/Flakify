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

import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cmd.SetProcessDefinitionVersionCmd;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;


public class ProcessInstanceMigrationTest extends PluggableActivitiTestCase {

  private static final String TEST_PROCESS_WITH_PARALLEL_GATEWAY = "org/activiti/examples/bpmn/gateway/ParallelGatewayTest.testForkJoin.bpmn20.xml";
  private static final String TEST_PROCESS = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.testSetProcessDefinitionVersion.bpmn20.xml";
  private static final String TEST_PROCESS_ACTIVITY_MISSING = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.testSetProcessDefinitionVersionActivityMissing.bpmn20.xml";

  private static final String TEST_PROCESS_CALL_ACTIVITY = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.withCallActivity.bpmn20.xml";
  private static final String TEST_PROCESS_USER_TASK_V1 = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.testSetProcessDefinitionVersionWithTask.bpmn20.xml";
  private static final String TEST_PROCESS_USER_TASK_V2 = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.testSetProcessDefinitionVersionWithTaskV2.bpmn20.xml";
  private static final String TEST_PROCESS_NESTED_SUB_EXECUTIONS = "org/activiti/engine/test/db/ProcessInstanceMigrationTest.testSetProcessDefinitionVersionSubExecutionsNested.bpmn20.xml";

  @Deployment(resources={TEST_PROCESS_WITH_PARALLEL_GATEWAY}) public void testSetProcessDefinitionVersionPIIsSubExecution(){ProcessInstance pi=runtimeService.startProcessInstanceByKey("forkJoin");Execution execution=runtimeService.createExecutionQuery().activityId("receivePayment").singleResult();CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();SetProcessDefinitionVersionCmd command=new SetProcessDefinitionVersionCmd(execution.getId(),1);try {commandExecutor.execute(command);fail("ActivitiException expected");} catch (ActivitiException ae){assertTextPresent("A process instance id is required, but the provided id '" + execution.getId() + "' points to a child execution of process instance '" + pi.getId() + "'. Please invoke the " + command.getClass().getSimpleName() + " with a root execution id.",ae.getMessage());}}

}
