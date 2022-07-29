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

  @Test public void testQueryByProcessKeyNotIn(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){List<String> processDefinitionKeyNotIn1=new ArrayList<String>();processDefinitionKeyNotIn1.add("%\\%%");List<String> processDefinitionKeyNotIn2=new ArrayList<String>();processDefinitionKeyNotIn2.add("%\\_%");List<String> processDefinitionKeyNotIn3=new ArrayList<String>();processDefinitionKeyNotIn3.add("%");List<String> processDefinitionKeyNotIn4=new ArrayList<String>();processDefinitionKeyNotIn4.add("______________");HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn1);assertEquals(2,query.list().size());assertEquals(2,query.list().size());query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn2);assertEquals(2,query.list().size());assertEquals(2,query.list().size());query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn3);assertEquals(0,query.list().size());assertEquals(0,query.list().size());query=historyService.createHistoricProcessInstanceQuery().processDefinitionKeyNotIn(processDefinitionKeyNotIn4);assertEquals(0,query.list().size());assertEquals(0,query.list().size());query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn1).processDefinitionId("undefined");assertEquals(2,query.list().size());assertEquals(2,query.list().size());query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn2).processDefinitionId("undefined");assertEquals(2,query.list().size());assertEquals(2,query.list().size());query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn3).processDefinitionId("undefined");assertEquals(0,query.list().size());assertEquals(0,query.list().size());query=historyService.createHistoricProcessInstanceQuery().or().processDefinitionKeyNotIn(processDefinitionKeyNotIn4).processDefinitionId("undefined");assertEquals(0,query.list().size());assertEquals(0,query.list().size());}}
}