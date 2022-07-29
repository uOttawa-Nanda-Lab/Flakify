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

  @Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricTaskInstanceQueryByDeploymentIdIn(){org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();HashSet<String> processInstanceIds=new HashSet<String>();for (int i=0;i < 4;i++){processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());}processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());List<String> deploymentIds=new ArrayList<String>();deploymentIds.add(deployment.getId());HistoricTaskInstanceQuery taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().deploymentIdIn(deploymentIds);assertEquals(5,taskInstanceQuery.count());List<HistoricTaskInstance> taskInstances=taskInstanceQuery.list();assertNotNull(taskInstances);assertEquals(5,taskInstances.size());deploymentIds.add("invalid");taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().deploymentIdIn(deploymentIds);assertEquals(5,taskInstanceQuery.count());deploymentIds=new ArrayList<String>();deploymentIds.add("invalid");taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().deploymentIdIn(deploymentIds);assertEquals(0,taskInstanceQuery.count());}

}
