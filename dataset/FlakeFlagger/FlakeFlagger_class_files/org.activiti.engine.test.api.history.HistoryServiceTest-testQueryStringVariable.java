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

package org.activiti.engine.test.api.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.api.runtime.ProcessInstanceQueryTest;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**


 */
public class HistoryServiceTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryStringVariable(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("stringVar","abcdef");ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult().getId());vars=new HashMap<String, Object>();vars.put("stringVar","abcdef");vars.put("stringVar2","ghijkl");ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance2.getId()).singleResult().getId());vars=new HashMap<String, Object>();vars.put("stringVar","azerty");ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance3.getId()).singleResult().getId());HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().variableValueEquals("stringVar","abcdef");List<HistoricProcessInstance> processInstances=query.list();assertNotNull(processInstances);assertEquals(2,processInstances.size());query=historyService.createHistoricProcessInstanceQuery().variableValueEquals("stringVar","abcdef").variableValueEquals("stringVar2","ghijkl");HistoricProcessInstance resultInstance=query.singleResult();assertNotNull(resultInstance);assertEquals(processInstance2.getId(),resultInstance.getId());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueNotEquals("stringVar","abcdef").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueGreaterThan("stringVar","abcdef").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueGreaterThan("stringVar","z").singleResult();assertNull(resultInstance);assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueGreaterThanOrEqual("stringVar","abcdef").count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueGreaterThanOrEqual("stringVar","z").count());processInstances=historyService.createHistoricProcessInstanceQuery().variableValueLessThan("stringVar","abcdeg").list();assertEquals(2,processInstances.size());List<String> expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());List<String> ids=new ArrayList<String>(Arrays.asList(processInstances.get(0).getId(),processInstances.get(1).getId()));ids.removeAll(expectedIds);assertTrue(ids.isEmpty());assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueLessThan("stringVar","abcdef").count());assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("stringVar","z").count());processInstances=historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("stringVar","abcdef").list();assertEquals(2,processInstances.size());expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());ids=new ArrayList<String>(Arrays.asList(processInstances.get(0).getId(),processInstances.get(1).getId()));ids.removeAll(expectedIds);assertTrue(ids.isEmpty());assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("stringVar","z").count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueLessThanOrEqual("stringVar","aa").count());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueLike("stringVar","azert%").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueLike("stringVar","%y").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueLike("stringVar","%zer%").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());assertEquals(3,historyService.createHistoricProcessInstanceQuery().variableValueLike("stringVar","a%").count());assertEquals(0,historyService.createHistoricProcessInstanceQuery().variableValueLike("stringVar","%x%").count());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueEquals("azerty").singleResult();assertNotNull(resultInstance);assertEquals(processInstance3.getId(),resultInstance.getId());processInstances=historyService.createHistoricProcessInstanceQuery().variableValueEquals("abcdef").list();assertEquals(2,processInstances.size());expectedIds=Arrays.asList(processInstance1.getId(),processInstance2.getId());ids=new ArrayList<String>(Arrays.asList(processInstances.get(0).getId(),processInstances.get(1).getId()));ids.removeAll(expectedIds);assertTrue(ids.isEmpty());resultInstance=historyService.createHistoricProcessInstanceQuery().variableValueEquals("notmatchinganyvalues").singleResult();assertNull(resultInstance);historyService.deleteHistoricProcessInstance(processInstance1.getId());historyService.deleteHistoricProcessInstance(processInstance2.getId());historyService.deleteHistoricProcessInstance(processInstance3.getId());}

}
