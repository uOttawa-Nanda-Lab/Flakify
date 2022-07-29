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

    // Test for https://activiti.atlassian.net/browse/ACT-2186
    @Deployment(resources = {"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"})
    public void testHistoricVariableRemovedWhenRuntimeVariableIsRemoved() throws InterruptedException {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("var1",
                 "Hello");
        vars.put("var2",
                 "World");
        vars.put("var3",
                 "!");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess",
                                                                                   vars);

        // Verify runtime
        assertEquals(3,
                     runtimeService.getVariables(processInstance.getId()).size());
        assertEquals(3,
                     runtimeService.getVariables(processInstance.getId(),
                                                 Arrays.asList("var1",
                                                               "var2",
                                                               "var3")).size());
        assertNotNull(runtimeService.getVariable(processInstance.getId(),
                                                 "var2"));

        // Verify history
        assertEquals(3,
                     historyService.createHistoricVariableInstanceQuery().list().size());
        assertNotNull(historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("var2").singleResult());

        // Verify historic details
        List<HistoricDetail> details = historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).variableUpdates().orderByTime().asc().list();
        assertEquals(3,
                     details.size()); // 3 vars
        for (HistoricDetail historicDetail : details) {
            assertNotNull(((HistoricVariableUpdate) historicDetail).getValue());
        }

        // Remove one variable
        Thread.sleep(800);
        runtimeService.removeVariable(processInstance.getId(),
                                      "var2");

        // Verify runtime
        assertEquals(2,
                     runtimeService.getVariables(processInstance.getId()).size());
        assertEquals(2,
                     runtimeService.getVariables(processInstance.getId(),
                                                 Arrays.asList("var1",
                                                               "var2",
                                                               "var3")).size());
        assertNull(runtimeService.getVariable(processInstance.getId(),
                                              "var2"));

        // Verify history
        assertEquals(2,
                     historyService.createHistoricVariableInstanceQuery().list().size());
        assertNull(historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("var2").singleResult());

        // Verify historic details
        details = historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).variableUpdates().orderByTime().asc().list();
        assertEquals(4,
                     details.size()); // 3 vars + 1 delete

        // The last entry should be the delete
        for (int i = 0; i < details.size(); i++) {
            if (i != 3) {
                assertNotNull(((HistoricVariableUpdate) details.get(i)).getValue());
            } else if (i == 3) {
                assertNull(((HistoricVariableUpdate) details.get(i)).getValue());
            }
        }
    }
}
