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

package org.activiti.engine.test.api.runtime;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import static org.assertj.core.api.Assertions.*;

public class RuntimeServiceTest extends PluggableActivitiTestCase {

    /**
	 * Testcase to reproduce ACT-950 (https://jira.codehaus.org/browse/ACT-950)
	 */@Deployment public void testFindActiveActivityIdProcessWithErrorEventAndSubProcess(){ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceByKey("errorEventSubprocess");List<String> activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());assertEquals(5,activeActivities.size());List<Task> tasks=taskService.createTaskQuery().list();assertEquals(2,tasks.size());Task parallelUserTask=null;for (Task task:tasks){if (!task.getName().equals("ParallelUserTask") && !task.getName().equals("MainUserTask")){fail("Expected: <ParallelUserTask> or <MainUserTask> but was <" + task.getName() + ">.");}if (task.getName().equals("ParallelUserTask")){parallelUserTask=task;}}assertNotNull(parallelUserTask);taskService.complete(parallelUserTask.getId());Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("subprocess1WaitBeforeError").singleResult();runtimeService.trigger(execution.getId());activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());assertEquals(4,activeActivities.size());tasks=taskService.createTaskQuery().list();assertEquals(2,tasks.size());Task beforeErrorUserTask=null;for (Task task:tasks){if (!task.getName().equals("BeforeError") && !task.getName().equals("MainUserTask")){fail("Expected: <BeforeError> or <MainUserTask> but was <" + task.getName() + ">.");}if (task.getName().equals("BeforeError")){beforeErrorUserTask=task;}}assertNotNull(beforeErrorUserTask);taskService.complete(beforeErrorUserTask.getId());activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());assertEquals(2,activeActivities.size());tasks=taskService.createTaskQuery().list();assertEquals(2,tasks.size());Task afterErrorUserTask=null;for (Task task:tasks){if (!task.getName().equals("AfterError") && !task.getName().equals("MainUserTask")){fail("Expected: <AfterError> or <MainUserTask> but was <" + task.getName() + ">.");}if (task.getName().equals("AfterError")){afterErrorUserTask=task;}}assertNotNull(afterErrorUserTask);taskService.complete(afterErrorUserTask.getId());tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());assertEquals("MainUserTask",tasks.get(0).getName());activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());assertEquals(1,activeActivities.size());assertEquals("MainUserTask",activeActivities.get(0));taskService.complete(tasks.get(0).getId());assertProcessEnded(processInstance.getId());}

    private void checkHistoricVariableUpdateEntity(String variableName,
                                                   String processInstanceId) {
        if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
            boolean deletedVariableUpdateFound = false;

            List<HistoricDetail> resultSet = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
            for (HistoricDetail currentHistoricDetail : resultSet) {
                assertTrue(currentHistoricDetail instanceof HistoricDetailVariableInstanceUpdateEntity);
                HistoricDetailVariableInstanceUpdateEntity historicVariableUpdate = (HistoricDetailVariableInstanceUpdateEntity) currentHistoricDetail;

                if (historicVariableUpdate.getName().equals(variableName)) {
                    if (historicVariableUpdate.getValue() == null) {
                        if (deletedVariableUpdateFound) {
                            fail("Mismatch: A HistoricVariableUpdateEntity with a null value already found");
                        } else {
                            deletedVariableUpdateFound = true;
                        }
                    }
                }
            }

            assertTrue(deletedVariableUpdateFound);
        }
    }

    private void startSignalCatchProcesses() {
        for (int i = 0; i < 3; i++) {
            runtimeService.startProcessInstanceByKey("catchAlertSignal");
            runtimeService.startProcessInstanceByKey("catchPanicSignal");
        }
    }

    private void startMessageCatchProcesses() {
        for (int i = 0; i < 3; i++) {
            runtimeService.startProcessInstanceByKey("catchAlertMessage");
            runtimeService.startProcessInstanceByKey("catchPanicMessage");
        }
    }
}
