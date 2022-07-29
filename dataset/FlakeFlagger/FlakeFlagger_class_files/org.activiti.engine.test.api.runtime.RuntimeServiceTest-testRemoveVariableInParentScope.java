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

    @Deployment(resources={"org/activiti/engine/test/api/oneSubProcess.bpmn20.xml"}) public void testRemoveVariableInParentScope(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("variable1","value1");vars.put("variable2","value2");ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startSimpleSubProcess",vars);Task currentTask=taskService.createTaskQuery().singleResult();runtimeService.removeVariable(currentTask.getExecutionId(),"variable1");assertNull(runtimeService.getVariable(processInstance.getId(),"variable1"));assertEquals("value2",runtimeService.getVariable(processInstance.getId(),"variable2"));checkHistoricVariableUpdateEntity("variable1",processInstance.getId());}

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
