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

    @Deployment public void testHistoricTaskInstanceQueryTaskVariableValueEquals() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();Map<String, Object> variables=new HashMap<String, Object>();variables.put("longVar",12345L);variables.put("shortVar",(short)123);variables.put("integerVar",1234);variables.put("stringVar","stringValue");variables.put("booleanVar",true);Date date=Calendar.getInstance().getTime();variables.put("dateVar",date);variables.put("nullVar",null);taskService.setVariablesLocal(task.getId(),variables);assertEquals(7,historyService.createHistoricDetailQuery().variableUpdates().taskId(task.getId()).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("longVar",12345L).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("shortVar",(short)123).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("integerVar",1234).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("stringVar","stringValue").count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("booleanVar",true).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("dateVar",date).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("nullVar",null).count());variables.put("longVar",67890L);variables.put("shortVar",(short)456);variables.put("integerVar",5678);variables.put("stringVar","updatedStringValue");variables.put("booleanVar",false);Calendar otherCal=Calendar.getInstance();otherCal.add(Calendar.DAY_OF_MONTH,1);Date otherDate=otherCal.getTime();variables.put("dateVar",otherDate);variables.put("nullVar",null);taskService.setVariablesLocal(task.getId(),variables);assertEquals(14,historyService.createHistoricDetailQuery().variableUpdates().taskId(task.getId()).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("longVar",12345L).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("shortVar",(short)123).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("integerVar",1234).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("stringVar","stringValue").count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("booleanVar",true).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("dateVar",date).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("longVar",67890L).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("shortVar",(short)456).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("integerVar",5678).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("stringVar","updatedStringValue").count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("booleanVar",false).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("dateVar",otherDate).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskVariableValueEquals("nullVar",null).count());}
}
