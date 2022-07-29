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

    @Deployment(resources={"org/activiti/standalone/jpa/JPAVariableTest.testQueryJPAVariable.bpmn20.xml"}) public void testReadJpaVariableValueFromHistoricVariableUpdate(){EntityManagerSessionFactory entityManagerSessionFactory=(EntityManagerSessionFactory)processEngineConfiguration.getSessionFactories().get(EntityManagerSession.class);EntityManagerFactory entityManagerFactory=entityManagerSessionFactory.getEntityManagerFactory();String executionId=runtimeService.startProcessInstanceByKey("JPAVariableProcess").getProcessInstanceId();String variableName="name";FieldAccessJPAEntity entity=new FieldAccessJPAEntity();entity.setId(1L);entity.setValue("Test");EntityManager manager=entityManagerFactory.createEntityManager();manager.getTransaction().begin();manager.persist(entity);manager.flush();manager.getTransaction().commit();manager.close();Task task=taskService.createTaskQuery().processInstanceId(executionId).taskName("my task").singleResult();runtimeService.setVariable(executionId,variableName,entity);taskService.complete(task.getId());List<HistoricDetail> variableUpdates=historyService.createHistoricDetailQuery().processInstanceId(executionId).variableUpdates().list();assertEquals(1,variableUpdates.size());HistoricVariableUpdate update=(HistoricVariableUpdate)variableUpdates.get(0);assertNotNull(update.getValue());assertTrue(update.getValue() instanceof FieldAccessJPAEntity);assertEquals(entity.getId(),((FieldAccessJPAEntity)update.getValue()).getId());}
}
