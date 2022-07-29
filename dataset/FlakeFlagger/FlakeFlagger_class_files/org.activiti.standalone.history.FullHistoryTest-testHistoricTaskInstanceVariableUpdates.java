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

package org.activiti.standalone.history;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.impl.variable.EntityManagerSession;
import org.activiti.engine.impl.variable.EntityManagerSessionFactory;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.test.api.runtime.DummySerializable;
import org.activiti.engine.test.history.SerializableVariable;
import org.activiti.standalone.jpa.FieldAccessJPAEntity;

public class FullHistoryTest extends ResourceActivitiTestCase {

    @Deployment public void testHistoricTaskInstanceVariableUpdates(){String processInstanceId=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest").getId();String taskId=taskService.createTaskQuery().singleResult().getId();runtimeService.setVariable(processInstanceId,"deadline","yesterday");taskService.setVariableLocal(taskId,"bucket","23c");taskService.setVariableLocal(taskId,"mop","37i");taskService.complete(taskId);assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());List<HistoricDetail> historicTaskVariableUpdates=historyService.createHistoricDetailQuery().taskId(taskId).variableUpdates().orderByVariableName().asc().list();assertEquals(2,historicTaskVariableUpdates.size());historyService.deleteHistoricTaskInstance(taskId);historicTaskVariableUpdates=historyService.createHistoricDetailQuery().taskId(taskId).variableUpdates().orderByVariableName().asc().list();assertEquals(0,historicTaskVariableUpdates.size());}
}
