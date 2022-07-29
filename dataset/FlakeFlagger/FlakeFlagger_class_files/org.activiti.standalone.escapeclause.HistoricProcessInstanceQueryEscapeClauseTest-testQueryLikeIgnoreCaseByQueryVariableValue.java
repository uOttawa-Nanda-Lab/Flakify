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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HistoricProcessInstanceQueryEscapeClauseTest extends AbstractEscapeClauseTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  private ProcessInstance processInstance1;

  private ProcessInstance processInstance2;

  @Test
  public void testQueryLikeIgnoreCaseByQueryVariableValue() {
    if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
        // queryVariableValueIgnoreCase
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().variableValueLikeIgnoreCase("var1", "%\\%%").singleResult();
        assertNotNull(historicProcessInstance);
        assertEquals(processInstance1.getId(), historicProcessInstance.getId());
        
        historicProcessInstance = historyService.createHistoricProcessInstanceQuery().variableValueLikeIgnoreCase("var1", "%\\_%").singleResult();
        assertNotNull(historicProcessInstance);
        assertEquals(processInstance2.getId(), historicProcessInstance.getId());
        
        // orQuery
        historicProcessInstance = historyService.createHistoricProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1", "%\\%%").processDefinitionId("undefined").singleResult();
        assertNotNull(historicProcessInstance);
        assertEquals(processInstance1.getId(), historicProcessInstance.getId());
        
        historicProcessInstance = historyService.createHistoricProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1", "%\\_%").processDefinitionId("undefined").singleResult();
        assertNotNull(historicProcessInstance);
        assertEquals(processInstance2.getId(), historicProcessInstance.getId());
    }
  }
}