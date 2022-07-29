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

    @Deployment(resources = "org/activiti/standalone/history/FullHistoryTest.testVariableUpdates.bpmn20.xml")
    public void testHistoricVariableInstanceQuery() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("process",
                      "one");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("receiveTask",
                                                                                   variables);
        runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());

        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableName("process").count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableValueEquals("process",
                                                                                              "one").count());

        Map<String, Object> variables2 = new HashMap<String, Object>();
        variables2.put("process",
                       "two");
        ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("receiveTask",
                                                                                    variables2);
        runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());

        assertEquals(2,
                     historyService.createHistoricVariableInstanceQuery().variableName("process").count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableValueEquals("process",
                                                                                              "one").count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableValueEquals("process",
                                                                                              "two").count());

        HistoricVariableInstance historicProcessVariable = historyService.createHistoricVariableInstanceQuery().variableValueEquals("process",
                                                                                                                                    "one").singleResult();
        assertEquals("process",
                     historicProcessVariable.getVariableName());
        assertEquals("one",
                     historicProcessVariable.getValue());

        Map<String, Object> variables3 = new HashMap<String, Object>();
        variables3.put("long",
                       1000l);
        variables3.put("double",
                       25.43d);
        ProcessInstance processInstance3 = runtimeService.startProcessInstanceByKey("receiveTask",
                                                                                    variables3);
        runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());

        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableName("long").count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableValueEquals("long",
                                                                                              1000l).count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableName("double").count());
        assertEquals(1,
                     historyService.createHistoricVariableInstanceQuery().variableValueEquals("double",
                                                                                              25.43d).count());
    }
}
