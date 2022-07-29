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

    @Deployment(resources = {"org/activiti/standalone/history/FullHistoryTest.testVariableUpdatesAreLinkedToActivity.bpmn20.xml"})
    public void testVariableUpdatesLinkedToActivity() throws Exception {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("ProcessWithSubProcess");

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("test",
                      "1");
        taskService.complete(task.getId(),
                             variables);

        // now we are in the subprocess
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        variables.clear();
        variables.put("test",
                      "2");
        taskService.complete(task.getId(),
                             variables);

        // now we are ended
        assertProcessEnded(pi.getId());

        // check history
        List<HistoricDetail> updates = historyService.createHistoricDetailQuery().variableUpdates().list();
        assertEquals(2,
                     updates.size());

        Map<String, HistoricVariableUpdate> updatesMap = new HashMap<String, HistoricVariableUpdate>();
        HistoricVariableUpdate update = (HistoricVariableUpdate) updates.get(0);
        updatesMap.put((String) update.getValue(),
                       update);
        update = (HistoricVariableUpdate) updates.get(1);
        updatesMap.put((String) update.getValue(),
                       update);

        HistoricVariableUpdate update1 = updatesMap.get("1");
        HistoricVariableUpdate update2 = updatesMap.get("2");

        assertNotNull(update1.getActivityInstanceId());
        assertNotNull(update1.getExecutionId());
        HistoricActivityInstance historicActivityInstance1 = historyService.createHistoricActivityInstanceQuery().activityInstanceId(update1.getActivityInstanceId()).singleResult();
        assertEquals("usertask1",
                     historicActivityInstance1.getActivityId());

        assertNotNull(update2.getActivityInstanceId());
        HistoricActivityInstance historicActivityInstance2 = historyService.createHistoricActivityInstanceQuery().activityInstanceId(update2.getActivityInstanceId()).singleResult();
        assertEquals("usertask2",
                     historicActivityInstance2.getActivityId());

    /*
     * This is OK! The variable is set on the root execution, on a execution never run through the activity, where the process instances stands when calling the set Variable. But the ActivityId of
     * this flow node is used. So the execution id's doesn't have to be equal.
     * 
     * execution id: On which execution it was set activity id: in which activity was the process instance when setting the variable
     */
        assertFalse(historicActivityInstance2.getExecutionId().equals(update2.getExecutionId()));
    }
}
