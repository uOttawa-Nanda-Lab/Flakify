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
package org.activiti.standalone.escapeclause;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HistoricVariableInstanceEscapeClauseTest extends AbstractEscapeClauseTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  private ProcessInstance processInstance1;

  private ProcessInstance processInstance2;

  @Test public void testQueryLikeByQueryVariableValueIgnoreCase(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){HistoricVariableInstance historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLikeIgnoreCase("var%","%\\%%").singleResult();assertNotNull(historicVariable);assertEquals(processInstance1.getId(),historicVariable.getProcessInstanceId());historicVariable=historyService.createHistoricVariableInstanceQuery().variableValueLikeIgnoreCase("var_","%\\_%").singleResult();assertNotNull(historicVariable);assertEquals(processInstance2.getId(),historicVariable.getProcessInstanceId());}}
}
