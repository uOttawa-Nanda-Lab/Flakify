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

    @Deployment public void testDeleteHistoricProcessInstance(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("processVar",123L);vars.put("anotherProcessVar",new DummySerializable());ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest",vars);assertNotNull(processInstance);Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();taskService.setVariableLocal(task.getId(),"taskVar",45678);taskService.setVariableLocal(task.getId(),"anotherTaskVar","value");taskService.complete(task.getId());assertEquals(1,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(4,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(4,historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).count());assertEquals(1,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).count());historyService.deleteHistoricProcessInstance(processInstance.getId());assertEquals(0,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(0,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());assertEquals(0,historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).count());assertEquals(0,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).count());try {historyService.deleteHistoricProcessInstance("unexisting");fail("Exception expected when deleting process-instance that is still running");} catch (ActivitiException ae){assertTextPresent("No historic process instance found with id: unexisting",ae.getMessage());}}
}
